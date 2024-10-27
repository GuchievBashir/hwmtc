
import org.example.Message;
import org.example.enrichments.*;
import org.example.users.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EnrichmentServiceTest {

  @Test
  public void addNewEnrichment() {
    MapUsers repository = new MapUsers();
    User user = new User("Ваня", "Облепихович");
    String msisdn = "8800535535";
    repository.updateUserByMsisdn(msisdn, user);

    MSISDN msisdnEnrichment = new MSISDN(repository);
    EnrichmentService service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, msisdnEnrichment);
    ArrayList<EnrichmentType> currentResult = service.printEnrichmentType();
    ArrayList<EnrichmentType> expectedResult =
            new ArrayList<>() {
              {
                add(EnrichmentType.MSISDN);
              }
            };
    assertEquals(currentResult, expectedResult);
  }

  @Test
  public void enrichWithoutUser() {
    MapUsers repository = new MapUsers();
    MSISDN msisdnEnrichment = new MSISDN(repository);
    EnrichmentService service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, msisdnEnrichment);

    Map<String, String> content =
            new HashMap<>(
                    Map.of(
                            "action", "button_click",
                            "page", "book_card",
                            "time", "10",
                            "msisdn", "8800535535"));
    Message message = new Message(content, EnrichmentType.MSISDN);
    Map<String, String> currentRes = service.enrich(message).getContent();
    Map<String, String> expectedResult = content;
    assertEquals(currentRes, expectedResult);
  }
}