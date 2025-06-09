import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectWorld extends World
{
    public SelectWorld()
    {    
        super(800, 400, 1);
        setBackground("Single/Select.png");
        Label title = new Label("Choose Your Character", 60);
        addObject(title, 400, 50);

        CharacterButton catButton = new CharacterButton("Cat");
        CharacterButton dogButton = new CharacterButton("Dog");

        addObject(catButton, 250, 200);
        addObject(dogButton, 550, 200); 
    }
}
