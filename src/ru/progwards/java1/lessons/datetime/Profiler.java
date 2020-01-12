package ru.progwards.java1.lessons.datetime;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

//Реализовать класс для ручной профилировки производительности программного кода

class StatisticInfo implements Comparable {
    public String sectionName; // имя секции
    public int fullTime = 0; // полное время выполнения секции в миллисекундах
    public int selfTime = 0; // чистое время выполнения секции в миллисекундах
    public int count = 0; // количество вызовов

    private boolean isInside = true; // выполняется ли секция
    private long startMs = System.currentTimeMillis(); // время начала
    private ArrayList<StatisticInfo> runInside = new ArrayList<StatisticInfo>();

    @Override
    public int compareTo(Object o) {
        return this.sectionName.compareTo(((StatisticInfo)o).sectionName);
    }
}

public class Profiler {

    //private static ArrayList<StatisticInfo> sections = new ArrayList<StatisticInfo>();
    static TreeMap<String, StatisticInfo> sections = new TreeMap<String, StatisticInfo>();

    // войти в профилировочную секцию
    public static void enterSection(String name) {

    }

    // выйти из профилировочной секции
    public static void exitSection(String name) {

    }

    // получить профилировочную статистику, отсортировать по наименованию секции
    public static List<StatisticInfo> getStatisticInfo() {
        return new ArrayList<StatisticInfo>(sections);
    }

}
