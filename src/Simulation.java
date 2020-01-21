public class Simulation {
    private static final Integer WIDTH = 800;
    private static final Integer HEIGHT = 600;

    private void startSimulation(){
        Stars stars = new Stars(WIDTH, HEIGHT);
        //stars.generateStars(20, 5e12, 10);
        //stars.generateStars(1, 5e14, 10);
        //stars.createCustom(new Star(400.0, 300.0, 5e14, 10));
        //stars.createCustom(new Star(750.0, 550.0, 5e14, 5));

        Frame layout = new Frame("Star simulation", WIDTH, HEIGHT);
        layout.initStars(stars);
        layout.setVisible(true);


        while(true) {
            layout.DrawStars();
            try{
                Thread.sleep(5);
            }catch(InterruptedException e){
                System.out.println("Interrupted");
            }
        }

    }


    public static void main(String[] args){
        new Simulation().startSimulation();
    }
}
