package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    /**
     * 解释说明：
     * 1，设置一个原子操作类，初始化为10
     * 2，我们使用CAS方法来更新这个变量的值（atomicInteger:10），看初始的10是不是等于expect的值，如果相等则更改变量atomicInteger的值为20
     * 3，基于2，atomicInteger的值为20了，再比较是不是等于10，显然是不等于的
     * @param args
     */

    public static void main(String[] args) {

        // 首先设置变量的初始值为10，主内存中的值设置为10
        AtomicInteger atomicInteger = new AtomicInteger(10);
        // 调用atomicInteger的CAS方法，先比较当前值是否为10，如果是则替换为20，不是则不替换
        Boolean result1 = atomicInteger.compareAndSet(10,20);
        System.out.printf("当前atomicInteger变量的值:%d 比较结果%s\r\n", atomicInteger.get(), result1);

        Boolean result2 = atomicInteger.compareAndSet(10,30);
        System.out.printf("当前atomicInteger变量的值:%d, 比较结果%s\n" , atomicInteger.get(), result2);

        int preValue = atomicInteger.getAndSet(30);
        System.out.printf("当前atomicInteger变量的值:%d, 之前的值%s\n" , atomicInteger.get(), preValue);
    }
}
