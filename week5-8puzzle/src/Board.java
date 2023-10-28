import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] tiles;
    private List<Board> neighbors;
    private int twinX1 = -1, twinY1 = -1, twinX2 = -1, twinY2 = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = (tiles == null) ? null : Arrays.stream(tiles)
                                                    .map(int[]::clone)
                                                    .toArray(int[][]::new);
    }

    // board dimension n
    public int dimension() {
        return tiles == null ? 0 : tiles[0].length;
    }

    // number of tiles out of place
    public int hamming() {
        int outOfPosition = 0;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y] != 0 && tiles[x][y] != (x * dimension() + y + 1)) outOfPosition++;
            }
        }

        return outOfPosition;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int result = 0;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y] == 0) continue;
                result += computeItemManhattanDinstance(tiles[x][y], x + 1, y + 1);
            }
        }

        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors != null) return neighbors;

        int blankRow = 0, blankColumn = 0;
        boolean isBlankFound = false;

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y] == 0) {
                    blankRow = x;
                    blankColumn = y;
                    isBlankFound = true;
                    break;
                }
            }
            if (isBlankFound) break;
        }

        List<Board> computeNeighbors = new LinkedList<>();
        if (blankRow != 0)
            computeNeighbors.add(swapOnNewBoard(blankRow, blankColumn, blankRow - 1, blankColumn));
        if (blankRow != dimension() - 1)
            computeNeighbors.add(swapOnNewBoard(blankRow, blankColumn, blankRow + 1, blankColumn));
        if (blankColumn != 0)
            computeNeighbors.add(swapOnNewBoard(blankRow, blankColumn, blankRow, blankColumn - 1));
        if (blankColumn != dimension() - 1)
            computeNeighbors.add(swapOnNewBoard(blankRow, blankColumn, blankRow, blankColumn + 1));
        neighbors = computeNeighbors;

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (twinX1 == -1 || twinY1 == -1 || twinX2 == -1 || twinY2 == -1) {
            do {
                twinX1 = StdRandom.uniformInt(dimension());
                twinY1 = StdRandom.uniformInt(dimension());
            } while (tiles[twinX1][twinY1] == 0);

            do {
                twinX2 = StdRandom.uniformInt(dimension());
                twinY2 = StdRandom.uniformInt(dimension());
            } while (tiles[twinX2][twinY2] == 0 || (twinX1 == twinX2 && twinY1 == twinY2));
        }

        return swapOnNewBoard(twinX1, twinY1, twinX2, twinY2);
    }

    // string representation of this board
    public String toString() {
        int lengthOfLongestNumber = String.valueOf(dimension() * dimension()).length();
        StringBuilder value = new StringBuilder(String.valueOf(dimension()));
        for (int[] row : tiles) {
            value.append("\n");
            for (int item : row) {
                value.append(String.format("%1$" + lengthOfLongestNumber + "s", item) + " ");
            }
        }
        return value.toString();
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (this == y) return true;
        if (!(y.getClass() == this.getClass())) return false;
        Board board = (Board) y;

        return Arrays.deepEquals(tiles, board.tiles);
    }
    //
    // public int hashCode() {
    //     return Arrays.deepHashCode(tiles);
    // }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] b = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        Board board = new Board(b);
        Board emptyBoard = new Board(null);
        int[][] bb = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        Board completedBoard = new Board(bb);

        assertThat(board.dimension() == 3);
        assertThat(emptyBoard.dimension() == 0);

        assertThat(("3\n" + "0 1 2 \n" + "3 4 5 \n" + "6 7 8 ").equals(board.toString()));
        StdOut.println(board);

        StdOut.println(board.twin());
        StdOut.println(board.twin());
        StdOut.println(board);

        assertThat(board.hamming() == 8);
        assertThat(!board.isGoal());
        assertThat(completedBoard.hamming() == 0);
        assertThat(completedBoard.isGoal());

        assertThat(completedBoard.manhattan() == 0);
        assertThat(board.manhattan() == 12);

        // assertThat(!board.equals(b));
        Board referenceToSameBoard = board;
        assertThat(referenceToSameBoard.equals(board));
        Board copyOfBoard = new Board(b);
        assertThat(copyOfBoard.equals(board));
        assertThat(copyOfBoard != board);
        assertThat(!board.equals(completedBoard));

        // Set<Board> neighbors = new HashSet<Board>();
        // int[][] neighbor1 = { { 1, 0, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        // int[][] neighbor2 = { { 3, 1, 2 }, { 0, 4, 5 }, { 6, 7, 8 } };
        // neighbors.add(new Board(neighbor2));
        // neighbors.add(new Board(neighbor1));
        // for (Board value : board.neighbors()) {
        //     assertThat(neighbors.remove(value));
        // }
        // assertThat(neighbors.size() == 0);
        // Write more test cases?
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }

    private int computeItemManhattanDinstance(int i, int currentRow, int currentColumn) {
        if (i == 0) return dimension() - currentColumn + dimension() - currentRow;

        int correctRow = (i - 1) / dimension() + 1;
        int rowMoves = correctRow - currentRow;
        rowMoves = (rowMoves < 0) ? -rowMoves : rowMoves;

        int correctColumn = (i - 1) % dimension();
        int columnMoves = correctColumn - (currentColumn - 1);
        columnMoves = (columnMoves < 0) ? -columnMoves : columnMoves;

        return rowMoves + columnMoves;
    }

    private Board swapOnNewBoard(int firstX, int firstY, int secondX, int secondY) {
        int[][] newTiles = Arrays.stream(tiles)
                                 .map(int[]::clone)
                                 .toArray(int[][]::new);

        swap(newTiles, firstX, firstY, secondX, secondY);

        return new Board(newTiles);
    }

    private void swap(int[][] array, int firstX, int firstY, int secondX, int secondY) {
        int swap = array[firstX][firstY];
        array[firstX][firstY] = array[secondX][secondY];
        array[secondX][secondY] = swap;
    }
}
