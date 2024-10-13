package wrap;

import java.util.ArrayList;
import java.util.List;

public class ListWrap {
  private final List<T> newlist;

  public ListWrapper(List<T> list) {
    this.newlist = new ArrayList<T>(list);
  }

  public List<T> cloneList() {
    return new ArrayList<T>(newlist);
  }

  public int lenght() {
    return newlist.size();
  }
}
