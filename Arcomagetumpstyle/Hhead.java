import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hhead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hhead extends Actor
{
            private int headsize = 1;
            private int counter = 3;
            private int baseVal = 3;
            private int power = 30;
            
            private Counter capow;
            private int currentp = 30;
    
    Hhead(Counter power){
        capow = power;
    }
            
    public void act() 
    {
            int times1 = capow.value / 4;
            int times2 = currentp / 4;
            int diff = Math.abs(times1-times2);
            if (capow.value <= currentp-4){
                HeadSmall();
                currentp -= 4*diff;
            } else if (capow.value >= currentp+4) {
                HeadBig();
                currentp += 4*diff;
            }
    }    
    
    public void HeadSmall(){
        counter--;
        
        if (counter % baseVal > 0){
            GreenfootImage image = getImage();
            image.scale((int)(image.getWidth()*0.8), (int)(image.getHeight()*0.8));
            setImage(image);
        } else {
            ChangeHead(false);
        }
    }
    
    public void HeadBig(){
        counter++;
        
        if (counter % baseVal > 0){
            GreenfootImage image = getImage();
            image.scale((int)(image.getWidth()*1.15), (int)(image.getHeight()*1.15));
            setImage(image);
        } else {
            ChangeHead(true);
        }
    }
    
    public void ChangeHead(boolean grow){  
        
        if (grow){
            headsize++;
        } else {
            headsize--;
        }
        
        if(headsize >= 7){
            return;
        } else if (headsize <= 0){
            return;
        }   
        GreenfootImage updated = new GreenfootImage("images/hillaryhead" + headsize  + ".png");
        setImage(updated);
    }
}
