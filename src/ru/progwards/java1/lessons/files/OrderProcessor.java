package ru.progwards.java1.lessons.files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

class Order {
    public String shopId; // идентификатор магазина
    public String orderId; // идентификатор заказа
    public String customerId; // идентификатор покупателя
    public LocalDateTime datetime; // дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items; // список позиций в заказе, отсортированный по наименованию товара
    public double sum; // сумма стоимости всех позиций в заказе
}

class OrderItem {
    public String googsName; // наименование товара
    public int count; // количество
    public double price; // цена за единицу
}

public class OrderProcessor {

/*
Информация о заказах поступает в виде файлов, которые лежат в под-папках разбитых по неделям,
имена папок не имеют значения. Имя каждого файла имеет формат: AAA-999999-ZZZZ.csv
где AAA - обязательные 3 символа shopId - идентификатор магазина,
999999 - обязательные 6 символов orderId - номер заказа,
ZZZZ - обязательные 4 символа customerId - идентификатор покупателя,
расширение файла - csv, например S02-P01X12-0012.csv: shopId=”S02”, orderId=”P01X12”, customerId=”0012”

Содержимое каждого файла имеет формат CSV (Comma Separated Values) со следующими данными

Наименование товара, количество, цена за единицу

Например:

Игрушка мягкая “Мишка”, 1, 1500
Пазл “Замок в лесу”, 2, 700
Книжка “Сказки Пушкина”, 1, 300
*/

    Path startPath; // начальная папка для хранения файлов
    List<Order> orders; // список заказов

    // инициализирует класс, с указанием начальной папки для хранения файлов
    public OrderProcessor(String startPath) {
        this.startPath = Paths.get(startPath);
    }

    // загружает заказы за указанный диапазон дат, с start до finish, обе даты включительно.
    // Если start == null, значит нет ограничения по дате слева, если finish == null, значит нет ограничения по дате справа,
    // если shopId == null - грузим для всех магазинов
    // При наличии хотя бы одной ошибки в формате файла, файл полностью игнорируется, т.е. Не поступает в обработку.
    // Метод возвращает количество файлов с ошибками. При этом, если в классе содержалась информация, ее надо удалить
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        orders = new ArrayList<Order>();
        int failedFiles = 0;

        sortOrders(); // отсортируем orders
        return failedFiles;
    }

    // сортировка заказов "в порядке обработки"
    private void sortOrders() {
        Comparator<Order> dateComparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        };
        Collections.sort(orders, dateComparator);
    }

    // выдать список заказов в порядке обработки (отсортированные по дате-времени), для заданного магазина.
    // Если shopId == null, то для всех
    public List<Order> process(String shopId) {
        return null;
    }

    // выдать информацию по объему продаж по магазинам (отсортированную по ключам): String - shopId, double - сумма стоимости всех проданных товаров в этом магазине
    public Map<String, Double> statisticsByShop() {
        return null;
    }

    // выдать информацию по объему продаж по товарам (отсортированную по ключам): String - goodsName, double - сумма стоимости всех проданных товаров этого наименования
    public Map<String, Double> statisticsByGoods() {
        return null;
    }

    // выдать информацию по объему продаж по дням (отсортированную по ключам): LocalDate - конкретный день, double - сумма стоимости всех проданных товаров в этот день
    public Map<LocalDate, Double> statisticsByDay() {
        return null;
    }

    // test
    public static void main(String[] args) {
        OrderProcessor p = new OrderProcessor("C:\\Users\\Darya\\IdeaProjects\\java1\\src\\ru\\progwards\\java1\\lessons\\files\\orders");
    }
}