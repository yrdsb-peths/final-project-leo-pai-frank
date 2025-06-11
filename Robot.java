import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Robot extends Actor
{
    private String direction;// Direction robot is facing
    private int shootTimer = 0;// Timer to control shooting
    private int health = 2;// Robot health

    public Robot(String direction) {
        this.direction = direction;// Set direction
        GreenfootImage image = new GreenfootImage("Single/Robot.png");
        
        if (direction.equals("left")) 
        {
            setRotation(0);// Face left
        } else {
            image.mirrorHorizontally(); // Face right
        }
        
        setImage(image);// Set the robot image
    }

    public void act()
    {
        shootBullet();// Robot tries to shoot
    }
    
    private void shootBullet()
    {
        shootTimer++;// Increase timer
        
        if (shootTimer >= 100)
        {
            if (getWorld() instanceof SingleWorld) // Check if in single player world
            {
            SingleWorld sw = (SingleWorld)getWorld();
            Actor player = sw.getPlayer();// Get current player

            if (player instanceof Cat) 
            {
                Bullet b = new Bullet("robot", 5); // Shoot right
                getWorld().addObject(b, getX() + 20, getY()); 
            } 
            else if (player instanceof Dog) 
            {
                Bullet b = new Bullet("robot", -5); // Shoot left
                getWorld().addObject(b, getX() - 20, getY()); 
            }
        }
            shootTimer = 0;// Reset timer
        }
    }
    
    public void takeDamage(int amount)
    {
        health -= amount;// Reduce health
        if (health <= 0)
        {
            if(getWorld() instanceof SingleWorld)
            {
                ((SingleWorld)getWorld()).addKill();
            }
            getWorld().removeObject(this);// Remove robot if health is 0
        }
    }
}
