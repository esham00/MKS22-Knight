
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
	if (board.length <=2 || board[0].length <= 2 || (board.length % 2 == 1 && board[0].length % 2 == 1) || board.length == 4 || board[0].length == 4) {
	    return false;
	}
	if (level == 1 + board.length * board[0].length) {
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
    public int countSolutions(int startingRow, int startingCol) {
	return countSolutionsH(0,0,0);
    }
    private int countSolutionsH(int row, int col, int step) {
	if (step == board.length * board[0].length) {
	    return 1;
	}
	int solutions = 0;
	for (int x = 0; x < board.length; x++) {
	    for (int y = 0; y < board[0].length; y++) {
		if (addKnight(x,y,x+1)) {
		    solutions += countSolutionsH(x,y, step + 1);
		}
		removeKnight(x,y);
	    }
	}
	return solutions;
    }
	    
    public static void main(String[] args) {
	KnightBoard a = new KnightBoard(3,10);
	a.solve(0,0);
	 System.out.println(a);
	//	System.out.println(a.countSolutions(0,0));
    }
}
