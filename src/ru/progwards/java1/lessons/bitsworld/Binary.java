package ru.progwards.java1.lessons.bitsworld;

/*
3.1 Реализовать конструктор
Binary(int num)

3.2 реализовать метод
public String toString()
который возвращает двоичное представление целого положительного числа используя только битовые операции
*/

public class Binary {

    int num;

    Binary(int num){
        this.num = num;
    }

    // возвращает двоичное представление целого числа используя только битовые операции
    public String toString() {
        int n = num & 0x7F_FF_FF_FF;
        String r = num < 0 ? "1" : "0";
        for(int i = 1; i < 32; i++) {
            r += (n & 0x40_00_00_00) == 0x40_00_00_00 ? "1" : "0";
            //if(i % 4 == 3) r += " ";
            n <<= 1;
        }
        return r;
    }

    public static void main(String[] args) {
        int t = 1;
        System.out.println(Integer.toBinaryString(t) + "=" + new Binary(t));
        t = -1;
        System.out.println(Integer.toBinaryString(t) + "=" + new Binary(t));
        t = 0x55;
        System.out.println(Integer.toBinaryString(t) + "=" + new Binary(t));
        t = 0x55_00_00_00;
        System.out.println(Integer.toBinaryString(t) + "=" + new Binary(t));
    }

}

/*
1=00000000000000000000000000000001
11111111111111111111111111111111=
11111111111111111111111111111111
1010101=00000000000000000000000001010101
1010101000000000000000000000000=
01010101000000000000000000000000
*/