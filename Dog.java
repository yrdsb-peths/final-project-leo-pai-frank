import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dog here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Dog extends Actor
{
    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 4;
    private HealthBar myHealthBar;// make the bar owner
    private int shootCooldown = 0;//time shoot
    private int shootDelay = 15;//time shoot
    
    public Dog(HealthBar opponent)
    {
        this.myHealthBar = opponent;
    }
    
    public Dog()
    {
        setImage("dog1.png");// connect the bar with Dog
    }
    
    public void act()
    {
        if (getWorld() instanceof TitleScreen)
        {
            setLocation(getX(), getY());
        }
        else if(getWorld() instanceof MyWorld)
        {
            if (Greenfoot.isKeyDown("w")) { setLocation(getX(), getY() - speed); }
            if (Greenfoot.isKeyDown("a")) { setLocation(getX() - speed, getY()); }
            if (Greenfoot.isKeyDown("d")) { setLocation(getX() + speed, getY()); }
            // the move control set
            
            if(shootCooldown > 0)
            {
                shootCooldown--;
                //counter for shoot time
            }
            
            if(Greenfoot.isKeyDown("s") && shootCooldown == 0 )
            {
                shoot();
                shootCooldown = shootDelay;
                // the shoot control button
            }
        }
        else if(getWorld() instanceof SpaceBattle)
        {
            if (Greenfoot.isKeyDown("w")) { setLocation(getX(), getY() - speed);}
            if (Greenfoot.isKeyDown("a")) { setLocation(getX() - speed, getY());}
            if (Greenfoot.isKeyDown("d")) { setLocation(getX() + speed, getY());}
            if (Greenfoot.isKeyDown("s")) { setLocation(getX() + speed, getY());}
            // the move control set
            
            if(shootCooldown > 0)
            {
                shootCooldown--;
                //counter for shoot time
            }
            
            if(Greenfoot.isKeyDown("f") && shootCooldown == 0 )
            {
                shoot();
                shootCooldown = shootDelay;
                // the shoot control button
            }
        }
    }
    
    public void shoot()
    {
        bullet bullet = new bullet("dog");
        getWorld().addObject(bullet, getX() + 30, getY());
        // the bullet shoot way with cat, from the dog X and Y
    }
    
    
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
        //the bar lose
    }
    
}