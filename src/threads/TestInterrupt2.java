package threads;

/**
 * 1. Main thread is always user thread <br/>
 * If it is attempted to convert it to daemon thread, IllegalThreadStateException <br/>
 * Reason for above: cannot modify a running thread (here main) from user to daemon <br/>
 * <p/>
 * 2. From user thread we can start child user or daemon threads <br/>
 * if the user thread ends or throws exception while running, all its <br/>
 * - child daemon threads will stop running (terminate immediately), <br/>
 * - child user threads will keep running <br/>
 * <p/>
 * Calling Thread.interrupt() is a way to tell the thread to stop what it is doing. <br/>
 * If the thread is in a <b>blocking call</b>, the blocking call will throw an <b>InterruptedException</b>, <br/>
 * otherwise the interrupted flag of the thread will be set. <br/>
 * <p/>
 * A Thread or a Runnable that is interruptable should check from time to time <br/>
 * Thread.currentThread().isInterrupted(). If it returns true, cleanup and return <br/>
 */

public class TestInterrupt2 {
  public static void main(String[] args) throws Exception {
    Th1 t1 = new Th1();
    //t1.setDaemon(true);
    t1.start();
    ThreadUtil.sleep(1000);
    boolean a = true;
    if (!a)
      throw new Exception("Yikes!");
    t1.interrupt();
    System.out.println("Done main");
  }
}

class Th1 extends Thread {
  public void run() {
    System.out.println("Thread: " + getName() + " starts");

    try {
      sleep(5000);
    } catch (InterruptedException e) {
      System.out.println(getName() + " interrupted");
      e.printStackTrace();
    }

    //ThreadUtil.sleep(10000);
    System.out.println(isInterrupted());
    System.out.println("Done Thread");
    System.out.println("Thread: " + getName() + " ends");
  }
}

class Th2 extends Thread {
  public void run() {

    System.out.println("Thread: " + getName() + " starts");
    System.out.println("Thread: " + getName() + " ends");
  }
}