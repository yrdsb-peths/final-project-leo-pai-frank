import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class healthBar here.
 * 
 * Leo
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
    private GreenfootImage fullBarImage;//red part
    private int maxHealth;
    private int currentHealth;
    private int barWidth;
    private int barHeight;
    private boolean isR = false;

    public HealthBar(String imageName, int maxHealth, boolean isR)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.isR = isR;
        //save the max bar and current bar
        fullBarImage = new GreenfootImage("HealthBar.png");
        barWidth = fullBarImage.getWidth();
        barHeight = fullBarImage.getHeight();
        //set image for bar
        updateBar();
    }
    
    public void updateBar()
    {
        GreenfootImage background = new GreenfootImage(barWidth, barHeight);
        background.setColor(Color.GRAY);
        background.fillRect(0, 0, barWidth, barHeight);
        //set new bar image 
        
        int currentWidth = (int)((double)currentHealth / maxHealth * barWidth);
        if(currentWidth <= 0)
        {
            setImage(new GreenfootImage(barWidth, barHeight));
            return;
            //when the bar less then 0, copy the create image.
        }
        
        GreenfootImage healthBar = new GreenfootImage(fullBarImage);
        //copy the bar before, to make the bar be gray
        healthBar.scale(currentWidth, barHeight);
        //deline the bar with width
        if(isR)
        {
            background.drawImage(healthBar, barWidth - currentWidth, 0);//draw in gray bar
        }
        else
        {
            background.drawImage(healthBar, 0, 0);//draw in gray bar
        }
        
        setImage(background); //set bar background
    }
    /**
     * when dog hurt will use loseHealth
     */
    public void loseHealth(int amount)
    {
        if(getWorld() instanceof MyWorld)
        {
            currentHealth -= amount;//lose the bar
            MyWorld world = (MyWorld) getWorld();
            if(currentHealth < 0)
            {
                currentHealth = 0;// make sure the bar can't less then 0
                world.gameOver();
                world.removeObject(this);
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
                world.gameOver();
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
                world.gameOver();
            }
            updateBar();
        }
    }
    /**
     * return the new bar
     */
    public int getHealth()
    {
        return currentHealth;
    }
}