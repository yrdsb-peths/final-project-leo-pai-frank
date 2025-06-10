import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Character selection screen
 * 
 * Leo 
 * 2025.06.09
 */
public class SelectWorld extends World
{
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
    }
}
