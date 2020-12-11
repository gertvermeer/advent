import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {


    public List<String> readfile(String filename){
        List<String> resultsList = new ArrayList<String>();
        try {
            File myObj = new File("C:\\Users\\ws60cp\\IdeaProjects\\advent\\src\\main\\resources\\"+ filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                resultsList.add(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return resultsList;
    }

}
