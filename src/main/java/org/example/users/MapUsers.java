package org.example.users;

import java.util.concurrent.*;

public class MapUsers implements UserRepository {
  private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

  public User findByMsisdn(String msisdn) {
    return this.users.get(msisdn);
  }

  public void updateUserByMsisdn(String msisdn, User user){
    this.users.put(msisdn, user);
  }

  public ConcurrentHashMap<String, User> getUsers() {
    return this.users;
  }
}
