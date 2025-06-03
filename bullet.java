import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * create the bullet code to shoot
 * 
 * Leo
 * 2025, 06, 03
 */
public class bullet extends Actor
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 3;//set the speed for bullet moving
    private String owner;//set the owner bullet
    
    public bullet(String owner)
    {
        this.owner = owner;//owner bullet
        GreenfootImage image = new GreenfootImage("bullet.png");//set image for bullet
        if(owner.equals("cat"))
        {
            image.mirrorHorizontally();
            speed = -3;
        }
        else
        {
            speed = 3;
        }
        /**
         *  the bullet move trajectory
         */
        setImage(image);
    }
    
    public void act()
    {
        move(speed);// bullet moving
        
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
                target.takeDamage(15);
                getWorld().removeObject(this);
                return;
            }
            //
        }
        else if(owner.equals("dog"))
        {
            Cat target = (Cat)getOneIntersectingObject(Cat.class);
            if(target != null)
            {
                target.takeDamage(25);
                getWorld().removeObject(this);
                return;
            } 
        }
    }
}
