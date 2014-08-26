package test;

/**
 * Created by archit on 25/8/14.
 */
public class TestThread1 {
  public static void main(String[] args) {
    new ABC().start();
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

class ABC extends Thread {
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        sleep(500);
        System.out.println(Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
