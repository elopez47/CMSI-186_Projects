/*
 Name: Eva Lopez
 File name: HighRoll.java
 Purpose: Die game
 Date:  2018-02-17
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll{

    public static void main( String args[] ) {
      // This line uses the two classes to assemble an "input stream" for the user to type
      // text into the program

      if (args.length != 2) {
        throw new IllegalArgumentException();
      }
      // Parse the arguments.
      int numOfDie = Integer.parseInt(args[0]);
      int numOfSides = Integer.parseInt(args[1]);
      try{

        while( true ) { //infinite loop, will break when the user hits the 'q' key
          System.out.print( ">>" );

          System.out.println( "\n\t Welcome to High Roll! \n" );
          System.out.println( " Press the 'q' key to quit the program." );

          DiceSet game = new DiceSet( numOfDie, numOfSides );

          System.out.println( "\n\t\t MENU" );
          System.out.println( "\n Enter '1' to roll all the dice." );
          System.out.println( "\n Enter '2' to roll a single die." );
          System.out.println( "\n Enter '3' to calculate the score for this set of rolls." );
          System.out.println( "\n Enter '4' to save as high score." );
          System.out.println( "\n Enter '5' to display your high score." );
          System.out.println( "\n Enter 'q' to end the game." );
          System.out.print( ">>" ); //prompt

          int highScore = 0;
          //int k = 0;
          String inputLine = null;
          BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
          String option = inputLine;
          option = input.readLine();

          switch (option.charAt(0)) {
              case '1': game.roll();
              case '2': System.out.println( HighRoll.optionTwo( game ) );
              case '3': System.out.println( game.sum() );
              case '4': highScore = game.sum();
              case '5': System.out.println("Your high score is: " + highScore);
              case 'q': System.exit(0);
              default:
               throw new IllegalArgumentException();

          }
        }
      }
      catch( Exception e ) {
        System.out.println( "Caught IOException" );
      }
    }

    public static int optionTwo(DiceSet ds) {
      System.out.println( "\n Enter the die you wish to roll." );
      String dienum = null;
      BufferedReader input2 = new BufferedReader( new InputStreamReader( System.in ) );

      try{
        dienum = input2.readLine();
      }
      catch(Exception e){
        System.out.println( "Caught IOException" );
      }

      int die = Integer.parseInt( dienum );
      return ds.rollIndividual( die-1 );
    }

  }
