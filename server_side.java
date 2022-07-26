import java.util.Scanner;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;


public class server_side {
    public static void main(String[] args){
        try{
            Connection con;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            con=DriverManager.getConnection(url,"system","root");
            System.out.println("Connected to db");

            JFrame MainFrame=new JFrame();
            MainFrame.setTitle("Hotel Management System");
            MainFrame.setVisible(true);
            MainFrame.setLayout(null);
            MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MainFrame.setBounds(100,50,1280,720);

            JLabel wlcm=new JLabel("Welcome to the app");
            wlcm.setHorizontalAlignment(JLabel.CENTER);
            wlcm.setSize(1000, 100);
            MainFrame.add(wlcm);
            
            JButton refresh=new JButton("Refresh List");
            refresh.setBounds(10, 50, 150, 20);
            MainFrame.add(refresh);

            JLabel tkn=new JLabel("Token ID:");
            tkn.setBounds(20, 60, 100, 100);
            MainFrame.add(tkn);

            JTextField tk_Field=new JTextField("Enter the token Number");
            tk_Field.setBounds(80, 100, 200, 20);
            MainFrame.add(tk_Field);

            JButton apprv=new JButton("Approve");
            apprv.setBounds(25,150,100,20);
            MainFrame.add(apprv);

            JButton reject=new JButton("Reject");
            reject.setBounds(150,150,100,20);
            MainFrame.add(reject);

            refresh.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        Statement stmt;
                        System.out.println("Working refresh Button");
                        stmt=con.createStatement();
                        String query="select * from order_demo where STATUS='PENDING'";
                        ResultSet rs=stmt.executeQuery(query);
                        ResultSetMetaData rsmd=rs.getMetaData();
                        int col=rsmd.getColumnCount();
                        String[] colName=new String[col];
                        DefaultTableModel model = new DefaultTableModel(); 
                        JTable pen = new JTable(model);
                        for(int i=0;i<col;i++)
                            colName[i]=rsmd.getColumnName(i+1);
                        model.setColumnIdentifiers(colName);
                        pen.setBounds(350, 150, 300, 200);
                        MainFrame.add(pen);
                    } catch (Exception e1) {
                        //TODO: handle exception
                        e1.printStackTrace();
                    }
                }
            });
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
