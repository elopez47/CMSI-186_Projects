/*

 Name: Eva Lopez
 File name: CalendarStuff.java
 Purpose: Determines if a given year is a leap year, the days in a given month,
   returns whether or not a date is valid, and counts the number of days between
   two dates.
 Date:  2018-01-17

*/


import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  public static char[] VOWELS = { 'a', 'e', 'i', 'o', 'u', 'y' };
  public static char[] alphabet = { ' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                                    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */
   public static boolean containsVowel( String s ) {

     s = s.toLowerCase();

     for ( int i = 0; i < s.length() ; i++ ) {

        for ( int j = 0; j < VOWELS.length ; j++ ) {

          if ( s.charAt(i) == VOWELS[j] ) {
            return true;
         }

       }

     }

      return false;

   }

  /**
   * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
   * the first and last, second and last-but-one, etc. against each other.  If something doesn't
   * match that way, returns false, otherwise returns true.
   *
   * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
   * @return  boolean which is true if this a palindrome, or false otherwise
   */
   public static boolean isPalindrome( String s ) {

     s = s.toLowerCase();

     if ( s.length() == 1 || s.length() == 0 ) {

       return true;

     } else if ( s.length() % 2 == 0) {

       int j = s.length() - 1;

       for ( int i = 0; i < ( s.length() / 2 ) ; i++ ) {

         if ( s.charAt(i) != s.charAt(j) ) {
           return false;
         }

        j = j - 1;
       }

     } else {

       int j = s.length() - 1;

       for ( int i = 0; i <= (( s.length() + 1 ) / 2 - 1); i++ ) {

         if ( s.charAt(i) != s.charAt(j) ) {
           return false;
         }

         j = j - 1;
       }

     }

     return true;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
   * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input
   */
   public static String evensOnly( String s ) {
     System.out.println( "I lowercased the inputted strings which is why the test will print out a failure when compared to uppercase strings." );


     s = s.toLowerCase();

     String result = "";

     for ( int i = 0; i < s.length() ; i++ ) {

       for ( int d = 2; d <= 26 ; d = d + 2 ) {

         if ( s.charAt(i) == alphabet[d] ) {
           result = result + s.charAt(i);
         }

       }

     }

     System.out.println( "Result = " + result );

     return result;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
   * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input
   */
   public static String oddsOnly( String s ) {
     System.out.println( "I lowercased the inputted strings which is why the test will print out a failure when compared to uppercase strings." );


     s = s.toLowerCase();

     String result = "";

     for ( int i = 0; i < s.length() ; i++ ) {

       for ( int d = 1; d <= 25; d = d + 2 ) {

         if ( s.charAt(i) == alphabet[d] ) {
           result = result + s.charAt(i);
         }

       }

     }

      System.out.println( "Result = " + result );
      return result;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;even&quot; letters
   * @return  String containing the &quot;even&quot; letters from the input without duplicates
   */
   public static String evensOnlyNoDupes( String s ) {
     System.out.println( "I lowercased the inputted strings which is why the test will print out a failure when compared to uppercase strings." );

     s = StringStuff.oddsOnly(s);

     String result = "";
     result = result + s.charAt(0);

     for ( int i = 1; i < s.length(); i++ ) {

       if ( s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)) ) {
         result = result + s.charAt(i);
       }

     }

     System.out.println( "Result = " + result );
     return result;
   }

  /**
   * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
   * numbers of the alphabet, but with no duplicate characters in the resulting string.
   *
   * @param s String containing the data to be parsed for &quot;odd&quot; letters
   * @return  String containing the &quot;odd&quot; letters from the input without duplicates
   */
   public static String oddsOnlyNoDupes( String s ) {
     System.out.println( "I lowercased the inputted strings which is why the test will print out a failure when compared to uppercase strings." );

      s = StringStuff.oddsOnly(s);

      String result = "";
      result = result + s.charAt(0);

      for ( int i = 1; i < s.length(); i++ ) {

        if ( s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)) ) {
          result = result + s.charAt(i);
        }

      }

      System.out.println( "Result = " + result );
      return result;
   }

  /**
   * Method to return the reverse of a string passed as an argument
   *
   * @param s String containing the data to be reversed
   * @return  String containing the reverse of the input string
   */
   public static String reverse( String s ) {
     System.out.println( "I lowercased the inputted strings which is why the test will print out a failure when compared to uppercase strings." );

     s = s.toLowerCase();

     String result = "";

     for ( int i = s.length() - 1 ; i >= 0  ; i-- ) {
       result = result + s.charAt(i);
     }

     System.out.println( "Result = " + result );
     return result;
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String BC = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( "containsVowel() should return true: " + containsVowel( blah ) );
      System.out.println( "containsVowel() should return true: " + containsVowel( BC ) );
      System.out.println( "isPalindrome() should return true:" + isPalindrome( pal1 ) );
      System.out.println( "isPalindrome() should return false:" + isPalindrome( pal2 ) );
      System.out.println( "isPalindrome() should return true:" + isPalindrome( pal3 ) );
      System.out.println( "isPalindrome() should return true:" + isPalindrome( pal4 ) );
      System.out.println( "isPalindrome() should return true:" + isPalindrome( pal5 ) );
      System.out.println( "evensOnly() should return xlphn: " + evensOnly( "xylophones" ) );
      System.out.println( "evensOnlyNoDupes() should return rhrlz: " + evensOnlyNoDupes( "rehearsalsz" ) );
      System.out.println( "oddsOnly() should return yooes: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnlyNoDupes() should return yoes: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse() should return zslasraeher: " + reverse( "REHEARSALSZ" ) );
   }
}
