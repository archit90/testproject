package threads;

/**
 * Simple program to illustrate thread running <br/>
 * Inside main thread, a child thread is started <br/>
 * The output from both the threads will come jumbled in some order <br/>
 * <p/>
 * Notes: <br/>
 * 1. sleep is a static method in Thread class <br/>
 * 2. sleep throws a checked exception, InterruptedException which must be
 * caught or declared <br/>
 * 2.1. an exception is declared in method signature as follows <br/>
 * *** public void function(String arguments) throws SomeException <br/>
 * 3. Default names for child threads are set like Thread-0, Thread-1, and so on <br/>
 * 4. When main (the first main) is run its thread name is main <br/>
 */

public class TestThread1 {
  public static void main(String[] args) {
    new ABC().start();
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        Thread.sleep(500);
        System.out.println(Thread.currentThread().getName()); // outputs main
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}

class ABC extends Thread {
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(i);
      try {
        sleep(500);
        System.out.println(Thread.currentThread().getName()); // outputs Thread-0
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
