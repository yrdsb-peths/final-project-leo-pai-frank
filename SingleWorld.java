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
    private String chosenCharacter;
    private Actor player;
    private int spawnTimer = 0;
    
    public SingleWorld(String choice) 
    {
        super(800, 400, 1);
        setBackground("World/SingleWorld.png");
        this.chosenCharacter = choice;
        
        if (choice.equals("Cat")) {
            healthBar2 = new HealthBar("HealthBar.png",1000, true);
            player= new Cat(healthBar2);
            addObject(player, 700, 300);
            addObject(healthBar2, 705, 20);
        } else {
            healthBar1 = new HealthBar("HealthBar.png",1000, false);
            player = new Dog(healthBar1);
            addObject(player, 100, 300);
            addObject(healthBar1, 95, 20);
        }
        
        Ground ground = new Ground();
        addObject(ground, 380, 390);
        
        StartButton exit = new StartButton("Exit", "exit");
        addObject(exit, 750, 380);
    }
    
    public void act()
    {
        spawnTimer ++;
        if(spawnTimer >= 250)
        {
            spawnEnemy();
            spawnTimer = 0;
        }
        
        if(isGameOver && Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new SingleWorld(chosenCharacter));
        }
    }
    
    private void spawnEnemy() 
    {
        int y = 150 + Greenfoot.getRandomNumber(150);
        
        if (chosenCharacter.equals("Cat")) 
        {
            addObject(new Robot("right"), 20, y);
        } else {
            addObject(new Robot("left"), 780, y);
        }
    }
    
    public Actor getPlayer()
    {
        return player;
    }
    
    public void gameOver()
    {
        if (isGameOver) return;
        isGameOver = true;
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
}