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
                    JFrame frame = null;
                    JTabbedPane myListTabs = null;
                    ComicsListPane myComicsListPane = null;
                    frame = new JFrame("Pending Bookings");
                    myListTabs = new JTabbedPane();

                    myComicsListPane = new ComicsListPane();
                    myListTabs.add(myComicsListPane);
                    
                    myListTabs.setTitleAt(myListTabs.getTabCount()-1, "Status");
                    frame.getContentPane().add(myListTabs);
                    frame.pack();
                    frame.setBounds(500, 150, 500, 500);

                    JTable myComicsTable = null;
                    DefaultTableModel model=new DefaultTableModel();
                    myComicsTable = new JTable(model);
                    myComicsTable.setPreferredScrollableViewportSize(new Dimension(750, 110));
                    myComicsTable.setFillsViewportHeight(true);
                    myComicsTable.setFillsViewportHeight(true);

                    try {
                        System.out.println("Button Clicked, working");
                        Statement stmt;
                        stmt=con.createStatement();
                        String query="select * from order_demo where STATUS='PENDING'";
                        ResultSet rs=stmt.executeQuery(query);
                        ResultSetMetaData rsmd=rs.getMetaData();
                        int col=rsmd.getColumnCount();
                        String[] colName=new String[col];
                        for(int i=0;i<col;i++)
                            colName[i]=rsmd.getColumnName(i+1);
                        model.setColumnIdentifiers(colName);
                        while(rs.next()){
                            String od=rs.getString(1);
                            String stat=rs.getString(2);
                            int seats=rs.getInt(3);
                            String seats_conf=Integer.toString(seats);
                            String[] row={od,stat,seats_conf};
                            model.addRow(row);
                        }
                        frame.setVisible(true);
                        myComicsTable.setDefaultEditor(Object.class, null);
                        //myComicsTable.setEnabled(false);
                    } catch (Exception e1) {
                        //TODO: handle exception
                        e1.printStackTrace();
                    }
                    JScrollPane scrollPane = new JScrollPane(myComicsTable);
                    scrollPane.setPreferredSize(new Dimension(450, 110));
                    frame.add(scrollPane, BorderLayout.CENTER);
                }
            });
            MainFrame.setVisible(true); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
