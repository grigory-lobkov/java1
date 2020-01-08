import java.util.HashMap;
import java.util.Map;

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
}
