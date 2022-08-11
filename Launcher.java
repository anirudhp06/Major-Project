import javax.swing.*;
class ViewManager{

    JFrame frame = null;
    JTabbedPane myListTabs = null;
    ComicsListPane myComicsListPane = null;

    public ViewManager(){

    //Create and set up the window.
    frame = new JFrame("My List Agregator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Add the Tabbed pane for lists.
    myListTabs = new JTabbedPane();
    myComicsListPane = new ComicsListPane();
    myListTabs.add(myComicsListPane);
    myListTabs.setTitleAt(myListTabs.getTabCount()-1, "Status");
    frame.getContentPane().add(myListTabs);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
    frame.setSize(500, 500);
    }
}

class ComicsListPane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -5207104867199042105L;
    JTable myComicsTable = null;

        public ComicsListPane() {
                //Create the column names and data
            //Create the table
            /* DefaultTableModel model=new DefaultTableModel();
            myComicsTable = new JTable(model);
            myComicsTable.setPreferredScrollableViewportSize(new Dimension(750, 110));
            myComicsTable.setFillsViewportHeight(true);
            myComicsTable.setFillsViewportHeight(true); */
    }
}

public class Launcher {
    public void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewManager vm=new ViewManager();
            }
        });
    }
}