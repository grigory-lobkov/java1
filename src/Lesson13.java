import java.util.ArrayDeque;

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

    public static void main(String[] args) {
        new Lesson13().dequeueTest();
    }
}
