import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * Pai, Leo 
 * 2025.05.28
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
        setBackground("World/Start.png");
        Label titleLabel = new Label("Two Guys", 80);
        addObject(titleLabel,getWidth()/2,getHeight()/7);
        prepare();
    }
    
    private void prepare()
    {
        HealthBar dummyBar = new HealthBar("HealthBar.png", 1000, false);
        Dog dog = new Dog(dummyBar);
        
        GreenfootImage dogImage = dog.getImage();
        dogImage.scale(dogImage.getWidth() * 3, dogImage.getHeight() * 3);
        dog.setImage(dogImage);
        addObject(dog, 150, 200);
        
        HealthBar dummyBar1 = new HealthBar("HealthBar.png", 1000, false);
        Cat cat = new Cat(dummyBar1);
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
        
        addObject(new StartButton("How to Play", "howto"), 100, 20);
    }
}