import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Lesson14 {

    //Реализуйте метод с сигнатурой HashMap<Integer, String> a2map(int[] akey, String[] aval),
    // который создает Map на основе пары массивов akey (ключи) и aval (значения).
    // Первому элементу массива akey соответствует первый элемент массива aval, и т.д.
    // Размеры массивов одинаковые.
    HashMap<Integer, String> a2map(int[] akey, String[] aval) {
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        //for (int i = akey.length - 1; i >= 0; i--) //не сошлось с тестом
        for (int i = 0; i < akey.length; i++)
            result.put(akey[i], aval[i]);
        return result;
    }

    //Создайте метод с сигнатурой int fillHoles(Map<Integer, String> map, int size),
    // который вставляет в HashMap пару <n> "Число n", если она там отсутствует,
    // Проверить от 1 до максимального числа size включительно. Метод возвращает количество
    // добавленных элементов. Пример пары: 1 "Число 1"
    int fillHoles(Map<Integer, String> map, int size) {
        int result = 0;
        for (int i = 1; i <= size; i++) {
            String oldVal = map.putIfAbsent(i, "Число " + i);
            if (oldVal == null) result++;
        }
        return result;
    }

    //Реализуйте метод с сигнатурой void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value),
    // который добавляет пару key-value в map при выполнении следующих условий:
    //
    //значение с таким key должно отсутствовать
    //значение key долно быть больше головы TreeMap
    //значение key долно быть меньше хвоста TreeMap
    static void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
        //System.out.println("\nkey=" + key);
        if (map.containsKey(key) || map == null || map.size() < 2) return;
        Integer first = map.firstKey();
        //System.out.println("first=" + first);
        if (key < first) return;
        Integer last = map.lastKey();
        //System.out.println("last=" + last);
        if (key > last) return;
        map.put(key, value);
        //System.out.println("putted!");
    }

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "Один");
        map.put(9, "Девять");
        checkAndAdd(map, 0, "Zero");
        checkAndAdd(map, 3, "Три");
        checkAndAdd(map, 2, "Два");
        checkAndAdd(map, 3, "Three");
        checkAndAdd(map, 10, "Ten");
        System.out.println(map);

        map = new TreeMap<>();
        checkAndAdd(map, 0, "Zero");
        checkAndAdd(map, 0, "Zero");
        System.out.println(map);
    }
}
