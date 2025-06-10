import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TimeLabel class displays a text label showing time in the game
 * It updates the displayed time string whenever setText is called
 * 
 * Leo
 * 2025.06.10
 */
public class TimeLabel extends Actor
{
    private GreenfootImage image;// Image to display the text
    
    //Constructor: creates a TimeLabel with initial text
    public TimeLabel(String text) {
        updateText(text);
    }
    
    //Update the displayed text
    public void setText(String text) {
        updateText(text);
    }
    
    //Helper method to create the image with the given text
    //Sets white font color and uses Arial font size 40
    private void updateText(String text) 
    {
        image = new GreenfootImage(150, 50);// Create a blank image of size 150x50
        image.setColor(Color.WHITE);// Set font color to white
        Font font = new Font("Arial", false, false, 40);// Create Arial font, size 40, not bold or italic
        image.setFont(font);// Set the font to the image
        image.drawString(text, 10, 40);// Draw the text at position (10, 40)
        setImage(image);// Set this image as the actor's image
    }
}
