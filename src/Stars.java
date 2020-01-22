import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;

class Star {
    private Double x;
    private Double y;
    private Double mass;
    private int size;
    public Point2D velocity;
    public Gravity gravity;

    protected Star(Double x, Double y, Double mass, Integer size) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.velocity = new Point2D.Double(0.0, 0.0);
        this.gravity = new Gravity(new Point2D.Double(this.x, this.y), this.velocity);
        this.size = size;
    }

    public void move(){
        double multiplier = 1.0 * 0.005;
        this.x += this.velocity.getX() * multiplier;
        this.y += this.velocity.getY() * multiplier;
        this.gravity.setPosition(new Point2D.Double(this.x, this.y));
        setBoundaries();
    }

    private void generateRandom(){
        if(this.velocity.getX() == 5)
        this.velocity.setLocation((Math.random() * 10) - 5, (Math.random() * 10) - 5);
    }

    public void CalcImpact(Star star){
        double distance = gravity.getDistance(new Point2D.Double(star.getX(), star.getY()));
        if(Math.abs(distance) < 800.0) {
            Point2D diff = gravity.Impact(star);
            double diffX = diff.getX();
            double diffY = diff.getY();
            if(diffX != 0.0){
                double F = ((6.673e-11 * star.mass)/(distance * Math.abs(distance)));
                diffX = (F * diffX)/distance;
            }
            if(diffY != 0.0){
                double F = ((6.673e-11 * star.mass)/(distance * Math.abs(distance)));
                diffY = (F * diffY)/distance;
            }


            this.velocity.setLocation(this.velocity.getX() + diffX, this.velocity.getY() + diffY);

        }
    }

    private void setBoundaries(){
        if(this.x > 800 || this.x < 0){
            this.velocity.setLocation(this.velocity.getX() * -1, this.velocity.getY());
        }
        if(this.y > 600 || this.y < 0){
            this.velocity.setLocation(this.velocity.getX(), this.velocity.getY() * -1);
        }
    }

    public boolean collision(Star star){
        double distance = this.gravity.getDistance(new Point2D.Double(star.getX(), star.getY()));
        if(Math.abs(distance) < star.size/2.0 && this != star){
            if(star.getMass() >= this.getMass()) {
                star.mass += this.mass / 2;
                double avgVelocity1 = (this.velocity.getX() + this.velocity.getY()) / 2;
                double avgVelocity2 = (star.velocity.getX() + star.velocity.getY()) / 2;

                double colVelocity = (star.velocity.getX() * star.getMass()) / (this.getMass() + star.getMass());
                colVelocity *= -1;
                //System.out.println(star.velocity.getX());
                //System.out.println(star.velocity.getY());
                //System.out.println(colVelocity);
                star.modVelocity(colVelocity, 0);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public void modVelocity(double x, double y){
        this.velocity.setLocation(this.velocity.getX() + x, this.velocity.getY() + y);
    }

    public boolean overlap(int x, int y){
        return x < (getX() + getSize()) && x > (getX() - getSize()) && y < (getY() + getSize()) && y > (getY() - getSize());
    }

    public Double getX(){
        return this.x;
    }

    public Double getY(){
        return this.y;
    }

    public Double getMass(){
        return this.mass;
    }

    public Integer getSize() {
        return this.size;
    }

}

public class Stars {
    private Integer numberOfStars;
    private Integer SimWidth;
    private Integer SimHeight;
    private ArrayList<Star> starList = new ArrayList<>();

    public Stars(Integer width, Integer height){
        this.SimWidth = width;
        this.SimHeight = height;
        this.numberOfStars = 0;
    }

    public void addStar(Star star){
        this.starList.add(star);
        this.numberOfStars++;
    }

    public void removeStar(Star star){
        this.starList.remove(star);
        this.numberOfStars--;
    }

    public Iterator<Star> iterStars(){
        return starList.iterator();
    }

    public void generateStars(Integer num, double m, int s){
        for(int i = 0;i < num; i++){
            createRandomStar(m, s);
        }

    }


    private void createRandomStar(double m, int s){
        Random r = new Random();
        double x = r.nextDouble() * SimWidth;
        double y = r.nextDouble() * SimHeight;
        Integer size = r.nextInt(s) + 3;
        double mass = r.nextDouble() * m;

        addStar(new Star(x, y, mass, size));

    }

    public void createCustom(Star star){
        addStar(star);
    }

    public Star overlap(int x, int y){
        return this.starList.stream().filter(s->s.overlap(x,y)).findFirst().orElse(null);
    }

    public void updateGravity(){
        Iterator<Star> starIter = iterStars();
        ArrayList<Star> toRemove = new ArrayList<>();
        while(starIter.hasNext()){
            Star star = starIter.next();
            for(Star next : starList){
                star.CalcImpact(next);
                if(star.collision(next)){
                    toRemove.add(star);
                }
            }
            star.move();
            //actual.CalcImpact(star);
        }
        for(Star s : toRemove){
            removeStar(s);
        }
    }

    public Integer getCount() {
        return numberOfStars;
    }



}
