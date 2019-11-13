package ru.progwards.java1.lessons.abstractnum;

/*
Реализовать класс Number, содержащий методы
3.5 public Number mul(Number n1, Number n2), который будет возвращать произведение n1 на n2.
Для класса Number - null
3.6 public Number div(Number n1, Number n2), который будет возвращать частное от деления n1 на n2.
Для класса Number - null
3.7 public Number newNumber(String strNum), который будет возвращать новый экземпляр
класса с указанным значением. Для класса Number - null
*/
public class Number {

    String strNum = null;

    Number() {}
    Number(String strNum) {
        this.strNum = strNum;
    }

    @Override
    public String toString() {
        return strNum;
    }

    public Number mul(Number n1, Number n2) {
        return null;
    }

    public Number div(Number n1, Number n2) {
        return null;
    }

    public static Number newNumber(String strNum) {
        return new Number(strNum);
    }

}
