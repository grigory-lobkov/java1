package ru.progwards.java1.lessons.trial;

public class IsDate {
/*Задача 2. Класс IsDate
3.1 Реализовать класс IsDate, который проверяет корректность заданной даты. Для этого реализовать функцию
public static boolean check(int day, int month, int year),
Дата задана 3-мя числами day, month, year. Следует проверить, существует ли такая дата, например не существует 31-го сентября.
Для февраля реализовать упрощенный алгоритм, считая каждый 4-й год високосным (29 дней), при этом 2000 год был високосным,
2020 тоже будет високосным. В не високосные годы, в феврале 28 дней, */
/*
На 2019 год:
В Январе - 31 день
В Феврале - 28 дней
В Марте - 31 день
В Апреле - 30 дней
В Мае - 31 день
В Июне - 30 дней
В Июле - 31 день
В Августе - 31 день
В Сентябре - 30 дней
В Октябре - 31 день
В Ноябре - 30 дней
В Декабре - 31 день
 */
    public static boolean check(int day, int month, int year) {
        return !((month==4 || month==6 || month==9 || month==11) && day > 30 ||
                month == 2 && (day > 29 || day > 28 && year % 4 > 0) //feb
                || day > 31 || day < 1 || month < 1 || month > 12);
    }
    public static boolean check2(int day, int month, int year) {
        System.out.print("day = " + day + " month = " + month + " year = " + year + ": ");
        return check(day, month, year);
    }
}
