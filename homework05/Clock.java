/*

 Name: Eva Lopez
 File name: Clock.java
 Purpose: Various clock methods.
 Date:  2018-02-21

*/
import java.text.DecimalFormat;
public class Clock {
  /**
   *  Class field definitions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double EPSILON = 3;
   DecimalFormat df = new DecimalFormat("#.00");
   DecimalFormat d = new DecimalFormat("#");

   double timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
   double totalSeconds = 0;
   double hourHandAngle = 0;
   double minuteHandAngle = 0;
   double angleBetweenHands = 0;
   double angle = 0;

  /**
   *  Constructor goes here
   */
   public Clock() {

   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;
      return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      angle = Double.parseDouble(argValue);

      if ( (argValue == null) || ( angle < 0 || angle >= MAXIMUM_DEGREE_VALUE ) )  {
         throw new NumberFormatException( "   INVALID ANGLE ARGUMENT: The angle value must be between 0 and 360.");
      }

      return angle;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
      this.timeSlice = Double.parseDouble(argValue);

      if ( (argValue == null) || ( timeSlice <= 0 || timeSlice > MAX_TIME_SLICE_IN_SECONDS  ) ) {
         throw new NumberFormatException( "   INVALID TIME SLICE ARGUMENT: The time slice must be between 0 and 1800.");
      }

      return timeSlice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      this.hourHandAngle += (( timeSlice ) * ( HOUR_HAND_DEGREES_PER_SECOND )) % 360;
      return hourHandAngle % 360;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      this.minuteHandAngle += (( timeSlice ) * ( MINUTE_HAND_DEGREES_PER_SECOND )) % 360;
      return minuteHandAngle % 360;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      this.angleBetweenHands =  Math.abs( hourHandAngle - minuteHandAngle ) ;
      if ( angleBetweenHands > 180 ) {
         return ( angleBetweenHands - 180 ) % 360;
      }
      return angleBetweenHands % 360;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
     return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() { // HH:MM:SS.SSS

      totalSeconds = getTotalSeconds();
      String stringClock = "";
      double hour = 0;
      double minute = 0;
      double seconds = 0;

      hour = Math.floor( totalSeconds / 3600 );
      minute = Math.floor( ( totalSeconds - ( 3600 * hour ) ) / 60 );
      seconds = ( totalSeconds - ( 3600 * hour ) ) - ( minute * 60 );
      stringClock = ( d.format(hour) ) + " : " + ( d.format(minute) ) + " : " + ( df.format(seconds) );
      return stringClock;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clk = new Clock();
      System.out.println( "    New clock created: " + clk.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clk.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      clk.validateAngleArg("90");
      clk.validateTimeSliceArg("60");
      System.out.println(clk.toString());
      clk.tick();
      clk.tick();
      System.out.println("Total Seconds: " + clk.getTotalSeconds());
      System.out.println(clk.toString());
      System.out.println(clk.getHourHandAngle());
      System.out.println(clk.getMinuteHandAngle());
      System.out.println(clk.getHandAngle());

   }
}
