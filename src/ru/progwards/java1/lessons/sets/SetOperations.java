package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {

    /*Реализовать класс SetOperations, операции над множествами целых чисел.

    1.1 Метод public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) - объединение множеств

    1.2 Метод public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) - пересечение множеств

    1.3 Метод public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) - разница множеств

    1.4 Метод public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) - симметрическая разница

    Кто подзабыл что значит каждая операция, можно глянуть тут https://studopedia.ru/14_138615_operatsii-nad-mnozhestvami-i-ih-svoystva.html или OK - google
    */
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = set1 != null ? new HashSet<Integer>(set1) : new HashSet<Integer>();
        if (set2 != null) result.addAll(set2);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {

    }

    static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {

    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {

    }
}
