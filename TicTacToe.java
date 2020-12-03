import java.util.Scanner;

public class TicTacToe
{
  // declare instance variables below
  Scanner scanner;
  char[][] board = new char[9][9];
  char[][] ultimateBoard = new char[3][3];
  boolean gameOver = false;
  int playerNum = 1;
  int nextRowLB = 0; //lowerbound of valid row for next player's move
  int nextRowUB = 8; //upperbound of valid row for next player's move
  int nextColLB = 0; //lowerbound of valid col for next player's move
  int nextColUB = 8; //upperbound of valid col for next player's move
  int r; int c; int ultRow; int ultCol;
  int numOfFilledSquares = 0;
  int filledSpots[][] = new int[3][3]; //number of filled spots for each smaller grid

  // constructor
  public TicTacToe(Scanner sc)
  {
    scanner = sc;
    //board
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = ' ';
      }
    }

    //ultimate board
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ultimateBoard[i][j] = ' ';
      }
    }

  }

  // prints 9x9 game grid
  public void printGameGrid()
  {
    //board
    System.out.println("    0 1 2   3 4 5   6 7 8");
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0) {
        System.out.println("  - - - - - - - - - - - - -");
      }
      for (int j = 0; j < 9; j++) {
        if (j == 0) {
          System.out.print(i + " ");
        }
        if (j % 3 == 0) {
          System.out.print("| ");
        }
        System.out.print(board[i][j] + " ");
      }
      System.out.println("|");
    }
    System.out.println("  - - - - - - - - - - - - -");

    //ultimate board
    System.out.println("Ultimate Board:");
    System.out.println("- - - - -");
    for (int i = 0; i < 3; i++) {
      System.out.print("|");
      for (int j = 0; j < 3; j++) {
        System.out.print(" " + ultimateBoard[i][j]);
      }
      System.out.println(" |");
    }
    System.out.println("- - - - -");
  }

  public boolean play()
  {
    //tie when there are no more available squares on ultimate board
    if (numOfFilledSquares == 9) {
      System.out.println("Tie!");
      return true;
    }
    //input
    boolean valid = false;
    while (!valid) {
     //input for row
     System.out.println("Enter row: ");
     r = -1;
     while (r == -1) {
       r = validateInput();
     }
     System.out.println(r);

     //input for column
     System.out.println("Player " + playerNum + ", enter a number between " + nextColLB + " and " + nextColUB + " to be the column number for your move:");
     c = -1;
     while (c == -1){
       c = validateInput();
     }
     System.out.println(c);

     valid = isValidMove(r, c);
    }
    playerMove();
    boolean win = checkWin();
    printGameGrid();
    if (win) {
      nextRowLB = 0; nextRowUB = 8;
      nextColLB = 0; nextColUB = 8;
      if (checkUltimateWin() && playerNum == 1) {
        System.out.println("Congratulations, Player 2! You won!");
      }
      else if (checkUltimateWin() && playerNum == 2) {
        System.out.println("Congratulations, Player 1! You won!");
      }
      return checkUltimateWin();
    }
    return false;
  }

  public void playerMove () {
    if (playerNum == 1) {
      board[r][c] = 'x';
      playerNum = 2;
    }
    else {
      board[r][c] = 'o';
      playerNum = 1;
    }

    //update lower and upper bounds for next player's move valid row and column
    if (ultimateBoard[r % 3][c % 3] == ' ') {
      nextRowLB = (r%3)*3;
      nextRowUB = (r%3)*3+2;
      nextColLB = (c%3)*3;
      nextColUB = (c%3)*3+2;
    }
    //when the next spot is already filled, player can play anywhere
    else {
      nextRowLB = 0; nextRowUB = 8;
      nextColLB = 0; nextColUB = 8;
    }
  }

  public int validateInput() {
		String input = scanner.next();
		int n = -1;
		try {
			n = Integer.parseInt(input);
      if (n < 0 || n > 8) {
        System.out.println("Please enter a number between 0 and 8:");
        n = -1;
      }
		}
		catch (NumberFormatException e) {
			System.out.println("Input is invalid. Player " + playerNum + ", enter the row number for your move:");
		}
		return n;
	}

  public boolean isValidMove(int row, int col) {
    //not in correct square
    if (!(row >= nextRowLB && row <= nextRowUB &&
    col >= nextColLB && col <= nextColUB)) {
      System.out.println("Invalid move. This spot is not in the right ultimate row and column.");
      return false;

    }
    //already occupied
    if (board[r][c] != ' ') {
       System.out.println("Invalid move. This spot is already occupied.");
       return false;
     }
    //in a square that was already won
    if (ultimateBoard[row / 3][col / 3] != ' ') {
      System.out.println("Invalid move. This spot is in a square that has been won already.");
      return false;
    }
    return true;
  }


  public boolean checkWin() {
    char player = 'x';
    if (playerNum == 1) {
      player = 'o';
    }

    //check vertical win
    int ultRow = r / 3;
    int rowWins = 0;
    for (int i = ultRow*3; i < ultRow*3 + 3; i++) {
      if (board[i][c] == player) {
        rowWins++;
      }
    }
    
    //check horizontal win
    int ultCol = c / 3;
    int colWins = 0;
    for (int i = ultCol*3; i < ultCol*3 + 3; i++) {
      if (board[r][i] == player) {
        colWins++;
      }
    }

    //check \ diagonal win
    int leftDiagonalWins = 0;
    for (int i = 0; i < 3; i++) {
      if (board[ultRow*3 + i][ultCol*3 + i] == player) {
        leftDiagonalWins++;
      }
    }

    //check / diagonal win
    int rightDiagonalWins = 0;
    for (int i = 0; i < 3; i++) {
      if (board[ultRow*3 + i][ultCol*3 + (2-i)] == player) {
        rightDiagonalWins++;
      }
    }

    //update
    filledSpots[ultRow][ultCol] = filledSpots[ultRow][ultCol] + 1;

    if ((rowWins == 3) || (colWins == 3) || (leftDiagonalWins == 3) || (rightDiagonalWins == 3)) {
      ultimateBoard[ultRow][ultCol] = player;
      numOfFilledSquares++;
      return true;
    }
    if (filledSpots[ultRow][ultCol] == 9) {
      ultimateBoard[ultRow][ultCol] = 't';
      numOfFilledSquares++;
      return true; //tie
    }
    return false;
  }

  public boolean checkUltimateWin () {
    char player = 'x';
    if (playerNum == 1) {
      player = 'o';
    }

    //check vertical win
    int rowWins = 0;
    for (int i = 0; i < 3; i++) {
      if (ultimateBoard[i][ultCol] == player) {
        rowWins++;
      }
    }
    
    //check horizontal win
    int colWins = 0;
    for (int i = 0; i < 3; i++) {
      if (ultimateBoard[ultRow][i] == player) {
        colWins++;
      }
    }

    //check \ diagonal win
    int leftDiagonalWins = 0;
    for (int i = 0; i < 3; i++) {
      if (ultimateBoard[i][i] == player) {
        leftDiagonalWins++;
      }
    }

    //check / diagonal win
    int rightDiagonalWins = 0;
    for (int i = 0; i < 3; i++) {
      if (ultimateBoard[i][2-i] == player) {
        rightDiagonalWins++;
      }
    }

    if ((rowWins == 3) || (colWins == 3) || (leftDiagonalWins == 3) || (rightDiagonalWins == 3)) {
      return true;
    }
    return false;
  }

}
