package ru.progwards.java1.lessons.compare_if_cycles;

public class TriangleInfo {

    // maximum int less than sum of two others
    public static boolean isTriangle(int a, int b, int c) {
        if (a > b) {
            if (a > c) return a < b + c;
        } else {
            if (b > c) return b < a + c;
        }
        return c < a + b;
    }

    // square int equals to sum of two other squares
    public static boolean isRightTriangle(int a, int b, int c) {
        if (a > b) {
            if (a > c) return a * a == b * b + c * c;
        } else {
            if (b > c) return b * b == a * a + c * c;
        }
        return c * c == a * a + b * b;
    }

    // if two of int's are equal
    public static boolean isIsoscelesTriangle(int a, int b, int c) {
        return a == b || b == c || a == c;
    }

}
