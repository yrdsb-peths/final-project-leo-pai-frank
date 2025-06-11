import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * SpaceBattle class: 2-player battle world with 5-minute countdown.
 * Handles health bars, players, timer, and game over.
 * 
 * Leo
 * 2025.06.05
 */
public class SpaceBattle extends World
{
    private HealthBar healthBar1;// Dog player health bar
    private HealthBar healthBar2;// Cat player health bar
    private boolean isGameOver = false;
    private int timeLeft; // Remaining time in frames
    private final int totalTime = 60 * 5 * 60; // 5 minutes in frames
    private boolean timeUpHandled = false; // Flag to prevent repeated handling
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/spacemusic.wav");// Background music for SpaceBattle
    private boolean musicStarted = false;
    
    /**
     * Constructor to set up players, health bars, timer, and UI.
     */
    public SpaceBattle() 
    {
        super(800, 400, 1);
        setBackground("World/SpaceWorld.png");
        
        // Create health bars and characters
        healthBar1 = new HealthBar("HealthBar.png",1000, false);
        healthBar2 = new HealthBar("HealthBar.png",1000, true);
        Cat cat = new Cat(healthBar2);
        Dog dog = new Dog(healthBar1);
        
        // Add them to the world
        addObject(healthBar1, 95, 20);
        addObject(healthBar2, 705, 20);
        addObject(dog, 100, 200);
        addObject(cat, 700, 200);
        
        // Add exit button
        StartButton exit = new StartButton("Exit", "exit");
        addObject(exit, 750, 380);
        
        // Initialize timer
        timeLeft = totalTime;

        // Add music toggle button
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
    }
    
    /**
     * Static method to stop background music.
     */
    public static void stopMusic() {
        bgMusic.stop();
    }
    
    /**
     * Main loop: update timer and handle game over state.
     */
    public void act() 
    {
        // Start music if not playing
        if (!musicStarted && !bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
            musicStarted = true;
        }
        
        if (!isGameOver) {
            if (timeLeft > 0) {
                timeLeft--;// Decrement time left each frame
                showTime();// Update timer display
            } else {
                handleTimeUp();// When time reaches zero
            }
        }
        
        // Restart game when space is pressed after game over
        if (isGameOver && Greenfoot.isKeyDown("space")) 
        {
            Greenfoot.setWorld(new SpaceBattle());
        }
    }
    
    /**
     * Display remaining time as "mm:ss" at the top center.
     */
    private void showTime() 
    {
        int seconds = timeLeft / 60;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        
        // Format time string
        String timeString = String.format("%2d:%2d", minutes, seconds);
        
        // Create or update TimeLabel on screen
        if (getObjects(TimeLabel.class).isEmpty()) {
            addObject(new TimeLabel(timeString), 400, 30);
        } else {
            getObjects(TimeLabel.class).get(0).setText(timeString);
        }
    }
    
    /**
     * Handle game over when time runs out: compare health bars and show result.
     */
    private void handleTimeUp() 
    {
        if (timeUpHandled) return;// Avoid multiple calls
        timeUpHandled = true;
        
        int health1 = healthBar1.getHealth();
        int health2 = healthBar2.getHealth();
        
        if (health1 < health2) {
            healthBar1.loseHealth(health1); // Dog loses all health, Cat wins
        } else if (health2 < health1) {
            healthBar2.loseHealth(health2);// Cat loses all health, Dog wins
        } else {
            // If tie, both lose all health, show no winner image
            healthBar1.loseHealth(health1);
            healthBar2.loseHealth(health2);
            gameOver("World/noWinner.jpg");
        }
    }
    
    public void stopped() {
        bgMusic.stop(); 
    } 
    
    /**
     * Show game over screen with winner image and restart prompt.
     */
    public void gameOver(String winnerImage)
    {
        if (isGameOver) return;// Prevent multiple calls
        isGameOver = true;
        
        // Show winner image
        GreenfootImage winImg = new GreenfootImage(winnerImage);
        getBackground().drawImage(winImg, getWidth()/2 - winImg.getWidth()/2, getHeight()/2 - winImg.getHeight()/2);
        
        // Remove all other actors to clean screen
        removeObjects(getObjects(null));
        // Add Game Over label
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        // Add restart instructions
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
}