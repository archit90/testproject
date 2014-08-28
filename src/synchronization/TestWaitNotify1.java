package synchronization;

public class TestWaitNotify1 {
  public static void main(String[] args) {
    TA a1 = new TA();
    Thr1 t1 = new Thr1(a1);
    Thr1 t2 = new Thr1(a1);
    t1.start();
    t2.start();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("About to release");
    a1.test2(); // releases one of the threads
    a1.test2(); // releases the other thread
    a1.test2(); // does not make a difference
  }
}

class TA {

  synchronized void test1() {
    String tname = Thread.currentThread().getName();
    System.out.println(tname + ": " + ": Test1 begin");
    try {
      wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(tname + ": " + ": Test1 end");
  }

  synchronized void test2() {
    notify();
  }

}

class Thr1 extends Thread {
  TA a;

  Thr1(TA a) {
    this.a = a;
  }

  public void run() {
    a.test1();
  }
}
