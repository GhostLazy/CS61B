package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        public WorldState state;
        public int moves;
        public SearchNode prev;
        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            prev = p;
        }

        private int getEDTG() {
            if (!cacheEDTG.containsKey(this.state)) {
                cacheEDTG.put(this.state, this.state.estimatedDistanceToGoal());
            }
            return cacheEDTG.get(this.state);
        }

        @Override
        public int compareTo(SearchNode other) {
            return (this.moves + this.getEDTG()) - (other.moves + other.getEDTG());
        }
    }

    private final List<WorldState> solution = new ArrayList<>();
    private final Map<WorldState, Integer> cacheEDTG = new HashMap<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode x = pq.delMin();
            if (x.state.isGoal()) {
                while (x != null) {
                    solution.add(x.state);
                    x = x.prev;
                }
                return;
            }
            for (WorldState y : x.state.neighbors()) {
                if (x.prev == null || !y.equals(x.prev.state)) {
                    pq.insert(new SearchNode(y, x.moves + 1, x));
                }
            }
        }
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return solution.reversed();
    }
}
