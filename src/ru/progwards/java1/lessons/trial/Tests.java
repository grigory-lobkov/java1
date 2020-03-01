package ru.progwards.java1.lessons.trial;

import java.util.Arrays;

public class Tests {

    public static void main(String[] args) {
        /*Season s = new Season();
        s.setLang(Season.Language.EN);
        for (int i = 0; i <= 13; i++)
            System.out.println(i + " " + s.toString(i));*/

//        System.out.println(IsDate.check2(31,10,2000));
//        System.out.println(IsDate.check2(29,2,2000));
//        System.out.println(IsDate.check2(30,2,2000));
//        System.out.println(IsDate.check2(29,2,2001));
//        System.out.println(IsDate.check2(0,1,2019));
//        System.out.println(IsDate.check2(1,0,2019));
//        System.out.println(IsDate.check2(32,1,2019));
//        System.out.println(IsDate.check2(1,13,2019));
//        System.out.println(IsDate.check2(31,12,2019));

        /*BArray b = new BArray<Integer>(10, 10);
        for (int i = 0; i < 9; i++) b.add(i);
        b.print();
        b.insert(9, 10);
        b.print();
        b.insert(3, 11);
        b.print();
        b.delete(3);
        b.print();
        b.delete(10);
        b.print();*/

        /*System.out.println(wordGrade(0));
        System.out.println(wordGrade(1));
        System.out.println(wordGrade(2));
        System.out.println(wordGrade(3));
        System.out.println(wordGrade(4));
        System.out.println(wordGrade(5));
        System.out.println(wordGrade(6));*/

        /*int[] a = new int[10];
        for (int i = 0; i < a.length; i++) a[i] = i;
        BArray.printInt(a);
        int[] b = BArray.subArrayMul(a,1);
        BArray.printInt(b);
        int[] c = BArray.subArrayMul(a,2);
        BArray.printInt(c);
        int[] d = BArray.subArrayMul(a,0);
        BArray.printInt(d);*/

        int[] array = {12, 4, 33, 17, 98, 0};

        System.out.println(Arrays.toString(subArray(array, 2, 4))); // должен вернуть {33,17,98}
        System.out.println(Arrays.toString(subArray(array, 2, 6)));// должен вернуть null
    }

    /*Напишите функцию с сигнатурой void testJava(String str)
    которая выводит на консоль строку "Учиться в Progwards это "+ str

    Например, при вызове testJava("интересно!") на консоль должно
    быть выведено "Изучать Java это интересно!"

    Для тех, кто не знает как это сделать, скопируйте следующий код в окно с ответом*/
    void testJava(String str) {
        System.out.println("Учиться в Progwards это " + str);
    }

    static String wordGrade(int num) {
        switch (num) {
            case 0: return "не оценено";
            case 1: return "очень плохо";
            case 2: return "плохо";
            case 3: return "удовлетворительно";
            case 4: return "хорошо";
            case 5: return "отлично";
            default: return "не определено";
        }
    }

    /*
Напишите функцию, возвращающую подмаслив из массива.
Сигнатура функции int[]  subArray(int[] array, int from, int to) где
array - исходный массив
from - индекс, с которого надо скопировать элементы (включительно)
to - индекс, по который надо скопировать элементы (включительно)

При выходе from и to за границы массива вернуть null

Пример,

int[] array = {12, 4, 33, 17, 98, 0}

subArray(array, 2,4} должен вернуть {33,17,98}, а
subArray({array, 2,6) должен вернуть null
*/

    static int[] subArray(int[] array, int from, int to) {
        int l = array.length - 1;
        if (from > l || to > l || from < 0 || to < 0 || to < from) return null;
        l = to - from + 1;
        int[] result = new int[l];
        //for(int i = from, k = 0; i<=to; i++, k++) result[k] = array[i];
        System.arraycopy(array, from, result, 0, l);
        return result;
    }
}