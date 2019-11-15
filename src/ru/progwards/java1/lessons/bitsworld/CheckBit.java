package ru.progwards.java1.lessons.bitsworld;

/*
Реализовать функцию public static int checkBit(byte value, int bitNumber) которая возвращает
значение бита с порядковым номером bitNumber от параметра value. При этом следует помнить,
что нумерация начинается с нуля и нулевой бит является младшим.

Подсказки:
при реализации этой функции следует использовать опыт, полученный при решении предыдущей задачи;
кроме того, удобно использовать битовую операцию сдвиг вправо >>.
*/

public class CheckBit {

    // возвращает значение бита с порядковым номером bitNumber от параметра value
    public static int checkBit(byte value, int bitNumber) {
        return value >>> bitNumber & 1;
    }

    public static void main(String[] args) {
        System.out.println("0=" + checkBit((byte)0b0000_0000, 7));
        System.out.println("1=" + checkBit((byte)0b1000_0000, 7));
        System.out.println("0=" + checkBit((byte)0b0000_0000, 4));
        System.out.println("1=" + checkBit((byte)0b0001_0000, 4));
        System.out.println("0=" + checkBit((byte)0b1000_0000, 4));
        System.out.println("1=" + checkBit((byte)0b1001_0000, 4));
    }

}
