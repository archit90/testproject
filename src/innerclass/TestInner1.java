package innerclass;

/**
 * This test is multi purpose <br/>
 * - To understand implications of declaring members accessed inside inner class as final <br/>
 * - To understand the difference between mutable and immutable objects <br/>
 * - To understand the implications of returning private member variables from public class methods <br/>
 */

public class TestInner1 {
  public static void main(String[] args) {

  }
}

class AObj {
  private int num; // immutable
  private String str; // immutable
  private StringBuffer sb; // immutable

  AObj(int num, String str) {
    this.num = num;
    this.str = str;
    this.sb = new StringBuffer();
  }

  public void addInt(int i) {
    num += i;
  }

  public void addString(String s) {
    str += s;

  }

  public void appendStringBuffer(String s) {
    sb.append(s);
  }

  public StringBuffer getSb() {
    return sb;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }
}