package com.tabelafipe.main;

import java.util.Comparator;
import java.util.List;

public class ListHelperMethods {

    public static <T> void sortListBy(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    public static <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }
}
