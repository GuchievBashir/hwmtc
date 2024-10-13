package sortings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {
  Random rnd = new Random();

  @Test
  void wrap() {
    InsertionSort sorter = new InsertionSort(17);
    List<Integer> list = Arrays.asList(3, 9, 1, 7, -2);
    sorter.wrap(list);
    assertEquals(list, sorter.wrapper.cloneList());
  }

  @Test
  void setElementsCountLimit() {
    InsertionSort sorter = new InsertionSort(17);
    int n = rnd.nextInt(1024);
    sorter.setElementsCountLimit(n);
    assertEquals(n, sorter.elementsCountLimit);
  }

  @Test
  void getElementsCountLimit() {
    int n = rnd.nextInt(1024);
    InsertionSort sorter = new InsertionSort(n);
    assertEquals(n, sorter.getElementsCountLimit());
  }


  @Test
  void sortingtype() {
    SorterType answer = new InsertionSort(100).type();
    assertEquals(answer, SortedTypes.InsertionSort);
  }
}