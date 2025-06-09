import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Robot extends Actor
{
    private int speed = 2;
    private String direction;
    private int shootTimer = 0;
    private int health = 2;

    public Robot(String direction) {
        this.direction = direction;
        GreenfootImage image = new GreenfootImage("Single/Robot.png");
        
        if (direction.equals("left")) 
        {
            setRotation(0);
        } else {
            image.mirrorHorizontally(); 
        }
        
        setImage(image);
    }

    public void act()
    {
        shootBullet();
    }
    
    private void shootBullet()
    {
        shootTimer++;
        
        if (shootTimer >= 100)
        {
            if (getWorld() instanceof SingleWorld) 
            {
            SingleWorld sw = (SingleWorld)getWorld();
            Actor player = sw.getPlayer();

            if (player instanceof Cat) 
            {
                bullet b = new bullet("robot", 5); 
                getWorld().addObject(b, getX() + 20, getY()); 
            } 
            else if (player instanceof Dog) 
            {
                bullet b = new bullet("robot", -5); 
                getWorld().addObject(b, getX() - 20, getY()); 
            }
        }
            shootTimer = 0;
        }
    }
    
    public void takeDamage(int amount)
    {
        health -= amount;
        if (health <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
