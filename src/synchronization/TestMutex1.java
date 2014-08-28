package synchronization;

/**
 * A very simple analogy for mutex and semaphore <br/>
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
 * <p/>
 * <b>Mutex is a general concept of restricting access of a shared resource.</b> <br/>
 * Semaphore is a kind of mutex <br/>
 * <p/>
 * A very good explanation of Mutex by analogy <br/>
 * <p/>
 * When I am having a big heated discussion at work, <br/>
 * I use a rubber chicken which I keep in my desk for just such occasions. <br/>
 * The person holding the chicken is the only person who is allowed to talk. <br/>
 * If you don't hold the chicken you cannot speak. <br/>
 * You can only indicate that you want the chicken and wait until you get it before you speak. <br/>
 * Once you have finished speaking, you can hand the chicken back to the moderator <br/>
 * who will hand it to the next person to speak. <br/>
 * This ensures that people do not speak over each other, and also have their own space to talk. <br/>
 * <p/>
 * Replace Chicken with Mutex and person with thread and you basically have the concept of a mutex. <br/>
 * Of course, there is no such thing as a rubber mutex. <br/>
 * Only rubber chicken. My cats once had a rubber mouse, but they ate it. <br/>
 * Of course, before you use the rubber chicken, <br/>
 * you need to ask yourself whether you actually need 5 people in one room <br/>
 * and it would not just be easier with one person in the room on their own doing all the work. <br/>
 * Actually, this is just extending the analogy, but you get the idea. <br/>

 */
public class TestMutex1 {
  public static void main(String[] args) {
    main2(args);
  }

  public static void main2(String[] args) {
    C1 c1 = new C1();
    final int COUNT = 4;
    MTh[] ts = new MTh[COUNT];
    for (int i = 0; i < COUNT; i++) {
      ts[i] = new MTh(c1);
    }
    for (int i = 0; i < COUNT; i++) {
      System.out.println("Started: "+ts[i].getName());
      ts[i].start();
    }
    try {
      Thread.sleep(7000);
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
class MTh extends Thread{
  C1 c;
  public MTh(C1 c){
    this.c=c;
  }
  public void run(){
    System.out.println(Thread.currentThread().getName()+": entered run()");
    c.test1();
    System.out.println(Thread.currentThread().getName()+": exiting run()");
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
