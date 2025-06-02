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
    private healthBar opponentHealthBar;
    
    public Cat()
    {
        setImage("cat1.png");
    }
    
    public Cat(healthBar opponent)
    {
        this.opponentHealthBar = opponent;
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
        if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - 4);
        if (Greenfoot.isKeyDown("down")) setLocation(getX(), getY() + 4);
        
        if(Greenfoot.isKeyDown("shift")) shoot();
    }
    
    public void shoot()
    {
        bullet bullet = new bullet("dog");
        getWorld().addObject(bullet, getX()-30, getY());
    }
    
    public void takeDamage(int amount)
    {
        opponentHealthBar.loseHealth(amount);
    }
}
