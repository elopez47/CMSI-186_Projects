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

      if (args.length != 2) {
        System.out.println( "You must provide two arguments: the number of die and the number of sides." );
        System.exit(0);
      }
      // Parse the arguments.
      int numOfDie = Integer.parseInt(args[0]);
      int numOfSides = Integer.parseInt(args[1]);

      try{

        while( true ) { //infinite loop, will break when the user hits the 'q' key

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
          System.out.print( ">> " ); //prompt

          int highScore = 0;

          String inputLine = null;
          BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
          String option = inputLine;
          option = input.readLine();


          if ( option.charAt(0) == '1' ) {
            System.out.println( game.toString() );
          } else if ( option.charAt(0) == '2' ) {
            System.out.println( " The new value for die you rolled is: " + HighRoll.optionTwo( game ) );
          } else if ( option.charAt(0) == '3' ) {
            System.out.println( " The sum of rolls is: " + game.sum() );
          } else if ( option.charAt(0) == '4' ) {
            highScore = game.sum();
            System.out.println( " Your score was saved. Your new high score is: " + highScore);
          } else if ( option.charAt(0) == '5' ) {
            System.out.println( " Your high score is: " + highScore);
          } else if ( option.charAt(0) == 'q' ) {
            System.exit(0);
          } else {
            System.out.println( " Enter some text." );
          }
        }
      }
      catch( Exception e ) {

        System.out.println( "Caught IOException" );

      }
    }

    public static int optionTwo( DiceSet ds  ) {
      System.out.println( "\n Enter the die you wish to roll." );
      System.out.print( ">> " ); //prompt
      String dienum = null;
      BufferedReader input2 = new BufferedReader( new InputStreamReader( System.in ) );

      try {
        dienum = input2.readLine();
      }
      catch( Exception e ) {
        System.out.println( "Caught IOException" );
      }

      int die = Integer.parseInt( dienum );
      return ds.rollIndividual( die );
    }

  }
