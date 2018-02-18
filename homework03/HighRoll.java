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

   public HighRoll( int i, int j ) {
     DiceSet game = new DiceSet( i, j );
   }

   public static void main( String args[] ) {
      // This line uses the two classes to assemble an "input stream" for the user to type
      // text into the program
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );

      System.out.println( "\n Welcome to High Roll!! \n" );
      System.out.println( "Press the 'q' key to quit the program." );
      System.out.println( "Press the enter key to continue." );


      while( true ) { //infinite loop, will break when the user hits the 'q' key
         System.out.print( ">>" ); //prompt
         String inputLine = null;


         try {
            inputLine = input.readLine(); // readLine() method reads the line once the user clicks enter
            if( 0 == inputLine.length() ) { //checks if user entered something
               System.out.println("enter some text!:");
            }

            System.out.println( "\n Enter the number of die you wish play with:" );
            int i = (int)Integer.parseInt(input.readLine());
            System.out.println( "\n Enter the number of sides you want your dice to have:" );
            int j = (int)Integer.parseInt(input.readLine());
            DiceSet game = new DiceSet( i, j );


            System.out.println( "\t MENU" );
            System.out.println( "\n Enter '1' to roll all the dice." );
            System.out.println( "\n Enter '2' to roll a single die." );
            System.out.println( "\n Enter '3' to calculate the score for this set of rolls." );
            System.out.println( "\n Enter '4' to save as high score." );
            System.out.println( "\n Enter '5' to display your high score." );
            System.out.println( "\n Enter 'q' to end the game." );
            System.out.print( ">>" ); //prompt


            String option = inputLine;

            int highScore = 0;
            int k = 0;



            switch (option.charAt(0)) {
               case '1': game.roll();
               case '2': game.rollIndividual(j-1);
               case '3': game.sum();
               case '4': highScore = game.sum();
               case '5': System.out.println("Your high score is: " + highScore);
               case 'q': System.exit(0);
            }
            System.out.println( "   You typed: " + inputLine ); // outputs users input
         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
