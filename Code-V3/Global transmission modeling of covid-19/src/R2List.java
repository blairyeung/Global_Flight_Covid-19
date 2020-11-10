import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class R2List {
    public static void main(String[] args) throws Exception{
        IO io = new IO();
        ArrayList<String> countrycode = IO.Countrycode;
        ArrayList<String> country = IO.Countryname;
        File files[] = new File[countrycode.size()];
        Scanner scans[] = new Scanner[countrycode.size()];
        for (int i = 0; i < countrycode.size(); i++) {
            String path1 = "E:/Global Model/Global Aug/";
            String path2 = ".csv";
            String code = countrycode.get(i);
            String outpath =path1 + code + "TR" + path2;
            files[i] = new File(outpath);
            scans[i] = new Scanner(files[i]);
        }
        for (int i = 0; i < countrycode.size(); i++) {
            String line = scans[i].nextLine();
        }
    }
}
