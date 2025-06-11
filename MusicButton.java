import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * control the music start and off
 * 
 * Leo, pai
 * 2025.06.10
 */
public class MusicButton extends Actor
{
    private GreenfootSound music;
    private boolean isPlaying = true;

    
    public MusicButton(GreenfootSound music) {
        this.music = music;
        setImage("music/Start.png"); // set music image
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            toggleMusic();
        }
    }
    
    // Toggle music on/off and update the icon
    private void toggleMusic() {
        if (isPlaying) {
            music.pause();
            setImage("music/Off.png");// set music off image
        } else {
            music.playLoop();
            setImage("music/Start.png");// set music start image
        }
        isPlaying = !isPlaying;
    }

}
