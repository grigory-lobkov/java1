package ru.progwards.java1.lessons.io1;

import javassist.NotFoundException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lesson9 {
    public static Integer sqr(Integer n) {
        try {
            return n * n;
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        /*System.out.println(sqr(2));
        System.out.println(sqr(null));*/

        //System.out.println(test(null));

        //test(0);

        try {
            System.out.println(new Lesson9().lineCount("java1.iml"));
        } catch (Throwable t) {
            System.out.println(t);
        }
    }

    public static String test (String filename) throws IOException {
        //который проверяет filename и если он равен null выбрасывает IOException со строкой "File not found",
        //в противном случает возвращает строку "File processing"
        if (filename == null) throw new RuntimeException("File not found");
        //try {
            java.io.FileWriter f = new java.io.FileWriter(filename, false);
            f.close();
        /*}
        catch (IOException e) {
            return e.toString();
        }*/
        return "File processing";
    }
    public static void doSomething(int n) throws IOException {
        throw new IOException("ok");
    }
    public static void test(int n){
        //Throwable suppressed = null;
        try {
            doSomething(n);
        }catch (Throwable e) {
            System.out.println(e.getMessage());
            //suppressed = e;
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println("finally executed");
        }
    }
/*
Существует метод public void doSomething(int n) throws IOException, создайте свой метод,
с сигнатурой public void test(int n), который должен содержать блоки try-catch-finally

1) В блоке try вызовите метод doSomething(n)

2) В блоке catch выведите на консоль строку, полученную из исключения через  getMessage()

3) Пробросите исключение дальше

4) В блоке finally выведите на консоль фиксированный текст "finally executed"
*/

/*
Реализовать метод с сигнатурой private int lineCount(String filename)
который возвращает количество строк в файле filename. В случае ошибки
пробросить исключение IOException со строкой сообщения "файл не найден"
*/
    private int lineCount(String filename) throws IOException {
        FileReader f = new FileReader(filename);
        Scanner s = new Scanner(f);
        int result = 0;
        while(s.hasNextLine()) {
            String str = s.nextLine();
            result++;
        }
        s.close();
        f.close();
        return result;
    }
}
