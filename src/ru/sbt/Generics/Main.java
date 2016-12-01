package ru.sbt.Generics;

import ru.sbt.Generics.CollectionUtils;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Object o = new Integer("4");
        System.out.println(list.getClass());

        //CollectionUtils<Integer> coll = new CollectionUtils<>();
        CollectionUtils.add(list, o);
        CollectionUtils.add(list, 4);
        CollectionUtils.add(list, 8);
        CollectionUtils.add(list, 2);
        CollectionUtils.add(list, 3);
        List ll = CollectionUtils.range(list, 2, 5);
        for (Integer integer : list) {
            System.out.println(integer + " int");
        }
        System.out.println("fff");
        for (int i = 0; i < ll.size(); i++) {
            System.out.println(ll.get(i) + " int" + list.get(i).getClass());
        }
    }
}
