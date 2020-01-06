package ru.progwards.java1.lessons.queues;

import jdk.jfr.DataAmount;

import java.util.*;

public class CollectionsSort {
/*    Сравнение методов сортировки коллекций

1.1 Реализовать метод public static void mySort(Collection<Integer> data) - переделать алгоритм
из класса ArraySort из ДЗ про массивы, на коллекции. Не использовать встроенные методы сортировок

1.2 Реализовать метод public static void minSort(Collection<Integer> data) по следующему алгоритму
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую

1.3 Реализовать метод public static void collSort(Collection<Integer> data) используя метод sort из Collections

1.4 Реализовать метод public static Collection<String> compareSort() в котором сравнить производительность
методов и вернуть их имена, отсортированные в порядке производительности, первый - самый быстрый. В случае
равенства производительности первым вернуть "collSort".
*/

    public static void mySort(Collection<Integer> data) { // буду реализовывать простейший ArraySort.sort3, т.к. эту функцию использовать не предвидится
        // лучше бы, конечно, не делать лишних свопов в типах коллекций, где доступ по индексу осуществляется быстро
        /*
        for (int i = 0; i < data.size(); i++) {
            // найдем в остатках максимальный
            for (int j = i + 1; j < data.size(); j++) {
                if (data[i] > a[i]) {
                    int tmpValue = a[i];
                    a[i] = a[j];
                    a[j] = tmpValue;
                }
            }
        }
        */
        Iterator<Integer> it1 = data.iterator();
        int idx1 = 0;
        while (it1.hasNext()) {
            Integer num1 = it1.next();
            Iterator<Integer> it2 = data.iterator();
            int idx2 = 0;
            Integer num2;
            for (int i = idx1; i >= 0; i--) it2.next();
            while (it2.hasNext()) {
                num2 = it2.next();
                if (num1.compareTo(num2) > 0) {
                    Collections.swap((List)data, idx1, idx2); // List!? ... а как иначе их поменять местами, не понимаю
                    num1 = num2;
                }
                idx2++;
            }
            idx1++;
        }
    }

    public static void minSort(Collection<Integer> data) {
/*
- создать новую коллекцию
- найти минимальный элемент с использованием функции min()
- переместить его в новую коллекцию
- делать так до тех пор, пока все элементы не окажутся в новой коллекции
- скопировать новую коллекцию в старую
*/
        ArrayList<Integer> list = new ArrayList(data);
        ArrayList<Integer> result = new ArrayList(data.size());

        while (list.size() > 0) {
            Integer min = Collections.min(list);
            result.add(min);
            list.remove(min);
        }

        data.clear();
        data.addAll(result);
    }

    public static void collSort(Collection<Integer> data){
        Collections.sort((List)data);
    }

    public static Collection<String> compareSort() {
        //сравнить производительность методов и вернуть их имена, отсортированные в порядке производительности,
        // первый - самый быстрый. В случае равенства производительности первым вернуть "collSort"
    }
}
