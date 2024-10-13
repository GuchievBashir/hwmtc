package sortings;


import java.util.Collections;
import java.util.List;

public class InsertionSort {
  @Override
  public List<Integer> sort(List<Integer> list) {
    ListWrap<Integer> wrapper = new ListWrap<>(list);
    List<Integer> newList = wrapper.cloneList();
    Collections.sort(newList);
    return newList;
  }

  @Override
  public SortedTypes type() {
    return SortedTypes.Insertionsort;
  }

}