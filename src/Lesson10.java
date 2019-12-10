import java.io.IOException;

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

    public static void main(String[] args) {
        System.out.println(new Lesson10().invertWords("Буря мглою небо кроет"));
    }
}
