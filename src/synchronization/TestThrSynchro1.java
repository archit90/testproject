package synchronization;

/**
 * Note: If notify() is called needlessly IllegalMonitorStateException occurs <br/>
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
    System.out.println(Thread.currentThread().getName() + ": here1");
    synchronized (t1) {
      System.out.println(Thread.currentThread().getName() + " inside synchronized");
      t1.notify();
    }
    System.out.println(Thread.currentThread().getName() + ": here2");
  }
}

class ThrSyn extends Thread {
  public synchronized void run() {
    System.out.println(getName() + ": begins");
    try {
      wait();
    } catch (InterruptedException e) {
      System.out.println("Exception in " + getName());
      e.printStackTrace();
    }
    System.out.println(getName() + ": ends");
  }
}
