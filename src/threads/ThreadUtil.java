package threads;

/**
 * Created by archit on 26/8/14.
 */
public class ThreadUtil {
  public static void sleep(long milli) {
    try {
      System.out.println("Thread: " + Thread.currentThread().getName() + " started sleeping");
      Thread.sleep(milli);
      System.out.println("Thread: " + Thread.currentThread().getName() + " woke up");
    } catch (InterruptedException e) {
      System.out.println("Thread: " + Thread.currentThread().getName() +" Interrupted Exception");
      e.printStackTrace();
    }
  }
}
