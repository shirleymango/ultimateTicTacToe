import java.util.Scanner;

public class TicTacToe
{
  // declare instance variables below
  Scanner scanner;
  char[][] board = new char[9][9];
  char[][] ultimateBoard = new char[3][3];
  boolean gameOver = false;
  int playerNum = 1;
  int nextRowLB = 0; //lowerbound
  int nextRowUB = 8; //upperbound
  int r;
  int c;


  // constructor
  public TicTacToe()
  {
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
    System.out.println("Board:");
    System.out.println("  012 345 678");
    for (int i = 0; i < 9; i++) {
      if (i % 3 == 0) {
        System.out.println(" -------------");
      }
      for (int j = 0; j < 9; j++) {
        if (j == 0) {
          System.out.print(i);
        }
        if (j % 3 == 0) {
          System.out.print("|");
        }
        System.out.print(board[i][j]);
      }
      System.out.println("|");
    }
    System.out.println(" -------------");

    //ultimate board
    System.out.println("Ultimate Board:");
    System.out.println("  0 1 2");
    System.out.println(" -------");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          System.out.print(i);
        }
        System.out.print("|" + ultimateBoard[i][j]);
      }
      System.out.println("|");
      System.out.println(" -------");
    }
  }


  public void play(Scanner sc)
  {
    scanner = sc;
    System.out.println("Player " + playerNum + ", enter the row number for your move:");
    String input = sc.nextLine();
    while (!validInputRow(input)) {
      System.out.println("This input is invalid. Player " + playerNum + ", enter the row number for your move:");
      input = sc.nextLine();
    }
    System.out.println("Player " + playerNum + ", enter the column number for your move:");
    c = sc.nextInt();
    playerMove(r, c);
    printGameGrid();
  }

  public void playerMove (int r, int c) {
    if (playerNum == 1) {
      board[r][c] = 'x';
      playerNum = 2;
    }
    else {
      board[r][c] = 'o';
      playerNum = 1;
    }
    if (ultimateBoard[r % 3][c % 3] == ' ') {
      nextRowLB = r % 3;
      nextRowUB = (r % 3) + 2;
    }
  }

  public boolean validInputRow(String s) {
    //check that only one character was entered
    if (s.length() > 1) {
      return false;
    }
    //check that the character is an integer from 0-8
    char c = s.charAt(0);
    if (c < 48 || c > 56) {
      return false;
    }
    //check that the integer is in the correct boundaries
    int i = c - 48;
    if (i < nextRowLB || i > nextRowUB) {
      return false;
    }
    r = i;
    return true;
  }

  	public int validateInput () {
		String input = scanner.nextLine();
		int n = -1;
		try {
			n = Integer.parseInt(input);
			if (n < nextRowLB || n > nextRowUB) {
				System.out.println("Please enter an integer between " + nextRowLB + " and " + nextRowUB + " (inclusive).");
				n = -1;
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Input is invalid. Player " + playerNum + ", enter the row number for your move:");
		}
		return n;
	}

}
