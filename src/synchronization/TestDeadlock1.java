package synchronization;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Shared class has two synchronized methods each take a <br/>
 * Shared object and calls the other function using that passed object <br/>
 * <p/>
 * Two Threads ThrA, ThrB take two Shared objects and call <br/>
 * s1.test1(s2) and s2.test2(s1) respectively <br/>
 * <p/>
 * A deadlock happens
 */

public class TestDeadlock1 {
  public static void main(String[] args) {
    Shared s1 = new Shared(), s2 = new Shared();
    ThrA a1 = new ThrA(s1, s2);
    a1.setName("T1");
    ThrB b1 = new ThrB(s1, s2);
    b1.setName("T2");
    System.out.println("Going to start Threads");
    a1.start();
    b1.start();
    System.out.println("Started Threads");
    Util.sleep(2000);
    System.out.println("Woke up from sleep");
    ThreadMXBean tx = ManagementFactory.getThreadMXBean();
    long[] lockedIds = tx.findDeadlockedThreads();

    if (lockedIds != null) {
      ThreadInfo[] ti = tx.getThreadInfo(lockedIds);
      System.out.println("Deadlocked Threads are: ");
      for (int i = 0; i < ti.length; ++i) {
        System.out.println(ti[i].getThreadName());
      }
    } else {
      System.out.println("No threads are deadlocked");
    }
  }
}

class Shared {
  synchronized void test1(Shared s) {
    System.out.println("Test1 begin");
    Util.sleep(10);
    s.test2(this);
    System.out.println("Test1 end");
  }

  synchronized void test2(Shared s) {
    System.out.println("Test2 begin");
    Util.sleep(10);
    s.test1(this);
    System.out.println("Test2 end");
  }
}

class ThrA extends Thread {
  Shared s1, s2;

  ThrA(Shared s1, Shared s2) {
    this.s1 = s1;
    this.s2 = s2;
  }

  public void run() {
    s1.test1(s2);
  }
}

class ThrB extends Thread {
  Shared s1, s2;

  ThrB(Shared s1, Shared s2) {
    this.s1 = s1;
    this.s2 = s2;
  }

  public void run() {
    s2.test2(s1);
  }
}

class Util {
  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
