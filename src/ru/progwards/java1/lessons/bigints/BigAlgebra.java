package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigAlgebra {
    //Реализовать класс BigAlgebra, содержащий следующие статические методы:

    //        1.1 Метод BigDecimal fastPow(BigDecimal num, int pow)
    //Реализовать алгоритм быстрого возведения в степень pow числа num в BigDecimal,
    // описание алгоритма можно прочитать например в Википедии https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D1%8B_%D0%B1%D1%8B%D1%81%D1%82%D1%80%D0%BE%D0%B3%D0%BE_%D0%B2%D0%BE%D0%B7%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D1%8F_%D0%B2_%D1%81%D1%82%D0%B5%D0%BF%D0%B5%D0%BD%D1%8C
    static BigDecimal fastPow(BigDecimal num, int pow) {
        switch (pow) {
            case 0: return BigDecimal.ONE;
            case 1: return num;
            case 2: return num.multiply(num);
        }
        int pow2 = pow / 2;
        BigDecimal t = num.multiply(num);
        return pow % 2 == 0 ? fastPow(t, pow2) : num.multiply(fastPow(t, pow2));
    }

    private static FiboCache fiboCache;
    static {
        fiboCache = new FiboCache();
    }

    private static class FiboCache {
        int n = Integer.MAX_VALUE; // число, для которого рассчитываем Фибоначчи
        BigInteger f1; // предыдущее число Фибоначчи
        BigInteger f2; // число Фибоначчи
    }

    //1.2 Метод BigInteger fibonacci(int n)
    //Реализовать алгоритм вычисления n-го числа фибоначчи в BigInteger.
    // Последовательность чисел Фибоначчи, это когда каждое последующее число равно сумме двух предыдущих чисел.
    // Первые 2 числа последовательности 1, 1. Итого получаем 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144 и т.д.
    static BigInteger fibonacci(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (fiboCache.n == n) return fiboCache.f2;
        boolean fromCache = fiboCache.n < n;
        BigInteger f1 = fromCache ? fiboCache.f1 : BigInteger.ZERO;
        BigInteger f2 = fromCache ? fiboCache.f2 : BigInteger.ONE;
        int i = fromCache ? fiboCache.n : 1;
        while (i++ < n) {
            BigInteger r = f2.add(f1);
            f1 = f2;
            f2 = r;
        }
        fiboCache.n = n;
        fiboCache.f1 = f1;
        fiboCache.f2 = f2;
        return f2;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 15; i++)
            System.out.print(fibonacci(i) + " ");
    }

}
