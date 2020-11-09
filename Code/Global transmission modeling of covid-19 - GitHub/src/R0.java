import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class R0 {
    public static void main(String[] args) throws Exception{
        File file = new File("E:/Global Model/R0.csv");
        Scanner scan = new Scanner(file);
        PrintWriter printer = new PrintWriter("E:/Global Model/R0weighted.csv");
        ArrayList<String> country = new ArrayList<>();
        ArrayList<String> R0 = new ArrayList<>();
        while (scan.hasNext()) {
            String coun = scan.nextLine();
            String unweighted = scan.nextLine();
            String weighted = scan.nextLine();
            printer.println(coun+","+weighted);
        }
        printer.close();
    }
}
