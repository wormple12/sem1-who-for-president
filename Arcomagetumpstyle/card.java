import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class card extends Actor
{
    public static int cardamount = 5; // UPDATE WHEN ADDING CARDS
    public static int[] currenthand = new int[6];
    public static int currentcard = 0;
    private int handpos;
    private boolean playagain = false;
    private boolean whoseturn = false; // this is not the primary whoseturn bool
    
    static int[] cardCosts = new int[] {2, 14, 9, 1, 9}; // UPDATE WHEN ADDING CARDS
    Counter[] p1Vals;
    Counter[] p1PW;
    Counter[] p2Vals;
    Counter[] p2PW;
    
    card(Counter[] p1vals, Counter[] p1pw, Counter[] p2vals, Counter[] p2pw, int handplacement){
        newCard();
        this.p1Vals = p1vals;
        this.p1PW = p1pw;
        this.p2Vals = p2vals;
        this.p2PW = p2pw;
        this.handpos = handplacement;
    }
    
    /**
     * Act - do whatever the card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
    public void disable(){
        getImage().setTransparency(0);
    }
    
    public void enable(){
        getImage().setTransparency(255);
    }
    
    public int getCostType(){ // UPDATE WHEN ADDING NEW CARDS
        if (currentcard <= 2){
            return 1;
        } else if (currentcard >= 4){
            return 2;
        } else {
            return 0;
        }
    }
    
    public int getCost(){
        return cardCosts[currentcard];
    }
    
    public int newCard(){
        Random random = new Random();
        int cardnum = random.nextInt(cardamount);
        GreenfootImage newimage = new GreenfootImage("card" + cardnum + ".png");
        setImage(newimage);
        enable();
        currenthand[handpos] = cardnum;
        return cardnum;
    }
    
    public void playCard(boolean player, int selected){ 
        currentcard = currenthand[selected];
        if (getCostType() == 0){ //EGO
            if ((!player && getCost() <= p1Vals[0].value)
                || (player && getCost() <= p2Vals[0].value)){
                currenthand[selected] = -1; playEffect(player); playVoice(player); disable();
            } else {
                cantPlay(); return;
            }
        } else if (getCostType() == 1){ //SUPPORT
            if ((!player && getCost() <= p1Vals[1].value)
                || (player && getCost() <= p2Vals[1].value)){
                currenthand[selected] = -1; playEffect(player); playVoice(player); disable();
            } else {
                cantPlay(); return;
            }
        } else { //MONEY
            if ((!player && getCost() <= p1Vals[2].value)
                || (player && getCost() <= p2Vals[2].value)){
                currenthand[selected] = -1; playEffect(player); playVoice(player); disable();
            } else {
                cantPlay(); return;
            }
        }
        if (playagain && !checkWin()){
        getWorld().addObject(new TempMessage("You may play another card!", 100), 670, 150);
        }else if (!checkWin()){ endTurn(); }
        playagain = false;
    }
    
    private void playEffect(boolean p){ // UPDATE WHEN NEW CARDS ARE ADDED
        if (currentcard == 0){
            if (!p){ p1Vals[1].increment(-2); p1PW[1].increment(1); p1Vals[0].increment(-3);}
            else { p1Vals[1].increment(-2); p2PW[1].increment(1); p2Vals[0].increment(-3);}
        } else if (currentcard == 1){
            if (!p){ p1Vals[1].increment(-14); p1Vals[3].increment(7); p2Vals[3].increment(-5);}
            else { p2Vals[1].increment(-14); p2Vals[3].increment(7); p1Vals[3].increment(-5);}
        } else if (currentcard == 2){
            if (!p){ p1Vals[1].increment(-9); p1Vals[3].increment(7); p1Vals[2].increment(7);}
            else { p2Vals[1].increment(-9); p2Vals[3].increment(7); p2Vals[2].increment(7);}
        } else if (currentcard == 3){
            if (!p){ p1Vals[0].increment(-1); p2Vals[3].increment(-2); playagain = true;}
            else { p2Vals[0].increment(-1); p1Vals[3].increment(-2); playagain = true;}
        } else if (currentcard == 4){
            if (!p){ p1Vals[2].increment(-9); p1PW[0].increment(1); p1Vals[3].increment(5);}
            else { p2Vals[2].increment(-9); p2PW[0].increment(1); p2Vals[3].increment(5);}
        }
    }
    
    private void playVoice(boolean p){
        Random random = new Random();
        int ranInt;
        if (!p){
            ranInt = random.nextInt(6);
            Greenfoot.playSound("sounds/trump" + ranInt + ".mp3");
        } else {
            ranInt = random.nextInt(5);
            Greenfoot.playSound("sounds/hill" + ranInt + ".mp3");
        }
    }
    
    private void cantPlay(){
        // show message and urghn! sound
        getWorld().addObject(new TempMessage("You can't afford that card!", 50), 670, 150);
    }
    
    public void endTurn(){
        MyWorld myworld = (MyWorld)getWorld();
        Controller controller = myworld.getController();
        whoseturn = controller.getTurn();
        controller.setTurn();
        controller.draw(currenthand);
        
        if (whoseturn) { // if new round has begun, add per weeks to values
            for (int k = 0; k<p1PW.length; k++){
                p1Vals[k].increment(p1PW[k].value);
                p2Vals[k].increment(p2PW[k].value);
            }
        } checkWin();
        
        String turnholder;
        if (whoseturn) { turnholder = "Trump's"; }
        else { turnholder = "Hillary's"; }
        getWorld().addObject(new TempMessage("It's " + turnholder + " turn!", 100), 670, 150);
    }
    
    private boolean checkWin(){
        // see if opponent has 0- Power, you have 100+ Power
        // or one of your ressources are 300+
        if (!whoseturn){
            if (p2Vals[3].value <= 0){
                Win(); return true;
            } else if (p1Vals[3].value >= 100){
                Win(); return true;
            } else if (p1Vals[0].value >= 300 || p1Vals[1].value >= 300 || p1Vals[2].value >= 300){
                Win(); return true;
            }
        } else {
            if (p1Vals[3].value <= 0){
                Win(); return true;
            } else if (p2Vals[3].value >= 100){
                Win(); return true;
            } else if (p2Vals[0].value >= 300 || p2Vals[1].value >= 300 || p2Vals[2].value >= 300){
                Win(); return true;
            }
        }
        return false;
    }
    
    private void Win(){
        String turnholder;
        if (!whoseturn) { turnholder = "Trump"; }
        else { turnholder = "Hillary"; }
        getWorld().addObject(new TempMessage(turnholder + " wins!", 1000), 670, 150);
        Greenfoot.stop();
    }
}