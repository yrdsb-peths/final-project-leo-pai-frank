import greenfoot.*;
/*
 * Frank
 */
public class MyWorld extends World {
    private healthBar healthBar1;
    private healthBar healthBar2;
    public MyWorld() {
        super(600, 400, 1);
        setBackground("resized_image.png");
        
        healthBar1 = new healthBar("HealthBar.png",100);
        healthBar2 = new healthBar("HealthBar.png",100);
        
        Cat cat = new Cat(healthBar2);
        Dog dog = new Dog(healthBar1);
        
        addObject(healthBar1, 95, 20);
        addObject(healthBar2, 505, 20);
        
        addObject(cat, 100, 200);
        addObject(dog, 700, 200);
        
    }
}
