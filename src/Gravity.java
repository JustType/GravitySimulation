import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Gravity {
    private Point2D position;
    private Point2D velocity;

    public Gravity(Point2D position, Point2D velocity){
        this.position = position;
        this.velocity = velocity;
    }

    public Point2D Impact(Star star){
        Point2D remoteStar = new Point2D.Double(star.getX(), star.getY());

        double distanceX = remoteStar.getX() - this.position.getX();
        double distanceY = remoteStar.getY() - this.position.getY();

        return new Point2D.Double(distanceX, distanceY);

    }

    public double getDistance(Point2D remotePos){
        return this.position.distance(remotePos);
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
}

