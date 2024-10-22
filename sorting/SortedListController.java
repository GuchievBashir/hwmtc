package org.sortings;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SortedListController {

  private final List<SortingInterface> sorts = new ArrayList<>();

  public void addSortingStrategy(SortingInterface sort) {
    this.sorts.add(sort);
  }

  public List<Integer> sort(List<Integer> list, SortedTypes type) {
    RuntimeException SortingTypeException = null;
    for (SortingInterface strategy : this.sorts) {
      if (type != SortedTypes.ANY && strategy.type() != type) {
        continue;
      }
      try {
        return strategy.sort(List.copyOf(list));
      } catch (RuntimeException exception) {
        SortingTypeException = exception;
      }
    }
    if (SortedTypesException != null) {
      throw SortedTypesException;
    }
    throw new NoSuchElementException("Такой вид сортировки не поддерживается");
  }
}