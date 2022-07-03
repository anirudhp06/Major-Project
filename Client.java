import java.util.Scanner;
import java.sql.*;

import javax.print.attribute.standard.JobKOctetsSupported;
import javax.swing.*;
import java.awt.event.*;
public class Client {
    public static void main(String[] args){
        JFrame f1=new JFrame();
        int addOn=0;
        JLabel l1=new JLabel("Hello Welcome to the JAVA UI");
        f1.setLayout(null);
        f1.setTitle("Hotel Reservation System");
        f1.setBounds(100, 50, 1280, 720);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setVisible(true);
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setSize(1000,100);
        f1.add(l1);
        JLabel name=new JLabel("Name:");
        name.setBounds(10, 50, 100, 100);
        f1.add(name);
        JTextField nameInput=new JTextField("Enter your name",16);
        nameInput.setBounds(50, 95, 200, 20);
        f1.add(nameInput); 
        JLabel seats=new JLabel("Number of seats:");
        seats.setBounds(10, 100, 100, 100);
        f1.add(seats);
        JTextField seatsInput=new JTextField("Number of Seats required"); 
        seatsInput.setBounds(115, 140, 200, 20);
        f1.add(seatsInput);
        JLabel starters=new JLabel("Starters");
        starters.setBounds(10,200,100,100);
        f1.add(starters);
        JCheckBox ch1=new JCheckBox("No starters(0/-)");
        ch1.setBounds(10, 270, 250, 30);
        f1.add(ch1);
        JCheckBox ch2=new JCheckBox("Gobi Manchurian(150/-)");
        ch2.setBounds(10, 300, 250, 30);
        ch2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch2.isSelected()){
                    ch1.setSelected(false);
                }
            }
        });
        f1.add(ch2);
        JCheckBox ch3=new JCheckBox("Paneer Manchurian(170/-)");
        ch3.setBounds(10, 330, 250, 30);
        ch3.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch3.isSelected()){
                    ch1.setSelected(false);
                }
            }
        });
        f1.add(ch3);
        JCheckBox ch4=new JCheckBox("Baby Corn Manchurian(160/-)");
        ch4.setBounds(10, 360, 250, 30);
        ch4.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch4.isSelected()){
                    ch1.setSelected(false);
                }
            }
        });
        f1.add(ch4);
        ch1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch1.isSelected()){
                    ch2.setSelected(false);
                    ch3.setSelected(false);
                    ch4.setSelected(false);
                }
            }
        });
        JButton submit =new JButton("SUBMIT");
        submit.setBounds(250,500,200,20);
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!nameInput.getText().equals(""))
                l1.setText("You have typed: "+nameInput.getText());
            }
        });
        f1.add(submit);
    }
}
