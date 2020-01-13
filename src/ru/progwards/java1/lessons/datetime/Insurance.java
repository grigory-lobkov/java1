package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/*
Класс должен проверять валидность страховок, например для выезжающих за рубеж.
Каждая страховка имеет дату-время начала, и продолжительность.
*/

public class Insurance {

    enum FormatStyle {SHORT, LONG, FULL} // стиль формата даты-времени // перенесено сюда для тестера

    ZonedDateTime start; // дата-время начала действия страховки
    Duration valid; // продолжительность действия

    // установить дату-время начала действия страховки
    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    Insurance() {
    }

    Insurance(ZonedDateTime start) {
        this.start = start;
    }

    Insurance(String strStart, FormatStyle style) {
        start = styledStringToZDT(strStart, style);
    }

    // найти дату-время по строке с заданным форматом
    private ZonedDateTime styledStringToZDT(String strStart, FormatStyle style) {
        DateTimeFormatter formatter;
        switch (style) {
            case SHORT:
                formatter = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneId.systemDefault());
                break;
            case LONG:
                formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(ZoneId.systemDefault());
                break;
            default:
                formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        }
        return ZonedDateTime.parse(strStart, formatter);
    }

    // установить продолжительность действия страховки
    public void setDuration(Duration duration) {
        valid = duration;
    }

    // установить продолжительность действия страховки, задав дату-время окончания
    public void setDuration(ZonedDateTime stop) {
        valid = Duration.between(start, stop);
    }

    public void setDuration(String strStart, FormatStyle style) {
        valid = Duration.between(start, styledStringToZDT(strStart, style));
    }

    // установить продолжительность действия страховки, задав целыми числами количество месяцев, дней и часов
    public void setDuration(int mons, int days, int hrs) {
        ZonedDateTime ldt = start == null ? ZonedDateTime.now() : start;
        ldt = ldt.plusMonths(mons).plusDays(days).plusHours(hrs);
        valid = Duration.between(start, ldt);
    }

    // проверить валидна ли страховка на указанную дату-время
    public boolean checkValid(ZonedDateTime dateTime) {
        ZonedDateTime end = start.plusHours(valid.toHours());
        return dateTime.isAfter(start) && dateTime.isBefore(end);
    }

    // вернуть строку формата "Insurance "+start+" "+duration
    @Override
    public String toString() {
        return "Insurance issued on " + start + " is " + (checkValid(ZonedDateTime.now()) ? "" : "not ") + "valid";
    }

}