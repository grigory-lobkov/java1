package ru.progwards.java1.lessons.helloworld;

public class Task1 {

    public static void main(String[] args) {
        String str1 = "Хорошо идут дела";
        String str2 = "Изучаю Java я!";
        String str3 = " ";

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str1 + str3 + str2);
        //System.out.println(str2 + str2 + str1); //would be shorter, but we need to use "print"
        System.out.print(str2);
        System.out.print(str3);
        System.out.println(str1);
    }

}
/*
Task:
Хорошо идут дела
Изучаю Java я!
Хорошо идут дела Изучаю Java я!
Изучаю Java я! Хорошо идут дела

Result:
Хорошо идут дела
Изучаю Java я!
Хорошо идут дела Изучаю Java я!
Изучаю Java я! Хорошо идут дела
*/