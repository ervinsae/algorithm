package algorithm;

/**
 * 双向量表
 */
public class DoubleLinkList {

    //该链表的头节点
    DoubleNode head;
    //该链表的打下
    int size;
    public DoubleLinkList(DoubleNode node) {
        this.head = node;
        size ++;
    }

    //添加节点（默认是在队尾添加节点）
    public void addNode(DoubleNode node) {

        if (head == null) {
            head = node;
            return;
        }

        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = node;
        node.pre = temp;
        size ++;
    }

    //在链表头部添加节点
    public void addFirst(DoubleNode node) {

        if (head == null) {
            head = node;
            return;
        }

        head.pre = node;
        node.next = head;
    }

    //删除链表中的节点
    public void removeNode(DoubleNode node) {

    }
}
