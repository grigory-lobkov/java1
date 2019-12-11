package ru.progwards.java1.lessons.io2;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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

    // заменяем с помощью переводчика с разбивкой на слова
    public static void censorFile1(String inoutFileName, String[] obscene) { // почему-то тестер не принял вариант с переводчиком и это было бы правильно для utf-8. Минус - пересоздание файла
        int obLen = obscene.length;
        String[] stars = new String[obLen];
        for (int i = 0; i < obLen; i++) {
            stars[i] = repeatStr("*", obscene[i].length());
        }
        Translator tr = new Translator(obscene, stars);

        String tmpFileName = inoutFileName + ".tmp";
        String lineSeparator = System.getProperty("line.separator");

        try (FileReader r = new FileReader(inoutFileName); Scanner s = new Scanner(r); FileWriter w = new FileWriter(tmpFileName)) {
            while (s.hasNext()) {
                String str = s.nextLine();
                str = tr.translate(str + lineSeparator);
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


    private static Word rafGetNextWord(RandomAccessFile raf, Word prevWord) throws IOException {
        long pos = prevWord.endPos + 1;
        raf.seek(pos);
        long len = raf.length();
        int c = 0;

        while (pos < len) {
            c = raf.read();
            if (Character.isLetter(c)) break;
            pos++;
        }
        if(pos == len) return null;
        Word rslt = new Word(pos);

        StringBuilder sb = new StringBuilder(16);
        sb.append((char)c);
        while (pos < len) {
            c = raf.read();
            if (!Character.isLetter(c)) break;
            sb.append((char)c);
            pos++;
        }
        rslt.endPos = pos;
        rslt.word = sb.toString();
        return rslt;
    }
    private static void rafEraseWord(RandomAccessFile raf, Word word, int charCode) throws IOException {
        long pos = word.startPos;
        raf.seek(pos);
        while (pos <= word.endPos) {
            raf.write(charCode);
            pos++;
        }
    }
    // заменяем не переписывая файл, с разбивкой на слова
    public static void censorFile2(String inoutFileName, String[] obscene) { //
        Set<String> obs = new HashSet<String>(Arrays.asList(obscene));

        try {
            RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw"); // вынес из try-with-resources по совету Арсения
            try {
                Word word = new Word(0, -1, "");
                while (word != null) {
                    word = rafGetNextWord(raf, word);
                    if (word != null) {
                        if (obs.contains(word.word)) {
                            rafEraseWord(raf, word, '*');
                        }
                        //System.out.println(word);
                    }
                }
            } catch (Exception e) {
                throw new CensorException(e.getMessage(), inoutFileName);
            }
            raf.close();
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
    }
    // Заменяем последовательности символов, не разбивая на слова
    public static void censorFile(String inoutFileName, String[] obscene) { // оказывается, надо искать не слова, а последовательность символов

        if (inoutFileName == null || inoutFileName.compareTo("") == 0) throw new CensorException("Имя файла передавать обязатльно", inoutFileName);
        if (obscene == null) throw new CensorException("Последовательность слов передавать обязательно", inoutFileName);

        int obLen;
        String[] stars;
        try {
            obLen = obscene.length;
            stars = new String[obLen];
            for (int i = 0; i < obLen; i++) {
                //stars[i] = "*".repeat(obscene[i].length()); // ваш тестер не компмилит
                stars[i] = repeatStr("*", obscene[i].length());
            }
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }

        String tmpFileName = inoutFileName + ".tmp";
        String lineSeparator = System.getProperty("line.separator");
        boolean firstLine = true;

        try (FileReader r = new FileReader(inoutFileName); Scanner s = new Scanner(r); FileWriter w = new FileWriter(tmpFileName)) {
            while (s.hasNext()) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    w.write(lineSeparator);
                }
                String str = s.nextLine();
                for (int i = obLen - 1; i >= 0; i--) {
                    str = str.replace(obscene[i], stars[i]);
                }
                w.write(str);
            }
        } catch (IOException e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
        try {
            File f = new File(inoutFileName);
            f.delete();
            File n = new File(tmpFileName);
            n.renameTo(f);
        } catch (Exception e) {
            throw new CensorException(e.getMessage(), inoutFileName);
        }
    }

    public static void main(String[] args) {
        //censorFile("src/ru/progwards/java1/lessons/io2/Censor.txt", new String[]{"java", "Oracle", "Sun", "Microsystems"});
        censorFile("src/ru/progwards/java1/lessons/io2/Censor1.txt", new String[]{"puck","бять"});
    }

}
class Word {
    long startPos;
    long endPos;
    String word;

    Word(long startPos) {
        this.startPos = startPos;
    }
    Word(long startPos, long endPos, String word) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "startPos=" + startPos +
                ", endPos=" + endPos +
                ", word='" + word + '\'' +
                '}';
    }
}
