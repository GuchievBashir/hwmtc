
import org.example.users.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUsersTest {

  @Test
  public void findByMsisdn() {
    MapUsers repository = new MapUsers();
    User user = new User("Жалагдан", "Тылыгда");
    String msisdn = "89151003040";
    repository.updateUserByMsisdn(msisdn, user);
    User currentResult = repository.findByMsisdn(msisdn);
    User extendedResult = user;
    assertEquals(currentResult, extendedResult);
  }

  @Test
  public void updateUserByMsisdn() {
    MapUsers repository = new MapUsers();
    User userFirst = new User("Фаина", "Приходько");
    User userSecond = new User("Эльдар", "Дарханович");
    String msisdnFirst = "89151429440";
    String msisdnSecond = "89121013040";
    repository.updateUserByMsisdn(msisdnFirst, userFirst);
    repository.updateUserByMsisdn(msisdnSecond, userSecond);
    ConcurrentHashMap<String, User> currentResult = repository.getUsers();
    ConcurrentHashMap<String, User> extendedResult =
            new ConcurrentHashMap<>() {
              {
                put(msisdnFirst, userFirst);
                put(msisdnSecond, userSecond);
              }
            };
    assertEquals(currentResult, extendedResult);
  }
}
