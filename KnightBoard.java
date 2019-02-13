public class KnightBoard{
    private int[][] board;
    public KnightBoard(itn startingRows, int startingCols) {
	if (startingRows <= 0 || startingCols <= 0) {
	    throw new IllegalArgumentException("Parameter cannot be less than or equal to 0");
	}
	board = new int[startingRows][startingCols];
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		board[i][j] = 0;
	    }
	}
    }
    public String toString() {
	String output = "";
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		if (board[i][j] == 0) {
		    output += " _";
		} else {
		    output += "";
		}
	    }
	}
    }
    private boolean solveH(int row, int col, int level) {
	if (row % 2 == 1 && col % 2 == 1) {
	    return false;
	}
	if (row <= col) {
	    if (row == 1 || row == 2 || row == 4 || (row == 3 && (col == 4 || col == 6 || col == 8))) {
		return false;
	    }
	} else if (col <= row) {
	    if (col == 1 || col == 2 || col == 4 || (col == 3 && (row == 4 || row == 6 || row == 8))) {
		return false;
	    }
	}
	if (level == board.length * board[0].length) {
	    return true;
	} else {
	    if (board[row][col] == 0) {
		board[row][col] += 1;
	    } else {
		return false;
	    }
	    return solveH(row + 2, col + 2, level+1) ||
	    
	

	
	
