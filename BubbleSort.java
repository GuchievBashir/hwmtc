package org.sortings;

import java.util.List;

public class BubbleSort {

  public List<Integer> sort(List<Integer> list) {
    ListWrap<Integer> wrapper = new ListWrap<>(list);
    List<Integer> newList = wrapper.cloneList();
    for (int i = 0; i < listCopy.size(); i++) {
      for (int j = i + 1; j < listCopy.size(); j++) {
        if (newList.get(i) > newList.get(i)) {
          int temp = newList.get(i);
          newList.set(i, newList.get(j));
          newList.set(j, temp);
        }
      }
    }
    return newList;
  }

  public SortedTypes type() {
    return SortedTypes.Bubblesort;
  }
}
