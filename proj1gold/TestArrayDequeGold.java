import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    @Test
    public void testAddRemove() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder log = new StringBuilder();

        for (int i = 0; i < 100; i++) {
            double num = StdRandom.uniform();
            if (num < 0.5) {
                sad.addFirst(i);
                ads.addFirst(i);
                log.append("sad.addFirst(").append(i).append(");\n");
            } else {
                sad.addLast(i);
                ads.addLast(i);
                log.append("sad.addLast(").append(i).append(");\n");
            }
        }

        for (int i = 0; i < 100; i++) {
            double num = StdRandom.uniform();
            if (num < 0.5) {
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                assertEquals(log + "sad.removeFirst()", expected, actual);
            } else {
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                assertEquals(log + "sad.removeLast()", expected, actual);
            }
        }
    }
}
