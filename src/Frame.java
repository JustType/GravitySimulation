import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

public class Frame extends JFrame {
    private MyCanvas canvas;
    private Stars stars;

    Frame(String name, int width, int height){
        super(name);
        setBounds(100, 100, width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //canvas.setBackground(Color.BLACK);


    }

    public void DrawStars(){
        this.canvas.repaint();
    }

    public void initStars(Stars stars){
        this.stars = stars;
        this.canvas = new MyCanvas();
        add(canvas);
    }



    private class MyCanvas extends Canvas{
        private int fromBorder = 10;

        private MyCanvas(){
            setBackground(Color.BLACK);
            UserControl uc = new UserControl();
            uc.setStars(stars);
            addMouseListener(uc);
        }
        public void paint(Graphics g){

            g.setColor(Color.WHITE);
            stars.updateGravity();
            Iterator<Star> starIter = stars.iterStars();
            while(starIter.hasNext()){
                Star star = starIter.next();
                if(star.getMass() > 1e5){
                    g.setColor(Color.RED);
                }
                else if(star.getMass() < 100){
                    g.setColor(Color.BLUE);
                }
                else{
                    g.setColor(Color.WHITE);
                }
                g.fillOval((int)Math.round(star.getX()) - (star.getSize()/2), (int)Math.round(star.getY()) - (star.getSize()/2), star.getSize(), star.getSize());
                //star.move();

            }

        }


    }


}
