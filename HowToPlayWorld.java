import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * HowToPlayWorld - A screen that shows game instructions and controls.
 * Supports multiple pages of text and a motivational message.
 * 
 * Leo
 * 2025.06.10
 */
public class HowToPlayWorld extends World
{
    private int currentPage = 0;// Current page of instructions
    private static final int LINES_PER_PAGE = 15;// Number of lines per page
    private static GreenfootSound bgMusic = new GreenfootSound("Worldmode/howToPlay.wav");//music set

        
    // All instruction lines to be displayed (split into pages)
    private String[] lines =
    {
        ">> General Controls:",
        "- Use ARROW KEYS (Cat) or WAD (Dog) to move",
        "- Press DOWN (Cat) or S (Dog) to shoot",
        "- Press UP or W to jump (in battle worlds)",
        "",
        ">> Single Mode:",
        "- You choose either Cat or Dog",
        "- Robots spawn every 5 seconds and shoot bullets",
        "- Avoid robot bullets and shoot them back",
        "- Robots are destroyed after 2 hits",
        "- If your health drops to 0, game over",
        "",
        ">> Battle Mode (1v1 Mode):",
        "- Cat and Dog fight each other",
        "- First to reduce opponent’s health to 0 wins",
        "",
        ">> SpaceBattle (Free Move Mode):",
        "- Use ARROW KEYS or WASD to move freely in 2D space",
        "- Cat uses SHIFT to shoot, Dog uses F to shoot",
        "- Dodge and shoot in open space",
        "",
        ">> Background Story:",
        "- Year: 3124. Animals rule the planet.",
        "- Cats and Dogs were allies once,",
        "- until ancient tech awoke...",
        "- Robotic Drones returned with new power.",
        "- Peace shattered. Now, a war begins.",
        "- You must choose a side.",
        "- Dog or Cat. Earth’s fate is yours.",
        "- Will you survive the chaos?"
    };
    
    // Constructor: create the world and show first page
    public HowToPlayWorld() 
    {
        super(800, 400, 1);
        showPage(currentPage);
        addObject(new StartButton("Back", "exit"), 750, 380);// Return to title
        addObject(new StartButton("Next", "nextPage"), 650, 350);// Go to next page
        
        MusicButton toggle = new MusicButton(bgMusic);// Music toggle
        addObject(toggle, 750, 30);
    }
    
    public static void stopMusic() {
        bgMusic.stop();
    }
    
    // Show instructions based on current page
    private void showPage(int page) {
        getBackground().setColor(Color.BLACK);
        getBackground().fill();// Clear the background
        
        // Draw the title text at the top
        GreenfootImage title = new GreenfootImage("How to Play", 36, Color.YELLOW, new Color(0, 0, 0, 160));
        getBackground().drawImage(title, 340, 10);
    
        // Set text layout values
        int startX = 60;
        int startY = 60;
        int lineHeight = 22;
        int startLine = page * LINES_PER_PAGE;
    
        // Draw each line of instruction on the screen
        for (int i = 0; i < LINES_PER_PAGE && startLine + i < lines.length; i++) {
            GreenfootImage lineImage = new GreenfootImage(lines[startLine + i], 18, Color.WHITE, new Color(0, 0, 0, 0));
            getBackground().drawImage(lineImage, startX, startY + i * lineHeight);
        }
        
        // Show motivational message only on the second page
        if (page == 1) {
            String motto = "Get ready\nyour battle begins now!\nShow your strength!";
            GreenfootImage mottoImage = new GreenfootImage(motto, 45, Color.RED, new Color(0, 0, 0, 0));
            getBackground().drawImage(mottoImage, 335, 125);
        }
    }
    
    public void stopped() {
        bgMusic.stop(); 
    } 
    
    // Handle clicks on the "Next" button to change page
    public void act() 
    {
        // Start music if not playing
        if (!bgMusic.isPlaying()) {
            bgMusic.setVolume(60);
            bgMusic.playLoop();
        }
        
        if(Greenfoot.mouseClicked(getObjects(StartButton.class).get(1))) { 
            currentPage++;
            if (currentPage * LINES_PER_PAGE >= lines.length) {
                currentPage = 0; // Loop back to first page
            }
            showPage(currentPage);
        }
    }
}
