/**
 * @author: Eva Lopez
 * File name: Fibonacci.java
 * Purpose: Finds nth Fibonacci number with 0 as the first number in the sequence.
 * Date:  2018-03-29
 */


public class Fibonacci {
   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

   private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
   private static int    maxCount    = 0;
   private static int    working     = 15000;
   private static String end1        = "st";
   private static String end2        = "nd";
   private static String end3        = "rd";
   private static String endRest     = "th";
   private static String cardinality = "";

   private static final  int NO_CMD_LINE_ARGS = -1;
   private static final  int BAD_CMD_LINE_ARG = -2;

   public Fibonacci() {
      super();
   }

   public static void main( String[] args ) {
      System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( NO_CMD_LINE_ARGS );
      }
      try {
         maxCount = Integer.parseInt( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( BAD_CMD_LINE_ARG );
      }
      if( 2 == args.length ) {
         try {
            working = Integer.parseInt( args[1] );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
         }
      }

     // this is just for making the output pretty... no real "fibonacci" functionality
      int lastIndex = args[0].length() - 1;
      switch( args[0].charAt( lastIndex ) ) {
         case '1': cardinality = end1;
                   break;
         case '2': cardinality = end2;
                   break;
         case '3': cardinality = end3;
                   break;
         default : cardinality = endRest;
                   break;
      }

      System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " );

     // NOTE: you may want to handle the first and second Fibonacci numbers as 'special cases'...

     // NOTE: you WILL need to initialize your BrobInts to keep track of things....

     // NOTE: this section is just a happy notification that lets the user know to be patient.......
     BrobInt[] fib = null;
     fib = new BrobInt[maxCount + 1];
     fib[0] = ZERO;
     fib[1] = ONE;
     fib[2] = ONE;
     //fib[3] = TWO;
      if( maxCount < working ) {
         System.out.println( "\n                This may take me a while; please be patient!!\n\n" );
         if (0 == maxCount) {
            System.out.println("The " + maxCount + cardinality + " number in the Fibonacci sequence is " + fib[0]);
         } else if (1 == maxCount || 2 == maxCount) {
            System.out.println("The " + maxCount + cardinality + " number in the Fibonacci sequence is " + fib[1]);
         } else {
            for(int i = 2; i <= maxCount; i++) {
               fib[i] = fib[i-1].addInt(fib[i-2]);
            }
            System.out.println("The " + maxCount + cardinality + " number in the Fibonacci sequence is " + fib[maxCount]);

         }
      }

      System.exit( 0 );
   }
}
