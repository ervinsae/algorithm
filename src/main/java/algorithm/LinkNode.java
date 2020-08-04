package algorithm;

public class LinkNode {


    public static void main(String[] args) {

        Node head = new Node(0);
        LinkNodeOperation linkNodeOperation = new LinkNodeOperation(head);
        linkNodeOperation.addNode(1);
        linkNodeOperation.addNode(2);
        linkNodeOperation.addNode(3);

        // 打印反转前的链表
        linkNodeOperation.traverseNode();
        // 调用反转方法
        head = linkNodeOperation.reverseNode(head);

        System.out.println("\n**************************");
        // 打印反转后的结果
        while (null != head) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }


}
