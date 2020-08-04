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
     * 返回整个链表的长度
     * @return
     */
    public int linkLength() {

        int length = 0;
        Node temp = head;

        while (temp != null) {
            length ++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 判断链表中是否有该值的节点存在
     * @param value
     * @return
     */
    public boolean hasNode(int value) {

        boolean flag = false;
        Node temp = head;

        while (temp != null) {
            if (temp.value == value) {
                flag = true;
            }

            temp = temp.next;
        }

        return flag;
    }


    /**
     * 目标节点的位置
     * @param value
     * @return
     */
    public int nodePos(int value) {

        int pos = 0;
        if(hasNode(value)) {

            Node temp = head;
            while (temp != null) {

                if (value == temp.value) {

                    return pos;
                }

                pos ++;
                temp = temp.next;
            }

        }

        return pos;
    }

    /**
     * 返回目标节点
     * @param value
     * @return
     */
    public Node getNode(int value) {

        Node node ;

        if (hasNode(value)) {

            Node temp = head;
            while (temp != null) {

                if (value == temp.value) {

                    node = temp;
                    return node;
                }

                temp = temp.next;
            }
        }

        return null;
    }

    /**
     * 插入节点
     * 1,插入位置是否合法的判断
     * 2,寻找到插入位置的上一个节点，如插入到位置2，那么就要改变位置1的节点指针引用
     */
    public void insertNode(int insertValue, int pos) {

        if (pos < 1 || pos > linkLength()) {
            return;
        }

        Node insertNode = new Node(insertValue);
        //当前节点位置
        int currentPos = 0;
        Node temp = head;

        while (temp.next != null) {
            //遍历到了要插入位置的上一个节点
            if (currentPos == pos -1) {
                //画图很容易理解
                insertNode.next = temp.next;
                temp.next = insertNode;
                return;
            }

            currentPos ++;
            temp = temp.next;
        }

    }

    /**
     * 删除某个节点
     * @param deleteValue 被删除的值
     * 1,这个节点是否存在
     * 2，和插入一样找到这个值的上一节点，更换指针
     *
     */
    public void deleteNode(int deleteValue) {

        if (!hasNode(deleteValue)) {
            return;
        }

        Node temp = head;
        //当前节点位置
        int currentPos = 0;
        //Node deleteNode = getNode(deleteValue);

        while (temp.next != null) {

            //被删除节点的上一个节点
            if (nodePos(deleteValue)-1 == currentPos) {
                //被删除节点
                Node deleteNode = temp.next;

                temp.next = deleteNode.next;

                return;

            }

            currentPos ++;
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
