package threads;

/**
 * A simple test for Thread interrupt <br/>
 * Note: interrupt() is a member function in Thread class unlike sleep() <br/>
 * A thread can be interrupted any number of times <br/>
 * <p/>
 * ThreadUtil.sleep() sleeps the main thread as it is called in main <br/>
 * z1.interrupt() interrupts the thread z1. <br/>
 * No exception is thrown in z1 thread as it was not sleeping at the time <br/>
 * otherwise InterruptedException would have been thrown <br/>
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