
import org.example.enrichments.*;
import org.example.users.*;
import org.junit.jupiter.api.*;

import org.example.Message;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
  private String createMsisdn() {
    long randomLongValue = (long) ((Math.random() + 1) * 1000000000L);
    return String.valueOf(randomLongValue);
  }

  private Message getMessageMsisdn(String msisdn) {
    Map<String, String> content = new HashMap<>(Map.of("msisdn", msisdn));
    return new Message(content, EnrichmentType.MSISDN);
  }

  @Test
  public void shouldSucceedEnrichmentInConcurrentEnvironmentSuccessfully()
          throws InterruptedException {
    EnrichmentService service = new EnrichmentService();
    int threadCount = 5;

    MapUsers repository = new MapUsers();
    List<String> msisdnElements = new CopyOnWriteArrayList<>();
    User user = new User("Иван", "Иван");

    for (int i = 0; i < threadCount; i++) {
      String msisdn = createMsisdn();
      msisdnElements.add(msisdn);
      repository.updateUserByMsisdn(msisdn, user);
    }
    MSISDN msisdnEnrichment = new MSISDN(repository);
    service.addEnrichment(EnrichmentType.MSISDN, msisdnEnrichment);

    List<Message> enrichmentResults = new CopyOnWriteArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      int finalI = i;
      executorService.submit(
              () -> {
                enrichmentResults.add(service.enrich(getMessageMsisdn(msisdnElements.get(finalI))));
                latch.countDown();
              });
    }
    latch.await();

    List<Message> messageArrayList = new CopyOnWriteArrayList<>();

    String firstNameToString = "firstName";
    String lastNameToString = "lastName";
    String msisdnToString = "msisdn";

    for (int i = 0; i < threadCount; i++) {
      String msisdn = msisdnElements.get(i);
      Map<String, String> content =
              new ConcurrentHashMap<>() {
                {
                  put(msisdnToString, msisdn);
                  put(firstNameToString, user.FirstName());
                  put(lastNameToString, user.LastName());
                }
              };
      Message message = new Message(content, EnrichmentType.MSISDN);
      messageArrayList.add(message);
    }

    List<Map<String, String>> currentResult = new CopyOnWriteArrayList<>();
    List<Map<String, String>> extendedResult = new CopyOnWriteArrayList<>();

    for (Message message : enrichmentResults) {
      currentResult.add(message.getContent());
    }
    for (Message message : messageArrayList) {
      extendedResult.add(message.getContent());
    }

    assertTrue(
            extendedResult.size() == currentResult.size()
                    && extendedResult.containsAll(currentResult)
                    && currentResult.containsAll(extendedResult));
  }
}
