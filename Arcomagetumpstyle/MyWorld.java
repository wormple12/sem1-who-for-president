import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends greenfoot.World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Controller controller;
    
    public Counter p1Power;
    public Counter p2Power;
    public Counter p1EgoPW;
    public Counter p1EgoC;
    public Counter p1SupPW;
    public Counter p1SupC;
    public Counter p1MonPW;
    public Counter p1MonC;
    public Counter p2EgoPW;
    public Counter p2EgoC;
    public Counter p2SupPW;
    public Counter p2SupC;
    public Counter p2MonPW;
    public Counter p2MonC;
    public Selector[] selectors;
    public card[] cards;
    
    public MyWorld()
    {    
        // Create a new world with 1300x800 cells with a cell size of 1x1 pixels.
        super(1300, 800, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Hillarybody hillarybody = new Hillarybody();
        addObject(hillarybody,1098,433);
        trump trump = new trump();
        addObject(trump,233,421);

        hillarybody.setLocation(943,405);
        trump.setLocation(360,395);

        p1EgoPW = new Counter ("per week: ", 1);
        addObject(p1EgoPW, 90, 65);
        p1SupPW = new Counter ("per week: ", 1);
        addObject(p1SupPW, 90, 185);
        p1MonPW = new Counter ("per week: ", 1);
        addObject(p1MonPW, 90, 305);

        p2EgoPW = new Counter ("per week: ", 1);
        addObject(p2EgoPW, 1215, 65);
        p2SupPW = new Counter ("per week: ", 1);
        addObject(p2SupPW, 1215, 185);
        p2MonPW = new Counter ("per week: ", 1);
        addObject(p2MonPW, 1215, 305);

        p1EgoC = new Counter ("current: ", 15);
        addObject(p1EgoC, 90, 105);
        p1SupC = new Counter ("current: ", 15);
        addObject(p1SupC, 90, 225);
        p1MonC = new Counter ("current: ", 15);
        addObject(p1MonC, 90, 345);

        p2EgoC = new Counter ("current", 15);
        addObject(p2EgoC, 1215, 105);
        p2SupC = new Counter ("current: ", 15);
        addObject(p2SupC, 1215, 225);
        p2MonC = new Counter ("current: ", 15);
        addObject(p2MonC, 1215, 345);

        p1Power = new Counter("", 30);
        addObject(p1Power,560,276);
        p2Power = new Counter("", 30);
        addObject(p2Power,754,276);

        money money = new money();
        addObject(money,1215,265);
        ego ego = new ego();
        addObject(ego,1215,23);
        money money2 = new money();
        addObject(money2,88,266);
        sup sup = new sup();
        addObject(sup,1215,145);
        sup sup2 = new sup();
        addObject(sup2,95,145);
        ego ego2 = new ego();
        addObject(ego2,88,23);
        power power = new power();
        addObject(power,659,198);
        HForHelp hForHelp = new HForHelp();
        addObject(hForHelp,662,336);

        Counter[] cP1vals = new Counter[] {p1EgoC, p1SupC, p1MonC, p1Power};
        Counter[] cP2vals = new Counter[] {p2EgoC, p2SupC, p2MonC, p2Power};
        Counter[] cP1pw = new Counter[] {p1EgoPW, p1SupPW, p1MonPW};
        Counter[] cP2pw = new Counter[] {p2EgoPW, p2SupPW, p2MonPW};

        cards = new card[6];
        cards[0] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 0);
        addObject(cards[0],112,630);
        cards[1] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 1);
        addObject(cards[1],327,630);
        cards[2] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 2);
        addObject(cards[2],542,630);
        cards[3] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 3);
        addObject(cards[3],758,630);
        cards[4] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 4);
        addObject(cards[4],973,630);
        cards[5] = new card(cP1vals, cP1pw, cP2vals, cP2pw, 5);
        addObject(cards[5],1188,630);
        for (int i = 0; i < cards.length; i++){
            cards[i].newCard();
        }

        selectors = new Selector[6];
        selectors[0] = new Selector();
        addObject(selectors[0],114,430);
        selectors[0].enable();
        selectors[1] = new Selector();
        addObject(selectors[1],327,430);
        selectors[2] = new Selector();
        addObject(selectors[2],544,430);
        selectors[3] = new Selector();
        addObject(selectors[3],762,430);
        selectors[4] = new Selector();
        addObject(selectors[4],972,430);
        selectors[5] = new Selector();
        addObject(selectors[5],1193,430);
        for (int j = 1; j < selectors.length; j++){
            selectors[j].disable();
        }

        controller = new Controller(selectors, cards);
        addObject(controller,657,365);
        controller.getImage().setTransparency(0);
        controller.setLocation(662,336);
        
        Hhead hhead = new Hhead(p2Power);
        addObject(hhead,935,249);
        Thead thead = new Thead(p1Power);
        addObject(thead,347,236);
        
        Greenfoot.start();
    }
    
    public Controller getController(){
        return controller;
    }
}
