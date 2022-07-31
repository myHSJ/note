package note.recursion.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrackTest {

    public List<List<Integer>> res = new ArrayList();
    public LinkedList<Integer> path = new LinkedList();

    //组合
    void backtracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 选择 : 该层可以选择的列表
        for (int i = startIndex; i <= n; i++) {
            path.add(i);//增加元素到路径
            System.out.println("添加：" + i);
            backtracking(n, k, i + 1);
            int p = path.removeLast();//在路径中撤销元素
            System.out.println("移出：" + p);
        }
    }

    //剪支
    void backtracking2(int n, int k, int startIndex) {
        if(path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 选择 : 该层可以选择的列表
        // n - (k - path.size()) + 1 表示从这个位置开始取值才有意义
        for(int i = startIndex ; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);//增加元素到路径
            backtracking(n, k, i + 1);
            path.removeLast();//在路径中撤销元素
        }
    }


    private void printList() {
        for (int i = 0; i < this.res.size(); i++) {
            List<Integer> list = this.res.get(i);
            for (int j = 0; j < list.size() - 1; j++) {
                System.out.print(list.get(j) + ", ");
            }
            System.out.print(list.get(list.size() - 1));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BackTrackTest backTrackTest = new BackTrackTest();
        backTrackTest.backtracking(4, 2, 1);
        System.out.println("------");
        //打印组合结果
        backTrackTest.printList();
    }


}
