import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void testAddRemove() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (int i = 0; i < 100; i++) {
            double num = StdRandom.uniform();
            if (num < 0.5) {
                sad.addFirst(i);
                ads.addFirst(i);
            } else {
                sad.addLast(i);
                ads.addLast(i);
            }
        }

        for (int i = 0; i < 100; i++) {
            double num = StdRandom.uniform();
            if (num < 0.5) {
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                assertEquals("Random number " + expected + " not equals to " + actual + " !", expected, actual);
            } else {
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                assertEquals("Random number " + expected + " not equals to " + actual + " !", expected, actual);
            }
        }
    }
}
