import org.example.enrichments.*;
import org.example.users.*;
import org.junit.jupiter.api.*;

import org.example.Message;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;


public class MsisdnTest {

  @Test
  void testWithEnrichMessage() {
    MapUsers repository = new MapUsers();
    User user = new User("Vasya", "Ivanov");
    repository.updateUserByMsisdn("8800535535", user);

    MSISDN msisdnEnrichment = new MSISDN(repository);
    EnrichmentService service = new EnrichmentService();
    service.addEnrichment(EnrichmentType.MSISDN, msisdnEnrichment);

    Map<String, String> content =
            new HashMap<>(
                    Map.of(
                            "action", "button_click",
                            "page", "book_card",
                            "msisdn", "8800535535"));
    Message message = new Message(content, EnrichmentType.MSISDN);
    Map<String, String> result = new HashMap<>(
            Map.of(
                    "action", "button_click",
                    "page", "book_card",
                    "msisdn", "8800535535",
                    "firstName", "Vasya",
                    "lastName","Ivanov"  ));
    assertEquals(service.enrich(message).getContent(), result);
  }
}
