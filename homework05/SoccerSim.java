/*

Name: Eva Lopez
File name: SoccerSim.java
Purpose: Contains methods that calculate ball location and velocity.
Date:  2018-03-27

*/

public class SoccerSim {

   private static Ball[] balls = null;
   private static double xPole = 152;
   private static double yPole = -245;
   private static double timeSlice = 1;
   private static final double RADIUS_OF_BALL = 4.45;

   public SoccerSim() {

   }

   public void handleInitialArguments( String args[] ) {
      if ( 0 == args.length || args.length % 4 == 2 || args.length % 4 == 3 ) {
         System.out.println( "   Sorry you must enter at least eight arguments\n" +
         "   Usage: java SoccerSim <ball 1's x position> <ball 1's y position> <ball 1's x direction> <ball 1's y direction <ball 2's x position> <ball 2's y position> <ball 2's x direction> <ball 2's y direction> ... [timeSlice]\n" +
         "   Please try again." );
         System.exit( -1 );
      }
      // an array of the arguments but as doubles not strings
      double[] arg = null;

      //for (int j = 0; j < args.length; j++) {
      //   arg[j] = Double.parseDouble(args[j]);
      //}

      if ( args.length % 4 == 0 ) {
         int numberOfBalls = args.length / 4;
         balls = new Ball[ numberOfBalls ];

         /*
         other way to do the for loop to create the balls with their arguements

         for( int i = 0; i < args.length; i+=4 ){
         int j = 0;
         balls[j] = new Ball( args[i], args[i + 1], args[i + 2], args[i + 3], timeSlice )
      }
      */
      try {

         for ( int i = 0; i < numberOfBalls; i++ ) {
            balls[i].validateXPositionArgument(args[i * 4]);
            balls[i].validateYPositionArgument(args[(i * 4) + 1]);
            balls[i].validateXVelocityArgument(args[(i * 4) + 2]);
            balls[i].validateYVelocityArgument(args[(i * 4) + 3]);
            balls[i] = new Ball(Double.parseDouble(args[i * 4]), Double.parseDouble(args[(i * 4) + 1]), Double.parseDouble(args[(i * 4) + 2]), Double.parseDouble(args[(i * 4) + 3]), timeSlice);
         }
      }
      catch (NumberFormatException nfe) {
         System.out.println("Invalid arguments." +
         "\n Usage: java SoccerSim <ball 1's x position> <ball 1's y position> <ball 1's x direction> <ball 1's y direction <ball 2's x position> <ball 2's y position> <ball 2's x direction> <ball 2's y direction> ... [timeSlice]\n" +
         "   Please try again.");
         System.exit(1);
      }

      }

      if ( args.length % 4 == 1 ) {
         int numberOfBalls = ( args.length - 1 ) / 4;
         balls = new Ball[ numberOfBalls ];
         try {
            for ( int i = 0; i < numberOfBalls; i++ ) {
               balls[i] = new Ball(Double.parseDouble(args[i * 4]), Double.parseDouble(args[(i * 4) + 1]), Double.parseDouble(args[(i * 4) + 2]), Double.parseDouble(args[(i * 4) + 3]), Double.parseDouble(args[(args.length - 1)]));
               balls[i].validateXPositionArgument(args[i * 4]);
               balls[i].validateYPositionArgument(args[(i * 4) + 1]);
               balls[i].validateXVelocityArgument(args[(i * 4) + 2]);
               balls[i].validateYVelocityArgument(args[(i * 4) + 3]);
               balls[i].validateTimeSliceArg(args[(args.length - 1)]);
               //balls[i] = new Ball(Double.parseDouble(args[i * 4]), Double.parseDouble(args[(i * 4) + 1]), Double.parseDouble(args[(i * 4) + 2]), Double.parseDouble(args[(i * 4) + 3]), Double.parseDouble(args[(args.length - 1)]));
            }
         }
         catch (NumberFormatException nfe) {
            System.out.println("Invalid arguments." +
            "\n Usage: java SoccerSim <ball 1's x position> <ball 1's y position> <ball 1's x direction> <ball 1's y direction <ball 2's x position> <ball 2's y position> <ball 2's x direction> <ball 2's y direction> ... [timeSlice]\n" +
            "   Please try again.");
            System.exit(1);
         }
      }

   }

   public boolean checkForCollision(Ball i, Ball j) {
      double distance = Math.sqrt( Math.pow(( i.getXPosition() - j.getXPosition() ), 2) + Math.pow(( i.getYPosition() - j.getYPosition() ), 2) );
      if ( distance <= 2 * RADIUS_OF_BALL ) {
         return true;
      }
      return false;
   }

   public static void main( String args[] ) {
      SoccerSim socSim = new SoccerSim();


      Clock clock = new Clock();
      if ( args.length % 4 == 1 ) {
         clock.validateTimeSliceArg( args[args.length - 1] );
      } else {
         clock.validateTimeSliceArg("1");
      }

      socSim.handleInitialArguments(args);

      System.out.println("\n Initial positions:");
      for (int i = 0; i < balls.length; i++ ) {
         System.out.println("Ball " + i + " is at " + balls[i].toString());
      }

      while (true) {
         clock.tick();
         System.out.println("\n Update position at time " + clock.toString());

         for ( int i = 0; i < balls.length ; i++ ) {
            balls[i].move();
            System.out.println(" Ball " + i + " is at " + balls[i].toString());

            if ( (Math.sqrt( Math.pow(( balls[i].getXPosition() - xPole ), 2) + Math.pow(( balls[i].getYPosition() - yPole ), 2))) <= RADIUS_OF_BALL){
               System.out.println("\n Ball " + i + " collided with the pole at time: " + clock.toString());
               System.exit(1);
            }
            if (balls[i].inBounds() == 0.0) {
               System.out.println("\n Ball " + i + " collided with the wall at time: " + clock.toString());
               System.exit(1);
            }

         }

         for ( int i = 0; i < balls.length - 1; i++ ) {
            for ( int j = i + 1; j < balls.length ; j++ ) {
               if( socSim.checkForCollision( balls[i], balls[j] ) ) {
                  System.out.println("\n Balls " + i + " and " + j + " collided at time: " + clock.toString());
                  System.exit(1);
               }
            }

         }

      }
   }

}

