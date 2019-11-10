package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleSimpleInfo {

    // Maximum of tree int's
    public static int maxSide(int a, int b, int c) {
        if (a > b) {
            if (a > c) return a;
        } else {
            if (b > c) return b;
        }
        return c;
    }

    // Minimum of tree int's
    public static int minSide(int a, int b, int c) {
        if (a < b) {
            if (a < c) return a;
        } else {
            if (b < c) return b;
        }
        return c;
    }

    // Is three int's is equal
    public static boolean isEquilateralTriangle(int a, int b, int c) {
        return a == b && b == c;
    }

}