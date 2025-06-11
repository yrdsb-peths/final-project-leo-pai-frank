import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Character selection screen cat and dog
 * 
 * Leo 
 * 2025.06.09
 */
public class SelectWorld extends World
{
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/select1.wav");
    private boolean musicStarted = false;
    
    public SelectWorld()
    {    
        super(800, 400, 1);// Set world size
        setBackground("Single/Select.png");// Set background
        
        Label title = new Label("Choose Your Character", 60);
        addObject(title, 400, 50);
        // Add title label
        
        CharacterButton catButton = new CharacterButton("Cat");
        CharacterButton dogButton = new CharacterButton("Dog");
        addObject(catButton, 250, 200);
        addObject(dogButton, 550, 200); 
        // Add character buttons
        
        MusicButton toggle = new MusicButton(bgMusic);
        addObject(toggle, 750, 30);
    }
    
    public void act()
    {
        // Start music if not playing
        if (!musicStarted && !bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
            musicStarted = true;
        }
    }
    
    public void stopped() {
        bgMusic.stop(); 
    } 
    
    public static void stopMusic() {
        bgMusic.stop();
    }
}
