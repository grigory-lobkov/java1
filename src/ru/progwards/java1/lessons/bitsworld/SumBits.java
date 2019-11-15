package ru.progwards.java1.lessons.bitsworld;

/*
Реализовать функцию public static int sumBits(byte value), которая суммирует все биты параметра byte value.

Например, для двоичного числа 0100101 функция должна вернуть 3.

Подсказки:

используйте & с числом 1 для того, что бы оставить только один, правый значащий бит
используйте сдвиг вправо для того, что бы проверить следующий бит
*/
public class SumBits {

    // суммирует все биты параметра byte value
    public static int sumBits(byte value) {
        byte r = 0;
        if (value < 0) {
            r++;
            value &= 0b0111_1111;
        }
        while (value != 0) {
            r += value & 0b1;
            value >>= 1;
        }
        // без условия, но на 1 операцию больше в цикле:
        /*while (value != 0) {
            r += (value & 0xFF) >>> 7;
            value <<= 1;
        }*/
        return r;
    }

    public static void main(String[] args) {
        System.out.println("3=" + sumBits((byte)0b0010_0110));
        System.out.println("4=" + sumBits((byte)0b1010_0110));
        System.out.println("1=" + sumBits((byte)0b1000_0000));
        System.out.println("0=" + sumBits((byte)0b0000_0000));
    }
}
