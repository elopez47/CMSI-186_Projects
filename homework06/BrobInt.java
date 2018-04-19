/*

 Name: Eva Lopez
 File name: BrobInt.java
 Purpose: Contains methods for large integers.
 Date:  2018-03-29

*/
import java.util.Arrays;

public class BrobInt {

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
   public static String value = null;

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );
   public static final int CHARS_THAT_FIT_IN_INT = 8;

  /// These are the internal fields
   private String internalValue = null;        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string
   private int[] intVersion     = null;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      this.internalValue = value;
      this.validateDigits();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      try {
         int i = 0;
         int length = internalValue.length();
         int start = length - CHARS_THAT_FIT_IN_INT;
         int size = (int)(Math.ceil( length / CHARS_THAT_FIT_IN_INT) + 1);
         this.intVersion = new int[ size ];

         while (length >= CHARS_THAT_FIT_IN_INT) {
            this.intVersion[i] = Integer.parseInt(this.internalValue.substring(start, length));
            start -= CHARS_THAT_FIT_IN_INT;
            length -= CHARS_THAT_FIT_IN_INT;
            i++;
         }

         if (length > 0) {
            this.intVersion[i] = Integer.parseInt(this.internalValue.substring(0, length));
         }
      }
      catch (IllegalArgumentException iae) {
         throw new IllegalArgumentException("Illegal Argument: You can only enter digits.");
      }
      return true;
   }



  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      String result = null;
      for ( int i = internalValue.length() - 1; i > 0; i-- ) {
         result += internalValue.charAt(i);
      }
      BrobInt b = new BrobInt(result);
      return b;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      return gint.reverser();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addInt( BrobInt gint ) {
      int[] result = null;

      if ( internalValue.compareTo( gint.toString() ) < 0 ) { // this < gint

         int size = (int)(Math.ceil( (gint.toString()).length() / CHARS_THAT_FIT_IN_INT) + 1) + 1;
         result = new int[ size ];
         int carry = 0;

         for ( int i = 0; i < this.intVersion.length; i++ ) {
            result[i] = this.intVersion[i] + gint.intVersion[i] + carry;

            if ( result[i] > 99999999 ){
               carry = 1;
            } else {
               carry = 0;
            }
         }

         int start = this.intVersion.length;

         for ( int i = start; i < gint.intVersion.length; i++ ) {
            result[i] = gint.intVersion[i] + carry;
            if ( result[i] > 99999999 ) {
               carry = 1;
            } else {
               carry = 0;
            }
         }

      } else if ( internalValue.compareTo(gint.toString()) > 0 ) { // this > gint

         int size = (int)(Math.ceil( this.internalValue.length() / CHARS_THAT_FIT_IN_INT) + 1) + 1;
         result = new int[ size ];
         int carry = 0;

         for ( int i = 0; i < gint.intVersion.length; i++ ) {
            result[i] = this.intVersion[i] + gint.intVersion[i] + carry;

            if ( result[i] > 99999999 ) {
               carry = 1;
            } else {
               carry = 0;
            }
         }

         int start = gint.intVersion.length;

         for ( int i = start; i < this.intVersion.length; i++ ) {
            result[i] = gint.intVersion[i] + carry;
            if ( result[i] > 99999999 ) {
               carry = 1;
            } else {
               carry = 0;
            }
         }

      } else {

         int size = (int)(Math.ceil( this.internalValue.length() / CHARS_THAT_FIT_IN_INT) + 1) + 1;
         result = new int[ size ];
         int carry = 0;

         for ( int i = 0; i < gint.intVersion.length; i++ ) {
            result[i] = this.intVersion[i] + gint.intVersion[i] + carry;

            if ( result[i] > 99999999 ) {
               carry = 1;
            } else {
               carry = 0;
            }
         }

         if ( carry == 1 ) {
            result[gint.intVersion.length] = carry;
         }
      }

      String sum = "";
      for ( int i = result.length - 2; i >= 0 ; i-- ) {
         sum += result[i];
      }

      return new BrobInt(sum);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractInt( BrobInt gint ) {
      String result = "";
      // set up for subtraction sign handling to decide what to do based on the following cases:
     //    9. both signs negative, this larger abs than arg abs:      remove signs, subtract, add neg to result
     //   10. both signs negative, this smaller abs than arg abs:     remove signs, swap a & b, subtract, result pos
      if (this.equals(gint)) {
         return ZERO;

      } else if ( (this.compareTo(gint) > 0 ) && !(gint.internalValue.contains("-")) && !(internalValue.contains("-")) ) {
         int[] a = intVersion;
         int[] b = gint.intVersion;
         int[] diff = null;
         diff = new int[intVersion.length];

         for (int i = 0; i < diff.length; i++) {
            diff[i] = 0;
         }

         int borrow = 100000000;
         for ( int i = 0; i < gint.intVersion.length; i++ ) {
            diff[i] += intVersion[i] - gint.intVersion[i];
            if (diff[i] < 0) {
               diff[i + 1] -= 1;
               diff[i] = borrow - diff[i];
            }
         }

         for ( int i = diff.length - 1; i >= 0; i--) {
            result += diff[i];
         }
         return new BrobInt(result);

      } else if ( (this.compareTo(gint) < 0) && !(gint.internalValue.contains("-")) && !(internalValue.contains("-")) ) {
         int[] a = gint.intVersion;
         int[] b = intVersion;
         int[] diff = null;
         diff = new int[gint.intVersion.length];

         for (int i = 0; i < diff.length; i++) {
            diff[i] = 0;
         }
         int borrow = 100000000;
         for ( int i = 0; i < intVersion.length; i++ ) {
            diff[i] += intVersion[i] - gint.intVersion[i];
            if (diff[i] < 0) {
               diff[i + 1] -= 1;
               diff[i] = borrow - diff[i];
            }
         }
         for ( int i = diff.length - 1; i >= 0; i--) {
            result += diff[i];
         }

         return new BrobInt(result);

      } else if ((gint.internalValue.contains("-")) && !(internalValue.contains("-"))) {
         String arg = "";
         int i = 0;
         while (i < gint.internalValue.length()) {
            if (gint.internalValue.charAt(i) != '-') {
               arg += gint.internalValue.charAt(i);
            }
            i++;
         }
         BrobInt b = new BrobInt(arg);
         return this.addInt(b);

      } else if ((internalValue.contains("-")) && !(gint.internalValue.contains("-"))) {
         String arg = "-";
         int i = 0;
         while (i < gint.internalValue.length()) {
            arg += gint.internalValue.charAt(i);
            i++;
         }
         BrobInt b = new BrobInt(arg);
         return this.addInt(b);
      } else {
         int[] a = intVersion;
         int[] b = gint.intVersion;
         int[] diff = null;
         diff = new int[intVersion.length];
         for ( int i = 0; i < gint.intVersion.length; i++ ) {
            diff[i] = intVersion[i] - gint.intVersion[i];
         }
         for ( int i = diff.length - 1; i >= 0; i--) {
            result += diff[i];
         }
         return new BrobInt(result);
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
      int[] a = null;
      int[] b = null;
      int[] c = null;

      if (this.internalValue.length() > gint.internalValue.length()) {
         a = new int[this.internalValue.length()];
         b = new int[gint.internalValue.length()];
         int r = 0;
         for (int i = a.length - 1; i >= 0; i--) {
            a[r] = Character.getNumericValue(this.internalValue.charAt(i));
            r++;
         }
         r = 0;
         for (int i = b.length - 1; i >= 0; i--) {
            b[r] = Character.getNumericValue(gint.internalValue.charAt(i));
            r++;
         }
      } else {
         a = new int[gint.internalValue.length()];
         b = new int[this.internalValue.length()];
         int r = 0;
         for (int i = b.length - 1; i >= 0; i--) {
            b[r] = Character.getNumericValue(this.internalValue.charAt(i));
            r++;
         }
         r = 0;
         for (int i = a.length - 1; i >= 0; i--) {
            a[r] = Character.getNumericValue(gint.internalValue.charAt(i));
            r++;
         }
      }

      c = new int[a.length + b.length + 1];

      for ( int i = 0; i < c.length; i++) {
         c[i] = 0;
      }

      int carry = 0;
      int k = 0;

      for ( int i = 0; i < b.length; i++) {
         k = i;
         for ( int j = 0; j < a.length; j++) {
            c[k] = (a[j] * b[i]) + carry + c[k];
            if (c[k] > 9) {
               carry = c[k] / 10;
               c[k] = c[k] % 10;
            } else {
               carry = 0;
            }
            k++;
         }
         if (carry > 0) {
            c[k] += carry;
            carry = 0;
         }
      }


      String result = "";
      for ( int i = c.length - 1; i >= 0; i--) {
         result += c[i];
      }

      int i = 0;
      while (result.charAt(i) == '0') {
         i++;
      }
      String r = result.substring(i, result.length());
      return new BrobInt(r);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      //throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );

      BrobInt d1 = gint;
      BrobInt d2 = new BrobInt(internalValue);
      BrobInt q = ZERO;
      int n = 0;

      if (d1.equals(ZERO)) {
         throw new IllegalArgumentException("Division by zero is undifined.");
      } else if (d1.equals(d2)) {
         return ONE;
      } else if (d1.compareTo(d2) > 0) {
         return ZERO;
      } else {
         n = d1.internalValue.length();
         BrobInt d3 = new BrobInt(d1.internalValue.substring(0,n));
         if (d1.compareTo(d3) > 0) {
            n++;
            d3 = new BrobInt(d1.internalValue.substring(0,n));
         }
         while (n <= d2.toString().length()) {
            while (d3.compareTo(d1) > 0) {
               d1 = d1.subtractInt(d3);
               q = q.addInt(ONE);
            }
            if (n++ == d1.toString().length()) {
               break;
            }
            d3 = d3.multiply(TEN);
            q = q.multiply(TEN);
            d3 = new BrobInt(d3.internalValue + d2.toString().substring( n-1, n ));
            n++;
         }
         return q;
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      //b1.mod(b2)
      //b3 = b1.divide(b2);
      //b3= b3.multiply(b2);
      //b3 = b1.subtractInt(b3);
      //return b3;
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  - if this BrobInt < gint ; 0 if this BrobInt == gint ; + if this BrobInt > gint
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = new Character( internalValue.charAt(i) );
            Character b = new Character( gint.internalValue.charAt(i) );
            if( new Character(a).compareTo( new Character(b) ) > 0 ) {
               return 1;
            } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String intVersionOutput = "";
      for( int i = 0; i < intVersion.length; i++ ) {
         intVersionOutput = intVersionOutput.concat( Integer.toString( intVersion[i] ) );
      }
      intVersionOutput = new String( new StringBuffer( intVersionOutput ).reverse() );
      return this.internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its ints
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt b = new BrobInt("12");
      System.out.println(b.toString());
      //BrobInt a = new BrobInt("384h");
      //System.out.println((b.reverser()).toString());

   }
}



