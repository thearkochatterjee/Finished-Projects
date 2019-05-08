import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MainWindow extends JFrame {
    private Container pane = new Container();
    private JTextArea txtusertext = new JTextArea();
    private JTextField txtnextword = new JTextField();
    private JMenuBar mnubar = new JMenuBar();
    private JMenu mnufile = new JMenu();
    private JMenuItem mnusave = new JMenuItem();
    private JMenuItem mnuopen = new JMenuItem();
    private JLabel lblusertext = new JLabel();
    private JLabel lblnextword = new JLabel();
    private JMenu mnusplittext = new JMenu();
    private JMenuItem mnusplitbyword = new JMenuItem();
    private JMenuItem mnusplitbyscentence = new JMenuItem();
    private JMenuItem mnusplitbyparagraph = new JMenuItem();
    private double limit = 0.95;
    private Text txt = new Text();
    private String passagetitle = "";

    public MainWindow() {
        setTitle("Reading Assistance");
        pane.setLayout(null);
        getContentPane().add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setJMenuBar(mnubar);
        mnubar.add(mnufile);
        mnufile.add(mnuopen);
        mnufile.add(mnusave);
        mnubar.add(mnusplittext);
        mnusplittext.add(mnusplitbyword);
        mnusplittext.add(mnusplitbyscentence);
        mnusplittext.add(mnusplitbyparagraph);
        mnusplittext.setText("Split Text");
        mnusplitbyword.setText("By Word");
        mnusplitbyscentence.setText("By Sentence");
        mnusplitbyparagraph.setText("By Paragraph");
        mnufile.setText("File");
        mnuopen.setText("Open");
        mnusave.setText("Save");
        pane.add(txtusertext);
        pane.add(lblnextword);
        pane.add(lblusertext);
        pane.add(txtnextword);
        lblnextword.setText("Next Word:");
        lblusertext.setText("All Text So Far:");
        lblnextword.setBounds(20,20,500,20);
        txtnextword.setBounds(20,50,150,20);
        lblusertext.setBounds(20,80,100,20);
        txtusertext.setBounds(20,110,300,600);
        setVisible(true);
        mnuopen.addActionListener(new open());
        mnusave.addActionListener(new save());
        mnusplitbyword.addActionListener(new splitbyword());
        mnusplitbyscentence.addActionListener(new splitbyscentence());
        mnusplitbyparagraph.addActionListener(new splitbyparagraph());
        txtnextword.addKeyListener(new submit() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }

    public class open implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            passagetitle = JOptionPane.showInputDialog("What is the passage title?");
            open("src/assets/" + passagetitle + ".txt");
        }
    }

    public class save implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            save("src/assets/" + passagetitle + ".txt");
        }
    }

    public abstract class submit implements KeyListener{
        @Override
        public void keyReleased(KeyEvent e) {
            if ((e.getKeyCode() ==  KeyEvent.VK_ENTER) || (e.getKeyCode() == KeyEvent.VK_SPACE)){
                if(txt.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "That is the end of the passage.");
                }
                else {
                    if (txtnextword.getText().toLowerCase().trim().equals(txt.getCompare().toLowerCase().trim())) {
                        txtusertext.setText(txtusertext.getText() + "\n" + txtnextword.getText());
                        txtnextword.setText("");
                        txt.nextword();
                        lblnextword.setText("Next Word: " + txt.getCompare());
                    }
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }
    }

    public class splitbyword implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setSplitmode(1);
        }
    }

    public class splitbyscentence implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setSplitmode(2);
        }
    }

    public class splitbyparagraph implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setSplitmode(3);
        }
    }

//    public class correctlimit implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            limit = Double.valueOf(JOptionPane.showInputDialog("What is the threshold for the percentage of the word that has to match for it to be considered correct?"));
//            if (limit > 1){
//                limit = limit / 100;
//            }
//        }
//    }

    private void save(String path){
        try(PrintWriter out = new PrintWriter(path)  ){
            for(int i = 0;i<txt.getText().size();i++){
                out.println(txt.getText().get(i));
            }
            out.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private void open(String path){
        txt.getText().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                txt.addline(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txt.setSplitmode(1);
        lblnextword.setText("Next Word: " + txt.getCompare());
    }
}
