package hw4.puzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private final WorldState state;
        private final int moves;
        private final SearchNode prev;
        public SearchNode(WorldState s, int m, SearchNode p) {
            state = s;
            moves = m;
            prev = p;
        }

        private int getPriority(SearchNode sn) {
            if (!cachePriority.containsKey(sn.state)) {
                cachePriority.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return cachePriority.get(sn.state) + sn.moves;
        }

        @Override
        public int compareTo(SearchNode other) {
            return (getPriority(this)) - (getPriority(other));
        }
    }

    private final List<WorldState> solution = new ArrayList<>();
    private final Map<WorldState, Integer> cachePriority = new HashMap<>();

    public Solver(WorldState initial) {
        PriorityQueue<SearchNode> pq = new PriorityQueue<>();
        pq.offer(new SearchNode(initial, 0, null));
        while (true) {
            SearchNode x = pq.poll();
            if (x.state.isGoal()) {
                while (x != null) {
                    solution.add(x.state);
                    x = x.prev;
                }
                return;
            }
            for (WorldState y : x.state.neighbors()) {
                if (x.prev == null || !y.equals(x.prev.state)) {
                    pq.offer(new SearchNode(y, x.moves + 1, x));
                }
            }
        }
    }

    public int moves() {
        return solution.size() - 1;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> res = new ArrayList<>();
        for (int i = solution.size() - 1; i >= 0; i--) {
            res.add(solution.get(i));
        }
        return res;
    }
}
