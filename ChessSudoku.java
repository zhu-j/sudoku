package finalproject;




import java.util.*;
import java.io.*;


public class ChessSudoku
{
	/* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For
	 * a standard Sudoku puzzle, SIZE is 3 and N is 9.
	 */
	public int SIZE, N;

	/* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
	 * not yet been revealed are stored as 0.
	 */
	public int grid[][];

	/* Booleans indicating whether of not one or more of the chess rules should be
	 * applied to this Sudoku.
	 */
	public boolean knightRule;
	public boolean kingRule;
	public boolean queenRule;


	// Field that stores the same Sudoku puzzle solved in all possible ways
	public HashSet<ChessSudoku1> solutions = new HashSet<ChessSudoku1>();


	/* The solve() method should remove all the unknown characters ('x') in the grid
	 * and replace them with the numbers in the correct range that satisfy the constraints
	 * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL
	 * possible solutions and store them in the field named solutions. */
	public void solve(boolean allSolutions){
		/*
		 * ADD YOUR CODE HERE
		 */
		ChessSudoku1 sudoku1 = new ChessSudoku1(this.SIZE);
		if(knightRule==false && kingRule == false && queenRule==false){
			int[][] gridClone = this.grid.clone();
			solveSudoku(this.grid, this.N, this.SIZE);
			sudoku1 = this;
			solutions.add(sudoku1);
			solveSudoku(gridClone, this.N, this.SIZE);
			this.grid = gridClone;
			solutions.add(this);
		}
		if(knightRule==true && kingRule == false && queenRule==false){
			knightSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==false && kingRule == true && queenRule==false){
			kingSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==false && kingRule == false && queenRule==true){
			queenSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==true && kingRule == false && queenRule==true){
			kqSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==false && kingRule == true && queenRule==true){
			kingqSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==true && kingRule == true && queenRule==false){
			kkSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}
		if(knightRule==true && kingRule == true && queenRule==true){
			allSolveSudoku(this.grid, this.N, this.SIZE);
			if(allSolutions==true) {
				sudoku1 = this;
				solutions.add(sudoku1);
			}
		}

	}
	private boolean knightSudoku(int board[][], int value, int row, int col){
		boolean isKnight = true;
		for(int r = 0; r<board.length;r++){
			for(int c=0;c<board.length;c++){
				if(board[r][c] == value && Math.abs(row-r)==2 && Math.abs(col-c)==1 ){
					isKnight = false;
					return isKnight;
				}
				if(board[r][c] == value && Math.abs(row-r)==1 && Math.abs(col-c)==2 ){
					isKnight = false;
					return isKnight;
				}
			}
		}
		return isKnight;
	}

	private boolean queenSudoku(int board[][] ,int row, int col, int element) {



		if (element == board.length) {
			for(int i = 0; i<board.length;i++) {
				if (row == 0) {
					if (col == 0 && col + i < board.length && row + i < board.length) {
						if (board[row + i][col + i] == board.length) {

							return false;
						}
					} else if (col == board.length && row + i < board.length && col - i >= 0) {
						if (board[row + i][col - i] == board.length) {

							return false;
						}
					} else if(row - i >= 0 && row + i < board.length && col - i >= 0 && col + i < board.length) {
						if (board[row + i][col + i] == board.length) {

							return false;
						}
						if (board[row + i][col - i] == board.length) {

							return false;
						}
					}
				}
				if (row == board.length) {
					if (col == 0 && row - i >= 0 && col + i < board.length) {
						if (board[row - i][col + i] == board.length) {

							return false;
						}
					} else if (col == board.length && col - i >= 0 && row - i >= 0) {
						if (board[row - i][col - i] == board.length) {

							return false;
						}
					} else if(row - i >= 0 && row + i < board.length && col - i >= 0 && col + i < board.length){
						if (board[row - i][col + i] == board.length) {

							return false;
						}
						if (board[row - i][col - i] == board.length) {

							return false;
						}
					}
				}

				if (row < board.length - 1 && col > 1 && row + i < board.length && col - i >= 0) {
					if (board[row + i][col - i] == board.length) {

						return false;
					}
				}
				if (row < board.length - 1 && col < board.length - 1 && row + i < board.length && col + i < board.length) {
					if (board[row + i][col + i] == board.length) {

						return false;
					}
				}
				if (row > 1 && col > 1 && col - i >= 0 && row - i >= 0) {
					if (board[row - i][col - i] == board.length) {

						return false;
					}
				}
				if (row > 1 && col < board.length - 1 && row - i >= 0 && col + i < board.length) {

					if (board[row - i][col + i] == board.length) {

						return false;
					}
				}
			}
		}


		return true;
	}
	private boolean kingSudoku(int board[][], int num, int row, int col) {
		if(row==0){
			if(col==0){
				if (board[row + 1][col + 1] == num) {
					return false;
				}
			}
			else if(col==board.length){
				if (board[row + 1][col - 1] == num) {
					return false;
				}
			}else{
				if (board[row + 1][col + 1] == num) {
					return false;
				}
				if (board[row + 1][col - 1] == num) {
					return false;
				}
			}
		}
		if(row==board.length){
			if(col==0){
				if (board[row - 1][col + 1] == num) {
					return false;
				}
			}
			else if(col==board.length){
				if (board[row - 1][col - 1] == num) {
					return false;
				}
			}else{
				if (board[row - 1][col + 1] == num) {
					return false;
				}
				if (board[row - 1][col - 1] == num) {
					return false;
				}
			}
		}

		if (row < board.length - 1 && col > 1){
			if (board[row + 1][col - 1] == num) {
				return false;
			}
		}
		if (row < board.length - 1 && col < board.length - 1) {
			if (board[row + 1][col + 1] == num) {
				return false;
			}
		}
		if (row > 1 && col > 1) {
			if (board[row - 1][col - 1] == num) {
				return false;
			}
		}
		if (row > 1 && col < board.length - 1) {

			if (board[row - 1][col + 1] == num) {
				return false;
			}
		}


		return true;


	}
	/***backtracking algorithm:
	 * as long as there are empty cells in the grid
	 pick an empty cell and place a number in it
	 if a conflict occurs (i.e., at least one of the Sudoku rules is broken),
	 then remove that number and backtrack
	 if there are no conflicts, attempt to fill in the rest of the grid
	 if you were unable to fill the rest of the grid
	 then remove that number and backtrack
	 ***/

	private boolean solveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if (isFree(box, row, col, num,size)){
				box[row][col] = num;
				if (solveSudoku(box, number, size)) {
					return true;
				}
				else
				{
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean knightSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(knightSudoku(box,num,row,col) && isFree(box, row, col, num,size)){
				box[row][col] = num;
				if (knightSolveSudoku(box, number, size)) {

					return true;
				}
				else
				{

					box[row][col] = 0;
				}
			}
		}
		return false;
	}
	private boolean queenSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(queenSudoku(box,row,col,num) && isFree(box, row, col, num,size)){
				box[row][col] = num;

				if (queenSolveSudoku(box, number, size)) {

					return true;
				}
				else
				{

					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean kingSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(kingSudoku(box,num,row,col) && isFree(box, row, col, num,size)){
				box[row][col] = num;
				if (kingSolveSudoku(box, number, size)) {
					return true;
				}
				else
				{
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean kqSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(queenSudoku(box,row,col,num) && isFree(box, row, col, num,size) && knightSudoku(box,num, row, col)){
				box[row][col] = num;

				if (kqSolveSudoku(box, number, size)) {
					return true;
				}
				else
				{
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean kkSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(kingSudoku(box,num,row,col) && isFree(box, row, col, num,size) && knightSudoku(box,num, row, col)){
				box[row][col] = num;

				if (kkSolveSudoku(box, number, size)) {
					return true;
				}
				else {
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean kingqSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(queenSudoku(box,row,col,num) && isFree(box, row, col, num,size) && kingSudoku(box,num, row, col)){
				box[row][col] = num;

				if (kingqSolveSudoku(box, number, size)) {
					return true;
				}
				else{
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private boolean allSolveSudoku(int box[][], int number, int size){
		int row = -1;
		int col = -1;
		boolean isX = true;
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (box[i][j] == 0) {
					row = i;
					col = j;
					isX = false;
					break;
				}
			}
			if(isX==false){
				break;
			}
		}
		if(isX){
			return true;
		}

		for (int num = 1; num <= number; num++) {

			if(queenSudoku(box,row,col,num) && isFree(box, row, col, num,size) && kingSudoku(box,num, row, col) && knightSudoku(box,num,row,col)){
				box[row][col] = num;

				if (kingqSolveSudoku(box, number, size)) {
					return true;
				}
				else{
					box[row][col] = 0;
				}
			}
		}
		return false;
	}

	private static boolean isFree(int square[][], int row, int column, int num, int size){
		for(int i = 0;i<square.length; i++){
			if(square[row][i] == num){
				return false;
			}
		}
		for(int j = 0;j<square.length; j++){
			if(square[j][column] == num){
				return false;
			}
		}
		int startRow = row - row%size;
		int startCol = column - column%size;
		for (int k = startRow; k < startRow + size; k++) {
			for (int l = startCol; l < startCol + size; l++) {
				if (square[k][l] == num) {
					return false;
				}
			}
		}
		return true;
	}


	/*****************************************************************************/
	/* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE METHODS BELOW THIS LINE. */
	/*****************************************************************************/

	/* Default constructor.  This will initialize all positions to the default 0
	 * value.  Use the read() function to load the Sudoku puzzle from a file or
	 * the standard input. */
	public ChessSudoku1( int size ) {
		SIZE = size;
		N = size*size;

		grid = new int[N][N];
		for( int i = 0; i < N; i++ )
			for( int j = 0; j < N; j++ )
				grid[i][j] = 0;
	}


	/* readInteger is a helper function for the reading of the input file.  It reads
	 * words until it finds one that represents an integer. For convenience, it will also
	 * recognize the string "x" as equivalent to "0". */
	static int readInteger( InputStream in ) throws Exception {
		int result = 0;
		boolean success = false;

		while( !success ) {
			String word = readWord( in );

			try {
				result = Integer.parseInt( word );
				success = true;
			} catch( Exception e ) {
				// Convert 'x' words into 0's
				if( word.compareTo("x") == 0 ) {
					result = 0;
					success = true;
				}
				// Ignore all other words that are not integers
			}
		}

		return result;
	}


	/* readWord is a helper function that reads a word separated by white space. */
	static String readWord( InputStream in ) throws Exception {
		StringBuffer result = new StringBuffer();
		int currentChar = in.read();
		String whiteSpace = " \t\r\n";
		// Ignore any leading white space
		while( whiteSpace.indexOf(currentChar) > -1 ) {
			currentChar = in.read();
		}

		// Read all characters until you reach white space
		while( whiteSpace.indexOf(currentChar) == -1 ) {
			result.append( (char) currentChar );
			currentChar = in.read();
		}
		return result.toString();
	}


	/* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
	 * grid is filled in one row at at time, from left to right.  All non-valid
	 * characters are ignored by this function and may be used in the Sudoku file
	 * to increase its legibility. */
	public void read( InputStream in ) throws Exception {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				grid[i][j] = readInteger( in );
			}
		}
	}


	/* Helper function for the printing of Sudoku puzzle.  This function will print
	 * out text, preceded by enough ' ' characters to make sure that the printint out
	 * takes at least width characters.  */
	void printFixedWidth( String text, int width ) {
		for( int i = 0; i < width - text.length(); i++ )
			System.out.print( " " );
		System.out.print( text );
	}


	/* The print() function outputs the Sudoku grid to the standard output, using
	 * a bit of extra formatting to make the result clearly readable. */
	public void print() {
		// Compute the number of digits necessary to print out each number in the Sudoku puzzle
		int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

		// Create a dashed line to separate the boxes
		int lineLength = (digits + 1) * N + 2 * SIZE - 3;
		StringBuffer line = new StringBuffer();
		for( int lineInit = 0; lineInit < lineLength; lineInit++ )
			line.append('-');

		// Go through the grid, printing out its values separated by spaces
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				printFixedWidth( String.valueOf( grid[i][j] ), digits );
				// Print the vertical lines between boxes
				if( (j < N-1) && ((j+1) % SIZE == 0) )
					System.out.print( " |" );
				System.out.print( " " );
			}
			System.out.println();

			// Print the horizontal line between boxes
			if( (i < N-1) && ((i+1) % SIZE == 0) )
				System.out.println( line.toString() );
		}
	}


	/* The main function reads in a Sudoku puzzle from the standard input,
	 * unless a file name is provided as a run-time argument, in which case the
	 * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
	 * outputs the completed puzzle to the standard output. */
	public static void main( String args[] ) throws Exception {
		InputStream in = new FileInputStream("veryEasy3x3_twoSolutions.txt");

		// The first number in all Sudoku files must represent the size of the puzzle.  See
		// the example files for the file format.
		int puzzleSize = readInteger( in );
		if( puzzleSize > 100 || puzzleSize < 1 ) {
			System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
			System.exit(-1);
		}

		ChessSudoku s = new ChessSudoku( puzzleSize );

		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;

		// read the rest of the Sudoku puzzle
		s.read( in );

		System.out.println("Before the solve:");
		s.print();
		System.out.println();

		// Solve the puzzle by finding one solution.
		s.solve(false);

		// Print out the (hopefully completed!) puzzle
		System.out.println("After the solve:");
		s.print();
	}
}



