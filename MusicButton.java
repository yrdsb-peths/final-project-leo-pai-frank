import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MusicButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MusicButton extends Actor
{
    private GreenfootSound music;
    private boolean isPlaying = true;

    
    public MusicButton(GreenfootSound music) {
        this.music = music;
        setImage("music/Start.png");
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            toggleMusic();
        }
    }
    
    private void toggleMusic() {
        if (isPlaying) {
            music.pause();
            setImage("music/Off.png");
        } else {
            music.playLoop();
            setImage("music/Start.png");
        }
        isPlaying = !isPlaying;
    }

}
