package wrapper;

import java.util.ArrayList;
import java.util.List;

public class ListWrap<T> {
  private final List<T> newlist;

  public ListWrap(List<T> list) {
    this.newlist = new ArrayList<T>(list);
  }

  public List<T> cloneList() {
    return new ArrayList<T>(newlist);
  }

}
