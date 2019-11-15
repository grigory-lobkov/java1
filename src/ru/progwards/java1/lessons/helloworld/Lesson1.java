package ru.progwards.java1.lessons.helloworld;

public class Lesson1 {

    static void helloWorld() {
        String str; // объявление
        str = "Hello World!"; // инициализация
        System.out.println(str);
    }

    static void println(String str) {
        System.out.println(str);
    }

    static int addition(int x, int y) {
        int z;
        z = x + y;
        return z;
    }

    public static void main(String[] args) {
        helloWorld();
        Lesson1.helloWorld();
        Lesson1.helloWorld();
        println("Привет, Мир!");
        println("Как дела?");
        System.out.print("Я знаю, сколько будет 5 + 7 = ");
        int z = addition(5,7);
        System.out.print(z);
    }

}
