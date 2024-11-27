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
                assertEquals(log.toString(), ads.get(0), sad.get(0));
            } else {
                sad.addLast(i);
                ads.addLast(i);
                log.append("sad.addLast(").append(i).append(");\n");
                assertEquals(log.toString(), ads.get(ads.size() - 1), sad.get(sad.size() - 1));
            }
        }

        for (int i = 0; i < 100; i++) {
            double num = StdRandom.uniform();
            if (num < 0.5) {
                Integer expected = ads.removeFirst();
                Integer actual = sad.removeFirst();
                log.append("sad.removeFirst()");
                assertEquals(log.toString(), expected, actual);
            } else {
                Integer expected = ads.removeLast();
                Integer actual = sad.removeLast();
                log.append("sad.removeLast()");
                assertEquals(log.toString(), expected, actual);
            }
        }
    }
}
