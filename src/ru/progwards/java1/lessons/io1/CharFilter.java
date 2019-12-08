package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Scanner;

/*
Создать статический метод
public static void filterFile(String inFileName, String outFileName, String filter)
, в котором прочитать файл inFileName и удалить символы,
содержащиеся в String filter, результат записать в выходной файл.
В случае возникновения ошибки, пробросить стандартное исключение выше, корректно закрыв все ресурсы

Например файл содержит:
Java — строго типизированный объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
obscene = " -,.()"
Должен выдать результат:
JavaстроготипизированныйобъектноориентированныйязыкпрограммированияразработанныйкомпаниейSunMicrosystemsвпоследующемприобретённойкомпаниейOracle
*/
public class CharFilter {

    private static String deleteFilterChars (String string, String filter) {
        StringBuilder stringBuilder = new StringBuilder();
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (filter.indexOf(string.charAt(i)) < 0)
                stringBuilder.append(string.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static void filterFile(String inFileName, String outFileName, String filter) {
        try (
                FileReader fileReader = new FileReader(inFileName);
                Scanner scanner = new Scanner(fileReader);
                FileWriter fileWriter = new FileWriter(outFileName);
        ) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                str = deleteFilterChars(str,filter);
                fileWriter.write(str);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        filterFile("src\\ru\\progwards\\java1\\lessons\\io1\\CharFile.txt",
                "src\\ru\\progwards\\java1\\lessons\\io1\\CharFileO.txt", " -,.()");
    }
}
