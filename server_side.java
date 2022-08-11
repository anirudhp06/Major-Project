import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;


public class server_side {
    public static void createTable(String stats,Connection con,JButton buttonType,boolean bl){
        JFrame frame = null;
        JTabbedPane myListTabs = null;
        frame = new JFrame("Pending Bookings");
        myListTabs = new JTabbedPane();

        /* myComicsListPane = new ComicsListPane();
        myListTabs.add(myComicsListPane); */
        /* myListTabs.setTitleAt(myListTabs.getTabCount()-1, "Status"); */

        JTable myComicsTable = null;
        DefaultTableModel model=new DefaultTableModel();
        myComicsTable = new JTable(model);
        myComicsTable.setPreferredScrollableViewportSize(new Dimension(750, 110));
        myComicsTable.setFillsViewportHeight(true);
        myComicsTable.setFillsViewportHeight(true);

        try {
            System.out.println(buttonType.getText()+" button working");
            Statement stmt;
            stmt=con.createStatement();
            String query;
            if(bl)
                query="select * from orders where STATUS='"+stats+"' or STATUS='REJECTED'";
            else
                query="select * from orders where STATUS='"+stats+"'";
            ResultSet rs=stmt.executeQuery(query);
            ResultSetMetaData rsmd=rs.getMetaData();
            int col=rsmd.getColumnCount();
            String[] colName=new String[col];
            for(int i=0;i<col;i++)
                colName[i]=rsmd.getColumnName(i+1);
            model.setColumnIdentifiers(colName);
            while(rs.next()){
                String od=rs.getString(1);
                String parts=rs.getString(2);
                int seats=rs.getInt(3);
                int bill=rs.getInt(4);
                String final_bill=Integer.toString(bill);
                String seats_conf=Integer.toString(seats);
                String final_status=rs.getString(5);
                String[] row={od,parts,seats_conf,final_bill,final_status};
                model.addRow(row);
            }
            myComicsTable.setDefaultEditor(Object.class, null);
            //myComicsTable.setEnabled(false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(myComicsTable);
        scrollPane.setPreferredSize(new Dimension(600, 110));
        frame.getContentPane().add(myListTabs);
        frame.pack();
        frame.setBounds(500, 150, 950, 250);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        try{
            Connection con;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            String username="project",password="project";
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to "+username+" database");

            JFrame MainFrame=new JFrame();
            MainFrame.setTitle("Hotel Management System");
            MainFrame.setLayout(null);
            MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MainFrame.setBounds(100,50,1280,720);

            JLabel wlcm=new JLabel("Welcome to the app");
            wlcm.setHorizontalAlignment(JLabel.CENTER);
            wlcm.setSize(1000, 100);
            MainFrame.add(wlcm);
            
            JButton pendingList=new JButton("Pending List");
            pendingList.setBounds(10, 50, 150, 20);
            MainFrame.add(pendingList);

            JButton viewBooked=new JButton("Booked List");
            viewBooked.setBounds(250,50,150,20);
            MainFrame.add(viewBooked);

            JLabel tkn=new JLabel("Token ID:");
            tkn.setBounds(20, 60, 100, 100);
            MainFrame.add(tkn);

            JTextField tk_Field=new JTextField("Enter the token Number");
            tk_Field.setBounds(80, 100, 200, 20);
            MainFrame.add(tk_Field);

            JButton apprv=new JButton("Approve");
            apprv.setBounds(25,150,100,20);
            apprv.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(tk_Field.getText().equals("")||tk_Field.getText().equals(" ")||tk_Field.getText().equals("Enter the token Number")){
                        wlcm.setText("Enter Valid Token Number");
                    }
                    else{
                        try {
                            System.out.println("Approve Button Clicked");
                            Statement stmn=con.createStatement();
                            String query="update orders set STATUS='BOOKED' where order_id='"+tk_Field.getText()+"'";
                            stmn.executeUpdate(query);
                            wlcm.setText("Approved token: "+tk_Field.getText()+" Successfully");
                        } catch (SQLException Se) {
                            wlcm.setText("Error with TOKEN id please check again.");
                            Se.printStackTrace();
                        }
                    }
                }
            });
            MainFrame.add(apprv);

            JButton reject=new JButton("Reject");
            reject.setBounds(150,150,100,20);
            reject.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(tk_Field.getText().equals("")||tk_Field.getText().equals(" ")||tk_Field.getText().equals("Enter the token Number")){
                        wlcm.setText("Enter Valid Token Number");
                    }
                    else{
                        try {
                            System.out.println("Reject Button Clicked");
                            Statement stmn=con.createStatement();
                            String query="update orders set STATUS='REJECTED' where order_id='"+tk_Field.getText()+"'";
                            stmn.executeUpdate(query);
                            wlcm.setText("Rejected token "+tk_Field.getText()+" Successfully");
                        } catch (SQLException Se) {
                            wlcm.setText("Error with TOKEN id please check again.");
                            Se.printStackTrace();
                        }
                    }   
                }
            });
            MainFrame.add(reject);

            pendingList.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        createTable("PENDING", con,pendingList,true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });

            viewBooked.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        createTable("BOOKED", con,viewBooked,false);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            });
            MainFrame.setVisible(true); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
