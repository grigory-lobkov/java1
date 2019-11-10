package ru.progwards.java1.lessons.basics;

public class AccuracyDoubleFloat {

    final static double pi = 3.14;
    final static double earthR = 6_371.2;

    public static double volumeBallDouble(double radius) {
        return pi * radius * radius * radius * 4 / 3;
    }

    public static float volumeBallFloat(float radius) {
        return (float)pi * radius * radius * radius * 4 / 3;
    }

    public static double calculateAccuracy(double radius) {
        return volumeBallDouble(radius) - volumeBallFloat((float)radius);
    }

    public static void main(String[] args) {
        System.out.println("Volume of R=1(d): " + volumeBallDouble(1));
        System.out.println("Volume of R=1(f): " + volumeBallFloat(1));
        System.out.println("Volume of Earth(d): " + volumeBallDouble(earthR));
        System.out.println("Volume of Earth(f): " + volumeBallFloat((float)earthR));
        System.out.println("Accuracy of calculations: " + calculateAccuracy(earthR));
    }

}

/* Output:
Volume of R=1(d): 4.1866666666666665
Volume of R=1(f): 4.186667
Volume of Earth(d): 1.082759741481069E12
Volume of Earth(f): 1.08275984E12
Accuracy of calculations: -98262.93103027344
*/