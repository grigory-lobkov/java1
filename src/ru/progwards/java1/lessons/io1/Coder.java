package ru.progwards.java1.lessons.io1;
import java.io.*;
import java.util.Scanner;

/*
Создать статический метод
public static void codeFile(String inFileName, String outFileName, char[] code, String logName)
, в котором прочитать файл inFileName и перекодировать его посимвольно
в соответствии с заданным шифром, результат записать в outFileName.
Шифр задается маcсивом char[] code, где каждому символу symbol оригинального файла
соответствует символ code[(int)symbol] выходного файла.

В случае ошибок, в файл с именем logName вывести название ошибки через метод класса Exception - getMessage()
*/

public class Coder {

    public static void codeFile1(String inFileName, String outFileName, char[] code, String logName) {
        try (
                FileInputStream fIn = new FileInputStream(inFileName);
                BufferedInputStream bIn = new BufferedInputStream(fIn);
                FileOutputStream fOut = new FileOutputStream(inFileName);
                BufferedOutputStream bOut = new BufferedOutputStream(fOut);
        ) {
            int i;
            while ((i = bIn.read()) != -1) {
                bOut.write(code[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        try {
            FileInputStream fIn = new FileInputStream(inFileName);
            BufferedInputStream bIn = new BufferedInputStream(fIn);
            FileOutputStream fOut = new FileOutputStream(inFileName);
            BufferedOutputStream bOut = new BufferedOutputStream(fOut);
            int i;
            while ((i = bIn.read()) != -1) {
                bOut.write(code[i]);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
