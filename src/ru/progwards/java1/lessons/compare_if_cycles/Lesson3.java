package ru.progwards.java1.lessons.compare_if_cycles;

public class Lesson3 {

    static int addAsStrings(int n1, int n2) {
        return Integer.parseInt((((Integer)n1).toString().concat(((Integer)n2).toString())));
    }

    public static void main(String[] args) {
        //System.out.println(addAsStrings(12, 34));
        //System.out.println(textGrade(45));
        System.out.println(factorial(3));
        System.out.println((int)765.567);
    }

/*    0 - не оценено
1..20 - очень плохо
21..40 - плохо
41..60 - удовлетворительно
61..80 - хорошо
81..100 - отлично
    в остальных случаях - не определено

    Сигнатура метода static String textGrade(int grade)

    Например, textGrade(45) должна вернуть "удовлетворительно"*/

    static String textGrade(int grade) {
        if (grade < 0 || grade > 100) return "не определено";
        if (grade == 0) return "не оценено";
        if (grade <= 20) return "очень плохо";
        if (grade <= 40) return "плохо";
        if (grade <= 60) return "удовлетворительно";
        if (grade <= 80) return "хорошо";
        return "отлично";
    }

    static int factorial(int n) {
        int r = 1;
        for (int i = n; i > 1; i--)
            r *= i;
        return r;
    }

}
