package ru.progwards.java1.lessons.basics;

public class ReverseDigits {

    // Mathematically reverse number
    public static int reverseDigits(int number){
        int rev = 0;
        int n = number;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        return rev;
    }

    // Tests
    public static void main(String[] args) {
        System.out.println(reverseDigits(-12345));
    }

}

/* Output:
-54321
*/