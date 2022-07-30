package test.recursion.loop;

import java.util.Arrays;
import java.util.List;

public class RecursionTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        RecursionTest recursionTest = new RecursionTest();
        //normal loop
        recursionTest.loopPrint(list);
        System.out.println("--------");
        //recursion
        recursionTest.recursionPrint(list, 0);
    }

    public void loopPrint(List<Integer> list) {
        for(int i = 0 ; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public void recursionPrint(List<Integer> list, int index) {
        if(index == list.size()) return;
        System.out.print(list.get(index) + " ");
        recursionPrint(list, index + 1);
    }
}
