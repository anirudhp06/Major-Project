import java.util.Scanner;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
public class Client {
    public static void main(String[] args){
        JFrame f1=new JFrame();
        JLabel l1=new JLabel("Hello Welcome to the JAVA UI");
        f1.setLayout(null);
        f1.setTitle("Hotel Reservation System");
        f1.setBounds(100, 50, 1280, 720);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        l1.setBounds(500, 10, 200, 100);
        f1.add(l1);
        JLabel name=new JLabel("Name:");
        name.setBounds(10, 50, 100, 100);
        f1.add(name);
        JTextField tf=new JTextField("Enter your name",16);
        tf.setBounds(50, 95, 200, 20);
        f1.add(tf);
        JButton submit =new JButton("SUBMIT");
        submit.setBounds(60,200,200,20);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!tf.getText().equals(""))
                l1.setText("You have typed: "+tf.getText());
            }
        });
        f1.add(submit); 
        JLabel seats=new JLabel("Number of seats:");
        seats.setBounds(10, 100, 100, 100);
        f1.add(seats);
    }
}
