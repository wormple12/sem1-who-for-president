import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * TempMessage that displays an text and a number.
 * 
 * @author Tine Mabjerg
 * @version 1
 */
public class TempMessage extends Actor
{
    private String text;
    private int timer = 0;
    private int thislong;
    /*
     * Create a counter with prefix text
     */
    public TempMessage(String prefix, int howlong)
    {
        thislong = howlong;
        text = prefix;
        GreenfootImage image = new GreenfootImage(text, 35, Color.BLUE, Color.WHITE);
        setImage(image);
        updateImage();
    }

    public void act()
    {
        timer++;
        if (timer == thislong) getWorld().removeObject(this);
    }
    
    /**
     * Make the image
     */
    private void updateImage()
    {
        setImage(new GreenfootImage(text, 35 , Color.BLUE, Color.WHITE));
    }
}
