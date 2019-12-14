import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lesson12 {
    //Реализуйте метод,с сигнатурой public Set<Integer> a2set(int[] a), который преобразует массив в множество
    public Set<Integer> a2set(int[] a) {
        Set<Integer> result = new HashSet();
        for (int i : a) result.add(new Integer(i));
        return result;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 2, 3, 0, 111};
        System.out.println(Arrays.toString(a));
        Set<Integer> s = new Lesson12().a2set(a);
        //for(int i:s) System.out.println(i);
        System.out.println(new Lesson12().a2set(a));
    }

}
