package sorting;


import java.util.Collections;
import java.util.List;
import wrapper.ListWrap;

public class InsertionSort {

  public List<Integer> sort(List<Integer> list) {
    ListWrap<Integer> wrapper = new ListWrap<>(list);
    List<Integer> newList = wrapper.cloneList();
    Collections.sort(newList);
    return newList;
  }

  public SortedTypes type() {
    return SortedTypes.INSERTION;
  }

}