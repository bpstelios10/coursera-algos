import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private final Stack<Board> solution = new Stack<>();
    private final SearchNode solutionNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("initial board cant be null");

        MinPQ<SearchNode> queue = new MinPQ<>();
        queue.insert(new SearchNode(null, initial, 0));
        MinPQ<SearchNode> twinQueue = new MinPQ<>();
        twinQueue.insert(new SearchNode(null, initial.twin(), 0));

        SearchNode previousMin = queue.delMin();
        SearchNode twinPreviousMin = twinQueue.delMin();
        while (!previousMin.board.isGoal() && !twinPreviousMin.board.isGoal()) {
            for (Board neighborBoard : previousMin.board.neighbors()) {
                if (previousMin.predecessor != null
                        && neighborBoard.equals(previousMin.predecessor.board)) continue;
                queue.insert(new SearchNode(previousMin, neighborBoard, previousMin.moves + 1));
            }

            for (Board twinNeighborBoard : twinPreviousMin.board.neighbors()) {
                if (twinPreviousMin.predecessor != null
                        && twinNeighborBoard.equals(twinPreviousMin.predecessor.board)) continue;
                twinQueue.insert(new SearchNode(twinPreviousMin, twinNeighborBoard,
                                                twinPreviousMin.moves + 1));
            }

            previousMin = queue.delMin();
            twinPreviousMin = twinQueue.delMin();
        }

        if (previousMin.board.isGoal()) {
            this.solutionNode = previousMin;

            solution.push(solutionNode.board);
            SearchNode currentNode = solutionNode;
            while (currentNode.predecessor != null) {
                currentNode = currentNode.predecessor;
                solution.push(currentNode.board);
            }
        }
        else {
            this.solutionNode = null;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return !solution.isEmpty();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? solutionNode.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        final SearchNode predecessor;
        final Board board;
        final int moves;
        final int manhattan;

        private SearchNode(SearchNode predecessor, Board board, int moves) {
            this.predecessor = predecessor;
            this.board = board;
            this.moves = moves;
            this.manhattan = board.manhattan();
        }

        public int compareTo(SearchNode other) {
            return Integer.compare(this.manhattan + this.moves,
                                   other.manhattan + other.moves);
        }
    }
}
