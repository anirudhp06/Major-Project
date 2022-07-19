/* import java.sql.*;
import java.util.Scanner;
public class testing{
    public static void main(String[] args){
        Connection con;
        Scanner scan=new Scanner(System.in);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            con=DriverManager.getConnection(url,"system","root");
            System.out.println("Connected to db");
        }catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e1){
            e1.printStackTrace();
        }
    }
} */
/* import javax.swing.*;
import java.sql.*;
import java.util.Scanner;
import java.awt.event.*;
public class testing{
	public static void main(String[] args){
		JFrame f1=new JFrame();
        JLabel l1=new JLabel();
		JButton b1=new JButton("Login");
		JButton b2=new JButton("Reset");
		f1.setLayout(null);
		f1.setVisible(true);
		f1.setSize(400,500);
		b1.setBounds(100,50,150,30);
		b2.setBounds(100,100,150,30);
        l1.setBounds(100, 200, 150, 30);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b1.setText("Login Button CLicked");
            }
        });
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b2.setText("Reset button Clicked");
            }
        });
        // b1.setActionCommand("LOGIN");
        //b2.setActionCommand("RESET");
		f1.add(b1);
        f1.add(l1);
		f1.add(b2);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Connection con;
        Scanner scan=new Scanner(System.in);
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url="jdbc:oracle:thin:@localhost:1521:XE";
            con=DriverManager.getConnection(url,"system","root");
            System.out.println("Connected to db");
            l1.setText("Connected to DB");
        }catch(SQLException e){
            e.printStackTrace();
        }
        catch(Exception e1){
            e1.printStackTrace();
        }
        scan.close();
	}
} */
//Testing Slicing of String...
public class testing{
    public static void main(String[] args){
        String a="Anirudh";
        int len=a.length();
        String b=a.substring((len-4), (len));
        System.out.println(b);
    }
}
//testing the gpg key verification.
//testing again
//test 3