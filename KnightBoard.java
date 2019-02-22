
public class KnightBoard{
    private int[][] board;
    public KnightBoard(int startingRows, int startingCols) {
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
		} else if (board[i][j] < 10) {
		    output += " " + board[i][j] + " ";
		} else {
		    output += board[i][j] + " ";
		}
	    }
	    output += "\n";
	}
	return output;
    }
    private boolean solveH(int row, int col, int level) {
	// if (row % 2 == 1 && col % 2 == 1) {
	//     return false;
	// }
	// if (row <= col) {
	//     if (row == 1 || row == 2 || row == 4 || (row == 3 && (col == 4 || col == 6 || col == 8))) {
	// 	return false;
	//     }
	// } else if (col <= row) {
	//     if (col == 1 || col == 2 || col == 4 || (col == 3 && (row == 4 || row == 6 || row == 8))) {
	// 	return false;
	//     }
	// }
	if (level == board.length * board[0].length) {
	    return true;
	} else {
	    if (row >= board.length || col >= board.length || row < 0 || col < 0) {
		return false;
	    }

		if (addKnight(row, col, level)) {
		    if (solveH(row + 2, col + 1, level + 1) == true
			|| solveH(row + 2, col - 1, level + 1) == true
			|| solveH(row + 1, col + 2, level + 1) == true
			|| solveH(row + 1, col - 2, level + 1) == true
			|| solveH(row - 1, col + 2, level + 1) == true
			|| solveH(row - 1, col - 2, level + 1) == true
			|| solveH(row + 2, col - 1, level + 1) == true
			|| solveH(row - 2, col - 1, level + 1) == true) {
			return true;
		    }
		    else {
			removeKnight(row, col);
		}
	    }
	}
	return false;
    }
    //adding knight at xy coordinate		
    private boolean addKnight(int r, int c, int level) {
	//works only if a knight hasn't been there
	if (board[r][c] == 0) {
	    board[r][c] = level;
	    return true;
	} else {
	    return false;
	}
    }
    //removing knight at xy coordinate
    private boolean removeKnight(int r, int c) {
	//works only if a knight has been there
	if (board[r][c] > 0) {
	    board[r][c] = 0;
	    return true;
	} else {
	    return false;
	}
    }
    public boolean solve(int startingRow, int startingCol) {
	return solveH(startingRow, startingCol, 1);
    }
    public static void main(String[] args) {
	KnightBoard a = new KnightBoard(5,5);
	a.solve(0, 0);
	System.out.println(a);
    }
}
