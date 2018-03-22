/*

 Name: Eva Lopez
 File name: SoccerSim.java
 Purpose: Contains methods that calculate ball location and velocity.
 Date:  2018-03-27

*/

public class SoccerSim {

  private Ball[] balls = null;
  private double xPole = 0;
  private double yPole = 0;
  private double timeSlice = 1;


  public SoccerSim() {
    this.xPole = Math.floor( (Math.random() * 300) + 1 );
    this.yPole = Math.floor( (Math.random() * 300) + 1 );
  }

  public void handleInitialArguments( String args[] ) {
     if( 0 == args.length || args.length % 4 == 2 || args.length % 4 == 3 ) {
        System.out.println( "   Sorry you must enter at least eight arguments\n" +
                            "   Usage: java SoccerSim <ball 1's x position> <ball 1's y position> <ball 1's x direction> <ball 1's y direction <ball 2's x position> <ball 2's y position> <ball 2's x direction> <ball 2's y direction> ... [timeSlice]\n" +
                            "   Please try again." );
        System.exit( -1 );
     }

     double[] arg = null;

     for (int j = 0; j < args.length; j++) {
       arg[j] = Double.parseDouble(args[j]);
     }

     if( args.length % 4 == 0 ) {
       int numberOfBalls = args.length / 4;
       balls = new Ball[ numberOfBalls ];

       /*
       other way to do the for loop to create the balls with their arguements

       for( int i = 0; i < args.length; i+=4 ){
          int j = 0;
          balls[j] = new Ball( args[i], args[i + 1], args[i + 2], args[i + 3], timeSlice )
     }
       */

       for ( int i = 0; i < numberOfBalls; i++ ) {
          balls[i] = new Ball(arg[i * 4], arg[(i * 4) + 1], arg[(i * 4) + 2], arg[(i * 4) + 3], timeSlice);
       }
     }

    if( args.length % 4 == 1 ) {
      int numberOfBalls = ( args.length - 1 ) / 4;
      balls = new Ball[ numberOfBalls ];

      for ( int i = 0; i < numberOfBalls; i++ ) {
         balls[i] = new Ball(arg[i * 4], arg[(i * 4) + 1], arg[(i * 4) + 2], arg[(i * 4) + 3], arg[(arg.length - 1)]);
      }

    }

  }

  public String toString() {
    return "";
  }

  public static void main( String args[] ){
    SoccerSim socSim = new SoccerSim();

    Clock clock = new Clock();
    if ( args.lenght % 4 == 1 ) {
       clock.validateTimeSliceArg( arg.length -1 );
    } else {
      clock.validateTimeSliceArg("1");
    }

  }

}

/*

try{
 ball.validateXPositionArgument("");
}
catch(IllegalArguementException iae){
   System.out.println("Bad Arguement");
   System.exit;
}


*/
