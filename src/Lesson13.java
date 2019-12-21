import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Lesson13 {
    void dequeueTest() {
        ArrayDeque deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i % 2 == 0)
                deque.poll();
        }

        System.out.println(deque);
    }

    void pqTest() {
        PriorityQueue pQueue = new PriorityQueue<>();
        pQueue.offer(10);
        pQueue.offer(1);
        pQueue.offer(9);
        pQueue.offer(3);
        System.out.println(pQueue);
    }

//Создайте метод с сигнатурой ArrayDeque<Integer> array2queue(int[] a),
// который копирует содержимое массива в очередь

    ArrayDeque<Integer> array2queue(int[] a) {
        ArrayDeque<Integer> result = new ArrayDeque<Integer>();
        if (a == null) return result;
        for (int e : a) {
            result.offer(e);
        }
        return result;
    }

    public static void main(String[] args) {
        //new Lesson13().dequeueTest();
        //new Lesson13().pqTest();
        System.out.println(new Lesson13().array2queue(new int[]{4, 2, 1}));
    }
}
