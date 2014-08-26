package threads;

/**
 * Note: String array can be passed in place of String varargs
 */

public class TestVarargs {
  public static void main(String[] args) {
    check(new String[]{"2", "3"});
    check("2", "3");
    // both the above are equivalent
  }

  static void check(String... as) {
    System.out.println(as.length);
    for (int i = 0; i < as.length; ++i) {
      System.out.println(as[i]);
    }
  }
}
