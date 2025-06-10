import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreLabel here.
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

    public void update(String text) 
    {
        setImage(new GreenfootImage(text, 24, Color.WHITE, new Color(0, 0, 0, 160)));
    }
}
