import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Window extends JFrame {
    private JMenuBar mnubar = new JMenuBar();
    private JMenu mnufile = new JMenu();
    private JMenuItem mnunewcontact = new JMenuItem();
    private JMenuItem mnurandom = new JMenuItem();
    private JMenuItem mnuremovecontact = new JMenuItem();
    private Container pane = new Container();
    private JButton cmdnewcontact = new JButton();
    private JButton cmdrandomcontact = new JButton();
    private JButton cmdremovecontact = new JButton();
    private JList lstcontacts = new JList();
    private DefaultListModel dlmcontacts = new DefaultListModel();
    private Rolladex rolladex = new Rolladex();
    private WindowListener close = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            rolladex.save(path);
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };
    private ScrollPane scrcontact = new ScrollPane();
    private int listindex = -1;
    private String path = "";

    public Window(){
        setTitle("Text Roulette");
        setSize(500,500);
        getContentPane().add(pane);
        pane.setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(close);
        path = JOptionPane.showInputDialog("What is the path for the contacts?");
        rolladex.open(path);
        for(int i = 0;i<rolladex.getArrcontacts().size();i++){
            dlmcontacts.addElement(rolladex.getArrcontacts().get(i).toString());
        }
        pane.add(scrcontact);
        scrcontact.add(lstcontacts);
        mnubar.add(mnufile);
        mnufile.setText("File");
        mnufile.add(mnunewcontact);
        mnufile.add(mnurandom);
        mnufile.add(mnuremovecontact);
        mnuremovecontact.setText("Remove Contact");
        mnurandom.setText("Random Contact");
        mnunewcontact.setText("New Contact");
        pane.add(cmdnewcontact);
        cmdnewcontact.setText("New Contact");
        pane.add(cmdrandomcontact);
        cmdrandomcontact.setText("Random Contact");
        pane.add(cmdremovecontact);
        cmdremovecontact.setText("Remove Contact");
        cmdremovecontact.setBounds(260,320,100,20);
        mnunewcontact.addActionListener(new newcontact());
        mnurandom.addActionListener(new randomcontact());
        cmdnewcontact.addActionListener(new newcontact());
        cmdrandomcontact.addActionListener(new randomcontact());
        mnuremovecontact.addActionListener(new removecontact());
        cmdremovecontact.addActionListener(new removecontact());
        setJMenuBar(mnubar);
        lstcontacts.setModel(dlmcontacts);
        scrcontact.setBounds(20,20,450,300);
        cmdrandomcontact.setBounds(20,320, 100,20);
        cmdnewcontact.setBounds(140,320,100,20);
        setVisible(true);
        lstcontacts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lstcontacts.getSelectedIndex() > -1) {
                    listindex = lstcontacts.getSelectedIndex();
                }
            }
        });
    }

    class newcontact implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String fname, lname, number, middlename;
            fname = JOptionPane.showInputDialog("What is the person's first name?");
            middlename = JOptionPane.showInputDialog("What is the person's middle name?");
            lname = JOptionPane.showInputDialog("What is the person's last name?");
            number = JOptionPane.showInputDialog("What is the person's number?");
            rolladex.newcontact(fname,lname,number, middlename);
            dlmcontacts.addElement(rolladex.lastadded().toString());
        }
    }

    class randomcontact implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null,rolladex.randomselection().toString());
        }
    }

    class removecontact implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dlmcontacts.remove(listindex);
            rolladex.remove(listindex);
        }
    }
}
