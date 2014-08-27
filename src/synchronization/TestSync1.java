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
    A a1 = new A();
    B b1 = new B(a1);
    C c1 = new C(a1);
    b1.start();
    c1.start();
  }
}

class A {
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

class B extends Thread {
  A a;

  B(A a) {
    this.a = a;
  }

  public void run() {
    a.test1();
  }
}

class C extends Thread {
  A a;

  C(A a) {
    this.a = a;
  }

  public void run() {
    a.test2();
  }
}