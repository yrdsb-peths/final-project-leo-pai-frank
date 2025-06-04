import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Cat extends Actor
{
    private HealthBar myHealthBar;// make the bar owner
    private int shootCooldown = 0;//time shoot
    private int shootDelay = 10;//time shoot
    
    public Cat()
    {
        setImage("cat2.png");//set Image
    }
    
    public Cat(HealthBar opponent)
    {
        this.myHealthBar = opponent;// connect the bar with Cat
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
        if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
        if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - 4);
        // the move control set
        
        if(shootCooldown > 0)
        {
            shootCooldown--;
            //counter for shoot time
        }
        
        if(Greenfoot.isKeyDown("down") && shootCooldown == 0 )
        {
            shoot();
            shootCooldown = shootDelay;
            // the shoot control button
        }
    }
    
    public void shoot()
    {
        bullet bullet = new bullet("cat");
        getWorld().addObject(bullet, getX()-30, getY());
        // the bullet shoot way with cat, from the cat X and Y
    }
    
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
        //the bar lose
    }
}
