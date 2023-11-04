import csteutils.myro.*;

/**
 * This class provides the main controller for a game of pong.
 * 
 * @author Kenny Cortum with starter files from Dr. Dorn
 * @version 08/03/2023
 */
public class PongGame
{
    // instance variables
    private Screen theScreen;
    private Paddle p1Paddle;
    private Paddle p2Paddle;
    private Ball theBall;

    private int p1Score;
    private int p2Score;

    private final int WINNING_SCORE = 3;

    /**
     * Constructor for objects of class PongGame
     */
    public PongGame()
    {
        // initialise instance variables
        theScreen = new Screen();
        p1Paddle = new Paddle(theScreen, 20, 50);
        p2Paddle = new Paddle(theScreen, theScreen.getWidth() - 30, 50);
        theBall = new Ball(theScreen);        

        p1Score = 0;
        p2Score = 0;
    }

    /**
     * The play method runs the main gameplay loop for this Pong game.  It will 
     * continue simulating timesteps until a player has won, updating the game
     * environment accordingly.
     */
    public void play()
    {
        MyroListener.flushKeys();

        // Run the game until someone has won
        while (p1Score < WINNING_SCORE && p2Score < WINNING_SCORE)
        {
            // each iteration here represents a timestep in the game during 

            // Check if either player has tried to move his/her paddle up or down
            if (MyroListener.isKeyPressed())
            {
                if (MyroListener.whichKey() == 'q') // Q means player 1 wants to move up.
                {
                    p1Paddle.up();
                }
                if (MyroListener.whichKey() == 'a') // A means player 1 wants to move down.
                {
                    p1Paddle.down();
                }
                if (MyroListener.whichKey() == 'o') // O means player 2 wants to move up.
                {
                    p2Paddle.up();
                }
                if (MyroListener.whichKey() == 'l') // L means player 2 wants to move down.
                {
                    p2Paddle.down();
                }
            }

            // Update theBall object's current position and saves its return value for later use in the control flow.
            int result = theBall.move();
            //System.out.println(theBall.getX());
            
            // Check to see if the ball has hit either of the paddles, in which case it should turn around using the "bounce" method.
            if ((theBall.getX() < theScreen.getWidth() / 2) && (p1Paddle.isTouching(theBall)))
            {
                theBall.bounce();
            }
            else if ((theBall.getX() > theScreen.getWidth() / 2) && (p2Paddle.isTouching(theBall)))
            {
                theBall.bounce();
            }

            // Check to see if the ball has gone off either side, if so the opponent's 
            // score should be updated and the ball should be placed back in the middle of the
            // screen.
            
            //if ball touches left wall, move assigns result a value of -1, player 2 gets a point, and ball resets to center.
            if (result == -1) 
            {
                p2Score++;
                theScreen.updateScore(2, p2Score);
                theBall.reset();
            }
            
            //if ball touches right wall, move assigns result a value of 1, player 1 gets a point, ball resets to center
            if (result == 1) 
            {
                p1Score++;
                theScreen.updateScore(1, p1Score);
                theBall.reset();
            }

            // Then we pause for a tiny bit to slow the game down, otherwise the ball
            // will move too fast
            MyroUtils.sleep(.1);
        }

        // Once game is over, display the game over animation
        if (p1Score == WINNING_SCORE)
            theScreen.gameOver("Player 1 Wins!");
        else
            theScreen.gameOver("Player 2 Wins!");

    }

}
