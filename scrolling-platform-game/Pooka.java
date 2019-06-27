import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pooka here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pooka extends Actor
{
    /**
     * Act - do whatever the Pooka wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        goTo();
    }

    public void goTo()
    {
        Taizo player = (Taizo)getWorld().getObjects(Taizo.class).get(0);
        int TaizoX = player.getX();
        int TaizoY = player.getY();
        turnTowards(TaizoX, TaizoY);
        move(1);
    }
}
