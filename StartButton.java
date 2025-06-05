import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    private String label;
    private String action;
    private boolean isHovered = false;
    
    public StartButton(String label, String action)
    {
        this.label = label;
        this.action = action;
        updateImage(Color.WHITE);
    }
    
    public void act()
    {
        if(Greenfoot.mouseMoved(this) && !isHovered)
        {
            updateImage(Color.YELLOW);
            isHovered = true;
        }
        else if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && isHovered)
        {
            updateImage(Color.WHITE);
            isHovered = false;
        }
        
        if(Greenfoot.mouseClicked(this))
        {
            if(action.equals("Battle"))
            {
                Greenfoot.setWorld(new MyWorld());
            }
            else if(action.equals("Space"))
            {
                Greenfoot.setWorld(new SpaceBattle());
            }
            else if(action.equals("exit"))
            {
                Greenfoot.setWorld(new TitleScreen());
            }
        }
    }
    private void updateImage(Color textColor)
    {
        GreenfootImage image = new GreenfootImage(label, 40, textColor, new Color(0, 0, 0, 160));
        setImage(image);
    }
}