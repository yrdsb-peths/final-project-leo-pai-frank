import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ScoreLabel displays a text score label on screen.
 * 
 * Leo
 * 2025.06.10
 */
public class ScoreLabel extends Actor
{
    public ScoreLabel(String text) 
    {
        update(text);
    }
    
    // Update the label text with given string
    public void update(String text) 
    {
        setImage(new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 160)));
    }
}
