package test;

/**
 * Created by archit on 25/8/14.
 */
public class Test1 {
  public static void main(String[] args) {
    check(new String[]{"2", "3"});
  }

  static void check(String... as) {
    System.out.println(as.length);
    for (int i = 0; i < as.length; ++i) {
      System.out.println(as[i]);
    }
  }
}
