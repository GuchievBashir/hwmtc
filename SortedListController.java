package sortings;

import java.util.List;

public class SortedListController {
  List<Sorter> listsort;

  public SortedListController(List<Sorter> listOfSorters) {
    this.listsort = listsort;
  }

  public List<Integer> sort(List<Integer> list, SorterType type) throws Exception {
    boolean fountSorter = false;
    for (var sorter: listOfSorters) {
      if (sorter.type().equals(type)) {
        fountSorter = true;
        return sorter.sort(list);
      }
    }
    if (!fountSorter) {
      throw new Exception("Такая сортировка не поддерживается");
    }
    return list;
  }
}
