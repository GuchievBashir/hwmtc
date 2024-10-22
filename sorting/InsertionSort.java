package org.sortings;


import java.util.Collections;
import java.util.List;

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