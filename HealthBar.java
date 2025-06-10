import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HealthBar - display and update player or enemy health
 * 
 * Leo, Pai，Frank
 * 2025.05.22
 */
public class HealthBar extends Actor
{
    /**
     * Act - do whatever the healthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /**
     * private some variant to help healthBar to decline
     */
    private GreenfootImage fullBarImage;// red health bar image
    private int maxHealth;// maximum health
    private int currentHealth;// current health
    private int barWidth;// bar width
    private int barHeight;// bar height
    private boolean isR = false;// if bar draws from right

    public HealthBar(String imageName, int maxHealth, boolean isR)
    {
        //save the max bar and current bar
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.isR = isR;
        
        fullBarImage = new GreenfootImage("HealthBar.png");// load red bar image
        barWidth = fullBarImage.getWidth();// get width
        barHeight = fullBarImage.getHeight();// get height
        
        // draw the initial full bar
        updateBar();
    }
    
    public void updateBar()
    {
        // Create gray background
        GreenfootImage background = new GreenfootImage(barWidth, barHeight);
        background.setColor(Color.GRAY);
        background.fillRect(0, 0, barWidth, barHeight); 
        
        int currentWidth = (int)((double)currentHealth / maxHealth * barWidth);// calculate remaining width
        if(currentWidth <= 0)
        {
            setImage(new GreenfootImage(barWidth, barHeight));// empty bar
            return;
        }
        
        // Copy red bar image
        GreenfootImage healthBar = new GreenfootImage(fullBarImage);
        healthBar.scale(currentWidth, barHeight);
        
        // Draw bar on background
        if(isR)
        {
            background.drawImage(healthBar, barWidth - currentWidth, 0);//draw in gray bar
        }
        else
        {
            background.drawImage(healthBar, 0, 0);//draw in gray bar
        }
        
        setImage(background);// set final image
    }
    /**
     * when dog hurt will use loseHealth
     */
    public void loseHealth(int amount)
    {
        // Reduce health and check if dead
        
        if(getWorld() instanceof MyWorld)
        {
            currentHealth -= amount;//lose the bar
            MyWorld world = (MyWorld) getWorld();
            if(currentHealth < 0)
            {
                currentHealth = 0;// make sure the bar can't less then 0
                world.removeObject(this);// remove health bar if dead
            }
            updateBar();
        }
        else if(getWorld() instanceof SpaceBattle)
        {
            currentHealth -= amount;//lose the bar
            SpaceBattle world = (SpaceBattle) getWorld();
            if(currentHealth < 0)
            {
                currentHealth = 0;// make sure the bar can't less then 0
                world.removeObject(this); // game over if dead
            }
            updateBar();
        }
        if(getWorld() instanceof SingleWorld)
        {
            currentHealth -= amount;//lose the bar
            SingleWorld world = (SingleWorld) getWorld();
            if(currentHealth < 0)
            {
                currentHealth = 0;// make sure the bar can't less then 0
                world.gameOver();// game over if dead
            }
            updateBar();
        }
    }
    /**
     * return the new bar
     */
    public int getHealth()
    {
        return currentHealth; // return current health
    }
}