package ru.progwards.java1.lessons.io1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName){
        try (FileInputStream fIn = new FileInputStream(inFileName); FileInputStream fOut = new FileInputStream(inFileName);) {
            while (fIn.) { //https://javarush.ru/groups/posts/2020-vvod-vihvod-v-java-klassih-fileinputstream-fileoutputstream-bufferedinputstream
                String str = s.nextLine();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
