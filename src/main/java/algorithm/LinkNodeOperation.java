package algorithm;

public class LinkNodeOperation {


    Node head;
    public LinkNodeOperation(Node head) {

        this.head = head;
    }
    /**
     * 新增节点
     * 1,找到队尾
     * 2,添加元素
     */
    public void addNode(int value) {

        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        //使用一个临时节点（指针）从head节点开始遍历到队尾
        Node temp = head;
        while (temp.next != null) {

            temp = temp.next;
        }

        //遍历到队尾后
        temp.next = newNode;
    }

    /**
     * 遍历节点
     */
    public void traverseNode() {

        Node temp = head;

        while (temp != null) {

            System.out.print(temp.value + " ");
            temp = temp.next;
        }

    }

    /**
     * 链表反转
     * 递归，在反转当前节点之前先反转后续节点
     */
    public Node reverseNode(Node head) {
        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
        if (head == null || head.next == null) {
            return head;// 若为空链或者当前结点在尾结点，则直接还回
        }
        Node reHead = reverseNode(head.next);// 先反转后续节点head.getNext()
        head.next.next = head;// 将当前结点的指针域指向前一结点
        head.next = null;// 前一结点的指针域令为null;
        return reHead;// 反转后新链表的头结点
    }
}
