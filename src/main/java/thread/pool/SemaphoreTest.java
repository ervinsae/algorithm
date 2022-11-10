package thread.pool;

import java.util.concurrent.*;

public class SemaphoreTest {

    /* 读取文件数据的线程数量,并创建此容量的线程池 */
    private static final int THREAD_COUNT = 10;
    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    /*private static ExecutorService executorService = new ThreadPoolExecutor(THREAD_COUNT,
            THREAD_COUNT,
            0L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            new ThreadPoolExecutor.AbortPolicy());*/

    /* 创建Semaphore对象实例，构造函数的参数指定信号量的数目，为了方便说明问题，设为3 */
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args){
        /* 创建线程读取数据，并尝试获取数据库连接，将数据存储到数据库中 */
        for(int i = 0;i < THREAD_COUNT * 2.5;i++){
            final int index = i;

            Runnable task = new Runnable() {
                public void run() {
                    try {
                        /*从远程读数据*/
                        System.out.println("thread-"+ index + " is reading data from remote host");

                        /* 通过acquire 函数获取数据库连接，如果成功将数据存储到数据库 */
                        semaphore.acquire();
                        //System.out.println("thread-"+ index + " is saving data....");
                        /*模拟存储数据耗时*/
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {

                        /* 最终使用release 函数释放信号量 */
                        semaphore.release();
                    }
                }
            };
            executorService.execute(task);
        }
    }

}
