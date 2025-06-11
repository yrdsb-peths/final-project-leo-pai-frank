import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Give button to jump world
 * 
 * Leo
 * 2025.06.03
 */
public class StartButton extends Actor
{
    private String label;// Button text 
    private String action;// Action type
    private boolean isHovered = false;// Hover effect
    private String choice = "";// Optional choice
    
    public StartButton(String label, String action)
    {
        this.label = label;
        this.action = action;
        updateImage(Color.WHITE);// Default color
    }
    
    public StartButton(String label, String action, String choice) 
    {
        this.label = label;
        this.action = action;
        this.choice = choice;
        updateImage(Color.WHITE);// Default color
    }
    
    public void act()
    {
        // Change color on hover
        if(Greenfoot.mouseMoved(this) && !isHovered)
        {
            updateImage(Color.YELLOW);// Hover color
            isHovered = true;
        }
        else if(Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this) && isHovered)
        {
            updateImage(Color.WHITE);// Back to normal
            isHovered = false;
        }
        
        // Button clicked
        if(Greenfoot.mouseClicked(this))
        {
            TitleScreen.stopMusic();
            if(action.equals("Battle"))
            {
                Greenfoot.setWorld(new MyWorld());// Go to battle mode
            }
            else if(action.equals("Space"))
            {
                Greenfoot.setWorld(new SpaceBattle());// Go to space battle
            }
            else if(action.equals("Single"))
            {
                Greenfoot.setWorld(new SelectWorld());// Single player
            }
            else if(action.equals("exit"))
            {
                Greenfoot.setWorld(new TitleScreen());// Back to title
                //stop world music
                SpaceBattle.stopMusic();
                MyWorld.stopMusic();
                SingleWorld.stopMusic();
                HowToPlayWorld.stopMusic();
            }
            else if(action.equals("howto"))
            {
                Greenfoot.setWorld(new HowToPlayWorld());//Enter the gameplay instructions
            }
        }
    }
    private void updateImage(Color textColor)
    {
        GreenfootImage image = new GreenfootImage(label, 40, textColor, new Color(0, 0, 0, 160));
        setImage(image);// Update button image 
    }
}