/*

 Name: Eva Lopez
 File name: CalendarStuff.java
 Purpose: Determines if a given year is a leap year, the days in a given month,
   returns whether or not a date is valid, and counts the number of days between
   two dates.
 Date:  2018-01-17

*/

public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY = 0;
   private static final int MONDAY = SUNDAY + 1;
   private static final int TUESDAY = MONDAY + 1;
   private static final int WEDNESDAY = TUESDAY + 1;
   private static final int THURSDAY = WEDNESDAY + 1;
   private static final int FRIDAY = THURSDAY + 1;
   private static final int SATURDAY = FRIDAY + 1;


  // you can finish the rest on your own

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY = 0;
   private static final int FEBRUARY = JANUARY + 1;
   private static final int MARCH = FEBRUARY + 1;
   private static final int APRIL = MARCH + 1;
   private static final int MAY = APRIL + 1;
   private static final int JUNE = MAY + 1;
   private static final int JULY = JUNE + 1;
   private static final int AUGUST = JULY + 1;
   private static final int SEPTEMBER = AUGUST + 1;
   private static final int OCTOBER = SEPTEMBER + 1;
   private static final int NOVEMBER = OCTOBER + 1;
   private static final int DECEMBER = NOVEMBER + 1;

   private static String[] listOfMonths = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
   private static String[] listOfDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};


  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {

     if ( ( year % 4 == 0 ) || ( ( year % 100 == 0 ) && ( year % 400 == 0 ) ) ) {
       return true;
     } else {
       return false;
     }

   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {

     if ( month == 2 && CalendarStuff.isLeapYear(year) ) {
       return 29;
     } else {
       if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
         return 31;
       } else if ( month == 2) {
         return 28;
       } else {
         return 30;
       }
     }

   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {

     if ( month1 == month2 && day1 == day2 && year1 == year2) {
      return true;
    } else {
      return false;
    }

   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {

     if ( dateEquals( month1, day1, year1, month2, day2, year2 ) ) {
       return 0;
     } else if ( ( year2 > year1 ) || ( month2 > month1 && year1 == year2 ) || ( month1 == month2 && day2 > day1 ) ) {
       return -1;
     } else {
       return 1;
     }

   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {

     // Checks if year is a leap year and if it was given an invalid leap year date.
     if ( month == 2 ) {
       if ( ( !isLeapYear( month ) ) ) {
         if ( ( day > 28 ) || ( day < 1 ) ) {
           return false;
         } else {
           return true;
         }
       } else if ( isLeapYear( month ) ) {
         if ( ( day > 29 ) || day < 1 ) {
           return false;
         } else {
           return true;
         }
       }
     }

    if ( ( month > 12 ) || ( month < 1 ) ) {
      return false;
    }

    if ( (month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12) ) {
      if ( ( day < 1 ) || ( day > 31 ) ) {
        return false;
      } else {
        return true;
      }
    } else {
      if ( ( day < 1 ) || ( day > 30 ) ) {
        return false;
      } else {
        return true;
      }
    }

   }



  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
        case 0: return listOfMonths[0];
        case 1: return listOfMonths[1];
        case 2: return listOfMonths[2];
        case 3: return listOfMonths[3];
        case 4: return listOfMonths[4];
        case 5: return listOfMonths[5];
        case 6: return listOfMonths[6];
        case 7: return listOfMonths[7];
        case 8: return listOfMonths[8];
        case 9: return listOfMonths[9];
        case 10: return listOfMonths[10];
        case 11: return listOfMonths[11];
        default:
          throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
        case 0: return listOfDays[0];
        case 1: return listOfDays[1];
        case 2: return listOfDays[2];
        case 3: return listOfDays[3];
        case 4: return listOfDays[4];
        case 5: return listOfDays[5];
        case 6: return listOfDays[6];
         default : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      return dayCount = 0;

   }

}

