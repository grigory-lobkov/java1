package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
Информация по продажам

Во входном файле находятся данные в CSV формате, CSV - Comma Separated Values,
значения разделенные запятыми. Каждая строка - данные об одной покупке. Входные данные

ФИ покупателя, наименование товара, количество, сумма
String, String, int, double

Пример

Иванов Сергей, iPhone 10X, 2, 150000
Петрова Анна, наушники JBL, 2, 7000
Иванов Сергей, чехол для iPhone, 1, 1000
Петрова Анна, пакет пластиковый, 1, 3
Радж Кумар, батарейка ААА, 1, 150
Михаил Цикло, iPhone 10X, 1, 75000
Радж Кумар, пакет пластиковый, 1, 3
Михаил Цикло, пакет пластиковый, 1, 3
Иванов Сергей, стекло защитное, 1, 1000
Василий Пупкин, спички, 10, 10

3.1 Реализовать метод public static int loadOrders(String fileName) - вернуть количество успешно
загруженных строк. Если в строке более или менее 4-x полей, или количество и сумма не преобразуются
в числа - эту строку не загружаем.

3.2 Реализовать метод public static Map<String, Double> getGoods() - вернуть список товаров,
отсортированный по наименованию товара. В String - наименование товара, в Double - общая сумма
продаж по товару

3.3 Реализовать метод public static Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() -
вернуть список покупателей, отсортированный по алфавиту. В String  - ФИ, в Double - сумма всех покупок
покупателя, в Integer - количество покупок

3.4 Протестировать на данных из примера выше
*/
public class SalesInfo {

    private static Map<String, Double> goods;
    private static Map<String, AbstractMap.SimpleEntry<Double, Integer>> consumers;

    // загрузить заказы из CSV файла: String, String, int, double
    public static int loadOrders(String fileName) {
        goods = new TreeMap<String, Double>();
        consumers = new TreeMap<String, AbstractMap.SimpleEntry<Double, Integer>>();
        int result = 0;
        try (FileReader r = new FileReader(fileName); Scanner s = new Scanner(r)) {
            while (s.hasNext()) {
                result += processLine(s.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(fileName + ":\n" + e.getMessage());
        }
        return result;
    }

    // загрузить содержимое строки, вернем количество успешно загруженных заказов
    private static int processLine(String str) {
        String[] words = str.split(",");
        if (words.length == 4) {
            try {
                String fio = words[0].trim();
                String good = words[1].trim();
                int cnt = Integer.parseInt(words[2].trim());
                double sum = Double.parseDouble(words[3].trim());
                //System.out.println(str);
                //В String - наименование товара, в Double - общая сумма продаж по товару
                goods.put(good, goods.containsKey(good) ? goods.get(good) + sum : sum);
                //В String  - ФИ, в Double - сумма всех покупок покупателя, в Integer - количество покупок
                if (consumers.containsKey(fio)) {
                    AbstractMap.SimpleEntry<Double, Integer> simpleEntry = consumers.get(fio);
                    sum += simpleEntry.getKey();
                    cnt += simpleEntry.getValue();
                }
                consumers.put(fio, new AbstractMap.SimpleEntry<Double, Integer>(sum, cnt));
                return 1;
            } catch (Exception e) {
            }
        }
        return 0;
    }

    public static Map<String, Double> getGoods() {
        return goods;
    }

    public static Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        return consumers;
    }

    public static void main(String[] args) {
        loadOrders("..\\java1\\src\\ru\\progwards\\java1\\lessons\\maps\\SalesInfo.java");
        System.out.println(getGoods());
        System.out.println(getCustomers());
    }
}
