import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start image
 * 
 * Pai, Leo 
 * 2025.05.28
 */
public class TitleScreen extends World
{
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/title1.wav");
    
    /**
     * Constructor to set up the main menu UI.
     */
    public TitleScreen()
    {    
        super(800, 400, 1);
        setBackground("World/Start.png");
        // Add the main title text
        Label titleLabel = new Label("Two Guys", 80);
        addObject(titleLabel,getWidth()/2,getHeight()/7);
        // Prepare menu buttons and player demo images
        prepare();
    }
    
    public void stopped() {
        bgMusic.stop(); 
    }   
    
    public void act()
    {
        // Play title music if not already playing
        if (!bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
        }
    }
    
    /**
     * Static method to stop the title music (called when switching world).
     */
    public static void stopMusic() {
        bgMusic.stop();
    }
    
    /**
     * Set up buttons and demo characters on title screen.
     */
    private void prepare()
    {
        // Create dummy health bar for Dog image demo
        HealthBar dummyBar = new HealthBar("HealthBar.png", 1000, false);
        Dog dog = new Dog(dummyBar);
        
        // Enlarge dog image and add to screen
        GreenfootImage dogImage = dog.getImage();
        dogImage.scale(dogImage.getWidth() * 3, dogImage.getHeight() * 3);
        dog.setImage(dogImage);
        addObject(dog, 150, 200);
        
        // Music toggle button top right
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
        
        // Create dummy health bar for Cat image demo
        HealthBar dummyBar1 = new HealthBar("HealthBar.png", 1000, false);
        Cat cat = new Cat(dummyBar1);
        
        // Enlarge cat image and add to screen
        GreenfootImage catImage = cat.getImage();
        catImage.scale(catImage.getWidth() * 3, catImage.getHeight() * 3);
        cat.setImage(catImage);
        addObject(cat, 650, 200);
        
        // Add buttons for game modes
        StartButton battleGame = new StartButton("Battle Mode", "Battle");
        addObject(battleGame, 600, 330);
        
        StartButton singleGame = new StartButton("Single Mode", "Single");
        addObject(singleGame, 200, 330);
        
        StartButton spaceBattle = new StartButton("Space Battle", "Space");
        addObject(spaceBattle, getWidth()/2, 375);
        
        // Add How to Play / Story button
        addObject(new StartButton("How to Play\nStory", "howto"), 100, 38);
    }
}