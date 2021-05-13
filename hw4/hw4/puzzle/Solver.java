package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    private class SearchNode {
        private WorldState state;
        private SearchNode prev;
        private Integer priority;
        private int moves;

        private SearchNode(WorldState state, SearchNode prev) {
            this.state = state;
            this.moves = prev == null ? 0 : prev.moves + 1;
            if (edCaches.containsKey(state)) {
                priority = edCaches.get(state) + moves;
            } else {
                int ed = state.estimatedDistanceToGoal();
                edCaches.put(state, ed);
                priority = ed + moves;
            }
            this.prev = prev;

        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode left, SearchNode right) {
            return left.priority.compareTo(right.priority);
        }

    }

    private Map<WorldState, Integer> edCaches = new HashMap<>();
    private int searchCount = 0;
    private Stack<WorldState> path = new Stack<>();

    public Solver(WorldState initial) {
        MinPQ<SearchNode> minNode = new MinPQ<>(new SearchNodeComparator());
        SearchNode currentNode = new SearchNode(initial, null);

        while (!currentNode.state.isGoal()) {
            for (WorldState nextState : currentNode.state.neighbors()) {
                if (currentNode.prev == null || !nextState.equals(currentNode.prev.state)) {
                    SearchNode nextNode = new SearchNode(nextState, currentNode);
                    minNode.insert(nextNode);
                    searchCount += 1;
                }

            }
            currentNode = minNode.delMin();
        }

        for (SearchNode node = currentNode; node != null; node = node.prev) {
            path.push(node.state);
        }
    }

    public int moves() {
        return path.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return path;
    }

    int getSearchCount() {
        return searchCount;
    }


}
