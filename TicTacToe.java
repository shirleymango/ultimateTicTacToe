import java.util.Scanner;

public class TicTacToe
{
  // declare instance variables below
  Scanner scanner;
  char[][] board = new char[9][9];
  char[][] ultimateBoard = new char[3][3];
  boolean gameOver = false;

  int playerNum = 1;


  // constructor with 1 parameter
  public TicTacToe(Scanner sc)
  {
    //board
    scanner = sc;
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
    int r = sc.nextInt();
    System.out.println("Player " + playerNum + ", enter the column number for your move:");
    int c = sc.nextInt();
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
  }

  public boolean validInput() {
    
    return false;
  }
}
