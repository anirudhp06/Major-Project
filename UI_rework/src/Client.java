package UI_rework.src;

import javax.swing.*;

public class Client extends JFrame{
    private JPanel Main_panel;
    private JLabel welcome;

    public static void main(String[] args) {
        Client c1=new Client();
        c1.setVisible(true);
        c1.setContentPane(c1.Main_panel);
        c1.setTitle("Hello");
        c1.setBounds(10,10,1280,720);

    }
}
