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
        
        healthBar1 = new healthBar("healthBar.png",100);
        healthBar2 = new healthBar("healthBar.png",100);
        
        addObject(healthBar1, 100, 20);
        addObject(healthBar2, 500, 20);
        
    }
}
