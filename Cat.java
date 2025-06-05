import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Cat extends Actor
{
    int ySpeed = 0; 
    int gravity = 1; 
    int jumpStrength = -15; 
    boolean onGround = false; 
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
        if (getWorld() instanceof TitleScreen)
        {
            setLocation(getX(), getY());
        }
        else if(getWorld() instanceof MyWorld || getWorld() instanceof SingleWorld)
        {
            fall();
            if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
            if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
            if (Greenfoot.isKeyDown("up") && onGround) 
            {
                ySpeed = jumpStrength;
                onGround = false;
            }
            
            checkGround();
            
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
        else if(getWorld() instanceof SpaceBattle)
        {
            if (Greenfoot.isKeyDown("right")) setLocation(getX() + 4, getY());
            if (Greenfoot.isKeyDown("left")) setLocation(getX() - 4, getY());
            if (Greenfoot.isKeyDown("up")) setLocation(getX(), getY() - 4);
            if (Greenfoot.isKeyDown("down")) setLocation(getX(), getY() + 4);
            // the move control set
            
            if(shootCooldown > 0)
            {
                shootCooldown--;
                //counter for shoot time
            }
            
            if(Greenfoot.isKeyDown("shift") && shootCooldown == 0 )
            {
                shoot();
                shootCooldown = shootDelay;
                // the shoot control button
            }
        }
    }
    public void fall() {
        setLocation(getX(), getY() + ySpeed);
        ySpeed += gravity;
    }

    public void shoot()
    {
        bullet bullet = new bullet("cat");
        getWorld().addObject(bullet, getX()-30, getY());
        // the bullet shoot way with cat, from the cat X and Y
    }
    
    public void checkGround() {
        if (isTouching(Ground.class)) 
        {
            ySpeed = 0;
            onGround = true;

        
            while (isTouching(Ground.class)) {
                setLocation(getX(), getY() - 1);
            }
        } 
        else 
        {
            onGround = false;
        }
    }
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
        //the bar lose
    }
}