import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dog here.
 * 
 * @author (your name) frank leo pei
 * @version (a version number or a date)05/11
 */
//the dog should be stonger than cat also more slow than cat
public class Dog extends Actor
{
    /**
     * Act - do whatever the Dog wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int ySpeed = 0; 
    int gravity = 1; //set the gravity 
    int jumpStrength = -15; 
    boolean onGround = false; 
    int speed = 4;
    private HealthBar myHealthBar;// make the bar owner
    private int shootCooldown = 0;//time shoot
    private int shootDelay = 15;//time shoot
    
    public Dog(HealthBar opponent)
    {
        this.myHealthBar = opponent; // connect the bar with Dog
        setImage("dog1.png"); // set Dog image
    }
    
    public Dog()
    {
        setImage("dog1.png"); // set Dog image for title screen
    }
    //set the act mrthod
    public void act()
    {
        if (getWorld() instanceof TitleScreen)
        {
            setLocation(getX(), getY());
        }
        //the action method when in the singlerworld
        else if(getWorld() instanceof MyWorld || getWorld() instanceof SingleWorld)
        {
            fall();
            if (Greenfoot.isKeyDown("a")) { setLocation(getX() - speed, getY()); }
            if (Greenfoot.isKeyDown("d")) { setLocation(getX() + speed, getY()); }
            
            if (Greenfoot.isKeyDown("w") && onGround) 
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
            
            if(Greenfoot.isKeyDown("s") && shootCooldown == 0 )
            {
                shoot();
                shootCooldown = shootDelay;
                // the shoot control button
            }
        }
        //action in spacebattle
        else if(getWorld() instanceof SpaceBattle)
        {
            if (Greenfoot.isKeyDown("w")) { setLocation(getX(), getY() - speed);}
            if (Greenfoot.isKeyDown("a")) { setLocation(getX() - speed, getY());}
            if (Greenfoot.isKeyDown("d")) { setLocation(getX() + speed, getY());}
            if (Greenfoot.isKeyDown("s")) { setLocation(getX(), getY() + speed);}
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
        if (myHealthBar.getHealth() <= 0)
        {
            ((MyWorld)getWorld()).gameOver("catwinner.png");  
        }
    }
    //the method when day are not on the ground
    public void fall() {
        setLocation(getX(), getY() + ySpeed);
        ySpeed += gravity;
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
    //the method that dog shoot
    public void shoot()
    {
        bullet bullet = new bullet("dog", 5);
        getWorld().addObject(bullet, getX() + 30, getY());
        // the bullet shoot way with cat, from the dog X and Y
    }
    
    
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
        //the bar lose
    }
}