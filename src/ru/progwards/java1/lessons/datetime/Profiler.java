package ru.progwards.java1.lessons.datetime;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Реализовать класс для ручной профилировки производительности программного кода

class StatisticInfo {
    public String sectionName; // имя секции
    public int fullTime; // полное время выполнения секции в миллисекундах
    public int selfTime; // чистое время выполнения секции в миллисекундах
    public int count; // количество вызовов

    private long startMs = System.currentTimeMillis(); // время начала
}

public class Profiler {

    private static ArrayList<StatisticInfo> sections = new ArrayList<StatisticInfo>();

    // войти в профилировочную секцию
    public static enterSection(String name) {

    }

    // выйти из профилировочной секции
    public static exitSection(String name) {

    }

    // получить профилировочную статистику, отсортировать по наименованию секции
    public static List<StatisticInfo> getStatisticInfo() {
        Collections.sort((List)sections);
        return sections;
    }

}
