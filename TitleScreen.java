import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * Pai 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(800, 400, 1);
        setBackground("Start.png");
        Label titleLabel = new Label("Two Guys", 80);
        addObject(titleLabel,getWidth()/2,getHeight()/7);
        prepare();
    }
    
    private void prepare()
    {
        Dog dog = new Dog();
        GreenfootImage dogImage = dog.getImage();
        dogImage.scale(dogImage.getWidth() * 3, dogImage.getHeight() * 3);
        dog.setImage(dogImage);
        addObject(dog, 150, 200);
        
        Cat cat = new Cat();
        GreenfootImage catImage = cat.getImage();
        catImage.scale(catImage.getWidth() * 3, catImage.getHeight() * 3);
        cat.setImage(catImage);
        addObject(cat, 650, 200);
        
        StartButton battleGame = new StartButton("Battle Mode", "Battle");
        addObject(battleGame, 600, 330);
        
        StartButton singleGame = new StartButton("Single Mode", "Single");
        addObject(singleGame, 200, 330);
        
        StartButton spaceBattle = new StartButton("Space Battle", "Space");
        addObject(spaceBattle, getWidth()/2, 375);
    }
}