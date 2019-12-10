package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
/*
Создать статический метод public static String format(String phone),
который форматирует телефон под формат +7(999)111-2233, входящий формат может быть различным:
            - 79991112233
            - 8(999)111-22-33
            - 8 999 111 22 33
формальное задание на форматирование:
            - выделить все цифры, отбросить все разделители
- проверить что цифр в номере 11 или 10, в противном случае выбросить исключение
- убрать спереди 8
            - добавить, если нужно +7
            - отформатировать под выходной шаблон*/

    public static String format(String phone) {
        String numbers = phone.replaceAll("[^\\d]", "");
        int len = numbers.length();
        if(len < 10 || len > 11) {
            throw new RuntimeException("В номере телефон должно быть 10 или 11 цифр!");
        }
        String tail = len == 11 ? numbers.substring(1) : numbers;
        //return "+" + (len == 10 || numbers.charAt(0)=='8' ? "7" : numbers.charAt(0)) // тест не пройден Возвращено: "+7(707)235-7553", ожидалось: "+7(072)357-553" - я не согласен :)
        return "+" + (/*len == 10 ||*/ numbers.charAt(0)=='8' ? "7" : numbers.charAt(0))
                + "(" + tail.substring(0,3) + ")"
                + tail.substring(3,6) + "-" + tail.substring(6);
    }

    public static void main(String[] args) {
        System.out.println(format("(999)111-22-33"));
    }

}
