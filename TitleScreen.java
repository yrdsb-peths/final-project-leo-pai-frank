import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * Pai 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Label titleLabel = new Label("Two Guys", 60);
        addObject(titleLabel,getWidth()/2,getHeight()/4);
        prepare();
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("Space"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    private void prepare()
    {
        Dog dog = new Dog();
        addObject(dog,422,133);
    }
}
