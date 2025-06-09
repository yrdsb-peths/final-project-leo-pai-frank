import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharacterButton extends Actor
{
    private String choice;

    public CharacterButton(String choice)
    {
        this.choice = choice;
        GreenfootImage image = new GreenfootImage("Choose " + choice, 50, Color.WHITE, new Color(0, 0, 0, 150));
        setImage(image);
    }

    public void act()
    {
        if(Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new SingleWorld(choice));
        }
    }
}
