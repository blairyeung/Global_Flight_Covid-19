import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UA {
    public static void main(String[] args) throws Exception{
        File file = new File("E:/Global Model/FlightCity/AIR.csv");
        Scanner scan = new Scanner(file);
        ArrayList<String> Lines= new ArrayList<>();
        while (scan.hasNext()) {
            String buffer = scan.nextLine();
            if(buffer.contains("-U-A")){
            }
            else {
                Lines.add(buffer);
            }

        }
        /*for (int i = 0; i < Lines.size(); i++) {
            if(Lines.get(i).contains("-U-A")){
                Lines.remove(i);
            }
        }*/
        File output = new File("E:/Global Model/FlightCity/Airs.csv");
        PrintWriter printer = new PrintWriter(output);
        for (int i = 0; i < Lines.size(); i++) {
            printer.println(Lines.get(i));
        }
    }
}
