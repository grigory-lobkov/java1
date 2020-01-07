import java.util.HashMap;

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
}
