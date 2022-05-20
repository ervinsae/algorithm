package thread;

public class ThreadDemo1 {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        for (int i = 0; i < 50 ;i++) {
            Thread thread = new Thread(myThread, "i:" + i);
            thread.start();
        }
    }
}
