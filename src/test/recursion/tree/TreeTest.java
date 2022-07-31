package test.recursion.tree;


import java.util.LinkedList;
import java.util.Queue;

public class TreeTest {

    /*********本例中树的结构*********
          1
        /  \
       2   3
     /  \
    4   5
    *********本例中树的结构**********/

    //前序遍历
    public void dfs(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        dfs(root.left);
        dfs(root.right);
    }

    Queue<TreeNode> queue = new LinkedList<>();

    //广度优先遍历
    public void bfs() {
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode t =  queue.poll();
                System.out.print(t.val + " ");
                if(t.left != null) queue.add(t.left);
                if(t.right != null) queue.add(t.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeTest treeTest = new TreeTest();
        TreeNode p = new TreeNode(1);
        TreeNode l2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode l4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        p.left = l2;
        p.right = r3;
        l2.left = l4;
        l2.right = r5;
        //传入根节点，前序遍历二叉树
        System.out.println("----前序遍历-----");
        treeTest.dfs(p);
        System.out.println();
        System.out.println("----广度优先遍历-----");
        treeTest.queue.add(p);
        treeTest.bfs();


    }
}
