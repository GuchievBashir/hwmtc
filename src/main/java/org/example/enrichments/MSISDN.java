package org.example.enrichments;

import org.example.users.User;
import org.example.users.UserRepository;

import java.util.Map;

public class MSISDN implements EnrichmentInterface {
  private final String enrichmentType = EnrichmentType.MSISDN.toString().toLowerCase();
  private final UserRepository repository;

  public MSISDN(UserRepository repository) {
    this.repository = repository;
  }

  @Override
  public Map<String, String> enrich(Map<String, String> input) {
     String msisdn = input.get(this.enrichmentType);
     if (msisdn == null) {
       return input;
     }
     User msisdnUser = repository.findByMsisdn(msisdn);
     if (msisdnUser == null) {
       return input;
     }
     input.put("firstName", msisdnUser.FirstName());
     input.put("lastName", msisdnUser.LastName());
     return input;
  }
}
