import java.util.Scanner;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
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
        JCheckBox ch1=new JCheckBox("No starters 0/-");
        ch1.setBounds(10, 270, 250, 30);
        f1.add(ch1);
        JCheckBox ch2=new JCheckBox("Gobi Manchurian 150/-");
        ch2.setBounds(10, 300, 250, 30);
        ch2.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch2.isSelected()){
                    ch1.setSelected(false);
                }
            }
        });
        f1.add(ch2);
        JCheckBox ch3=new JCheckBox("Paneer Manchurian 170/-");
        ch3.setBounds(10, 330, 250, 30);
        ch3.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(ch3.isSelected()){
                    ch1.setSelected(false);
                }
            }
        });
        f1.add(ch3);
        JCheckBox ch4=new JCheckBox("Baby Corn Manchurian 160/-");
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
        JTextArea ta1=new JTextArea(10,25);
        ta1.setBounds(500, 150, 600, 200);
		ta1.setLineWrap(true);
		Border b3=BorderFactory.createLineBorder(Color.GREEN,10);
		ta1.setBorder(b3);
		ta1.setFont(new Font("Times New Roman",Font.BOLD,20));
		f1.add(ta1);
        JButton calc =new JButton("CALCULATE BILL");
        calc.setBounds(10,500,200,20);
        calc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int bill=150;
                String msg="Online Seat Booking:150/-\n",part="";
                ta1.setText(msg);
                if(ch1.isSelected()){bill=150;ta1.setText(msg+"\n Total Bill:"+bill+"/-");}
                if(ch2.isSelected()){bill+=150;part+=ch2.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill);}
                if(ch3.isSelected()){bill+=170;part+=ch3.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill);}
                if(ch4.isSelected()){bill+=160;part+=ch4.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill);}
                //ta1.setText("Total Bill:"+bill);
            }
        });
        f1.add(calc);
    }
}
