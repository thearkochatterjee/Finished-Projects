public class Contact {
    private String fname, lname, number, middlename;

    public Contact(){
        fname = "";
        lname = "";
        number = "";
        middlename = "";
    }

    public Contact(String line){
        fname = line.split(",")[0];
        middlename = line.split(",")[1];
        lname = line.split(",")[2];
        number = line.split(",")[3];
    }

    public Contact(String fname, String lname, String number, String middlename){
        this.fname = fname;
        this.lname = lname;
        this.number = number;
        this.middlename = middlename;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getNumber() {
        return number;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void resetblanks(){
        if (fname.equals("-")){
            fname = "";
        }
        if (lname.equals("-")){
            lname = "";
        }
        if (number.equals("-")){
            number = "";
        }
        if(middlename.equals("-")){
            middlename = "";
        }
    }

    public void createblanks(){
        if (fname.equals("")){
            fname = "-";
        }
        if (lname.equals("")){
            lname = "-";
        }
        if (number.equals("")){
            number = "-";
        }
        if (middlename.equals("")){
            middlename = "-";
        }
    }

    @Override
    public String toString() {
        return fname + "," + middlename + "," + lname + "," + number;
    }
}
