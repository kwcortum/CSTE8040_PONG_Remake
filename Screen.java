/**
 * The Screen represents the playing field of a game of Pong
 * 
 * @author Kenny Cortum with starter code by Dr. Dorn
 * @version 08/03/2023
 */

import csteutils.myro.*;
import java.awt.Color;
//import csteutils.*;

public class Screen
{
    // instance variables 
    private MyroCanvas theCanvas;

    private MyroText p1ScoreLabel;
    private MyroText p2ScoreLabel;

    // helpful constants for the screen size
    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    /**
     * Constructor for objects of class Screen
     */
    public Screen()
    {
        // initialise instance variables
        theCanvas = new MyroCanvas("CS114 Pong!", WIDTH, HEIGHT);
        theCanvas.setAutoRepaint(true);

        p1ScoreLabel = new MyroText(theCanvas, 10, 10, "Player 1: 0");
        p2ScoreLabel = new MyroText(theCanvas, WIDTH - 160, 10, "Player 2: 0");

        p1ScoreLabel.visible();
        p2ScoreLabel.visible();

    }

    /**
     * Used to update a player's score label on the screen
     * 
     * @param   player      which player to update, either 1 or 2
     * @param   newScore    the specified player's current score to be displayed
     */
    public void updateScore(int player, int newScore)
    {
        if (player == 1)
            p1ScoreLabel.setText("Player 1: " + newScore);
        else if (player == 2)
            p2ScoreLabel.setText("Player 2: " + newScore);

        //oherwise we have an error
    }

    /**
     * A simple accessor to retrieve the underlying canvas for this Screen
     * 
     * @return  the canvas
     */
    public MyroCanvas getCanvas()
    {
        return theCanvas;
    }

    /**
     * A simple accessor to retrieve this Screen's width
     * 
     * @return the width of the Screen
     */
    public int getWidth()
    {
        return WIDTH;
    }

    /**
     * A simple accessor to retrieve this Screen's height
     * 
     * @return the height of the Screen
     */
    public int getHeight()
    {
        return HEIGHT;
    }

    /**
     * The gameOver method provides a unique animation that should be displayed
     * on the Screen at the end of the game (when a player won). The animation
     * consists of three purple stars and "travelling" MyroText message.
     * 
     * @param   message     A text message to be incorporated as part of the animation (e.g., "Player 1 Won!")
     */
    public void gameOver(String message)
    {
        MyroCanvas winnerCanvas = new MyroCanvas(message, theCanvas.getWidth(), theCanvas.getHeight()); //creates new canvas for animation
        winnerCanvas.setAutoRepaint(true);
        int numberOfStars = 3; //creates the number of stars wanted.
        for (int i = 0; i < numberOfStars ; i++)
        {
            MyroStar myStar = new MyroStar(winnerCanvas, (200 * i) + 100, winnerCanvas.getHeight() / 2 , 25); //spaces out the stars evenly using a for loop.
            Color c = new Color(255, 0, 255);
            myStar.makeFilled();
            myStar.setFillColor(c);
            myStar.setOutlineColor(c);
            myStar.visible();
        }

        int textX = 0; //coordinate needed to control MyroTexts top-left x-coordinate.
        int textY = 0; //coordinate needed to control MyroTexts top-left y-coordinate.
        MyroText newMessage = new MyroText(winnerCanvas, textX, textY, message);
        newMessage.visible();
        
        //the for loop uses the canvas's ratio of 2 height for every 3 width in order to make the text appear to travel.
        //the for loop uses the incrementer in order for the text to travel diagonally from top-left to bottom-right.
        for (int i = 0; i < 200; i++)
        {
            textX = i * 3;
            textY = i * 2;
            newMessage.setCoordinate(textX, textY);
            MyroUtils.sleep(0.1);

        }
        
        //the for loop uses the canvas's ratio of 2 height for every 3 width in order to make the text appear to travel.
        //the for loop uses the incrementer in order for the text to travel diagonally from bottom-right to top-left.
        for (int i = 200; i > 0; i--)
        {
            textX = i * 3;
            textY = i * 2;
            newMessage.setCoordinate(textX, textY);
            MyroUtils.sleep(0.1);
            if (newMessage.getCenterX() == winnerCanvas.getWidth() / 2) //when the center of the text is centered along the width, the text will stop moving.
            {
                break;
            }
        }
    }

}
