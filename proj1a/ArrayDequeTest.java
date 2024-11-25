import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayDequeTest {

    @Test
    public void testAddSizeEmpty() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        assertEquals(true, dq.isEmpty());

        dq.addFirst(1);
        dq.addLast(8);
        assertEquals(false, dq.isEmpty());

        dq.addLast(7);
        dq.addFirst(2);
        dq.addLast(6);
        dq.addLast(5);
        assertEquals(6, dq.size());

        dq.addFirst(3);
        dq.addLast(4);
        dq.addFirst(0);
        assertEquals(9, dq.size());
    }

    @Test
    public void testAddRemoveGet() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(1);
        dq.addLast(8);
        dq.addLast(7);
        dq.addFirst(2);
        dq.addLast(6);
        dq.addLast(5);
        dq.addFirst(3);
        dq.addLast(4);
        dq.addFirst(0);
        assertEquals(4, dq.get(8));
    }
}
