import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class healthBar here.
 * 
 * Leo
 * 2025.05.22
 */
public class healthBar extends Actor
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
    public healthBar(String imageName, int maxHealth)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
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
        GreenfootImage healthBar = new GreenfootImage(fullBarImage);
        //copy the bar before, to make the bar be gray
        healthBar.scale(currentWidth, barHeight);
        //deline the bar with width
        
        background.drawImage(healthBar, 0, 0);//draw in gray bar
        setImage(background);
        //set bar background
    }
    /**
     * when dog hurt will use loseHealth
     */
    public void loseHealth(int amount)
    {
        currentHealth -= amount;
        if(currentHealth < 0)
        {
            currentHealth = 0;
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
