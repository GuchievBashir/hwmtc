package org.sortings;

import java.util.List;

public interface SortingStrategy {
  List<Integer> sort(List<Integer> array);

  SortedTypes type();
}