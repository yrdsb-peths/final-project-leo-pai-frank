import greenfoot.*;
/**
 * MyWorld class: main game world with 5-minute countdown timer.
 * Manages health bars, characters, and game over conditions.
 * 
 * Frank, Leo, pai
 */
public class MyWorld extends World {
    private HealthBar healthBar1; // Health bar for Dog
    private HealthBar healthBar2; // Health bar for Cat
    private boolean isGameOver = false; // Game over flag
    private int timeLeft;// Remaining time in frames
    private final int totalTime = 60 * 5 * 60;// 5 minutes in frames
    private boolean timeUpHandled = false;// To avoid handling time-up multiple times
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/battlemusic.wav");//music set

    // Setup world
    public MyWorld() {
        super(800, 400, 1);// Create a world of size 800x400
        setBackground("World/resized_image.png");// Set background image
        
        // Create health bars
        healthBar1 = new HealthBar("HealthBar.png",1000, false);// for dog
        healthBar2 = new HealthBar("HealthBar.png",1000, true);// for cat
        
        // Create characters
        Cat cat = new Cat(healthBar2);
        Dog dog = new Dog(healthBar1);
        
        // Add health bars and characters to the world
        addObject(healthBar1, 95, 20);
        addObject(healthBar2, 705, 20);
        addObject(dog, 100, 200);
        addObject(cat, 700, 200);
        
        // Add ground and exit button
        Ground ground = new Ground();
        addObject(ground, 380, 390);
        StartButton exit = new StartButton("Exit", "exit");
        addObject(exit, 750, 380);
        
        timeLeft = totalTime;
        
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
    }
    
    public void stopped() {
        bgMusic.stop(); 
    }  
    
    public static void stopMusic() {
        bgMusic.stop();
    }
    
    // Main game loop
    public void act() 
    {
        if (!bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
        }
        
        if (!isGameOver) {
            if (timeLeft > 0) {
                timeLeft--;
                showTime();
            } else {
                handleTimeUp();
            }
        }
        
        // Restart game if over and space is pressed
        if (isGameOver && Greenfoot.isKeyDown("space")) 
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    // Display remaining time as m:s
    private void showTime() {
        int seconds = timeLeft / 60;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        
        String timeString = String.format("%2d:%2d", minutes, seconds);
        
        if (getObjects(TimeLabel.class).isEmpty()) {
            addObject(new TimeLabel(timeString), 400, 30);
        } else {
            getObjects(TimeLabel.class).get(0).setText(timeString);
        }
    }
    
    // Called when time runs out: reduce health of player with less health to 0
    private void handleTimeUp() 
    {
        if (timeUpHandled) return;
        timeUpHandled = true;
        
        int health1 = healthBar1.getHealth();
        int health2 = healthBar2.getHealth();
        
        if (health1 < health2) {
            healthBar1.loseHealth(health1); 
        } else if (health2 < health1) {
            healthBar2.loseHealth(health2);
        } else {
            // If equal health, both lose all health
            healthBar1.loseHealth(health1);
            healthBar2.loseHealth(health2);
            gameOver("World/noWinner.jpg");
        }
    }
    
    // Show game over screen with winner image and restart instructions
    public void gameOver(String winnerImage)
    {
        if (isGameOver) return; // Avoid multiple calls 
        isGameOver = true;//change isGameOver to true to move gameove
        
        // Show winner image
        GreenfootImage winImg = new GreenfootImage(winnerImage);
        getBackground().drawImage(winImg, getWidth()/2 - winImg.getWidth()/2, getHeight()/2 - winImg.getHeight()/2);
        
        // Show "Game Over" and restart message
        removeObjects(getObjects(null));
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
    
    
}