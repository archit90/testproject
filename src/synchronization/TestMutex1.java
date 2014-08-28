package synchronization;

/**
 * A very simple explanation of mutex and semaphore <br/>
 * <p/>
 * <b>Mutex:</b> <br/>
 * It is like a key to a toilet. One person can have the key - occupy the toilet - at the time. <br/>
 * When finished, the person gives (frees) the key to the next person in the queue. <br/>
 * <p/>
 * Officially: "Mutexes are typically used to serialise access to a section of <br/>
 * re-entrant code that cannot be executed concurrently by more than one thread. <br/>
 * A mutex object only allows one thread into a controlled section, <br/>
 * forcing other threads which attempt to gain access to that section to wait <br/>
 * until the first thread has exited from that section." <br/>
 * (A mutex is really a semaphore with value 1.) <br/>
 * <p/>
 * <b>Semaphore: </b><br/>
 * It is like a number of free identical toilet keys.<br/>
 * Example, say we have four toilets with identical locks and keys. <br/>
 * The semaphore count - the count of keys - is set to 4 at beginning (all four toilets are free), <br/>
 * then the count value is decremented as people are coming in. <br/>
 * If all toilets are full, ie. there are no free keys left, the semaphore count is 0. <br/>
 * Now, when eq. one person leaves the toilet, semaphore is increased to 1 (one free key), <br/>
 * and given to the next person in the queue. <br/>
 * <p/>
 * Officially: "A semaphore restricts the number of simultaneous <br/>
 * users of a shared resource up to a maximum number. <br/>
 * Threads can request access to the resource (decrementing the semaphore), <br/>
 * and can signal that they have finished using the resource (incrementing the semaphore)." <br/>
 */
public class TestMutex1 {
  public static void main(String[] args) {
    main2(args);
  }

  public static void main2(String[] args) {
    final C1 c1 = new C1();
    final int COUNT = 4;
    Thread[] ts = new Thread[COUNT];
    for (int i = 0; i < COUNT; i++) {
      ts[i] = new Thread() {
        public void run() {
          c1.test1();
        }
      };
    }
    for (int i = 0; i < COUNT; i++) {
      System.out.println(ts[i].getName());
      ts[i].start();
    }
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("About to call test2 on C1 object");
    c1.test2();
  }

  public static void main1(String[] args) {
    final C1 c1 = new C1();
    Thread t1 = new Thread() {
      public void run() {
        c1.test1();
      }
    };
    t1.start();
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    c1.test2();
  }
}

class C1 {
  void test1() {
    System.out.println(Thread.currentThread().getName() + ": Entered test1");
    synchronized (this) {
      System.out.println(Thread.currentThread().getName() + ": Entered test1 synchronized block");
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + ": Exiting test1 synchronized block");
    }
    System.out.println(Thread.currentThread().getName() + ": Exiting test1");
  }

  void test2() {
    System.out.println(Thread.currentThread().getName() + ": Entered test2");
    synchronized (this) {
      System.out.println(Thread.currentThread().getName() + ": test2 notifyAll");
      notifyAll();
    }
    System.out.println(Thread.currentThread().getName() + ": Exiting test2");
  }
}
