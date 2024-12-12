package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private final int source;
    private final int target;
    private final Maze m;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        this.m = m;
        source = m.xyTo1D(sourceX, sourceY);
        target = m.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(source);
        marked[source] = true;
        while (!q.isEmpty()) {
            int x = q.removeFirst();
            if (x == target) {
                return;
            }
            for (int y : m.adj(x)) {
                if (!marked[y]) {
                    marked[y] = true;
                    q.addLast(y);
                    distTo[y] = distTo[x] + 1;
                    edgeTo[y] = x;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

