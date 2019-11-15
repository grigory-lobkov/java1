package ru.progwards.java1.lessons.basics;

public class Lesson2 {

    static double fractional(double num) {
        return num % 1;
    }

    public static void main(String[] args) {
        /*byte b1 = 123;
        short s1 = 32023;
        int i1 = 65432;
        int i2 = 0xFAB;
        int i3 = 0b010101;
        long l1 = 123456789012345L;

        double pi = 3.1415926535D;
        double koe = 1.5e7;
        String message = "Запись фильма \"Матрица\" находится в файле C:\\video\\matrix.avi";*/
        System.out.println(fractional(0.32));
        System.out.println(fractional(2.32));
    }
/*    public static void main(String[] args) {
        System.out.println(fractional(0.32)); // должно вывести "0.32"
        System.out.println(fractional(2.32)); // должно вывести "0.32"
    }*/
}
