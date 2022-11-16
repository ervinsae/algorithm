package thread.pool;

import java.util.concurrent.*;

public class ThreadTest1 {

   /* private ExecutorService executors;

    public ThreadTest01() {
        //阿里规范中不推荐使用Executors来创建线程池
        //executors = Executors.newSingleThreadExecutor();

        executors = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(500));
    }

    public ExecutorService getExecutors() {
        return executors;
    }
*/

    public static void main(String[] args) {
        ExecutorService executors = new ThreadPoolExecutor(3,
                6,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardPolicy());
        //ThreadTest01 test01 = new ThreadTest01();
        for (int i = 1; i < 21; i++) {
            int taskId = i;
            Runnable task = () -> {
                try {
                    System.out.println("thread-" + Thread.currentThread().getName() + " is saving data:" + taskId);
                    /*模拟存储数据耗时*/
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executors.execute(task);
        }


    }

}
