package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testPercolation() {
        Percolation p = new Percolation(5);
        p.open(0, 2);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        p.open(2, 4);
        p.open(3, 4);
        p.open(4, 4);
        p.open(4, 4);
        assertTrue(p.isOpen(2, 4));
        assertTrue(p.percolates());
        assertTrue(p.isFull(2, 2));
        assertEquals(7, p.numberOfOpenSites());
    }

    @Test
    public void testPercolationStats() {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 50, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
        assertTrue(1 == 1);
    }
}
