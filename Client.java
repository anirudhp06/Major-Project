import java.util.Scanner;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
public class Client {
    public static String gen(JTextField tkn){
        String tk=tkn.getText().substring(tkn.getText().length()-4, tkn.getText().length());
        return tk;
    }
    public static String gen_again(JTextField tkn){
        String tk=tkn.getText().substring(0,4);;
        return tk;
    }
    public static void insertRecord(Connection con,JLabel l1,JCheckBox ch1,JCheckBox ch2,JCheckBox ch3,JCheckBox ch4,String token,JTextField nameInput,JTextField contactInput,JTextField seatsInput){
        try {
            int bill=150;
            if(!(ch1.isSelected()||ch2.isSelected()||ch3.isSelected()||ch4.isSelected())){bill=150;}
            if(ch1.isSelected()){bill=150;}
            if(ch2.isSelected()){bill+=150;}
            if(ch3.isSelected()){bill+=170;}
            if(ch4.isSelected()){bill+=160;}
            Statement stmt;
            stmt=con.createStatement();
            String ContactQuery="insert into customer_details values('"+nameInput.getText()+"',"+contactInput.getText()+")";
            stmt.executeUpdate(ContactQuery);
            /*
                * Required Things:
                * token->order_id
                * particulars->Starters Selected
                * seats->total Seats required
                * total_bill->Final bill
                * status->By default during insertion this has to be 'PENDING'
                */
            String chk1="No Starters",chk2="Gobi Manchuri",chk3="Paneer Manchuri",chk4="Baby Corn Manchuri";
            String parts="";
            if(ch1.isSelected()){parts+=chk1;}
            else{
                if(ch2.isSelected()){parts+=chk2+",";}
                if(ch3.isSelected()){parts+=chk3+",";}
                if(ch4.isSelected()){parts+=chk4;}
            }
            String InsertQuery="insert into orders(order_id,particulars,seats,total_bill,status)values(?,?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(InsertQuery);
            pstmt.setString(1, token);
            pstmt.setString(2, parts);
            pstmt.setInt(3, Integer.parseInt(seatsInput.getText()));
            pstmt.setInt(4,bill);
            pstmt.setString(5, "PENDING");
            pstmt.executeUpdate();
            l1.setText("Token ID:"+token+" inserted into db successfully");
            System.out.println("Token ID:"+token+" inserted into db successfully");
        } catch (Exception e) {
            System.out.println("Please Generate Another Token ID and call function again");
        }
    }
    public static void main(String[] args){
        try{
            Connection con;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            String username="project",password="project";
            con=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to "+username+" database");

            JFrame f1=new JFrame();
            JLabel l1=new JLabel("Hello Welcome to the JAVA UI");
            f1.setLayout(null);
            f1.setTitle("Hotel Reservation System");
            f1.setBounds(100, 50, 1280, 720);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            JLabel contact=new JLabel("Contact Number:");
            contact.setBounds(10,200,100,10);
            f1.add(contact);

            JTextField contactInput=new JTextField("Enter Contact Number");
            contactInput.setBounds(115, 200, 200, 20);
            f1.add(contactInput);

            JLabel starters=new JLabel("Starters");
            starters.setBounds(10,200,100,100);
            f1.add(starters);

            JCheckBox ch1=new JCheckBox("No starters 0/-");
            ch1.setBounds(10, 270, 250, 30);
            f1.add(ch1);

            JCheckBox ch2=new JCheckBox("Gobi Manchurian:150/-");
            ch2.setBounds(10, 300, 250, 30);
            f1.add(ch2);

            JCheckBox ch3=new JCheckBox("Paneer Manchurian:170/-");
            ch3.setBounds(10, 330, 250, 30);
            f1.add(ch3);

            JCheckBox ch4=new JCheckBox("Baby Corn Manchurian:160/-");
            ch4.setBounds(10, 360, 250, 30);
            f1.add(ch4);

            JLabel txnID=new JLabel("Enter Transaction ID:");
            txnID.setBounds(10,500,200,20);
            f1.add(txnID);

            JTextField txn=new JTextField("Transaction ID generated after payment",16);
            txn.setBounds(150,500,300,20);
            f1.add(txn);

            JButton submit =new JButton("SUBMIT");
            submit.setBounds(10,550,200,20);
            f1.add(submit);

            JTextArea ta1=new JTextArea(10,25);
            ta1.setEditable(false);
            ta1.setBounds(500, 150, 600, 200);
            ta1.setLineWrap(true);
            Border b3=BorderFactory.createLineBorder(Color.GREEN,10);
            ta1.setBorder(b3);
            ta1.setFont(new Font("Berlin Sans FB",Font.PLAIN,20));
            f1.add(ta1);

            JButton calc =new JButton("CALCULATE BILL");
            calc.setBounds(10,450,200,20);
            ch1.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    if(ch1.isSelected()){
                        ta1.setText(ch1.getText()+"");
                        ch2.setSelected(false);
                        ch3.setSelected(false);
                        ch4.setSelected(false);
                    }
                }
            });
            
            ch4.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    if(ch4.isSelected()){
                        ch1.setSelected(false);
                    }
                }
            });
            
            
            ch3.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    if(ch3.isSelected()){
                        ch1.setSelected(false);
                    }
                }
            });

            
            ch2.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent e){
                    if(ch2.isSelected()){
                        ch1.setSelected(false);
                    }
                }
            });


            calc.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int bill=150;
                    String msg="Online Seat Booking:150/-\n",part="";
                    ta1.setText(msg);
                    if(!(ch1.isSelected()||ch2.isSelected()||ch3.isSelected()||ch4.isSelected())){msg+="\n\nTotal Bill:150/-";ta1.setText(msg);}
                    if(ch1.isSelected()){bill=150;ta1.setText(msg+"\n Total Bill:"+bill+"/-");}
                    if(ch2.isSelected()){bill+=150;part+=ch2.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill+"/-");}
                    if(ch3.isSelected()){bill+=170;part+=ch3.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill+"/-");}
                    if(ch4.isSelected()){bill+=160;part+=ch4.getText()+"\n";ta1.setText(msg+part+"\n Total Bill:"+bill+"/-");}
                    //ta1.setText("Total Bill:"+bill);
                }
            });
            f1.add(calc);

            JButton payBill=new JButton("Pay Bill");
            payBill.setBounds(300,450,200,20);
            payBill.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame f2=new JFrame();
                    ImageIcon ii=new ImageIcon("images/cropped_qr.png");
                    JLabel pic=new JLabel(ii);
                    pic.setBounds(10, 50, 590, 566);
                    JScrollPane jsp=new JScrollPane(pic);
                    f2.getContentPane().add(jsp);
                    f2.setBounds(750, 50, 600, 700);
                    f2.add(pic);
                    f2.setTitle("Scan to Pay");
                    f2.setVisible(true);
                }
            });
            submit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(!(txnID.getText().equals("")||txnID.getText().equals("Transaction ID generated after payment"))){
                        String token=gen(txn);
                        try {
                            insertRecord(con, l1, ch1, ch2, ch3, ch4, token, nameInput, contactInput, seatsInput);
                        } catch (Exception e1) {
                            System.out.println("Token Already exists, will insert modified one");
                            token=gen_again(txn);
                            insertRecord(con, l1, ch1, ch2, ch3, ch4, token, nameInput, contactInput, seatsInput);
                        }
                    }
                }
            });
            f1.add(payBill);
            f1.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
