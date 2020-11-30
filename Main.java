import java.util.Scanner;
import java.io.*;
class Main 
{
  public static void main(String[] args) throws FileNotFoundException 
  {
    // print intro
    System.out.println("Hello! Welcome to Ultimate Tic Tac Toe. Here is the board: ");
    // Uncomment below to test cases
    //Scanner sc = new Scanner(new File("winX.dat"));

    Scanner sc = new Scanner(System.in);
    TicTacToe t = new TicTacToe();
    t.printGameGrid();

    System.out.println("Player 1's moves will be recorded with x, and player 2's mmoves will be recorded with o.");

    boolean gameWon = false;

    while (!gameWon) {
      gameWon = t.play(sc);
    }
  }

}
