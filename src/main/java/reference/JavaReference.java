package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

public class JavaReference {


    public static void main(String[] args) {

        softRefMemoryEnough();
        System.out.println("------内存不够用的情况------");
        softRefMemoryNotEnough();
        System.out.println("------弱引用------");
        weakRefDemo();
        System.out.println("------弱引用WeakHashMap------");
        myHashMap();
        try {
            myWeakHashMap();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------虚引用------");
        try {
            phantomRefDemo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void softRefMemoryEnough() {

        Object o1 = new Object();
        SoftReference s1 = new SoftReference(o1);
        System.out.println(o1);
        System.out.println(s1.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(s1.get());//不会被回收
    }

    /**
     * JVM配置`-Xms5m -Xmx5m` ，然后故意new一个一个大对象，使内存不足产生 OOM，看软引用回收情况
     */
    private static void softRefMemoryNotEnough() {
        Object o1 = new Object();
        SoftReference s1 = new SoftReference(o1);
        System.out.println(o1);

        System.out.println(s1.get());
        o1 = null;
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.out.println(o1);
        System.out.println(s1.get()); //会被回收
    }


    /**
     * 弱引用
     */
    private static void weakRefDemo() {

        Object o1 = new Object();
        WeakReference w1 = new WeakReference(o1);
        System.out.println(o1);
        System.out.println(w1.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(w1.get());
    }

    public static void myHashMap() {
        HashMap map = new HashMap();
        String key = new String("k1");
        String value = "v1";
        map.put(key, value);
        System.out.println(map);
        key = null;
        System.gc();
        System.out.println(map);//不会被回收
    }

    public static void myWeakHashMap() throws InterruptedException {
        WeakHashMap map = new WeakHashMap();
        //String key = "weak"; //key指向一个JVM字符串常量池中的"weak"字符串
        // 刚开始写成了上边的代码
        //思考一下，写成上边那样会怎么样？ 那可不是引用了
        String key = new String("weak");
        String value = "map";
        map.put(key, value);
        System.out.println(map);
        //去掉强引用
        key = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println(map);//被回收，因为只被一个弱引用引用（WeakHashMap的原理）
    }


    /**
     * 虚引用
     */
    private static void phantomRefDemo() throws InterruptedException  {
        Object o1 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(referenceQueue.poll());

        System.out.println(phantomReference.get());
        o1 = null;
        System.gc();
        Thread.sleep(3000);
        System.out.println(o1);
        System.out.println(referenceQueue.poll()); //引用队列中
        System.out.println(phantomReference.get());
    }

}