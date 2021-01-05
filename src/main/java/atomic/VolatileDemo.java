package atomic;

import java.util.Scanner;

public class VolatileDemo implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {

        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");

        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();

        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];

        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++)
            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());

    }

    public static void main(String[] args) throws InterruptedException {

        VolatileDemo aVolatile = new VolatileDemo();

        new Thread(aVolatile, "thread A").start();

        System.out.println("main 线程正在运行");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String value = sc.next();
            if (value.equals("1")) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        aVolatile.stopThread();
                    }
                }, "thread B").start();
                break;
            }
        }
        System.out.println("主线程退出了！");


        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();

        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];

        currentGroup.enumerate(lstThreads);
        for (int i = 0; i < noThreads; i++)
            System.out.println("线程号：" + i + " = " + lstThreads[i].getName());

        System.out.println("----------------------------------------");
    }

    private void stopThread() {
        flag = false;
    }

}
