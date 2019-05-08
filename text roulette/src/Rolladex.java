import java.util.ArrayList;

public class Rolladex {
    private ArrayList<Contact> arrcontacts = new ArrayList<Contact>();

    public Rolladex(){
        arrcontacts = new ArrayList<Contact>();
    }

    public void newcontact(String fname, String lname, String number, String middlename){
        arrcontacts.add(new Contact(fname, lname, number, middlename));
    }

    public Contact randomselection(){
        return arrcontacts.get((int)(Math.random() * arrcontacts.size()));
    }

    public Contact lastadded(){
        return arrcontacts.get(arrcontacts.size()-1);
    }

    public void save(String path){
        saveandretrieve save = new saveandretrieve();
        for(int i = 0;i<arrcontacts.size();i++){
            arrcontacts.get(i).createblanks();
        }
        save.save(path, arrcontacts);
    }

    public void open(String path){
        saveandretrieve open = new saveandretrieve();
        ArrayList<String> temp = open.open(path);
        for(int i = 0;i<temp.size();i++){
            arrcontacts.add(new Contact(temp.get(i)));
            arrcontacts.get(arrcontacts.size()-1).resetblanks();
        }
    }

    public void remove(int index){
        arrcontacts.remove(index);
    }

    public ArrayList<Contact> getArrcontacts() {
        return arrcontacts;
    }

    public void setArrcontacts(ArrayList<Contact> arrcontacts) {
        this.arrcontacts = arrcontacts;
    }
}
