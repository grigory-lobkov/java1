import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Lesson15 {

    //Напишите метод с сигнатурой Date createDate(), который возвращает дату - 28 февраля 1986, 0 часов, 0 минут, 0 секунд, 0 миллисекунд
    static Date createDate() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1986,1,28);
        return cal.getTime();
    }

    public static void main(String[] args) {
        /*System.out.println(createDate());
        LocalDateTime ldt2= LocalDateTime.of(2019, 05, 05, 22, 24);
        System.out.println(ldt2);
        ZoneId zid1 = ZoneId.of("Europe/Moscow");
        System.out.println(zid1.getRules().getOffset(Instant.now()));
        System.out.println(createInstant());*/
        //System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
        System.out.println(parseZDT("01.01.2020 16:27:14.444 +0300 Moscow Standard Time"));
    }

    //Создайте метод с сигнатурой Instant createInstant(), который возвращает Instant,
    // соответствующий 1 января 2020 года, 15 часов и одна наносекунда по Московскому времени
    static Instant createInstant() {
        java.time.LocalDateTime ldt1 = java.time.LocalDateTime.of(2020,1,1,15,0,0,1);//.plusHours(3);
        //соответствующий 1 января 2020 года, 15 часов и одна наносекунда по Московскому времени
        return ldt1.toInstant(java.time.ZoneOffset.of("+03:00:00"));
    }

    //Напишите метод, с сигнатурой ZonedDateTime parseZDT(String str),
    // который возвращает ZonedDateTime, парся строку вида "01.01.2020 16:27:14.444 +0300 Moscow Standard Time"
    static ZonedDateTime parseZDT(String str) {
        Locale l = Locale.forLanguageTag("EN");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD.MM.yyyy HH:mm:ss.SSS Z zzzz", l);
        return ZonedDateTime.parse(str, dtf.withZone(ZoneId.of("Europe/Moscow")));
    }
}
