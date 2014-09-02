package synchronization;

/**
 * Test what happens if the run method of Thread is marked synchronized <br/>
 * Inside the synchronized run method, the thread just waits <br/>
 * Only one thread can ever get into this run method, <br/>
 * (probably) means only one thread can start the thread <br/>
 * if t1.notify() is not synchronized on t1 then IllegalThreadStateException occurs for <b>some</b> reason <br/>
 * TODO: figure out reason for this behaviour
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
      e.printStackTrace();
    }
    System.out.println(getName() + ": ends");
  }
}
