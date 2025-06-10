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
    private HealthBar healthBar1;
    private HealthBar healthBar2;
    private boolean isGameOver = false;
    private int timeLeft; // Remaining time in frames
    private final int totalTime = 60 * 5 * 60; // 5 minutes in frames
    private boolean timeUpHandled = false; // Flag to prevent repeated handling
    private GreenfootSound bgMusic = new GreenfootSound("spacemusic.mp3");

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
        
        timeLeft = totalTime;
        
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
    
    public void act() 
    {
        if (!isGameOver) {
            if (timeLeft > 0) {
                timeLeft--;
                showTime();
            } else {
                handleTimeUp();
            }
        }
        
        // Restart game when space is pressed after game over
        if (isGameOver && Greenfoot.isKeyDown("space")) 
        {
            Greenfoot.setWorld(new SpaceBattle());
        }
    }
    
    private void showTime() 
    {
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
            
            healthBar1.loseHealth(health1);
            healthBar2.loseHealth(health2);
            gameOver("World/noWinner.jpg");
        }
    }
    
    public void gameOver(String winnerImage)
    {
        if (isGameOver) return;
        isGameOver = true;
        
        // Show winner image
        GreenfootImage winImg = new GreenfootImage(winnerImage);
        getBackground().drawImage(winImg, getWidth()/2 - winImg.getWidth()/2, getHeight()/2 - winImg.getHeight()/2);
        
        // Show Game Over and restart message
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 400, 100);
        Label reStart = new Label("Press 'space'\n restart same mode", 80);
        addObject(reStart, 400, 250);
    }
}