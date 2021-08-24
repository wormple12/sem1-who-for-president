import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * BlackScreen that displays an text and a number.
 * 
 * @author Tine Mabjerg
 * @version 1
 */
public class BlackScreen extends Actor
{
    private int timer = 0;
    private int thislong;
    
    BlackScreen(int howlong) {
        thislong = howlong;
    }

    public void act()
    {
        timer++;
        if (timer == thislong) getWorld().removeObject(this);
    }
}
