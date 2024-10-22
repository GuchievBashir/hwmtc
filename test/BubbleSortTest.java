package test.sortings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
  @Test
  void sort() {
    List<Integer> listToSort = Arrays.asList(9,8,7,6,5,4,3,2,1);
    List<Integer> answerlist = Arrays.asList(1,2,3,4,5,6,7,8,9);
    BubbleSort sorter = new BubbleSort();
    List<Integer> sortedList = sorter.sort(listToSort);
    assertEquals(sortedList, answerlist);
  }

  @Test
  void sortingtype() {
    SortedTypes answer = new BubbleSort().type();
    assertEquals(answer, SortedTypes.BUBBLE);
  }
}
