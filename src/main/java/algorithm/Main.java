package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

        //int[] array = {25, 11, 45, 26, 78, 12};//要排序的数组
        int[] array = {3, 8, 6, 9, 7, 5, 2, 4, 1};//要排序的数组

        //straightInsertSort(array);
        //straightInsertSort2(array);
        //selectSort(array);
        //bubbleSort(array);


        soonSort(array, 0, array.length - 1);
        //quickSort(array, 0, array.length - 1);


        //heapSort(array);

        System.out.println(Arrays.toString(array));

        //hashmap.put会覆盖重复的键
        HashMap<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
//        map.put("a",2);
//        map.put("a",3);
//        map.put("a",4);

        //LinkedHashMap
        Map<String,Integer> map1 = new LinkedHashMap<>(10,0.75f,true);
        map1.put("a",4);
        map1.put("b",5);
        map1.put("v",6);
        map1.put("d",7);

        map.get("v");

        for(Integer value : map.values()){
            System.out.println(value+"");
            //System.out.println(map.size()+"个");
        }

        System.out.println(5 << 1);
    }

    /**
     * 直接插入排序
     * 思想：将数组分为左右两边，左边是默认排好序的，右边是无序的，依次将右边的数组插入到左边排好序的数组，直到完成整个数组排序。
     * 时间复杂度O（n2）
     *
     * @param array 数组
     */
    public static void straightInsertSort(int[] array) {

        int length = array.length;

        for (int i = 1; i < length; i++) {

            int insertNum = array[i];
            int j;
            //循环左边的排好序数组
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > insertNum) {
                    array[j + 1] = array[j];//调换位置
                } else {
                    break;//如果break，则表示左边排序完成
                }
            }

            array[j + 1] = insertNum;//注意j的值，最后一次循环-1
        }


        System.out.println(Arrays.toString(array));

    }

    public static void straightInsertSort2(int[] array) {

        int length = array.length;
        int temp, j;

        //从index=1开始
        for (int i = 1; i < length; i++) {
            //插入的数据
            temp = array[i];

            j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                //可以退出while循环
                j--;
            }

            array[j + 1] = temp;
        }

        System.out.println(Arrays.toString(array));

    }

    /**
     * 选择排序
     * 思想：遍历数组中的最小值，移动到最左边，再从剩下的数组中找最小的。直到所有数排序完成。最基本的排序
     * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 时间复杂度O（n2）
     *
     * @param array 数组
     */
    public static void selectSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                //和外穿第一个元素互换
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }

            }
        }

        System.out.println(Arrays.toString(array));
    }

    /**
     * 冒泡排序
     * 思想：内层循环会将数组两两比较，完成一轮后，最大的数会排在最后。
     * <p>
     * 时间复杂度O（n2）
     *
     * @param array 数组
     */

    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {//优化了，每一轮后面最后一个数不用比较了
                //相邻两个元素互换
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

            }
        }
        System.out.println(Arrays.toString(array));

    }

    /**
     * 快排
     * 思想：分治，前后两个指针
     *
     * @param a     待排序数组
     * @param low   数组最低位指针
     * @param hight 数组最高位指针
     */

    public static void soonSort(int[] a, int low, int hight) {
        //开始第一步是从右找---第一回和找到比基准小的，交换，找不到就左移
        //接下来左侧查找---找到比基准大的，交换，找不到就右移
        //直到i》j结束循环
        //----
        //接下来分组进行查找，递归
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) {
            while (i < j && a[j] >= index)
                j--;
            if (i < j) a[i++] = a[j];
            while (i < j && a[i] <= index)
                i++;
            if (i < j) a[j--] = a[i];
        }
        System.out.println("i=" + i + ",j="+j);
        a[i] = index;
        soonSort(a, low, i - 1); // 对低子表进行递归排序
        soonSort(a, i + 1, hight); // 对高子表进行递归排序

    }


    /**
     * 快排2,从小到大
     * https://wiki.jikexueyuan.com/project/easy-learn-algorithm/floyd.html
     */
    public static void quickSort(int[] a, int low, int high) {

        int i, j, index;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        index = a[i]; // 用子表的第一个记录做基准

        while (i < j) {
            //从最右边找到一个比index小的值
            while (i < j && a[j] >= index) {
                j--;
            }
            //从最左边找到一个比index大的值
            while (i < j && a[i] <= index) {
                i++;
            }

            //都找到之后，交换找到的这两个数
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        //当i=j的时候跳出循环，此时i和j指向的应该是同一个点，再把基准点和该点完成交换。
        //此时第一轮比较就完毕了，基准点的左边的数都比基准点要小，基准点右边的数都要比基准点大
        System.out.println("i=" + i + ",j="+j);
        a[low] = a[i];
        a[i] = index;

        //递归完成以基地划分的左边和右边两个序列
        quickSort(a, low, i - 1);
        quickSort(a, i + 1, high);

    }


    /**
     * 堆排序
     * 思想：可以画图看出堆其实就是一颗完全二叉树，第一步是将二叉树变成稳定二叉树，（大堆模式，根结点大于等于叶子结点），遍历所有的根结点后，最大的数就在根结点了，第二步，将根结点后最后一个数交换，再重复第一步
     * @param array 排序数组
     * @param parent 根结点下标
     * @param length 数组长度
     */

    public static void HeapAdjust(int[] array, int parent, int length) {
        int temp = array[parent]; // temp保存当前父节点
        int childIndex = 2 * parent + 1; // 先获得左孩子结点下标

        while (childIndex < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (childIndex != length-1 && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[childIndex])
                break;

            // 父节点小于孩子节点，把孩子结点的值赋给父结点
            array[parent] = array[childIndex];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = childIndex;
            childIndex = 2 * childIndex + 1;
        }

        //遍历完后给新的parent赋值 ？
        array[parent] = temp;
    }

    public static void heapSort(int[] list) {
        // 从最后一个父节点开始循环建立初始堆
        //构建堆的时候，从最后一个父节点开始，每个父节点和自己的左右子树比较，将自己这一子树（从自己到叶子）构建成大根堆。每个父节点下滤时，不用考虑他的父节点和兄弟节点，只需考虑孩子即可。

        for (int i = list.length / 2-1; i >= 0; i--) {
            HeapAdjust(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            HeapAdjust(list, 0, i);

        }
    }

    /**
     * 二分查找法，递归实现：二分查找法是针对有序数列来的
     */

    public static int binarySearch(int[] a,int startIndex,int endIndex,int key){

        if (startIndex  >= endIndex){
            return -1;
        }

        int mid = (endIndex - startIndex) / 2 + startIndex;
        while (startIndex < endIndex){

            if (a[mid] == key){
                return mid;
            } else if (key > a[mid]){
                binarySearch(a,mid+1,endIndex,key);
            } else if (key < a[mid]){
                binarySearch(a,startIndex,endIndex - 1,key);
            }

        }
        return -1;

    }

    //单向链表转🈯️
    //https://blog.csdn.net/xyh269/article/details/70238501
    public static Node revereNodeList(Node head){

        Node pre = null;
        Node next = null;

        while (head != null){

            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;

    }
}
