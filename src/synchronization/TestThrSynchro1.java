package synchronization;

/**
 * Test what happens if the run method of Thread marked synchronized
 */

public class TestThrSynchro1 {
  public static void main(String[] args) {
    ThrSyn t1 = new ThrSyn();
    t1.start();
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    synchronized (t1) {
      t1.notify();
    }
  }
}

class ThrSyn extends Thread {
  public synchronized void run() {
    System.out.println(getName() + ": begins");
    try {
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(getName() + ": ends");
  }
}
