package ru.progwards.java1.lessons.io2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Censor {
    public static String repeatStr(String value, int count) {
        StringBuilder s = new StringBuilder(count);
        for (int i=count; i>0; i--) s.append(value);
        return s.toString();
    }
/*
Создать статический метод public static void censorFile(String inoutFileName, String[] obscene),
в котором прочитать файл inoutFileName и заменить слова, содержащиеся в String[] obscene на '*',
соответствующие количеству букв в слове, изменения записать в исходный файл. В случае
возникновения ошибки, выбросить свое собственное исключение CensorException в котором
сохранить - строку, полученную у оригинального exception через метод getMessage() и имя файла,
в котором возникла ошибка. В классе перекрыть метод toString(), вернув <имя файла>:<строка ошибки>.
Класс CensorException разместить в классе Censor

Например файл содержит:
Java — строго типизированный объектно-ориентированный язык программирования, разработанный компанией Sun Microsystems (в последующем приобретённой компанией Oracle).
obscene = {"Java", "Oracle", "Sun", "Microsystems"}

Должен выдать результат:
**** — строго типизированный объектно-ориентированный язык программирования, разработанный компанией *** ************ (в последующем приобретённой компанией ******).
*/

    static class CensorException extends RuntimeException {

        String errName;
        String fileName;

        CensorException(String errName, String fileName) {
            this.errName = errName;
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return fileName + ":" + errName;
        }
    }

    public static void censorFile(String inoutFileName, String[] obscene) {
        int obLen = obscene.length;
        String[] stars = new String[obLen];
        for (int i = 0; i < obLen; i++) {
            //stars[i] = "*".repeat(obscene[i].length());
            stars[i] = repeatStr("*", obscene[i].length());
        }
        //System.out.println(Arrays.toString(obscene));
        //System.out.println(Arrays.toString(stars));
        Translator tr = new Translator(obscene, stars);

        String tmpFileName = inoutFileName + ".tmp";
        String lineSeparator = System.getProperty("line.separator");

        try (FileReader r = new FileReader(inoutFileName); Scanner s = new Scanner(r); FileWriter w = new FileWriter(tmpFileName)) {
            while (s.hasNext()) {
                String str = s.nextLine();
                //System.out.println(str);
                str = tr.translate(str);
                //System.out.println(str);
                //w.write(str + lineSeparator);
                w.write(str);
            }
        } catch (IOException e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
        File f = new File(inoutFileName);
        f.delete();
        File n = new File(tmpFileName);
        n.renameTo(f);
    }

    public static void main(String[] args) {
        //censorFile("src/ru/progwards/java1/lessons/io2/Censor.txt", new String[]{"java", "Oracle", "Sun", "Microsystems"});
        censorFile("src/ru/progwards/java1/lessons/io2/Censor1.txt", new String[]{"puck"});
    }

}
