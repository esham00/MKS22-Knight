
public class KnightBoard{
    //board
    private int[][] board;
    //possible moves for rows
    private int[] rowMoves = new int[]{1,1,2,2,-1,-1,-2,-2};
    //possible moves for cols (same thing but in different order)
    private int[] colMoves = new int[]{2,-2,1,-1,2,-2,1,-1};
    //x-cor and y-cor of the move w smallest moves
    private int[] minRow = new int[1];
    private int[] minCol = new int[1];
    //constructor
    public KnightBoard(int startingRows, int startingCols) {
	if (startingRows <= 0 || startingCols <= 0) {
	    throw new IllegalArgumentException("Parameter cannot be less than or equal to 0");
	}
	board = new int[startingRows][startingCols];
	//initialize everything to 0
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		board[i][j] = 0;
	    }
	}
    }
    //toString
    public String toString() {
	String output = "";
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board[0].length; j++) {
		//if unsolved print underscores
		if (board[i][j] == 0) {
		    output += " _";
		}
		//spaces for single digit numbers
		else if (board[i][j] < 10) {
		    output += " " + board[i][j] + " ";
		}
		//print out numbers
		else {
		    output += board[i][j] + " ";
		}
	    }
	    output += "\n";
	}
	return output;
    }
    //finding the minimum moves/comparison w other moves
    private int  minMoves(int row, int col, int move){
	//if the move is out of bounds or already used: return 0
	if (row < board.length && col < board[0].length && row >= 0 && col >=0 && board[row][col] == 0) {
	    //keep track of the moves
	    int moves = 0;
	    for (int i = 0; i < rowMoves.length; i++) {
		if ( addKnight(row + rowMoves[i], col + colMoves[i], 0) && board[row+rowMoves[i]][col + colMoves[i]] == 0) {
		    //testing all possible moves w this move
		    moves++;
		}
	    }
	    //System.out.println(moves + " vs. " + move);
	    //if the amount of moves this move has is smaller than the previous smallest amount of moves, replace with x-cor and y-cor of this move
	    if (moves != 0 && moves < move) {
		//System.out.println("Next Move: " + row + ", " + col);
		minRow[0] = row;
	        minCol[0] = col;
	    }
	     // else {
	     // 	System.out.println("Not Chosen: " + row + ", " + col);
	     // }
	    return moves;
	} else {
	    return 0;
	}
    }
    //solve helper
    private boolean solveH(int row, int col, int level) {
	//if it is the last move, test all possible moves to see which space is missing
	if (level == board.length * board[0].length) {
	    for(int i = 0; i < rowMoves.length; i++) {
		addKnight(row + rowMoves[i], col + colMoves[i], level);
	    }
	    return true;
	} else {
	    //max moves (current min)
	    int moves = 8;
	    //moves that another place has
	    int tempMove = 8;
	    //if you can add a knight there, test out the next step's moves
	    if (addKnight(row, col, level)) {
		//System.out.println(level);
		//System.out.println("Original: " + row + ", " + col);
		//testing out all possible moves
		for(int i = 0; i < rowMoves.length; i++) {
		    tempMove = minMoves(row + rowMoves[i], col + colMoves[i], moves);
		    //if tempMove is not 0, bc if it is, it's already used/not a qualified min
		    //setting a new min x-cor and y-cor if the # of moves that coordinate has is smaller
		    if (tempMove != 0 && tempMove < moves) {
			moves = tempMove;
		    }
		}
		//recursion to see it until the end
		if (solveH(minRow[0], minCol[0], level+1)) {
		    return true;
		}
		//remove if it didn't work
		removeKnight(row, col);
	    }
	}
	return false;
    }
    //adding knight at xy coordinate		
    private boolean addKnight(int r, int c, int level) {
	//works only if a knight hasn't been there & its coordinates fit
	if (r >= board.length || r < 0 || c < 0 || c >= board[0].length) {
	    return false;
	} 
	if (board[r][c] == 0) {
	    board[r][c] = level;
	    return true;
	} else {
	    return false;
	}
    }
    //removing knight at xy coordinate
    private boolean removeKnight(int r, int c) {
	//works only if a knight has been there & its coordinates fit
	if (r >= board.length || r < 0 || c < 0 || c >= board[0].length) {
	    return false;
	} 
	if (board[r][c] > 0) {
	    board[r][c] = 0;
	    return true;
	} else {
	    return false;
	}
    }
    //function for exceptions
    private void exceptions(int r, int c) {
	//if row or column is out of board, throw a new illegeal argument exception
	if (r >= board.length || r < 0 || c >= board[0].length || c < 0) {
	    throw new IllegalArgumentException("Index out of bounds");
	}
	//if board has any number not 0, throw illegal state exception
	for (int i = 0; i < board.length; i++) {
	    for (int j = 0; j < board.length; j++) {
		if (board[i][j] != 0) {
		    throw new IllegalStateException("board is unclean");
		}
	    }
	}
    }
    public boolean solve(int startingRow, int startingCol) {
	//exceptions
	exceptions(startingRow, startingCol);
	// if (board.length <=2 || board[0].length <= 2 || (board.length % 2 == 1 && board[0].length % 2 == 1) || board.length == 4 || board[0].length == 4) {nnnnn
	//     return false;
	// }
	//solve helper
	return solveH(startingRow, startingCol, 1);
    }
    public int countSolutions(int startingRow, int startingCol) {
	//exceptions
	exceptions(startingRow, startingCol);
	//count solutions helper
	return countSolutionsH(0,0,0);
    }
    //count solutions helper
    private int countSolutionsH(int row, int col, int step) {
	int solutions = 0;
	//if the step reached the limits of the board: that's one solution
	if (step == board.length * board[0].length) {
	    return 1;
	}
	//if if it hasn't: test all moves
	else if (addKnight(row,col,step)) {
	    for (int i = 0; i < rowMoves.length; i++) {
		//reiterate move to see all possible ways it works
		solutions += countSolutionsH(row + rowMoves[i], col + colMoves[i], step+1);
	    }
	    removeKnight(row, col);
	}
	return solutions;
    }
    public static void runTest(int i){

	KnightBoard b;
	int[]m =   {4,5,5,5,5};
	int[]n =   {4,5,4,5,5};
	int[]startx = {0,0,0,1,2};
	int[]starty = {0,0,0,1,2};
	int[]answers = {0,304,32,56,64};
	if(i >= 0 ){
	    try{
		int correct = answers[i];
		b = new KnightBoard(m[i%m.length],n[i%m.length]);

		int ans  = b.countSolutions(startx[i],starty[i]);

		if(correct==ans){
		    System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
		}else{
		    System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
		}
	    }catch(Exception e){
		System.out.println("FAIL Exception case: "+i);

	    }
	}
    }

	    
    public static void main(String[] args) {
	KnightBoard a = new KnightBoard(3,4);
	// a.solve(0,0);
	// System.out.println(a);
	System.out.println(a.countSolutions(0,0));
	for (int i = 0; i < 5; i++) {
	    runTest(i);
	}
    }
}


