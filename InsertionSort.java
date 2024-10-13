package sortings;


import java.util.Collections;
import java.util.List;

public class InsertionSort {
  @Override
  public List<Integer> sort(List<Integer> list) {
    ListWrap<Integer> wrapper = new ListWrap<>(list);
    Collections.sort(wrapper);
    return wrapper.cloneList();
  }

  @Override
  public SortedTypes type() {
    return SortedTypes.Insertionsort;
  }

}