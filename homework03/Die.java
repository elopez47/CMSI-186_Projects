/*

 Name: Eva Lopez
 File name: Die.java
 Purpose: Creates a die of n sides.
 Date:  2018-02-17

*/
public class Die {

  /**
   * private instance data
   */
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;
   public int nSides;

   // public constructor:
  /**
   * constructor
   * @param nSides int value containing the number of sides to build on THIS Die
   * @throws       IllegalArgumentException
   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
   */
   public Die( int nSides ) {
      this.nSides = nSides;
      if ( nSides >= MINIMUM_SIDES ) {
      } else {
         throw new IllegalArgumentException( "Minimum number of sides is 4." );
      }
   }

  /**
   * Roll THIS die and return the result
   * @return  integer value of the result of the roll, randomly selected
   */
   public int roll() {
      return (int)(Math.floor(Math.random() * this.nSides) + 1);
   }

  /**
   * Get the value of THIS die to return to the caller; note that the way
   *  the count is determined is left as a design decision to the programmer
   *  For example, what about a four-sided die - which face is considered its
   *  "value"?
   * @return the pip count of THIS die instance
   */
   public int getValue() {
      return this.roll();
   }

  /**
   * @param  int  the number of sides to set/reset for this Die instance
   * @return      The new number of sides, in case anyone is looking
   * @throws      IllegalArgumentException
   */
   public void setSides( int sides ) {
     if ( sides >= MINIMUM_SIDES ) {
       this.nSides = sides;
     } else {
        throw new IllegalArgumentException( "Minimum number of sides is 4." );
     }
   }

  /**
   * Public Instance method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public String toString() {
      String result = "[" + this.getValue() + "]";
      return result;
   }

  /**
   * Class-wide method that returns a String representation of THIS die instance
   * @return String representation of this Die
   */
   public static String toString( Die d ) {
      return "";
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
      Die d = new Die(1);
      Die q = new Die(2);
      Die r = new Die(5);
      Die e = new Die(6);
      System.out.println( "Hello world from the Die class..." );
   }

}
