import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

class Person {
    public String name;
    public Date birth;
    public double salary;

    Person(String name, Date birth, double salary) {
        this.name = name;
        this.birth = birth;
        this.salary = salary;
    }
}

public class Lesson17 {

    /*
    Реализуйте метод сигнатурой void printPersons(Person[] persons),
    который выводит на консоль содержимое массива persons столбиком,
    одна строка - один человек, причем, каждая строка имеет вид:
    |Вася      |01/01/1970|200 000,00|

    1. Разделитель значений - "|"
    2. Порядок вывода значений: name, birth, salary
    3. Для имени name, ширина 10 символов, прижим влево
    4. Для дня рождения birth формат "дд/мм/гггг"
    5. Для зарплаты salary ширина 10 точность 2, и задать разделитель тысяч, в русской раскладке
    */
    void printPersons(Person... persons) {
        for (Person p : persons) {
            System.out.format(new Locale("ru"), "|%-10s|%2$td/%2$tm/%2$tY|%3$,10.2f|\n", p.name, p.birth, p.salary);
        }
    }

    public static void main(String[] args) {
        String txt =
                "StringTokenizer - этот класс, " +
                        "нам строку разобьёт на раз.";
        StringTokenizer tokenizer = new StringTokenizer(txt, " .,");
        while (tokenizer.hasMoreTokens())
            System.out.print(tokenizer.nextToken());
        System.out.println();
        System.out.println(new Lesson17().swapWords(txt));
        System.out.println(new Lesson17().swapWords("Слово - серебро, молчание - золото!"));
        System.out.format("|%04d|%#x|%2.1f|", 2, 15, 3.25);
        System.out.println();
        new Lesson17().printPersons(new Person("Вася", new Date(0), 200000.001),
                new Person("Петя", new Date(199234885678L), 55331.123),
                new Person("Коля", new Date(598812345678L), 99999.999));
    }

/*
Создайте метод с сигнатурой String swapWords(String sentance),
который возвращает слова фразы из sentence через одно, начиная с первого,
через пробел в виде строки. Разделители " .,-!\n"

Например, слова фразы "Слово - серебро, молчание - золото!"
должны быть преобразованы в  "серебро Слово золото молчание"
*/

    String swapWords1(String sentance) {
        boolean firstWord = true;
        int i = 0;
        StringBuilder sb = new StringBuilder(sentance.length());
        StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
        while (tokenizer.hasMoreTokens()) {
            if (i++ % 2 == 1) {
                if (!firstWord) sb.append(" ");
                else firstWord = false;
                sb.append(tokenizer.nextToken());
            } else tokenizer.nextToken();
        }
        return sb.toString();
    }

    String swapWords(String sentance) {
        boolean firstWord = true;
        int i = 0;
        String save = "";
        StringBuilder sb = new StringBuilder(sentance.length());
        StringTokenizer tokenizer = new StringTokenizer(sentance, " .,-!\n");
        while (tokenizer.hasMoreTokens()) {
            if (i++ % 2 == 1) {
                if (!firstWord) sb.append(" ");
                else firstWord = false;
                sb.append(tokenizer.nextToken() + " " + save);
                save = "";
            } else save = tokenizer.nextToken();
        }
        if (!save.isEmpty()) sb.append(" " + save);
        return sb.toString();
    }

}
