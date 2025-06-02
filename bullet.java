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
        GreenfootImage image = new GreenfootImage("bullet.png");
        if(owner.equals("cat"))
        {
            image.mirrorHorizontally();
            speed = -3;
        }
        else
        {
            speed = 3;
        }
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
        move(speed);
        
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        
        if(owner.equals("cat"))
        {
            Dog target = (Dog)getOneIntersectingObject(Dog.class);
            if(target != null)
            {
                target.takeDamage(10);
                getWorld().removeObject(this);
                return;
            }
        }
        else if(owner.equals("dog"))
        {
            Cat target = (Cat)getOneIntersectingObject(Cat.class);
            if(target != null)
            {
                target.takeDamage(10);
                getWorld().removeObject(this);
                return;
            } 
        }
    }
}
