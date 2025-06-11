import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Single player world 
 * 
 * Leo
 * 2025.06.05
 */
public class SingleWorld extends World
{
    private HealthBar healthBar1;// Health bar for Dog player
    private HealthBar healthBar2;// Health bar for Cat player
    private boolean isGameOver = false; // Flag to indicate if game is over
    private String chosenCharacter;// "Cat" or "Dog"
    private Actor player;// Player character
    private int spawnTimer = 0;// Timer for spawning enemies
    private int killCount = 0;// Number of enemies killed
    private ScoreLabel scoreLabel;// Label to display kills count
    private int spawnInterval = 250;// Interval (frames) between enemy spawns
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/solomusic.wav");// Background music for single player mode
    private boolean musicStarted = false;
    
    //Constructor for SingleWorld.
    public SingleWorld(String choice) 
    {
        super(800, 400, 1);
        setBackground("World/SingleWorld.png");
        this.chosenCharacter = choice;
        
        // Create and add kill count label on top-left
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
        
        // Add the ground object
        Ground ground = new Ground();
        addObject(ground, 380, 390);
        
        // Add Exit button on bottom-right
        StartButton exit = new StartButton("Exit", "exit");
        addObject(exit, 750, 380);
        
        // Add Music toggle button on top-right
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
    }
    
    //Static method to stop background music
    public static void stopMusic() {
        bgMusic.stop();
    }
    
    /**
     * Increase kill count and update label.
     * Also speeds up enemy spawn every 5 kills (to increase difficulty).
     */
    public void addKill() 
    {
        killCount++;
        scoreLabel.update("Kills: " + killCount);
        
        if(killCount % 5 == 0 && spawnInterval > 60)
        {
            spawnInterval -= 60;// Decrease spawn interval to spawn enemies faster
        }
    }
    
    /**
     * The main game loop, called every frame.
     */
    public void act()
    {
        // Start music if not playing
        if (!musicStarted && !bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
            musicStarted = true;
        }
        
        // Spawn enemies every 250 frames
        spawnTimer ++;
        // Spawn enemy when timer reaches the spawn interval
        if(spawnTimer >= spawnInterval)
        {
            spawnEnemy();
            spawnTimer = 0;
        }
        
        // If game is over and space key pressed, restart the same world
        if(isGameOver && Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new SingleWorld(chosenCharacter));
        }
    }
    
    /**
     * Spawn a robot enemy at a random Y position.
     * Spawn on opposite side relative to the player character.
     */
    private void spawnEnemy() 
    {
        int y = 150 + Greenfoot.getRandomNumber(150);// Random Y position
        
        //Make sure the Robot can only appear opposite the character
        if (chosenCharacter.equals("Cat")) 
        {
            // If player is Cat, spawn enemy on left (x=20), moving right
            addObject(new Robot("right"), 20, y);
        } else {
            // If player is Dog, spawn enemy on right (x=780), moving left
            addObject(new Robot("left"), 780, y);
        }
    }
    
    public void stopped() {
        bgMusic.stop(); 
    } 
    
    /**
     * Get the player actor object.
     */
    public Actor getPlayer()
    {
        return player;//return choose player
    }
    
    /**
     * Handle game over state: display "Game Over" and restart instructions.
     */
    public void gameOver()
    {
        if (isGameOver) return;// Prevent multiple calls
        isGameOver = true;
        
        // Display Game Over label at center top
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        
        // Display instructions to restart
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
}