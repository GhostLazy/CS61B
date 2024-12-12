package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] pathTo;
    public boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        pathTo = new int[maze.V()];
        pathTo[0] = 0;
        dfs(0);
        announce();
    }

    // Helper methods go here
    public void dfs(int x) {
        marked[x] = true;
        for (int y : maze.adj(x)) {
            if (cycleFound) {
                return;
            }
            if (!marked[y]) {
                pathTo[y] = x;
                dfs(y);
            } else if (y != pathTo[x]) {
                pathTo[y] = x;
                int cur = x;
                edgeTo[cur] = pathTo[cur];
                while (cur != y) {
                    cur = pathTo[cur];
                    edgeTo[cur] = pathTo[cur];
                }
                cycleFound = true;
                return;
            }
        }
    }
}
