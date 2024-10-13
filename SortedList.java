package sortings;

import wrapper.ListWrap;
import java.util.List;

abstract public class Sorter implements SortedListinterface {
  protected ListWrap<Integer> wrapper;
  protected int elementsCountLimit;

  public Sorter(int limit) {
    this.elementsCountLimit = limit;
  }

  protected void wrap(List<Integer> list) {
    this.wrapper = new ListWrap<Integer>(list);
  }

  @Override
  public void setElementsCountLimit(int limit) {
    if (limit <= 0) {
      throw new IllegalArgumentException("В списке должен быть хотя бы 1 элемент");
    }
    this.elementsCountLimit = limit;
  }

  @Override
  public int getElementsCountLimit() {
    return elementsCountLimit;
  }
}
