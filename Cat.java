import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cat extends Actor
{
    /**
     * Act - do whatever the Cat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private HealthBar myHealthBar;
    
    public Cat()
    {
        setImage("cat2.png");
    }
    
    public Cat(HealthBar opponent)
    {
        this.myHealthBar = opponent;
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
        if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - 4);
        if(Greenfoot.isKeyDown("down")) shoot();
    }
    
    public void shoot()
    {
        bullet bullet = new bullet("cat");
        getWorld().addObject(bullet, getX()-30, getY());
    }
    
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
    }
}
