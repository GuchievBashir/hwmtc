package sortings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

  @Test
  public void sort() {
    final InsertionSort strategy = new InsertonSort();
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
    assertEquals(answer, SortedTypes.InsertionSort);
  }
}