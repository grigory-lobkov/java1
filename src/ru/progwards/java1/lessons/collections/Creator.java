package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;

public class Creator {
/*
1.1 Реализовать метод
public static Collection<Integer> fillEven(int n)
 - создать коллекцию и заполнить последовательностью четных возрастающих
чисел начиная с 2, количество элементов в коллекции n
*/

    public static Collection<Integer> fillEven(int n) {
        int v = 2;
        Collection<Integer> result = new ArrayList<>();
        for (int i = n; i > 0; i--, v += 2) result.add(v);
        return result;
    }

/*
1.2 Реализовать метод
public static Collection fillOdd<Integer>(int n)
 - создать коллекцию и заполнить последовательностью нечетных убывающих
чисел, минимальное число в коллекции 1, количество элементов в коллекции n
*/

    public static Collection<Integer> fillOdd(int n) {
        int v = n * 2 - 1;
        Collection<Integer> result = new ArrayList<>();
        for (int i = n; i > 0; i--, v -= 2) result.add(v);
        return result;
    }

/*
1.3 Реализовать метод
public static Collection<Integer> fill3(int n)
 - создать коллекцию и заполнить ее тройками чисел.
Каждая тройка создается по алгоритму: первое число тройки - индекс числа в коллекции,
второе - индекс в квадрате, третье - индекс в кубе, количество элементов в коллекции n
*/

    public static Collection<Integer> fill3(int n) {
        Collection<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            switch (i % 3) {
                case 0:
                    result.add(i);
                    break;
                case 1:
                    result.add(i * i);
                    break;
                case 2:
                    result.add(i * i * i);
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //System.out.println(fillEven(6));
        //System.out.println(fillOdd(6));
        System.out.println(fill3(6));
    }
}