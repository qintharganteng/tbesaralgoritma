import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
import java.util.ArrayList;
public class MyWorld extends World
{
    public static final int WIDTH = 800, HEIGHT = 600, PIXEL = 1, TOWINBALLOONS = 50, TOWINFISH = 10;
    private int textX = WIDTH/2, textY1 = HEIGHT/2, balloonsPopped, fishCaught;
    private boolean prepared;
    private Tux tux;
    private ArrayList<BlueBalloon> balloons = new ArrayList<>();
    private ArrayList<Fish> fishs = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(WIDTH, HEIGHT, PIXEL);
        showText("TEKAN S UNTUK MEMULAI GAME, AYO MULAI MEMANCING", textX, textY1);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("s") && !prepared){
            prepare();
        }
    }
    
    private void updateText() {
        showText("Balon Biru Tertangkap: " + balloonsPopped + "\n\nIkan Tertangkap: " + fishCaught, WIDTH/2, 3*HEIGHT/4);
    }
    
    private void addBalloons() {
        for(int i = 0; i < 70; i++) {
            BlueBalloon balloon = new BlueBalloon();
            
            int x = (int) (Math.random() * (WIDTH-balloon.getImage().getWidth()))+balloon.getImage().getWidth()/2;
            int y = (int) (Math.random() * (HEIGHT-balloon.getImage().getHeight()))+balloon.getImage().getHeight()/2;
            
            while(x + balloon.getImage().getWidth() >= tux.getX() && y + balloon.getImage().getHeight() >= tux.getY()) {
                x = (int) (Math.random() * (WIDTH-balloon.getImage().getWidth()))+balloon.getImage().getWidth()/2;
                y = (int) (Math.random() * (HEIGHT-balloon.getImage().getHeight()))+balloon.getImage().getHeight()/2;
            }
            
            addObject(balloon, x,y);
            
            balloons.add(balloon);
        }
    }
    
    private void addFish() {
        for(int i = 0; i < 20; i++){
            Fish fish = new Fish();
            
            int x = (int) (Math.random() * (WIDTH-fish.getImage().getWidth()))+fish.getImage().getWidth()/2;
            int y = (int) (Math.random() * (HEIGHT-fish.getImage().getHeight()))+fish.getImage().getHeight()/2;
            
            while(x + fish.getImage().getWidth() >= tux.getX() && y + fish.getImage().getHeight() >= tux.getY()) {
                x = (int) (Math.random() * (WIDTH-fish.getImage().getWidth()))+fish.getImage().getWidth()/2;
                y = (int) (Math.random() * (HEIGHT-fish.getImage().getHeight()))+fish.getImage().getHeight()/2;
            }
            
            addObject(fish, x,y);
            
            fishs.add(fish);
        }
    }
    
    private void addRandomBomb() {
        for(int i = 0; i < 17; i++){
            Bomb bomb = new Bomb();
            
            int x = (int) (Math.random() * (WIDTH-bomb.getImage().getWidth()-tux.getImage().getWidth()))+bomb.getImage().getWidth()/2;
            int y = (int) (Math.random() * (HEIGHT-bomb.getImage().getHeight()-tux.getImage().getHeight()))+bomb.getImage().getHeight()/2;
            
            while(x + bomb.getImage().getWidth() >= tux.getX() && y + bomb.getImage().getHeight() >= tux.getY()) {
                x = (int) (Math.random() * (WIDTH-bomb.getImage().getWidth()-tux.getImage().getWidth()))+bomb.getImage().getWidth()/2;
                y = (int) (Math.random() * (HEIGHT-bomb.getImage().getHeight()))+bomb.getImage().getHeight()/2;
            }
            
            addObject(bomb, x,y);
            
            bombs.add(bomb);
        }
    }
    
    private void addTux(){
        tux = new Tux(this);
        
        int x = WIDTH - tux.getImage().getWidth()/2;
        int y = HEIGHT - tux.getImage().getHeight()/2;
        
        addObject(tux, x,y);
    }
    
    private void prepare() {
        showText("", textX, textY1);
        setBackground("sand2.jpg");
        addTux();
        addBalloons();
        addFish();
        addRandomBomb();
        balloonsPopped = 0;
        fishCaught = 0;
        
        prepared = true;
    }
    
    public void setPopped(int x){
        balloonsPopped = x;
        updateText();
    }
    
    public void addPopped(int x) {
        balloonsPopped += x;
        updateText();
    }
    
    public int getPopped(){
        return balloonsPopped;
    }
    
    public void setFishCaught(int x) {
        fishCaught = x;
        updateText();
    }
    
    public void addFishCaught(int x) {
        fishCaught+=x;
        updateText();
    }
    
    public int getFishCaught() {
        return fishCaught;
    }
    
    public void removeObjects() {
        balloons.forEach(b -> {
            removeObject(b);
        });
        
        fishs.forEach(f -> {
            removeObject(f);
        });
        
        bombs.forEach(b -> {
            removeObject(b);
        });
    }
}
