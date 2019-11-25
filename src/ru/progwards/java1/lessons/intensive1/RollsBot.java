package ru.progwards.java1.lessons.intensive1;

import java.util.Scanner;

import org.telegram.telegrambots.ApiContextInitializer;

import ru.progwards.java1.telegrambot.ProgwardsTelegramBot;

public class RollsBot extends ProgwardsTelegramBot {

    static Menu menu;
    static Order order;

    private final String menuStr = "У нас есть роллы, добавки, напитки и десерт";

    static final String groupKey = "group";
    static final String orderKey = "order";
    static final String addressKey = "address";

    private boolean stop = false;

    // Пердложить группы блюд
    // спросить адрес доставки
    String finishCheck(Integer userid) {
        // проверить что все 4 группы блюд в заказе
        // если какой-то группы нет && бот не предлагал
        // то предложить и учтановить ключ, что бы не предлагать 2 раза

        // спросить адрес доставки
        // проверить что заказ не пуст
        if (getUserData(userid, addressKey) == null ) {
            setUserData(userid, addressKey, "*");
            return "Укажите, пожалуйста, адрес доставки";
        }
        stop = true;
        // если заказ пуст выдать другое сообщение
        return "Спасибо за заказ.";
    }

    // сохранить в заказ
    void saveOrderItem(Integer userid, FoundTags tags, String text) {
        Meal meal = menu.getMeal(tags);
        order.add(userid, meal);

        // ключи - order1, order2
        // данные - getLastFound(tags)

        // дополнительно 1
        // проверить связанные покупки
        // если он заказывает картошку, то предложить соус, если не предлагали

    }

    String getOrder(Integer userid) {
        // считать количество

        // в цикле по каждому элементу вывести содержимое
        // ключ orderKey + номер
        return "Выш заказ";
    }

    /**
     * Метод, который возвращает ответ бота
     * @return ответ
     */
    @Override
    public String processMessage(Integer userid, String text) {
        // проверяем, спрашивали ли адрес доставки
        if (getUserData(userid, addressKey) != null && getUserData(userid, addressKey).equals("*")) {
            setUserData(userid, addressKey, text);
            return finishCheck(userid);
        }
        // сброс всех данных для пользователя - полезно для тестирования
        if (text.equals("/reset"))
            cleartUserData(userid);

        // ищем подходящие тэги под запрос из заданных через addTags
        FoundTags tags = checkTags(text);
        // если найдено всего один вариант
        if (foundCount(tags) == 1) {
            if (checkLastFound(tags, "привет"))
                return "Добрый день, любитель вкусной и здоровой пищи!\nЧто вы хотели бы отведать сегодня? " + menuStr;
            if (checkLastFound(tags, "конец"))
                return finishCheck(userid);
            if (checkLastFound(tags, "дурак"))
                return "Не надо ругаться. Я не волшебник, я только учусь";
            if (checkLastFound(tags, "нет"))
                return "Ну нет, так нет";
            if (checkLastFound(tags, "заказ"))
                return getOrder(userid);
            // Добавить связанные предложения, например если он заказывает картошку, то предложить соус,
            // если отказывается - сохранить флажок, что бы бесконечно не предлагать
            // дополнительно 2
            // реализовать отмену позиции заказа

            saveOrderItem(userid, tags, text);
            return "Отлично, добавляю в заказ " + getLastFound(tags) + " Желаешь что-то еще?";
        }
        if (foundCount(tags) > 1)
            return "Под твой запрос подходит: \n" + extract(tags) + "Выбери что-то одно, и я добавлю это в заказ.";
        return "Я не понял, возможно у нас этго нет, попробуй сказать по другому. " + menuStr;
    }

    public static void main(String[] args) {
        System.getProperties().put("proxySet", "true"); // Tor Browser proxy params
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", "9150");

        System.out.println("Hello bot!");
        ApiContextInitializer.init();

        // инициализируем бота
        RollsBot bot = new RollsBot();
        bot.username = "Pizza24testingbot"; // не стал создавать нового бота
        bot.token = "1011637303:AAE7o8myLHhW96fgAlnHI3EQeAwEbs12_fE";

        // наполняем тэгами
        bot.addTags("привет", "привет, здасьте, здравствуйте, добр, день, вечер, утро, hi, hello");
        bot.addTags("конец", "конец, все, стоп, нет, хватит, хорош, спасибо");
        bot.addTags("дурак", "дурак, идиот, тупой");

        // добавлено
        bot.addTags("заказ", "заказ");
        bot.addTags("нет", "нет");

        menu = new Menu(bot);
        Meal m1 = menu.add("Тянь шань 360р.", "ролл, шань, тянь, снежный краб, краб, огурец, сливочный сыр, угорь, лосось", 1, 360);
        Meal m2 = menu.add("Филадельфия Классик 360р.", "ролл, классик, филадельф, сливочный сыр, лосось", 1, 360);
        Meal m3 = menu.add("Спайси краб 280р.", "ролл, спайси, снежный краб, краб, икра лосося, спайси соус", 1, 280);
        Meal m4 = menu.add("Унаги филадельфия 360р.", "ролл, унаги, филадельф, угорь, сливочный сыр, лосось, соус унаги", 1, 360);
        Meal m5 = menu.add("Самурай 320р.", "ролл, самурай, креветка тигр, лосос, томат", 1, 320);
        Meal m6 = menu.add("Аляска 270р.", "ролл, аляск, сливочный сыр, креветк, огурец, укроп, икра", 1, 270);
        Meal m7 = menu.add("Горячая тортилья 270р.", "ролл, тортил, говя, сливочный сыр, помид, огур, лук", 1, 270);

        Meal a1 = menu.add("Больше Имбиря 20р.", "добавк, приправ, имбир", 2, 20);
        a1.linkTo(new Meal[]{m1, m2, m4, m5});
        Meal a2 = menu.add("Больше Васаби 20р.", "добавк, приправ, васаб", 2, 20);
        a2.linkTo(new Meal[]{m2, m3, m4, m5, m6});
        Meal a3 = menu.add("Больше Соевого соуса 20р.", "добавк, соус, соев, приправ, васаб", 2, 20);
        a3.linkTo(new Meal[]{m5, m6, m7});

        menu.add("Торт тирамису, 110р.", "десерт, кофе, маскарпоне, бисквит", 3, 110);
        menu.add("Торт медовик, 80р.", "десерт, мед, бисквит", 3, 80);
        menu.add("Эклеры, 70р.", "десерт, заварной, крем, тесто", 3, 70);

        menu.add("Имбирный компот 1л. 140р.", "напит, компот", 4, 140);
        menu.add("Кола, 80р.", "напит, пить, кола", 4, 80);
        menu.add("Холодный чай, 70р.", "напит, пить, чай, липтон, лимон", 4, 70);
        menu.add("Сок 60р.", "напит, пить, сок, апельсиноый, яблочный, вишневый", 4, 60);

        menu.fillTags();
        order = new Order(bot, menu);
        //bot.start();
        bot.test();
    }


    void test() {
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();

            System.out.println(processMessage(123, input));
        } while (!stop);
        in.close();
    }
}
