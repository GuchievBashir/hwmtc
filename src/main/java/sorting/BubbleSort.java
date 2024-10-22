package sorting;

import java.util.ArrayList;
import java.util.List;
import wrapper.ListWrap;

public class BubbleSort {

  public List<Integer> sort(List<Integer> list) {
    ListWrap<Integer> wrapper = new ListWrap<>(list);
    List<Integer> newList = wrapper.cloneList();
    for (int i = 0; i < newList.size(); i++) {
      for (int j = i + 1; j < newList.size(); j++) {
        if (newList.get(i) > newList.get(j)) {
          int temp = newList.get(i);
          newList.set(i, newList.get(j));
          newList.set(j, temp);
        }
      }
    }
    return newList;
  }

  public SortedTypes type() {
    return SortedTypes.BUBBLE;
  }

}
