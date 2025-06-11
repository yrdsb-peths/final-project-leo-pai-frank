import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CharacterButton - button to choose a character
 * 
 * Leo
 * 2025.05.26
 */
public class CharacterButton extends Actor
{
    private String choice;// character name

    public CharacterButton(String choice)
    {
        this.choice = choice;
        // Create image with text "Choose <character>"
        GreenfootImage image = new GreenfootImage("Choose " + choice, 50, Color.WHITE, new Color(0, 0, 0, 150));
        setImage(image);// set the button image
    }

    public void act()
    {
        // When clicked, go to new world with selected character
        if(Greenfoot.mouseClicked(this))
        {
            SelectWorld.stopMusic();
            Greenfoot.setWorld(new SingleWorld(choice));
        }
    }
}
