package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
Создать статический метод public static int calcEmpty(String fileName),
в котором посчитать количество пустых строк в файле. Пустая строка,
это та, которая содержит только "\n". В случае всяких ошибок, вернуть -1
*/
public class LineCount {

    public static int calcEmpty(String fileName) {

        int result = 0;
        int resultOnError = -1;
        String searchString = "";

        try (FileReader f = new FileReader(fileName); Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String str = s.nextLine();
                if (str.compareTo(searchString) == 0) result++;
            }
        } catch (FileNotFoundException e) {
            result = resultOnError;
        } catch (IOException e) {
            result = resultOnError;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(calcEmpty("java1.iml"));
        System.out.println(calcEmpty("java1-.iml"));
    }

}