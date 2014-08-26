package threads;

/**
 * A small class having a static method sleep to put the current thread
 * to sleep for specified time in milliseconds
 * <p/>
 * Thread.currentThread() returns the instance of currently running thread
 */
public class ThreadUtil {
  public static void sleep(long milli) {
    try {
      System.out.println("Thread: " + Thread.currentThread().getName() + " started sleeping");
      Thread.sleep(milli);
      System.out.println("Thread: " + Thread.currentThread().getName() + " woke up");
    } catch (InterruptedException e) {
      System.out.println("Thread: " + Thread.currentThread().getName() + " Interrupted Exception");
      e.printStackTrace();
    }
  }
}
