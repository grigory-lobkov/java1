package ru.progwards.java1.lessons.abstractnum;

/*
Реализовать класс IntNumber, который содержит
3.8 конструктор
public IntNumber(int number), который будет сохранять в классе значение целого числа.
3.9 переопределение метода
public Number mul(Number n1, Number n1), который будет вычислять произведение n1 на n2 в целых числах.
3.10 переопределение метода
public Number div(Number n1, Number n1), который будет вычислять частное от деления n1 на n2 в целых числах.
3.11 переопределение метода
public Number newNumber(String strNum), который будет возвращать новый экземпляр класса IntNumber
со значением параметра strNum, приведенного к типу int.
Внимание! Реально будет передаваться строковое значение в виде и double и int, например 3.14.... или 4, 3.
Надо будет правильно распарсить строку и привести значение к нужному типу.
*/

public class IntNumber extends Number {

    public IntNumber(int number) {
        super("" + number, NumType.INT);
        //numType = NumType.INT;
    }

    //@Override
    public static Number mul(Number n1, Number n2) {
        // debug
        /*System.out.print(n1 + "*" + n2 + "=");
        int i1 = Integer.parseInt(n1.toString());
        int i2 = Integer.parseInt(n2.toString());
        System.out.print(i1 + "*" + i2 + "=");
        int result = i1 * i2;
        System.out.println(result);
        return new IntNumber(result);*/
        // release
        return new IntNumber(Integer.parseInt(n1.toString()) * Integer.parseInt(n2.toString()));
    }

    //@Override
    public static Number div(Number n1, Number n2) {
        //System.out.println(n1 + "/" + n2);
        return new IntNumber(Integer.parseInt(n1.toString()) / Integer.parseInt(n2.toString()));
    }

    // Этот метод невозможно переопределить с "int strNum"
    // если не добавлять static, то невозможно будет обратиться через IntNumber.newNumber, и вернуть IntNumber
    //@Override
    public static Number newNumber(String strNum) {
        return newNumber(strNum);
    }
    //ERROR: Тест "Класс IntNumber, метод newNumber(String strNum)" не пройден.
    //Метод newNumber(String strNum) не возвращает новый экземпляр класса IntNumber со значением параметра strNum, в виде строкового значения double

}
