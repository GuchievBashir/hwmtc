import java.CustomArrayList;
public class Test {
  public static void main(String[] args) {
    CustomArrayList<Integer> list = new CustomArrayList()
    for (int i = 0; i < 12; i++) {
      list.add(i)
    }
    print(list.get(11))
  }
}