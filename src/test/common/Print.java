package test.common;

import java.util.List;

public class Print {
    public static void printList(List<Integer> list) {
        for(int i = 0 ; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
