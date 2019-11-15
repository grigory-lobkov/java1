package ru.progwards.java1.lessons.bitsworld;

public class Lesson5 {
/*Написать программный код, который возвращает младший (нулевой) бит переменной byte value.
Ответ нужно поместить в переменную int result.

Таким образом, если младший бит параметра value равен 0, то result должен быть равен 0.
Если младший бит равен 1, то и result должен быть равен 1.
Подсказка:

при реализации этой функции удобно использовать битовую операцию &*/

    public static void main(String[] args) {
        byte value = 23;
        int result = value & 1;
        System.out.println(result);

        System.out.println(intToGrade(3));
        System.out.println(intToGrade(0));
    }

/*  Создайте метод, возвращающий значение enum Grade по числовой оценке.
    Сигнатура метода static Grade intToGrade(int grade)
    Соответствие оценок
1 - VERYBAD
2 - BAD
3 - SATISFACTORILY
4 - GOOD
5 - EXCELLENT
все остальное NOTDEFINED
    Например, intToGrade(4) должно вернуть GOOD*/
    enum Grade {VERYBAD, BAD, SATISFACTORILY, GOOD, EXCELLENT, NOTDEFINED}
    static Grade intToGrade(int grade) {
        switch (grade) {
            case 1: return Grade.VERYBAD;
            case 2: return Grade.BAD;
            case 3: return Grade.SATISFACTORILY;
            case 4: return Grade.GOOD;
            case 5: return Grade.EXCELLENT;
            default: return Grade.NOTDEFINED;
        }
    }

}
