import java.util.ArrayList;

public class combine {
    public void combine(){
        ArrayList<String> arrfname = new ArrayList<String>();
        ArrayList<String> arrlname = new ArrayList<String>();
        ArrayList<String> arrmiddle = new ArrayList<String>();
        ArrayList<String> arrnumber = new ArrayList<String>();
        saveandretrieve open = new saveandretrieve();
        arrfname = open.open("src/assets/first name.txt");
        arrlname = open.open("src/assets/last name.txt");
        arrmiddle = open.open("src/assets/middle name.txt");
        arrnumber = open.open("src/assets/number.txt");
        addblanks(arrfname, arrlname);
        addblanks(arrlname,arrmiddle);
        addblanks(arrmiddle,arrnumber);
        ArrayList<Contact> arrcontact = new ArrayList<Contact>();
        remove(arrnumber,'-');
        remove(arrnumber,'(');
        remove(arrnumber,')');
        remove(arrnumber,' ');
        remove(arrfname,' ');
        remove(arrlname,' ');
        remove(arrmiddle,' ');
        replaceblank(arrfname);
        replaceblank(arrlname);
        replaceblank(arrmiddle);
        replaceblank(arrnumber);
        if(allsamesize(arrfname,arrlname,arrmiddle,arrnumber)) {
            for (int i = 0; i < arrfname.size(); i++) {
                arrcontact.add(new Contact(arrfname.get(i), arrlname.get(i), arrnumber.get(i), arrmiddle.get(i)));
            }
            open.save("src/assets/contacts.txt",arrcontact);
        }
        else {
            System.out.println("Not all the same size");
//            System.exit(0);
        }
    }

    private void remove(ArrayList<String> arr, char remove){
        String temp = "";
        for(int i = 0;i<arr.size();i++){
            temp = arr.get(i);
            arr.set(i,removecharacter(temp,remove));
        }
    }

    private void addblanks(ArrayList<String> arr1, ArrayList arr2){
        if (arr1.size() != arr2.size()){
            if(arr1.size() > arr2.size()){
                while (arr2.size()<arr1.size()){
                    arr2.add("");
                }
            }
            else{
                while (arr2.size()>arr1.size()){
                    arr1.add("");
                }
            }
        }
    }

    private boolean allsamesize(ArrayList<String> arr1, ArrayList<String> arr2, ArrayList<String> arr3, ArrayList<String> arr4){
        return arr1.size() == arr2.size() && arr2.size() == arr3.size() && arr3.size() == arr4.size();
    }

    private String removecharacter(String word, char remove){
        String temp = "";
        for(int i = 0;i<word.length();i++){
            if(word.charAt(i) != remove){
                temp += word.charAt(i);
            }
        }
        return temp;
    }

    private void replaceblank(ArrayList<String> arr){
        for(int i = 0;i<arr.size();i++){
            if(arr.get(i).isEmpty()){
                arr.set(i,"-");
            }
        }
    }
}
