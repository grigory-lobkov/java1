package ru.progwards.java1.lessons.basics;

public class Astronomy {

    final static double pi = 3.14;
    final static double earthR = 6_371.2;
    final static double mercuryR = 2_439.7;
    final static double jupiterR = 71_492.0;

    // Sphere surface area S = 4πR2
    public static Double sphereSquare(Double r){
        return r * r * pi * 4.0;
    }
    // Surface area of Earth
    public static Double earthSquare() {
        return sphereSquare(earthR);
    }
    // Surface area of Earth
    public static Double mercurySquare() {
        return sphereSquare(mercuryR);
    }
    // Surface area of Earth
    public static Double jupiterSquare() {
        return sphereSquare(jupiterR);
    }
    // Surface area ratio, using previous
    public static Double earthVsMercury() {
        return earthSquare() / mercurySquare();
    }
    // Surface area ratio, fast
    public static Double earthVsMercury2() {
        return earthR * earthR / mercuryR / mercuryR;
    }
    // Surface area ratio, using previous
    public static Double earthVsJupiter() {
        return earthSquare() / jupiterSquare();
    }
    // Surface area ratio, fast
    public static Double earthVsJupiter2() {
        return earthR * earthR / jupiterR / jupiterR;
    }

    // Tests
    public static void main(String[] args) {
        System.out.println("Area of sphere R=10: " + sphereSquare(10D));
        System.out.println("Surface area of Earth: " + earthSquare());
        System.out.println("Surface area of Jupiter: " + jupiterSquare());
        System.out.println("Surface area of Mercury: " + mercurySquare());
        System.out.println("Ratio of surface of Earth to Mercury: " + earthVsMercury());
        System.out.println("Ratio of surface of Earth to Mercury(2): " + earthVsMercury2());
        System.out.println("Ratio of surface of Earth to Jupiter: " + earthVsJupiter());
        System.out.println("Ratio of surface of Earth to Jupiter(2): " + earthVsJupiter2());
    }

}

/* Output:
Area of sphere R=10: 1256.0
Surface area of Earth: 5.098378993664E8
Surface area of Jupiter: 6.4195492163840004E10
Surface area of Mercury: 7.475882929039998E7
Ratio of surface of Earth to Mercury: 6.819768369913063
Ratio of surface of Earth to Mercury(2): 6.819768369913061
Ratio of surface of Earth to Jupiter: 0.007941957950336911
Ratio of surface of Earth to Jupiter(2): 0.007941957950336911
*/