import greenfoot.*;
/*
 * Frank
 */
public class MyWorld extends World {
    private HealthBar healthBar1;
    private HealthBar healthBar2;
    public MyWorld() {
        super(800, 400, 1);
        setBackground("resized_image.png");
        
        healthBar1 = new HealthBar("HealthBar.png",100);
        healthBar2 = new HealthBar("HealthBar.png",100);
        
        Cat cat = new Cat(healthBar2);
        Dog dog = new Dog(healthBar1);
        
        addObject(healthBar1, 95, 20);
        addObject(healthBar2, 705, 20);
        
        addObject(dog, 100, 200);
        addObject(cat, 700, 200);
        
    }
}
