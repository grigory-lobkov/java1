package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;

import static ru.progwards.java1.lessons.collections.Creator.*;

public class Finder {

/*
2.1 Реализовать метод
public static Collection<Integer> findMinSumPair(Collection<Integer> numbers)
 - найти 2 соседних числа в коллекции сумма которых минимальна,
 вернуть коллекцию, содержащую индексы этих чисел
*/

    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {

        if (numbers == null) return null;

        int prev = 0;
        int idx = 0;
        int sum;
        int minIdx = 0;
        int minSum = Integer.MAX_VALUE;

        for (int num : numbers) {
            if (idx > 0) {
                sum = num + prev;
                if (sum < minSum) {
                    minSum = sum;
                    minIdx = idx;
                }
            }
            prev = num;
            idx++;
        }

        Collection<Integer> result = new ArrayList<>();
        if (minIdx >= 1) result.add(minIdx - 1);
        if (minIdx >= 0) result.add(minIdx);
        return result;
    }

/*
2.2 Реализовать метод
public static Collection<Integer> findLocalMax(Collection<Integer> numbers)
 - найти локальные максимумы - числа, которые больше соседа справа и слева.
 Первый и последний элемент коллекции не может являться локальным  максимумом,
 вернуть коллекцию, содержащую значения этих максимумов
*/

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {

        if (numbers == null) return null;

        int prev1 = Integer.MAX_VALUE;
        int prev2 = Integer.MAX_VALUE;
        int idx = 0;
        Collection<Integer> result = new ArrayList<>();

        for (int num : numbers) {
            if (idx > 1 && prev1 > num && prev1 > prev2) {
                result.add(idx - 1);
            }
            prev2 = prev1;
            prev1 = num;
            idx++;
        }

        return result;
    }

/*
2.3 Реализовать метод
public static boolean findSequence(Collection<Integer> numbers)
 - проверить, содержит ли коллекция все числа от 1 до size(),
 порядок может быть произвольный
*/

    public static boolean findSequence(Collection<Integer> numbers) {
        if (numbers == null) return false;
        int size = numbers.size();
        if (size == 0) return false;

        BitSet s = new BitSet(size + 1);
        int i = 1;
        int num;

        for (Iterator<Integer> iterator = numbers.iterator(); iterator.hasNext(); ) {
            num = iterator.next();
            if (num < 1 || num > size) {
                return false;
            }
            s.set(num, true);
            i++;
        }

        for (i = 1; i <= size; i++) {
            //System.out.println(i + ":" + s.get(i));
            if (s.get(i)) continue;
            else return false;
        }

        return true;
    }

/*
2.4 Реализовать метод
public static String findSimilar(Collection<String> names)
 - найдите максимальное количество повторяющихся элементов.
 Результат вернуть в виде строки <элемент>:<количество>, например Василий:5.
 При равенстве максимального количества у разных повторяющихся элементов,
 вернуть результат для элемента, повторяющаяся последовательность которого началась с наименьшего индекса.
*/

    public static void main(String[] args) {
        /*Collection<Integer> e = fillEven(10);
        Collection<Integer> o = fillOdd(10);
        Collection<Integer> t = fill3(10);
        System.out.println(e);
        System.out.println(findSequence(e));
        System.out.println(o);
        System.out.println(findSequence(o));
        System.out.println(t);
        System.out.println(findSequence(t));*/
        Collection<Integer> s = new ArrayList<>();
        for (int i = 1; i < 10; i++) s.add(i);
        //s.add(1);
        System.out.println(s);
        System.out.println(findSequence(s));

    }

}
