/*

 Name: Eva Lopez
 File name: DieSet.java
 Purpose: Does various to roll n number of dice with m number of sides.
 Date:  2018-02-17

*/
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
      ds = new Die[ count ];
      this.count = count;
      this.sides = sides;

      if ( count <= 0 ) {
         throw new IllegalArgumentException( "The dice count must be greater than 0." );
      } else if ( sides < 4 ) {
         throw new IllegalArgumentException( "The minimum number of sides is 4." );
      } else {
        for ( int i = 0; i < count; i++ ) {
           ds[i] = new Die(sides);
        }
      }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
      int total = 0;

      for ( int i = 0; i <
      count; i++) {
         total += ds[i].getValue();
      }

      return total;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for ( int i = 0; i < count; i++ ) {
        ds[i].roll();
     }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      if ( dieIndex > count || dieIndex <= 0) {
         throw new IllegalArgumentException( "The index entered is out of range." );
      }
      return ds[ dieIndex-1 ].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      if ( dieIndex > count || dieIndex <= 0) {
         throw new IllegalArgumentException( "The index entered is out of range." );
      }
      return ds[ dieIndex-1 ].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
      String result = "";
      for ( int i = 0; i < count; i++ ) {
        result += "[" + ds[i].getValue() + "]";
      }
      return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  true iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
      if ( this.count == ds.count && this.sides == ds.sides && this.sum() == ds.sum() ) {
        return true;
      }
      return false;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      //DiceSet x = new DiceSet(0,3);
      DiceSet y = new DiceSet(3,7);
      DiceSet z = new DiceSet(13,11);
      DiceSet g = new DiceSet(4,6);
      DiceSet j = new DiceSet(3,7);
/*    throws IllegalArgumentException when args are invalid
      System.out.println( x.sum() );
      System.out.println( x.rollIndividual(3) );
      System.out.println( x.rollIndividual(5) );
      System.out.println( x.getIndividual(3) );
      System.out.println( x.getIndividual(5) );
      System.out.println( x.toString() );
      System.out.println( z.isIdentical(g) );
*/
      System.out.println("\n");

      System.out.println( y.sum() );
      System.out.println( y.rollIndividual(3) );
      System.out.println( y.rollIndividual(1) );
      System.out.println( y.getIndividual(3) );
      System.out.println( y.getIndividual(2) );
      System.out.println( y.toString() );
      System.out.println( y.isIdentical(j) );

      System.out.println("\n");

      System.out.println( z.sum() );
      System.out.println( z.rollIndividual(3) );
      System.out.println( z.rollIndividual(1) );
      System.out.println( z.getIndividual(3) );
      System.out.println( z.getIndividual(8) );
      System.out.println( z.toString() );
      System.out.println( z.isIdentical(g) );

      System.out.println("\n");

      System.out.println( g.sum() );
      System.out.println( g.rollIndividual(3) );
      System.out.println( g.rollIndividual(2) );
      System.out.println( g.getIndividual(1) );
      System.out.println( g.getIndividual(2) );
      System.out.println( g.toString() );
      System.out.println( g.isIdentical(g) );

      System.out.println("\n");

      System.out.println( j.sum() );
      System.out.println( j.rollIndividual(3) );
      System.out.println( j.rollIndividual(2) );
      System.out.println( j.getIndividual(3) );
      System.out.println( j.getIndividual(1) );
      System.out.println( j.toString() );
      System.out.println( j.isIdentical(y) );

   }

}
