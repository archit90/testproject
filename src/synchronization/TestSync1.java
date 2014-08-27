package synchronization;

/**
 * The A class has two methods that are synchronized. <br/>
 * Threads B, C are passed the same object of class A <br/>
 * and methods on A are called. <br/>
 * Only one thread can access the function test1 or test2. <br/>
 * <p/>
 * The lock is on the object of A.<br/>
 * If one thread enters a synchronized method of A it locks the object A for itself, <br/>
 * no other thread can access the any synchronized methods of A <br/>
 * al long as the synchronized method of A finishes <br/>
 */
public class TestSync1 {
  public static void main(String[] args) {
    AA a1 = new AA();
    BB b1 = new BB(a1);
    CC c1 = new CC(a1);
    b1.start();
    c1.start();
  }
}

class AA {
  synchronized void test1() {
    for (int i = 0; i < 100; i++) {
      System.out.println("test1: " + i);
    }
  }

  synchronized void test2() {
    for (int i = 100; i < 200; i++) {
      System.out.println("test2: " + i);
    }
  }
}

class BB extends Thread {
  AA AA;

  BB(AA AA) {
    this.AA = AA;
  }

  public void run() {
    AA.test1();
  }
}

class CC extends Thread {
  AA AA;

  CC(AA AA) {
    this.AA = AA;
  }

  public void run() {
    AA.test2();
  }
}