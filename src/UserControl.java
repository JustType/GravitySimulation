import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserControl extends MouseAdapter {
    private Stars s;
    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        try {
            askValues(mx, my);
        }catch(Exception ex){
            System.out.println(this.s.getCount());
            System.out.println(ex.getMessage());
        }
    }

    public void setStars(Stars s){
        System.out.println("Stars set!");
        System.out.println(s.getCount());
        this.s = s;
    }

    public boolean askValues(int mx, int my){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        JButton create = new JButton("Create");
        JLabel labelX = new JLabel("X: ");
        JLabel labelY = new JLabel("Y: ");
        JLabel labelMass = new JLabel("Mass: ");
        JLabel labelSize = new JLabel("Size: ");
        JLabel labelVelX = new JLabel("Vel X: ");
        JLabel labelVelY = new JLabel("Vel Y: ");

        JTextField x = new JTextField("" + mx);
        JTextField y = new JTextField("" + my);
        JTextField mass = new JTextField();
        JTextField size = new JTextField();
        JTextField velX = new JTextField();
        JTextField velY = new JTextField();
        JTextField empty = new JTextField();

        create.setBounds(0, 250, 300, 30);
        labelX.setBounds(5, 10, 40, 30);
        labelY.setBounds(5, 50, 40, 30);
        labelMass.setBounds(5, 90, 40, 30);
        labelSize.setBounds(5, 130, 40, 30);
        labelVelX.setBounds(5, 170, 40, 30);
        labelVelY.setBounds(5, 210, 40, 30);

        x.setBounds(50, 10, 100, 30);
        y.setBounds(50, 50, 100, 30);
        mass.setBounds(50, 90, 100, 30);
        size.setBounds(50, 130, 100, 30);
        velX.setBounds(50, 170, 100, 30);
        velY.setBounds(50, 210, 100, 30);



        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });

        dialog.add(create);

        dialog.add(labelX);
        dialog.add(labelY);
        dialog.add(labelMass);
        dialog.add(labelSize);
        dialog.add(labelVelX);
        dialog.add(labelVelY);


        dialog.add(x);
        dialog.add(y);
        dialog.add(mass);
        dialog.add(size);
        dialog.add(velX);
        dialog.add(velY);
        dialog.add(empty);



        //dialog.setSize(300, 300);
        dialog.setBounds(mx, my, 300, 300);
        dialog.setVisible(true);

        double valX = Double.parseDouble(x.getText());
        double valY = Double.parseDouble(y.getText());
        double valMass = Double.parseDouble(mass.getText());
        Integer valSize = Integer.parseInt(size.getText());
        double valVelX = Double.parseDouble(velX.getText());
        double valVelY = Double.parseDouble(velY.getText());

        try {
            Star star = new Star(valX, valY, valMass, valSize);
            star.velocity.setLocation(valVelX, valVelY);
            s.createCustom(star);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

}
