package ru.progwards.java1.lessons.trial;

public class Season {
/*Задача 1. Класс Season
Реализовать класс Season, выдающий строковое название времени года по номеру месяца
1.1 Реализовать перечисляемый тип, задающий язык, английский и русский
enum Language {EN, RU};
1.1 Реализовать метод
public void setLang(Language lang), которая устанавливает язык, в котором надо выдать время года.
1.2 Реализовать функцию
public String toString(int month), выдаст строковое название времени года, в соответствии с заданным языком.
Для EN это {winter, spring, summer, autumn}, для некорректного month выдать "not valid"
Для RU это {зима, весна, лето, осень},  для некорректного month выдать "не определено"
*/
    enum Language {EN, RU}
    String[] enSeason = {
            "winter",
            "spring",
            "summer",
            "autumn",
            "not valid"
    };
    String[] ruSeason = {
            "зима",
            "весна",
            "лето",
            "осень",
            "не определено"
    };

    Language lang;

    public void setLang(Language lang) {
        this.lang = lang;
    }

    /*public Integer toInt(int month) {
        //returns season number: 0=winter, 3=autumn
        if (month > 12 || month < 1) return 4;
        return (month / 3) % 4;
    }*/

    public String toString(int month) {
        //returns season name
        int s = (month > 12 || month < 1) ? 4 : (month / 3) % 4;
        if (lang == null) return "Language is not set! Use setLang first!";
        switch(lang) {
            case EN: return enSeason[s];
            case RU: return ruSeason[s];
            default: return "toString Not implemented yet.";
        }
    }
}
