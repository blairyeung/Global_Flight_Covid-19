import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToCode {
    public static void main(String[] args) throws Exception {
        File file = new File("E:/Global Model/R0weighted.csv");
        Scanner scan = new Scanner(file);
        File output = new File("E:/Global Model/R00W.csv");
        PrintWriter printer = new PrintWriter(output);
        IO io = new IO();
        ArrayList<String> lines = new ArrayList<>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }
        ArrayList<String> c = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String buff = lines.get(i);
            String C = buff.substring(0,buff.indexOf(",")-1);
            String R = buff.substring(buff.indexOf(",")+1);
            c.add(C);
            r.add(R);
            System.out.println(C);
            System.out.println(R);
        }
        ArrayList<String> CO = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String C = c.get(i);
            int ind = IO.Countryname.indexOf(C);
            String Code;
            if(ind == -1){
                Code = "NaN";
            }
            else{
                Code = IO.Countrycode.get(ind);
            }
            c.set(i,Code);
        }
        ArrayList<String> cods = IO.Countrycode;
        ArrayList<String> R0c = IO.Countrycode;
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            int ind = c.indexOf(R0c.get(i));
            if(ind!=-1){
                R0c.set(i,r.get(ind));
            }
            else {
                R0c.set(i,"2.68");
            }
        }
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            printer.println(IO.Countrycode.get(i)+"," + R0c.get(i));
            System.out.println(IO.Countrycode.get(i)+"," + R0c.get(i));
        }
        printer.close();
    }
}
