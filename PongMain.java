
/**
 * This is a simple main method that gets the pong game started.
 * 
 * @author Dr. Dorn
 * @version 11/2/12
 * 
 * @editor Kenny Cortum
 * @version 08/03/2023
 */

import csteutils.myro.*; //MyroGUI to get the user to want to play again.
public class PongMain
{
    public static void main(String [] args)
    {
        PongGame theGame = new PongGame();
        theGame.play();

        String answer = ""; //empty string to store user response
        answer = MyroGUI.askQuestion("Would you like to play again?"); //"Yes" starts the while loop. "No" ends the program entirely.
        
        while (answer.equals("Yes")) //repeats the game for as long as the user wants.
        {
            PongGame newGame = new PongGame(); //creates a new PongGame object each time the loop executes
            newGame.play();
            answer = MyroGUI.askQuestion("Would you like to play again?"); //allows the user to restart or exit the game once completed
        }
    }
}
