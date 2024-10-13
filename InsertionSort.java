package sortings;


import java.util.Collections;
import java.util.List;

public class InsertionSort extends SortedList {
  public InsertionSort(int limit) {
    super(limit);
  }

  @Override
  public List<Integer> sort(List<Integer> list) throws Exception {
    super.wrap(list);
    if (wrapper.lenght() > elementsCountLimit) {
      throw new Exception("Превышен лимит размера для сортировки слиянием, должно быть не больше "
              + elementsCountLimit + " элементов, в переданном списке - " + wrapper.lenght());
    }
    List<Integer> result = wrapper.cloneList();
    Collections.sort(result);
    return result;
  }

  @Override
  public SortedTypes type() {
    return SortedTypes.InsertonSort;
  }
}