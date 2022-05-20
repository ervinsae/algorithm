package thread;

public class MyThread implements Runnable{

    //共有资源
    private int count  = 50;
    @Override
    synchronized public void run() {
            count--;
            System.out.println("由" + Thread.currentThread().getName() + "计算, count= " + count);
    }
}