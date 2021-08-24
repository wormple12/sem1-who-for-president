import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/*
 * Counter that displays an text and a number.
 * 
 * @author Tine Mabjerg
 * @version 1
 */
public class Counter extends Actor
{
    public int value = 1;
    private String text;
    /*
     * Create a counter with prefix text
     */
    public Counter(String prefix, int count)
    {
        value = count;
        text = prefix;
        GreenfootImage image = new GreenfootImage( text + " " +  value, 35, Color.BLUE, Color.WHITE);
        setImage(image);
        updateImage();
    }
    /*
     * Increment the counter by one.
     */
    public void increment(int amount)
    {
        value += amount;
        updateImage();
    }

    /*
     * Return the current counter value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Make the image
     */
    private void updateImage()
    {
        setImage(new GreenfootImage(text + " " +  value, 35 , Color.BLUE, Color.WHITE));
    }
}