import csteutils.myro.*;
import java.awt.Color;

/**
 * The Ball class represents a ball in the pong game.  It also maintains
 * the graphical information about the ball on a screen.
 * 
 * @author Kenny Cortum with starter files from Dr. Dorn
 * @version 08/03/2023
 */
public class Ball
{
    // instance variables 
    private MyroCircle theCircle;
    private Screen theScreen;
    private int centerX;
    private int centerY;
    private int horizDirection;
    private int vertDirection;
    
    // Some useful constants
    private final int RADIUS = 5;   //the ball's fixed radius
    // the maximum number of pixels allowed in each dimension within a single move
    private final int MOVEMENT_AMOUNT = 5;  

    /**
     * Constructor for objects of class Ball
     * 
     * @param   s   The Screen on which to place this Ball
     */
    public Ball(Screen s)
    {
        theScreen = s;
        theCircle = new MyroCircle(theScreen.getCanvas(), 0, 0, RADIUS);
        theCircle.setFillColor(Color.BLACK);
        theCircle.makeFilled();
        theCircle.visible();
        
        //Place the ball in the middle and give it a direction
        this.reset();

    }
    
    /**
     * Reset is a method that will cause this Ball to reset to its initial
     * position on the screen.  It will also be given a random trajectory.
     * 
     * Added my own flare here to the vertical direction to create even
     * more random trajectories.
     */
    public void reset()
    {
        int coinFlip;

        centerX = theScreen.getWidth() / 2;
        centerY = theScreen.getHeight() / 2;
        theCircle.setCenter(centerX, centerY);

        coinFlip = MyroUtils.randomInt(0, 1);
        if (coinFlip == 0)
            horizDirection = -MOVEMENT_AMOUNT;
        else
            horizDirection = MOVEMENT_AMOUNT;

        coinFlip = MyroUtils.randomInt(-1, 1); //gives a vertical direction to the movement vector
        if (coinFlip == 0)
            vertDirection = 0; //no movement in the y direction
        else
            vertDirection = coinFlip * MOVEMENT_AMOUNT; //-1 makes the ball go up while 1 makes the ball go down

    }

  
    /**
     * This method causes the ball to "move" within a single timestep.  That is, it 
     * is allowed to move a small distance vertically and/or horizontally from its
     * current position according to it's current trajectory.  Repeated calls to this method
     * will simulate a smooth motion within the Screen.  This method is also responsible
     * for keeping the ball within the given Screen dimensions and allowing it to bounce
     * off of the walls.
     * 
     * @return  The return value signifies whether or not the ball has hit the left
     *          or right walls as a result of this move---aka, a player has scored.
     *          The value will be -1 if this ball hit the left wall, 1 if it hit the 
     *          right wall, and 0 otherwise.
     */
    public int move()
    {
        //TODO: You need to finish writing the move method
        this.theCircle.move(horizDirection, vertDirection);
        this.centerX = this.theCircle.getCenterX();
        this.centerY = this.theCircle.getCenterY();
        
        //if ball hits top wall or bottom wall, but not in the corner, the ball will change to the opposite vertical direction and continue moving.
        if ((this.getY() <= 0 || this.getY() >= this.theScreen.getHeight()) && (this.getX() != 0 || this.getX() != this.theScreen.getWidth()))
        {
            vertDirection = -vertDirection;
            this.theCircle.move(horizDirection, vertDirection);
        }
        
        else if (this.getX() <= 0) //ball hits the left wall
        {
            return -1;
        }
        
        else if (this.getX() >= this.theScreen.getWidth()) //ball hits the right wall
        {
            return 1;
        }
        
        return 0;
        
    }

    /**
     * The bounce method causes this ball to react accordingly when it hits one of the
     * paddles.  Specifically, the horizontal trajectory will be reversed and the vertical
     * trajectory will be randomly affected.
     */
    public void bounce()
    {
        
        horizDirection = -horizDirection;
        int coinFlip = MyroUtils.randomInt(-1, 1); //coinFlip will have only three choices -1, 0, or 1.
        int diceRoll = MyroUtils.randomInt(1, 3); //randomly changes how fast the ball moves after it hits a paddle based on a d3 dice roll.
        
        //Randomly adjust the vertical direction so it moves, veritcally up or down, fast or slow vertically, or does not move vertically at all.
        horizDirection = diceRoll * horizDirection;
        vertDirection = coinFlip * diceRoll * MOVEMENT_AMOUNT;
            
        //Possible extension:  We could make this so it also randomly adjusted the 
        //speed of the ball making it move randomly faster or slower.
    }
    
    /**
     * Simple accessor for getting the center x coordinate of this Ball.
     * 
     * @return  the x coordinate
     */
    public int getX()
    {
        return centerX;
    }
    
    /**
     * Simple accessor for getting the center y coordinate of this Ball.
     * 
     * @return  the y cooridinate
     */
    public int getY()
    {
        return centerY;
    }

    /**
     * Simple accessor for getting the radius of this Ball
     * 
     * @return  the radius
     */
    public int getRadius()
    {
        return RADIUS;
        
    }
        
}
