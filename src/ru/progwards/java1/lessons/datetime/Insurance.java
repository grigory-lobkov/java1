package ru.progwards.java1.lessons.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/*
Класс должен проверять валидность страховок, например для выезжающих за рубеж.
Каждая страховка имеет дату-время начала, и продолжительность.
*/

enum FormatStyle {SHORT, LONG, FULL}; // стиль формата даты-времени

public class Insurance {

    ZonedDateTime start; // дата-время начала действия страховки
    Duration valid; // продолжительность действия

    // установить дату-время начала действия страховки
    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    //установить дату-время начала действия страховки,
    //SHORT соответствует ISO_LOCAL_DATE  (часовой пояс по умолчанию)
    //LONG  - ISO_LOCAL_DATE_TIME  (часовой пояс по умолчанию)
    //FULL - ISO_ZONED_DATE_TIME
    public void setStart(String strStart, FormatStyle style) {
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
        start = ZonedDateTime.parse(strStart, formatter);
    }

    // установить продолжительность действия страховки
    public void setDuration(Duration duration) {
        valid = duration;
    }

    // установить продолжительность действия страховки, задав дату-время окончания
    public void setDuration(ZonedDateTime stop) {
        valid = Duration.between(start, stop);
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
        return "Insurance " + start + " " + valid;
    }

}