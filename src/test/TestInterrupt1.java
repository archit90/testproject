package test;

/**
 * Created by archit on 26/8/14.
 */
public class TestInterrupt1 {
  public static void main(String[] args) {
    Z z1 = new Z();
    z1.start();
    ThreadUtil.sleep(1);
    z1.interrupt();
    System.out.println("Done1");
    ThreadUtil.sleep(2);
    z1.interrupt();

    ThreadUtil.sleep(1);
    System.out.println("DoneFinal");
  }
}

class Z extends Thread {
  public void run() {
    for (int i = 0; i < 300; i++) {
      if (!isInterrupted()) {
        System.out.println("Not Interrupted " + i);
      } else {
        System.out.println("Interrupted " + i);
      }

    }
  }
}