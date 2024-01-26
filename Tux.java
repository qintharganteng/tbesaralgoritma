import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Tux extends Actor
{
    private final int SPEED = 2;
    private final MyWorld world;
    
    public Tux(MyWorld world) {
        this.world = world;
    }
    /**
     * Act - do whatever the Tux wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        checkKeyPress();
        clamp();
        checkCollision();
    }
    
    private void checkKeyPress() {
        if(Greenfoot.isKeyDown("up")) {
            setLocation(getX(),getY()-SPEED);
        }
        if(Greenfoot.isKeyDown("down")) {
            setLocation(getX(),getY()+SPEED);
        }
        if(Greenfoot.isKeyDown("left")) {
            setLocation(getX()-SPEED,getY());
        }
        if(Greenfoot.isKeyDown("right")) {
            setLocation(getX()+SPEED,getY());
        }
    }
    
    private void checkCollision() {
        if(isTouching(Bomb.class)){
            removeTouching(Bomb.class);
            //Greenfoot.playSound("au.wav");
            world.showText("", world.WIDTH/2, 3*world.HEIGHT/4);
            world.removeObjects();
            world.showText("Kamu Kalah, Coba Lagi :)", world.WIDTH/2,world.HEIGHT/2);
            Greenfoot.stop();
        }
        
        while(isTouching(BlueBalloon.class)) {
            removeTouching(BlueBalloon.class);
            //Greenfoot.playSound("NewPop.m4a");
            world.addPopped(1);
            
            if(world.getPopped() == world.TOWINBALLOONS) {
                //Greenfoot.playSound("fanfare.wav");
                world.showText("", world.WIDTH/2, 3*world.HEIGHT/4);
                world.removeObjects();
                world.showText("KAMU MENANG!", world.WIDTH/2, world.HEIGHT/2);
                Greenfoot.stop();
            }
        }
        
        while(isTouching(Fish.class)){
            removeTouching(Fish.class);
            //Greenfoot.playSound("NewPop.m4a");
            world.addFishCaught(1);
            
            if(world.getFishCaught() == world.TOWINFISH) {
                //Greenfoot.playSound("fanfare.wav");
                world.showText("", world.WIDTH/2, 3*world.HEIGHT/4);
                world.removeObjects();
                world.showText("KAMU MENANG!!", world.WIDTH/2, world.HEIGHT/2);
                Greenfoot.stop();
            }
        }
    }
    
    private void clamp() {
        if(getX()+getImage().getWidth()/2 >= MyWorld.WIDTH){
            setLocation(MyWorld.WIDTH-getImage().getWidth()/2, getY());
        }
        
        if(getX() <= getImage().getWidth()/2){
            setLocation(getImage().getWidth()/2, getY());
        }
        
        if(getY()+getImage().getHeight()/2 >= MyWorld.HEIGHT) {
            setLocation(getX(), MyWorld.HEIGHT-getImage().getHeight()/2);
        }
        
        if(getY() <= getImage().getHeight()/2) {
            setLocation(getX(), getImage().getHeight()/2);
        }
    }
}
