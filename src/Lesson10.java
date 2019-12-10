import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Lesson10 {

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
Реализуйте метод с сигнатурой public String invertWords(String sentence),
который переставляет слова, в полученной фразе. В качестве исходного
разделителя использовать пробел. В качестве соединительного точку.

Например,
invertWords("Буря мглою небо кроет") должен вернуть
"кроет.небо.мглою.Буря"
*/

    public String invertWords(String sentence) {
        String[] splitted = sentence.split(" ");
        StringBuilder result = new StringBuilder(100);
        for (int i = splitted.length - 1; i >= 0; i--) {
            result.append(splitted[i]);
            if (i>0) result.append('.');
        }
        return result.toString();
    }

/*
Реализовать метод с сигнатурой public String setStars(String filename)
который читает файл filename и меняет в нем каждый 10-йбайт на символ *,
при этом конкатенируя оригинальный символ в строку ответа.

В случае ошибки выбросить исключение IOException со строкой сообщения:равной имени класса оригинального сообщения

Например,при содержимом файла:

0123456789012345678A012345678B01

новое содержимое должно быть

012345678*012345678*012345678*01

и метод должен вернуть "9AB"
*/

    String setStars(String filename) {
        try(RandomAccessFile raf = new RandomAccessFile(filename,"rw")) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Lesson10().invertWords("Буря мглою небо кроет"));
    }
}
