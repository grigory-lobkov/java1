package ru.progwards.java1.lessons.arrays;

import java.util.Arrays;

public class Lesson6 {

    public static int sumArrayItems(int[] a) {
        int r = 0;
        for (int v : a) r += v;
        return r;
    }

    public static void main(String[] args) {
        int[] a = {3,2,1};
        System.out.println("" + sumArrayItems(a));
        String str1 = "12345";
        String str2 = "12345";
        System.out.println(str1 == str2);
        System.out.println("" + arrayMax(a));
    }

    class Rectangle {
        private double a;
        private double b;

        public Rectangle(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double area() {

            return a*b;
        }
        public int compareTo(Rectangle anRectangle) {
            double s1 = this.area();
            double s2 = anRectangle.area();
            return s1 >= s2 ? (s1 == s2 ? 0 : 1) : -1;
        }
    }

/*
Реализуйте функцию, возвращающую максимальный по значению элемент массива.
Если в массиве 0 элементов, вернуть 0. Сигнатура функции

public int arrayMax(int[] a)

Подсказка - для быстрой реализации удобно использовать функцию Arrays.sort()
*/

    public static int arrayMax(int[] a) {
        Arrays.sort(a);
        return a.length > 0 ? a[a.length - 1] : 0;
    }

}
