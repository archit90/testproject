package synchronization;

public class TestSync1 {
  public static void main(String[] args) {
    A a1=new A();
    B b1 =new B(a1);
    C c1=new C(a1);
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