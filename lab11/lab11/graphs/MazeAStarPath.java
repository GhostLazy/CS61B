package lab11.graphs;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private class Node implements Comparable<Node> {
        private int pos;
        private int priority;

        private Node(int x) {
            this.pos = x;
            this.priority = distTo[x] + h(x);
        }

        @Override
        public int compareTo(Node o) {
            return this.priority - o.priority;
        }
    }

    private int s;
    private int t;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        marked[s] = true;
        pq.offer(new Node(s));
        while (!pq.isEmpty()) {
            Node node = pq.remove();
            int x = node.pos;
            if (x == t) {
                return;
            }
            for (int y : maze.adj(x)) {
                if (!marked[y]) {
                    marked[y] = true;
                    distTo[y] = distTo[x] + 1;
                    edgeTo[y] = x;
                    pq.offer(new Node(y));
                    announce();
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

