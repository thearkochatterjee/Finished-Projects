import javax.swing.*;
import java.util.ArrayList;

public class Text {
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> arrsplit = new ArrayList<String>();
    private int splitmode = 0;

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void addline(String line){
        text.add(line);
    }

    public void nextword(){
        arrsplit.remove(0);
    }

    private void createwordlist(){
        arrsplit.clear();
        for(int x = 0;x<text.size();x++){
            switch (splitmode){
                case 1:
                    for (int i = 0;i<text.get(x).split(" ").length;i++){
                        arrsplit.add(text.get(x).split(" ")[i]);
                    }
                    break;
                case 2:
                    for (int i = 0;i<text.get(x).split("[.?!]").length;i++){
                        arrsplit.add(text.get(x).split("[.?!]")[i]);
                    }
                    break;
                case 3:
                    for (int i = 0;i<text.get(x).split("\n").length;i++){
                        arrsplit.add(text.get(x).split("\n")[i]);
                    }
                    break;
            }
        }
    }

    private boolean hasInvalidCharacter(String test){
        for(int x = 0; x < test.length(); x++){
            if (!inrange((int) test.toLowerCase().charAt(x))){
                return true;
            }
        }
        return false;
    }

    private boolean inrange(int test){
        return ((test >= 97 && test <= 122) || (test >= 48 && test <= 57));
    }

    private String removeinvalidcharacters(String test){
        String ans = "";
        for(int i = 0;i<test.length();i++){
            if(inrange((int) test.toCharArray()[i])){
                ans += test.toCharArray()[i];
            }
        }
        return ans;
    }

    public String getCompare() {
        if(hasInvalidCharacter(arrsplit.get(0))){
            arrsplit.set(0, removeinvalidcharacters(arrsplit.get(0)));
        }
        return arrsplit.get(0);
    }

    public int getSplitmode() {
        return splitmode;
    }

    public void setSplitmode(int splitmode) {
        this.splitmode = splitmode;
        createwordlist();
    }
}
