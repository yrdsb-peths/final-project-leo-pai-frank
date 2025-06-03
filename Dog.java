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
    private HealthBar myHealthBar;
    private int shootCooldown = 0;
    private int shootDelay = 15;
    
    public Dog(HealthBar opponent)
    {
        this.myHealthBar = opponent;
    }
    
    public Dog()
    {
        setImage("dog1.png");
    }
    
    public void act()
    {
        // Add your action code here.
        
        if (Greenfoot.isKeyDown("w")) { setLocation(getX(), getY() - speed); }
        if (Greenfoot.isKeyDown("a")) { setLocation(getX() - speed, getY()); }
        if (Greenfoot.isKeyDown("d")) { setLocation(getX() + speed, getY()); }
        
        if(shootCooldown > 0)
        {
            shootCooldown--;
        }
        
        if(Greenfoot.isKeyDown("s") && shootCooldown == 0 )
        {
            shoot();
            shootCooldown = shootDelay;
        }
        
        
    } 
    public void shoot()
    {
        bullet bullet = new bullet("dog");
        getWorld().addObject(bullet, getX() + 30, getY());
    }
    
    
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
    }
    
}
