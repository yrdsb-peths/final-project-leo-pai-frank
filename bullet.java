import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * create the bullet code to shoot
 * 
 * Leo
 * 2025, 06, 03
 */
public class bullet extends Actor
{
    /**
     * Act - do whatever the bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 0;//set the speed for bullet moving
    private String owner;//set the owner bullet
    
    public bullet(String owner)
    {
        this.owner = owner;//owner bulle
        GreenfootImage image = new GreenfootImage("bullet.png");//set image for bullet
        if (owner.equals("cat")) 
        {
            image.mirrorHorizontally();
            speed = -8;
        } 
        else if (owner.equals("dog")) 
        {
            speed = 8;
        } 
        else if (owner.equals("left")) 
        {
            image.mirrorHorizontally();
            speed = -5;
        } 
        else if (owner.equals("right")) 
        {
            speed = 5;
        }
        /**
         *  the bullet move trajectory
         */
        setImage(image);
    }
    
    public bullet(String owner, int speed)
    {
        this.owner = owner;
        this.speed = speed;

        GreenfootImage image = new GreenfootImage("bullet.png");
        if (speed < 0) {
            image.mirrorHorizontally();
        }

        setImage(image);
    }
    
    public void act()
    {
        move(speed);// bullet moving
        
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        
        if(owner.equals("cat"))
        {
            Dog target = (Dog)getOneIntersectingObject(Dog.class);
            if(target != null)
            {
                target.takeDamage(15);
                getWorld().removeObject(this);
                return;
            }
            
            Robot robot = (Robot)getOneIntersectingObject(Robot.class);
            if(robot != null)
            {
                robot.takeDamage(1);
                getWorld().removeObject(this);
                return;
            }
        }
        else if(owner.equals("dog"))
        {
            Cat target = (Cat)getOneIntersectingObject(Cat.class);
            if(target != null)
            {
                target.takeDamage(25);
                getWorld().removeObject(this);
                return;
            }
            
            Robot robot = (Robot)getOneIntersectingObject(Robot.class);
            if(robot != null)
            {
                robot.takeDamage(1);
                getWorld().removeObject(this);
                return;
            }
        }
        else if(owner.equals("robot"))
        {
            if (getWorld() instanceof SingleWorld) {
                SingleWorld sw = (SingleWorld)getWorld();
                Actor player = sw.getPlayer();

                if (player != null && intersects(player))
                {
                    if (player instanceof Cat) {
                        ((Cat)player).takeDamage(30);
                    } else if (player instanceof Dog) {
                        ((Dog)player).takeDamage(30);
                    }

                    getWorld().removeObject(this);
                }
            }
        }
    }
}