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

    private String strNum = null;
    enum NumType {INT, DOUBLE};
    NumType numType = null;

    Number() {}
    Number(String strNum) {
        this();
        this.strNum = strNum;
        this.numType = getNumType(strNum);
    }
    Number(String strNum, NumType numType) {
        this();
        this.strNum = strNum;
        this.numType = numType;
    }

    @Override
    public String toString() {
        return strNum;
    }

    public static Number mul(Number n1, Number n2) {
        //checkNumType(n1);
        switch (n1.numType) {
            case INT:
                return IntNumber.mul(n1, n2);
            case DOUBLE:
                return DoubleNumber.mul(n1, n2);
        }
        return null;
    }

    public static Number div(Number n1, Number n2) {
        //checkNumType(n1);
        switch (n1.numType) {
            case INT:
                return IntNumber.div(n1, n2);
            case DOUBLE:
                return DoubleNumber.div(n1, n2);
        }
        return null;
    }

    // работает автоопределение типа!
    public static Number newNumber(String strNum) {
        //return new Number(strNum); //it's workable, but creates not IntNumber, DoubleNumber
        Number n = null;
        switch (getNumType(strNum)) {
            case INT:
                n = new IntNumber(Integer.parseInt(strNum));
                break;
            case DOUBLE:
                n = new DoubleNumber(Double.parseDouble(strNum));
                break;
        }
        return n;
    }
    public static void checkNumType(Number n) {
        if (n.numType == null) n.numType = getNumType(n.strNum);
    }
    public static NumType getNumType(String strNum) {
        try {
            Integer.parseInt(strNum);
            return NumType.INT;
        } catch(NumberFormatException e) {
            Double.parseDouble(strNum);
            return NumType.DOUBLE;
        }
    }

}
