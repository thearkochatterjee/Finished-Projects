import java.io.*;
import java.util.ArrayList;

public class saveandretrieve {
    public void save(String path, ArrayList<Contact> arrsave){
        try(PrintWriter out = new PrintWriter(path)){
            for(int i = 0;i<arrsave.size();i++){
                out.println(arrsave.get(i).toString());
            }
            out.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public ArrayList<String> open(String path){
        ArrayList<String> arrlines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                arrlines.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrlines;
    }
}
