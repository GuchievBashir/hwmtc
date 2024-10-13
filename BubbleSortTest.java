package sortings;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
  @Test
  void sort() throws Exception {
    List<Integer> listToSort = Arrays.asList(9,8,7,6,5,4,3,2,1);
    List<Integer> answerlist = Arrays.asList(1,2,3,4,5,6,7,8,9);
    BubbleSort sorter = new BubbleSort(32);
    List<Integer> sortedList = sorter.sort(listToSort);
    assertEquals(sortedList, answerlist);
  }

  @Test
  void sortingtype() {
    SorterType answer = new BubbleSort(100).type();
    assertEquals(answer, SortedTypes.BubbleSort);
  }
}
