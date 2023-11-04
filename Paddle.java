import csteutils.myro.*;
import java.awt.Color;

/**
 * This class represents a paddle within a game of pong.  The paddle is controlled by 
 * a user's input.  Paddles can be used to block a ball from scoring.
 * 
 * @author Kenny Cortum with starter code by Dr. Dorn
 * @version 08/03/2023
 */
public class Paddle
{
    // TODO: declare instance variables you need to model the properties of the Paddle.
    private MyroRectangle paddle;
    private Screen s;
    
    /**
     * Constructor for objects of class Paddle
     * 
     * @param   s   The Screen on which to place this paddle
     * @param   left    The x-coordinate of the top-left corner of the new paddle on the Screen
     * @param   top     The y-coordinate of the top-left corner of the new paddle on the Screen
     */
    public Paddle(Screen s, int left, int top)
    {
        // initialise instance variables as needed and make this new paddle show up
        // on the Screen.
        this.s = s;
        this.paddle = new MyroRectangle(s.getCanvas(), left, top, 20, 100);
        this.paddle.makeFilled();
        Color c = new Color(0, 255, 0);
        this.paddle.setFillColor(c);
        this.paddle.setOutlineColor(c);
        this.paddle.visible();

    }

    
    /**
     * The up method will cause this Paddle to move up on the screen by 20 pixels.
     * The method also prevents the Paddle from going off the screen at the top.
     */
    public void up()
    {  
        //highest position to be able to move up
        final int TOP_MAX = 20;
        if (this.paddle.getTop() >= TOP_MAX)
        {
            this.paddle.move(0, -20);
            this.paddle.visible();
        }
        
    }
    
    /**
     * The down method will cause this Paddle to move down on the screen by 20 pixels.
     * The method also prevents the Paddle from going off the screen at the bottom.
     */
    public void down()
    {
        //lowest position to be able to move down
        final int BOTTOM_MAX = this.s.getHeight() - 20;
        if (this.paddle.getBottom() <= BOTTOM_MAX)
        {
          this.paddle.move(0, 20);
          this.paddle.visible();
        }
    }

    
    /**
     * isTouching is used to determine whether this Paddle is "touching" the Ball
     * object specified in the parameter list.
     * 
     * @param   b   The Ball to compare
     * @return  true if Ball b is directly adjacent to or partially overlapping this Paddle 
     *               in terms of their visible dimensions on screen
     *          false if Ball b is not adjacent to or overlapping this Paddle
     */
    public boolean isTouching(Ball b)
    {
        if (b.getX() < (s.getWidth() / 2)) //ball on the left-hand side of the screen.
        {
            if (b.getX() > this.paddle.getRight()) //ball is between left paddle and the width's halfway point, not touching nor adjacent to left paddle.
            {
                return false;
            }
            else
            {
                if (b.getY() > this.paddle.getTop() && b.getY() < this.paddle.getBottom()) //ball is touching or adjacent to left paddle.
                {
                    return true;
                }
                else //ball is not touching or adjacent to left paddle and will hit the wall.
                {
                    return false;
                }
            }
        }
        else if (b.getX() > (s.getWidth() / 2)) //ball on the right hand side of the screen.
        {
            if (b.getX() < this.paddle.getLeft()) //ball is between the paddle and the halway way point of the width, not touching nor adjacent to right paddle.
            {
                return false;
            }
            else
            {
                if (b.getY() > this.paddle.getTop() && b.getY() < this.paddle.getBottom()) //ball is touching or adjacent to right paddle.
                {
                    return true;
                }
                else //ball is not touching or adjacent to left paddle and will hit the wall.
                {
                    return false;
                }
            }
        }
        else //ball is at the width's halfway point.
        {
            return false;
        }
    }
}
