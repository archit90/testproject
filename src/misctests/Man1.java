package misctests;

public class Man1 {
  public static void main(String[] args) {
    MA a = new MA();
    System.out.println("a: " + a.getState());
    a.start();
    System.out.println("b: " + a.getState());
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("c: " + a.getState());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("d: " + a.getState());
  }
}

class MA extends Thread {
  public void run() {
    for (int i = 0; i < 4000; i++)
      System.out.println(getName() + ": " + i);
  }
}
