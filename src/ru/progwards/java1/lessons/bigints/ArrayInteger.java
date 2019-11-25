package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.util.Arrays;

/*
Реализовать класс ArrayInteger - целого числа произвольной длины на массиве byte[] digits;
Каждый элемент массива digits[i] может хранить только цифру, то есть число от 0 до 9.
Например, число 159 должно занять 3 ячейки массива digits[0] = 9; digits[1] = 5; digits[2] = 1;

Реализовать методы:

3.1 ArrayInteger(int n) - инициализирует класс, с максимальной точностью n цифр (размер массива)

3.2 void fromInt(BigDecimal value) - установить свое значение, взяв его из value

3.3 BigDecimal toInt() - привести свое значение к BigDecimal

3.4 boolean add(ArrayInteger num) - сложить 2 числа, не используя BigInteger, а  используя массив digits,
результат поместить в экземпляр ArrayInteger, у которого был вызван метод. При переполнении вернуть false,
при этом само число сбросить в 0
*/
public class ArrayInteger {
    byte[] digits; // массив цифр от 0 до 9
    int signif; // сколько цифр в даное время значимых

    ArrayInteger() {
        int n = 10;
        digits = new byte[n];
        clear(n);
    }
    ArrayInteger(int n) {
        digits = new byte[n];
        clear(n);
    }
    ArrayInteger(String value) {
        this();
        fromString(value);
    }
    private void clear() {
        clear(signif);
    }
    private void clear(int count) {
        for (int i = 0; i < count; i++) digits[i] = 0;
        signif = 0;
    }
    void fromString(String value) {
        char[] s = value.toCharArray();
        int sig = s.length;
        // переведем массив к числовому
        for (int i = sig - 1, k = 0; i >= 0; i--, k++) {
            digits[k] = (byte) (s[i] - '0');
        }
        // обнулим ранее использовавшиеся
        for (int i = sig; i < signif; i++) {
            digits[i] = 0;
        }
        signif = sig;
    }
    void fromInt(BigDecimal value) {
        fromString(value.toString());
    }
    BigDecimal toInt() {
        char[] s = new char[signif];
        for (int i = signif - 1, k = 0; i >= 0; i--, k++) {
            s[i] = (char)((digits[k] + '0') & 0xFF);
        }
        return new BigDecimal(s);
    }

    boolean raiseCalcError(){
        clear(digits.length);
        return false;
    }
    boolean add(ArrayInteger num) {
        int sigMax = num.signif >= signif ? num.signif : signif; // max significant
        int l = digits.length;
        int ln = num.digits.length;
        int p = 0; // перенос
        int r; // результат для цифр
        int sig = 0; // ИНДЕКС последнего значащего
        for (int i = 0; i <= sigMax; i++) {
            r = p;
            if (i < l) r += digits[i];
            if (i < ln) r += num.digits[i];
            if (r > 0) {
                sig = i;
                if (sig >= l) return raiseCalcError();
                digits[sig] = (byte) (r % 10);
            } else {
                if (i < l) digits[i] = 0;
            }
            p = r / 10;
        }
        signif = sig + 1;
        return true;
    }

    @Override
    public String toString() {
        byte[] r = new byte [signif];
        for (int i = signif - 1, k = 0; i >= 0; i--, k++) {
            r[k] = (byte) (digits[i] + '0');
        }
        return new String(r);
    }

    public static void main(String[] args) {
        ArrayInteger a = new ArrayInteger("999");
        ArrayInteger b = new ArrayInteger("99");
        System.out.print(a + " + " + b + " = ");
        a.add(b);
        System.out.println(a);
    }
}
