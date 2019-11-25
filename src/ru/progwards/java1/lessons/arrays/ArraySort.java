package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;
import java.util.Random;

/*
Свой алгоритм сортировки, класс ArraySort

2.1 Реализовать метод, сортирующий произвольный массив целых чисел:
public static void sort(int[] a), по следующему алгоритму:
1. Берем первый элемент и сравниваем его со вторым, если второй меньше, меняем элементы в массиве местами.
2. Далее, сравниваем первый элемент с третьим, и если третий меньше, меняем их местами.
3. Так делаем для всех элементов с индексом больше первого
4. Берем второй элемент и сравниваем его с третьим, если нужно, меняем местам
5. Далее сравниваем второй элемент с четвертым, и если нужно, меняем местами.
6. Делаем так для всех элементов, с индексом больше 2-го
7. Переходим к элементу с индексом 3...
8. Обобщая, алгоритм звучит следующим образом - сделать 2 вложенных цикла, внешний по i и внутренний по j. Внутренний
цикл начинается от i+1, и если a[i] > a[j], то нужно поменять элементы a[i] и a[j] местами.
*/
public class ArraySort {

    public static void sort(int[] a) {
        //чтобы исключить множественное обращение к .length буду сортировать от большего к меньшему
        //чтобы избежать множественных перезаписей элементов, заведу переменную, содержащую максимальный элемент
        //и значение этого элемента
        int maxValue, maxIndex;
        for (int i = a.length - 1; i >= 0; i--) {
            maxValue = a[i];
            maxIndex = i;
            // найдем в остатках максимальный
            for (int j = 0; j < i; j++) {
                if (a[j] > maxValue) {
                    maxValue = a[j];
                    maxIndex = j;
                }
            }
            // поменяем местами, если нашли больший
            if (maxIndex < i) {
                a[maxIndex] = a[i];
                a[i] = maxValue;
            }
        }
    }
    public static void sort1(int[] a) {
        int tmpValue;
        for (int i = a.length - 1; i >= 0; i--) {
            // найдем в остатках максимальный
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    tmpValue = a[i];
                    a[i] = a[j];
                    a[j] = tmpValue;
                }
            }
        }
    }

    public static void sort2(int[] a) {
        int tmpValue;
        for (int i = 0; i < a.length; i++) {
            // найдем в остатках максимальный
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    tmpValue = a[i];
                    a[i] = a[j];
                    a[j] = tmpValue;
                }
            }
        }
    }

    public static void sort3(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // найдем в остатках максимальный
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] > a[i]) {
                    int tmpValue = a[i];
                    a[i] = a[j];
                    a[j] = tmpValue;
                }
            }
        }
    }
    /*public static void randomFill(int[] a, int from, int to) {
        for(int i = a.length - 1; i>=0; i++) a[i] = 1;
    }*/
    public static void main(String[] args) {
        for (int cnt = 1; cnt<=10; cnt++) {
            int[] a1 = new Random().ints(100, -100, 100).toArray();
            int[] b1 = new Random().ints(50_000, -50_000, 50_000).toArray();
            int[] c1 = new Random().ints(100_000, -100_000, 100_000).toArray();
            int[] a = new int[100];
            int[] b = new int[50_000];
            int[] c = new int[100_000];
            int aCnt = 100000;
            int bCnt = 3;
            int cCnt = 1;
            long time0 = System.currentTimeMillis();
            for (int i = aCnt; i > 0; i--) {
                System.arraycopy(a1, 0, a, 0, a1.length);
                sort3(a);
            }
            long time1 = System.currentTimeMillis();
            for (int i = bCnt; i > 0; i--) {
                System.arraycopy(b1, 0, b, 0, b1.length);
                sort3(b);
            }
            long time2 = System.currentTimeMillis();
            for (int i = cCnt; i > 0; i--) {
                System.arraycopy(c1, 0, c, 0, c1.length);
                sort3(c);
            }
            long time3 = System.currentTimeMillis();
            System.out.println((time1 - time0) + " " + (time2 - time1) + " " + (time3 - time2));
        }
        /*System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));*/
    }

}
/*
sort()
263 1107 1059
164 798 1057
166 793 1064
188 792 1059
172 792 1064
164 792 1064
168 791 1069
168 792 1056
166 792 1058
166 793 1056
sort1()
333 5020 6445
222 4782 6414
216 4772 6425
209 4779 6418
206 4786 6431
sort2()
370 10293 13446
307 10113 13515
338 10031 13424
302 10049 13387
sort3()
380 10202 13473
316 10013 13429
316 10060 13415
*/