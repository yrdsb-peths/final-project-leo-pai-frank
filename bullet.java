import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class bullet extends Actor
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 3;
    private String owner;
    
    public bullet(String owner)
    {
        this.owner = owner;
    }
    
    public void act()
    {
        // Add your action code here.
        move(speed);
        if (owner.equals("cat")&&isTouching(Cat.class)) 
        {
            Dog target = (Dog)getOneIntersectingObject(Dog.class);
            target.takeDamage(3);
            getWorld().removeObject(this);    
        }
        else if (owner.equals("dog")&&isTouching(Dog.class)) 
        {
            Cat target = (Cat)getOneIntersectingObject(Cat.class);
            target.takeDamage(3);
            getWorld().removeObject(this);    
        }
        
        if(getX() < 5 || getX() >getWorld().getWidth() - 5)
        {
            getWorld().removeObject(this);
        }
    }
}
