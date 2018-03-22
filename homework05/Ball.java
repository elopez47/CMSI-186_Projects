/*

 Name: Eva Lopez
 File name: Ball.java
 Purpose: Contains methods that calculate ball location and velocity.
 Date:  2018-03-27

*/
import java.text.DecimalFormat;

public class Ball {
   private static final double RADIUS_OF_BALL = 4.45;
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1;
   private static final double NORTH_END_OF_FIELD = 300;
   private static final double SOUTH_END_OF_FIELD = -300;
   private static final double WEST_END_OF_FIELD = -300;
   private static final double EAST_END_OF_FIELD = 300;
   private static final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private static double[] ballPosition = null;
   private static double[] ballVelocity = null;
   private double xPosition = 0;
   private double yPosition = 0;
   private double xVelocity = 0;
   private double yVelocity = 0;
   private double timeSlice = 1;
   DecimalFormat df = new DecimalFormat("#.00");



   public Ball( double xPosition, double yPosition, double xVelocity, double yVelocity, double timeSlice) {
     this.xPosition = xPosition;
     this.yPosition = yPosition;
     this.xVelocity = xVelocity;
     this.yVelocity = yVelocity;
     this.timeSlice = timeSlice;

     double[] ballPosition = { this.getXPosition() , this.getYPosition() };
     double[] ballVelocity = { this.getXVelocity() , this.getYVelocity() };

   }



   public double validateXPositionArgument( String argValue ) throws NumberFormatException {
      this.xPosition = Double.parseDouble( argValue );
      if ( (argValue == null) || ( xPosition <= WEST_END_OF_FIELD  || xPosition >= EAST_END_OF_FIELD ) )  {
         throw new NumberFormatException( "INVALID X POSITION ARGUMENT: enter a number between " + WEST_END_OF_FIELD + " and " + EAST_END_OF_FIELD + ".");
      }
      return xPosition;
   }

   public double validateYPositionArgument( String argValue ) throws NumberFormatException {
      this.yPosition = Double.parseDouble( argValue );
      if ( (argValue == null) || ( yPosition >= NORTH_END_OF_FIELD  || yPosition <= SOUTH_END_OF_FIELD ) )  {
         throw new NumberFormatException( "INVALID Y POSITION ARGUMENT: enter a number between " + SOUTH_END_OF_FIELD + " and " + NORTH_END_OF_FIELD + ".");
      }
      return yPosition;
   }

   public double validateXVelocityArgument( String argValue ) throws NumberFormatException {
      this.xVelocity = Double.parseDouble( argValue );
      if ( (argValue == null) || ( xVelocity <= WEST_END_OF_FIELD  || xVelocity >= EAST_END_OF_FIELD ) )  {
         throw new NumberFormatException( "INVALID X VELOCITY ARGUMENT: enter a number between " + WEST_END_OF_FIELD + " and " + EAST_END_OF_FIELD + ".");
      }
      return xVelocity;
   }

   public double validateYVelocityArgument( String argValue ) throws NumberFormatException {
      this.yVelocity = Double.parseDouble( argValue );
      if ( (argValue == null) || ( yVelocity >= NORTH_END_OF_FIELD  || yVelocity <= SOUTH_END_OF_FIELD ) )  {
         throw new NumberFormatException( "INVALID Y VELOCITY ARGUMENT: enter a number between " + SOUTH_END_OF_FIELD + " and " + NORTH_END_OF_FIELD + ".");
      }
      return yVelocity;
   }

   public double validateTimeSliceArg( String argValue ) {
      this.timeSlice = Double.parseDouble(argValue);
      if ( (argValue == null) || ( timeSlice <= 0 || timeSlice > MAX_TIME_SLICE_IN_SECONDS  ) ) {
         throw new NumberFormatException( "INVALID TIME SLICE ARGUMENT: The time slice must be between 1 and 1800.");
      }
      return timeSlice;
   }

   public double calculateXPosition() {
      this.xPosition = (xPosition * this.getXVelocity());
      return xPosition;
   }

   public double calculateYPosition() {
      this.yPosition = (yPosition * this.getYVelocity());
      return yPosition;
   }

   public double getXPosition() {
      return xPosition;
   }

   public double getYPosition() {
      return yPosition;
   }

   public double calculateXVelocity() {
      this.xVelocity = this.getXVelocity() - ((this.getXVelocity() * 0.01) * this.timeSlice);
      return xVelocity;
   }

   public double calculateYVelocity() {
      this.yVelocity = this.getYVelocity() - ((this.getYVelocity() * 0.01) * this.timeSlice);
      return yVelocity;
   }

   public double getXVelocity() {
      return xVelocity;
   }

   public double getYVelocity() {
      return yVelocity;
   }

   public void move(){
      this.calculateXPosition();
      this.getXPosition();
      this.calculateYPosition();
      this.getYPosition();
      this.calculateXVelocity();
      this.getXVelocity();
      this.calculateYVelocity();
      this.getYVelocity();
   }

   // If ball is at rest, returns 0.0. If ball is still moving, returns 1.0.
   public double atRest() {
      if( ( this.getXVelocity() <= .083 ) && ( this.getYVelocity() <= .083 ) ) {
         return 1.0;
      }
         return 0.0;
   }

   // If ball is out of bounds, returns 0.0. If ball is still in bounds, returns 1.0.
   public double inBounds(){
      if ( this.calculateXPosition() < WEST_END_OF_FIELD || this.calculateXPosition() > EAST_END_OF_FIELD || this.calculateYPosition() > NORTH_END_OF_FIELD || this.calculateYPosition() < SOUTH_END_OF_FIELD ) {
         return 0.0;
      }
      return 1.0;
   }


   public String toString() {
      return ( "Position (" + df.format(xPosition) + ", " + df.format(yPosition) + "), Velocity (" + df.format(xVelocity) + ", " + df.format(yVelocity) + ")" );
   }

   public static void main( String args[] ) {

     System.out.println( "\n BALL CLASS TESTER PROGRAM\n" +
                         "--------------------------\n" );
     System.out.println( "Creating a ball: " );
     Ball ball = new Ball( 10 , 10, 1, 1, 1 );
     ball.getXPosition();
     ball.getYPosition();
     ball.getXVelocity();
     ball.getYVelocity();
     System.out.println( " \n  Initial ball coordinates: " + ball.toString() );
     ball.move();
     System.out.println( " \n  New ball coordinates: " + ball.toString() );
     ball.move();
     System.out.println( " \n  New ball coordinates: " + ball.toString() );
     ball.move();
     System.out.println( "\n  New ball coordinates: " + ball.toString() );

     //Testing negative numbers
     Ball ball2 = new Ball(-2, 1, 3, -1, 1);
     System.out.println( "\nCreating new ball (ball2): " );
     System.out.println( "\n  Initial coodinates of ball2: " + ball2.toString() );
     ball.move();
     System.out.println( " \n  New ball coordinates: " + ball.toString() );
     ball.move();
     System.out.println( " \n  New ball coordinates: " + ball.toString() );
     ball.move();
     System.out.println( "\n  New ball coordinates: " + ball.toString() );
   }
}
