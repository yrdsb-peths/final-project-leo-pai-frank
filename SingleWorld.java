import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Single player world 
 * 
 * Leo
 * 2025.06.05
 */
public class SingleWorld extends World
{
    private HealthBar healthBar1;
    private HealthBar healthBar2;
    private boolean isGameOver = false;
    private String chosenCharacter;// "Cat" or "Dog"
    private Actor player;// Player character
    private int spawnTimer = 0;// Timer for spawning enemies
    private int killCount = 0;
    private ScoreLabel scoreLabel;
    private int spawnInterval = 250;
    private GreenfootSound bgMusic = new GreenfootSound("solomusic.mp3");

    
    public SingleWorld(String choice) 
    {
        super(800, 400, 1);
        setBackground("World/SingleWorld.png");
        this.chosenCharacter = choice;
        scoreLabel = new ScoreLabel("Kills: 0");
        addObject(scoreLabel, 80, 60);
        
        // Add player and health bar based on choice
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
        
        // Add ground and exit button
        Ground ground = new Ground();
        addObject(ground, 380, 390);
        StartButton exit = new StartButton("Exit", "exit");
        addObject(exit, 750, 380);
        
        bgMusic.setVolume(60);
        bgMusic.playLoop();
        
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
    }
    
    public void stopped() {
        bgMusic.pause();
    }

     public void started() {
        bgMusic.playLoop();
    }
    
    public void addKill() 
    {
        killCount++;
        scoreLabel.update("Kills: " + killCount);
        
        if(killCount % 5 == 0 && spawnInterval > 60)
        {
            spawnInterval -= 60;
        }
    }
    
    public void act()
    {
        // Spawn enemies every 250 frames
        spawnTimer ++;
        if(spawnTimer >= spawnInterval)
        {
            spawnEnemy();
            spawnTimer = 0;
        }
        
        // Restart if game is over and space is pressed
        if(isGameOver && Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new SingleWorld(chosenCharacter));
        }
    }
    
    private void spawnEnemy() 
    {
        int y = 150 + Greenfoot.getRandomNumber(150);// Random Y position
        
        //Make sure the Robot can only appear opposite the character
        if (chosenCharacter.equals("Cat")) 
        {
            addObject(new Robot("right"), 20, y);
        } else {
            addObject(new Robot("left"), 780, y);
        }
    }
    
    public Actor getPlayer()
    {
        return player;//return choose player
    }
    
    public void gameOver()
    {
        if (isGameOver) return;
        isGameOver = true;
        
        // Show Game Over and restart message
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
}