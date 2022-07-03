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
    }
}
