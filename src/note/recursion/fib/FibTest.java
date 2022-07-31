package note.recursion.fib;

import java.util.HashMap;
import java.util.Map;

public class FibTest {

    //自顶向下
    int fib(int n) {
        int p = 0, c = 1;
        for(int i = 2; i <= n; i++) {
            int sum = p + c;
            p = c;
            c = sum;
        }
        return c;
    }

    //备忘录
    int fib(int n, Map<Integer, Integer> memo) {
        if(n == 0 || n == 1) return n;
        if(memo.containsKey(n)) return memo.get(n);
        //
        int tmp = fib(n -1, memo) + fib(n -2, memo);
        memo.put(n, tmp);
        return tmp;
    }

    public static void main(String[] args) {
        FibTest fibTest = new FibTest();
        Map<Integer, Integer> memo = new HashMap<>();
        int i = 5;
        //备忘录
        fibTest.fib(i, memo);
        //自底向上，由已知推未知
        fibTest.fib(i);
    }
}
