package ru.progwards.java1.lessons.io1;
import java.io.*;

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
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }

    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        FileInputStream fIn = null;
        BufferedInputStream bIn = null;
        FileOutputStream fOut = null;
        BufferedOutputStream bOut = null;
        try {
            fIn = new FileInputStream(inFileName);
            bIn = new BufferedInputStream(fIn);
            fOut = new FileOutputStream(outFileName);
            bOut = new BufferedOutputStream(fOut);
            int i;
            while ((i = bIn.read()) != -1) {
                bOut.write(code[i]);
            }
        } catch (Throwable e) {
            FileWriter fEOut = null;
            BufferedWriter bEOut = null;
            try {
                fEOut = new FileWriter(logName, true);
                bEOut = new BufferedWriter(fEOut);
                bEOut.write(e.getMessage());
            } catch (Throwable e2) {
            } finally {
                try {
                    if (bEOut != null) bEOut.close();
                    if (fEOut != null) fEOut.close();
                } catch (Throwable e3) {
                }
            }
        } finally {
            try {
                if (bOut != null) bOut.close();
                if (fOut != null) fOut.close();
                if (bIn != null) bIn.close();
                if (fIn != null) fIn.close();
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        char[] code = new char[256];
        //for (int i = 0; i < 256; i++) code[i] = (char)i;
        for (int i = 0; i < 256; i++) code[i] = (char) (Character.isDigit((char) i) ? i + 1 : i);
        codeFile("src\\ru\\progwards\\java1\\lessons\\io1\\Coder1.java",
                "src\\ru\\progwards\\java1\\lessons\\io1\\Coder.txt",
                code,
                "src\\ru\\progwards\\java1\\lessons\\io1\\Coder.log");
    }

}
