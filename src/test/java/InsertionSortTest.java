import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import sorting.*;

class InsertionSortTest {

  @Test
  public void sort() {
    final InsertionSort strategy = new InsertionSort();
    final List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(-2);
    list.add(3);
    list.add(-4);
    list.add(5);
    assertEquals(List.of(-4, -2, 1, 3, 5), strategy.sort(list));
  }

  @Test
  void sortingtype() {
    SortedTypes answer = new InsertionSort().type();
    assertEquals(answer, SortedTypes.INSERTION);
  }
}