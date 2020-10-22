import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CityCode {
    public String Beijing = "PEK";
    public ArrayList<String[]> Cities= new ArrayList<>();
    public CityCode(){
        File file = new File("E:I");//Need modification
        Scanner scan = new Scanner("E:O");//Path
    }
    public ArrayList<String[]> getCityCode(){
        return Cities;
    }
}
