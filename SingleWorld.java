import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SingleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SingleWorld extends World
{
    private HealthBar healthBar1;
    private HealthBar healthBar2;
    private boolean isGameOver = false;
    
    public SingleWorld() {
        super(800, 400, 1);
        setBackground("SpaceWorld.png");
        
        healthBar1 = new HealthBar("HealthBar.png",1000, false);
        healthBar2 = new HealthBar("HealthBar.png",1000, true);
        
        Cat cat = new Cat(healthBar2);
        Dog dog = new Dog(healthBar1);
        
        addObject(healthBar1, 95, 20);
        addObject(healthBar2, 705, 20);
        
        addObject(dog, 100, 200);
        addObject(cat, 700, 200);
        
        StartButton exit = new StartButton("Exit", "Exit");
        addObject(exit, 750, 380);
    }
    
    public void gameOver()
    {
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
        if(isGameOver && Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new SingleWorld());
        }
    }
}