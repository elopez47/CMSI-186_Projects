/**
* File Name: DynamicChangeMaker.java
* @author: Eva Lopez
* Date: 2018-04-20
* Description: A program designed to find the optimal change given denominations and an amount.
*/

public class DynamicChangeMaker {

   // Initialing variables
   public static Tuple denominations;
   public static Tuple ZEROTUPLE;
   public static int[] denom;
   public static int target;


  /**
   * Validates denomination arguements
   *
   * @param denominations an int array
   * @return boolean true if the arguments are valid
   * @throws IllegalArgumentException
   *
   */
   public static boolean validateDenominationArgument( int[] denominations ) throws IllegalArgumentException {
      for ( int i = 0; i < denominations.length; i++ ) {
         for ( int j = 0; j < i; j++ ) {
            if ((denominations[i] == denominations[j]) || denominations[i] < 1) {
               throw new IllegalArgumentException(" IMPOSSIBLE BAD DATA: The denominations must be disticnt positive integers.");
            }
         }
      }
      return true;
   }

  /**
   * Validates target arguement
   *
   * @param target an int
   * @return boolean true if the arguments are valid
   * @throws IllegalArgumentException
   *
   */
   public static boolean validateTargetArgument( int target ) throws IllegalArgumentException {
      if (target < 1) {
         throw new IllegalArgumentException(" IMPOSSIBLE BAD DATA: The targetted amount must be a positive integer.");
      }
      return true;
   }

   /**
   * Finds optimal change given demonminations and a target amount
   *
   * @param denom an int array with the possible denominations to be used
   * @param target an integer that is the targetted sum
   * @return tuple with the optimal solution
   *
   */
   public static Tuple makeChangeWithDynamicProgramming( int[] denominations, int target ) {
      validateDenominationArgument(denominations);
      validateTargetArgument(target);

      int rowCount = denominations.length;
      int columnCount = target + 1;

      Tuple[][] table = new Tuple[rowCount][columnCount];

      for ( int i = 0; i < rowCount; i ++ ) {
         for ( int j = 0; j < columnCount; j++ ) {
            table[i][j] = new Tuple(denominations.length);

            if ( j == 0 ) {
               table[i][j] = new Tuple(denominations.length);

            } else if ( (denominations[i]) > j ) {
               table[i][j] = Tuple.IMPOSSIBLE;

               if (i != 0) {
                  if (!((table[i - 1][j]).isImpossible())) {
                     table[i][j] = table[i - 1][j];
                  }
               }

            // Can take one out of j
            } else {

               (table[i][j]).setElement(i, 1);

               if ((table[i][j - denominations[i]]).isImpossible()) {
                  table[i][j] = Tuple.IMPOSSIBLE;
               } else {
                  table[i][j] = (table[i][j - denominations[i]]).add(table[i][j]);

               }

               if (i != 0) {
                  if (table[i - 1 ][j].isImpossible()) {

                  } else if ( !((table[i - 1][j]).isImpossible()) && !((table[i][j]).isImpossible()) ) {
                     if ((table[i - 1][j]).total() < (table[i][j]).total()) {
                        table[i][j] = table[i - 1][j].add(new Tuple(denominations.length));
                     } else {
                        table[i][j] = table[i][j];
                     }
                  } else {
                     table[i][j] = table[i - 1][j];
                  }
               }
            }
         }
      }
      return table[rowCount - 1][columnCount - 1];
   }

  /**
   * Main where the command line arguments can be handled.
   *
   * @param args String array which contains command line arguments
   *
   */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the DynamicChangeMaker program!!\n" );

      try {
         String[] d = args[0].split(",");
         int[] denominations = new int[d.length];
         for ( int i = 0; i < d.length; i++ ) {
            denominations[i] = Integer.parseInt(d[i]);
         }
         validateDenominationArgument(denominations);
         Tuple dt = new Tuple (denominations);
         int target = Integer.parseInt(args[1]);
         validateTargetArgument(target);

         System.out.println("Using the denominations: " + dt.toString() + " to get the targetted amount of: " + target + ", the optimal solution is: ");

         System.out.println(makeChangeWithDynamicProgramming(denominations, target));

      }

      catch ( Exception e ) {
         e.toString();
      }
   }

}
