package ru.job4j.productivity;

import java.util.*;
import static java.lang.String.format;

public class StartProductivity {

    public static void main(String[] args) {
        ProductivityCollection pc = new ProductivityCollection();
        List<String> linkedList = new LinkedList<>();
        List<String> arrayList = new ArrayList<>();
        TreeSet<String> set = new TreeSet<>();

        long linkedListAddTime = pc.add(linkedList, 50000);
        long linkedListDeleteTime = pc.delete(linkedList, 50000);

        long arrayListAddTime = pc.add(arrayList, 50000);
        long arrayListDeleteTime = pc.delete(arrayList, 50000);

        long setAddTime = pc.add(set, 50000);
        long setDeleteTime = pc.delete(set, 50000);

        System.out.println(format("linkedListAddTime - %s", linkedListAddTime));
        System.out.println(format("linkedListDeleteTime - %s", linkedListDeleteTime));
        System.out.println(format("arrayListAddTime - %s", arrayListAddTime));
        System.out.println(format("arrayListDeleteTime - %s", arrayListDeleteTime));
        System.out.println(format("setAddTime - %s", setAddTime));
        System.out.println(format("setDeleteTime - %s", setDeleteTime));
    }
}
