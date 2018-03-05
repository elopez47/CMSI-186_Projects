/*

 Name: Eva Lopez
 File name: ClockSolver.java
 Purpose: Checks clock for angle between hands.
 Date:  2018-02-21

*/

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static final double EPSILON_VALUE = 3;      // small value for double-precision comparisons
   static double target;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!\n\n" ) ;

      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again." );
         System.exit( -1 );
      }

      Clock clock = new Clock();

      if ( 1 == args.length ) {
        this.target = clock.validateAngleArg( args[0] );
        clock.timeSlice = DEFAULT_TIME_SLICE_SECONDS;
      } else if ( 2 == args.length ) {
         this.target = clock.validateAngleArg( args[0] );
         clock.timeSlice = clock.validateTimeSliceArg( args[1] );
      }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cs = new ClockSolver();
      Clock clock = new Clock();
      double[] timeValues = new double[3];

      cs.handleInitialArguments( args );
      target = clock.validateAngleArg( args[0] );

      if (args.length == 2) {
        clock.timeSlice = clock.validateTimeSliceArg( args[1] );
      }

      System.out.println( "Looking for " + target + " degree angle." ) ;

      double elapsedTime = 0;

      while( elapsedTime < 43200 ) {
        clock.tick();
        elapsedTime = clock.getTotalSeconds();
        clock.getHourHandAngle();
        clock.getMinuteHandAngle();

        if ( Math.abs( clock.getHandAngle() - target ) <= EPSILON_VALUE ) {
           System.out.println("Target angle of was found at: " + clock.toString() );
        }

      }
   }
}
