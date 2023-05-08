package algorithm

import java.sql.DriverManager.println
import java.util.*


/**
 * 深度优先算法，其实就是对二叉树做前序遍历 Depth First Search
 * https://www.51cto.com/article/614590.html
 */
class Dfs {

    //递归遍历
    fun dfsRecursive(root: TreeNode?) {
        if (root == null) return // 如果根节点为空，直接返回

        // 访问当前节点并遍历左右子树
        println(root.value)
        dfsRecursive(root.left)
        dfsRecursive(root.right)
    }

    //非递归遍历
    fun dfsIterative(root: TreeNode?) {
        if (root == null) return // 如果根节点为空，直接返回

        //使用栈来解决顺序取值问题
        val stack = Stack<TreeNode>()
        stack.push(root) // 将根节点压入栈中

        while (stack.isNotEmpty()) {
            val node = stack.pop() // 弹出栈顶节点

            // 访问当前节点并将其右子节点和左子节点压入栈中
            println(node.value)
            node.right?.let { stack.push(it) }
            node.left?.let { stack.push(it) }
        }
    }



}

/**
 * 广度优先算法 Breath First Search
 */
class Bfs {

    fun bfs(root: TreeNode?) {
        if (root == null) return

        /**
         * 在Java和Kotlin中，LinkedList类既可以作为链表，也可以作为队列使用。这是因为LinkedList类实现了java.util.Queue接口，而队列就是一种特殊的链表数据结构。
        在队列中，元素按照FIFO（先进先出）的原则进行插入和删除操作。LinkedList类提供了offer()方法用于在队列尾部插入元素，提供了poll()方法用于从队列头部删除元素。因此，我们可以使用LinkedList类来模拟队列的行为。
        例如，在上面的BFS函数中，我们使用queue.offer()方法将节点加入队列尾部，使用queue.poll()方法从队列头部取出节点。由于LinkedList类底层实现是链表结构，因此这些操作的时间复杂度是O(1)，非常高效。因此，我们可以将LinkedList类看作是队列数据结构的一种实现，用于实现BFS算法中的队列。
         */
        val queue = LinkedList<TreeNode>()
        queue.offer(root) // 将根节点加入队列

        while (queue.isNotEmpty()) {
            val node = queue.poll() // 出队队首节点

            // 访问当前节点并将其左右子节点加入队列
            println(node.value)
            node.left?.let { queue.offer(it) }
            node.right?.let { queue.offer(it) }
        }
    }


}

class TreeNode(val value: Int, var left: TreeNode? = null, var right: TreeNode? = null)



fun main(args: Array<String>) {

    //节点信息
    val root = TreeNode(1,
            left = TreeNode(2,
                    left = TreeNode(4),
                    right = TreeNode(5)),
            right = TreeNode(3,
                    left = TreeNode(6),
                    right = TreeNode(7)))

    var dfs = Dfs()
    dfs.dfsIterative(root)

    println("------------")

    dfs.dfsRecursive(root)
}