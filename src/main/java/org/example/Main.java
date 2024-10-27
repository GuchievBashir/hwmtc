package org.example;

import org.example.users.MapUsers;

import org.example.users.*;
import org.example.enrichments.*;

import java.util.*;

public class Main {
  public static void main(String[] args) {
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
    System.out.println(service.enrich(message).getContent());
    System.out.println(message.getContent());
  }
}
