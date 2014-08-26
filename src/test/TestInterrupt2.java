package test;

/**
 * Created by archit on 26/8/14.
 */
public class TestInterrupt2 {
  public static void main(String[] args) throws Exception {
    Th1 t1 = new Th1();
    t1.setDaemon(true);
    t1.start();
    //ThreadUtil.sleep(1000);
    boolean a=true;
    if(a)
      throw new Exception("Yikes!");
    t1.interrupt();
    System.out.println("Done main");
  }
}

class Th1 extends Thread {
  public void run() {
    System.out.println("Thread: " + getName()+" starts");

    try {
      sleep(5000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }

    //ThreadUtil.sleep(10000);
    System.out.println(isInterrupted());
    System.out.println("Done Thread");
    System.out.println("Thread: " + getName()+" ends");
  }
}
class Th2 extends Thread{
  public void run(){

    System.out.println("Thread: " + getName()+" starts");
    System.out.println("Thread: " + getName()+" ends");
  }
}