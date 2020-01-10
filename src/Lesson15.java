import java.util.Calendar;
import java.util.Date;

public class Lesson15 {

    //Напишите метод с сигнатурой Date createDate(), который возвращает дату - 28 февраля 1986, 0 часов, 0 минут, 0 секунд, 0 миллисекунд
    static Date createDate() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1986,1,28);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(createDate());
    }
}
