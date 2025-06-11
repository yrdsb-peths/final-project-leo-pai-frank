import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cat class represents the cat character.
 * 
 * Leo, Frank 
 * 2025.6.02
 */

public class Cat extends Actor
{
    int ySpeed = 0; 
    int gravity = 1; 
    int jumpStrength = -15; 
    boolean onGround = false; 
    private HealthBar myHealthBar;// health bar associated with the cat
    private int shootCooldown = 0;// cooldown time between shots
    private int shootDelay = 10;// shooting delay time
    private GreenfootSound fallSound = new GreenfootSound("fall.wav");
    private GreenfootSound shootSound = new GreenfootSound("shoot.wav");
    private boolean isFallen = false;//make sure the fall sound only show one time
    private int fallSoundDelay = 20;// the fall sound time
    private int fallSoundTimer = 0;//the time to check the fall
    
    public Cat(HealthBar opponent)
    {
        this.myHealthBar = opponent;// connect the bar with Cat
        setImage("cat2.png"); //set Image
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
        if (myHealthBar.getHealth() <= 0 && !isFallen) 
        {
            fallDown();
        }
        if (isFallen) 
        {
            fallSoundTimer++;
            if (fallSoundTimer == fallSoundDelay) 
            {
                fallSound.play();
            }
            if (fallSoundTimer > fallSoundDelay) 
            {
                World currentWorld = getWorld();
                if (currentWorld instanceof MyWorld) {
                    ((MyWorld)currentWorld).gameOver("dogwinner.png");
                } else if (currentWorld instanceof SpaceBattle) {
                    ((SpaceBattle)currentWorld).gameOver("dogwinner.png");
                } else if (currentWorld instanceof SingleWorld) {
                    ((SingleWorld)currentWorld).gameOver();
                }
                isFallen = false;
            }
        }
    }
    
    private void fallDown() {
        shootSound.play();
        isFallen = true;
        fallSoundTimer = 0;
    }
    
    // Apply gravity effect and move down
    public void fall() {
        setLocation(getX(), getY() + ySpeed);
        ySpeed += gravity;
    }

    // Shoot a bullet from the cat
    public void shoot()
    {
        Bullet bullet = new Bullet("cat", -5);
        getWorld().addObject(bullet, getX()-30, getY());
        // the bullet shoot way with cat, from the cat X and Y
    }
    
    // Check if the cat is on the ground
    public void checkGround() {
        if (isTouching(Ground.class)) 
        {
            ySpeed = 0;
            onGround = true;
            while (isTouching(Ground.class))
            {
                setLocation(getX(), getY() - 1);
            }
        } 
        else 
        {
            onGround = false;
        }
    }
    
    // Reduce health by specified amount
    public void takeDamage(int amount)
    {
        myHealthBar.loseHealth(amount);
        //the bar lose
    }
}