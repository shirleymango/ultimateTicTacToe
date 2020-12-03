import java.util.Scanner;
import java.io.*;
class Main 
{
  public static void main(String[] args) throws FileNotFoundException 
  {
    // print intro
    System.out.println("*~*~*~**~*~*~**~*~*~**~*~*~**~*~*~**~*~*~**~*");
    System.out.println("        WELCOME TO ULTIMATE TIC TAC TOE        ");
    System.out.println("*~*~*~**~*~*~**~*~*~**~*~*~**~*~*~**~*~*~**~*");
    System.out.println("Player 1's moves will be recorded with x, and player 2's moves will be recorded with o.");    

    Scanner sc = new Scanner(new File("winX.dat"));
    TicTacToe t = new TicTacToe(sc);
    t.printGameGrid();

    boolean gameWon = false;

    while (!gameWon) {
      gameWon = t.play();
    }
  }

}
