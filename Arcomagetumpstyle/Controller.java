import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Controller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controller extends Actor
{
    public static boolean whoseturn = false; // false = Trump, true = Hillary
    public int selected = 0;
    public boolean isPushed = false;
    public Selector[] selects;
    public card[] cards;
    
    Controller(Selector[] s, card[] ca){
        this.selects = s;
        this.cards = ca;
    }
    
    public boolean getTurn(){
        return whoseturn;
    }
  
    public void setTurn(){
        whoseturn = !whoseturn;
    }
    
    public void draw(int[] hand){
        for (int i = 0; i<hand.length; i++){
            if (hand[i] == -1){
                cards[i].newCard();
            }
        }
    }
    
    /**
     * Act - do whatever the Controller wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkForKeypress();
    } 
    
    public void checkForKeypress(){
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("e") && !Greenfoot.isKeyDown("r")){
          isPushed= false;
        }
        
      if (!isPushed){
        if (Greenfoot.isKeyDown("left")){
            isPushed = true;
            if (selected != 0){
                selects[selected].disable();
                selected -= 1;
                selects[selected].enable();
            }
        }
        else if (Greenfoot.isKeyDown("right")){
            isPushed = true;
            if (selected != 5){
                selects[selected].disable();
                selected += 1;
                selects[selected].enable();
            }
        }
        else if (Greenfoot.isKeyDown("e")){
            isPushed = true;
            cards[selected].playCard(whoseturn, selected);
        }
        else if (Greenfoot.isKeyDown("r")){
            isPushed = true;
            switcheroo();
            cards[0].endTurn();
        }
        else if (Greenfoot.isKeyDown("h")){
            isPushed = true;
            getWorld().addObject(new BlackScreen(1), 650, 400);
            getWorld().addObject(new TempMessage("Use left and right arrows to pick a card.", 1), 670, 150);
            getWorld().addObject(new TempMessage("Press E to play a card.", 1), 670, 190);
            getWorld().addObject(new TempMessage("Press R to switch all cards for new ones, then end your turn.", 1), 670, 230);
            getWorld().addObject(new TempMessage("You can win in three different ways:", 1), 670, 310);
            getWorld().addObject(new TempMessage("* Get your opponent's power down to 0!", 1), 670, 350);
            getWorld().addObject(new TempMessage("* Reach 100 Power!", 1), 670, 390);
            getWorld().addObject(new TempMessage("* Reach 300 in one of your ressources! (Ego, Support or Money)", 1), 670, 430);

        }
      }
    }
    
    private void switcheroo(){
        for (int n = 0; n<cards.length; n++){
            cards[n].newCard();
        }
    }
}
