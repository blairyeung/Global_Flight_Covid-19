import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AgeSt {
    public static void main(String[] args) throws Exception{
        File input = new File("E:/Global Model/Age.csv");
        Scanner scan = new Scanner(input);
        File output = new File("E:/Global Model/Age Stratification.csv");
        PrintWriter printer = new PrintWriter(output);
        ArrayList<String> lines = new ArrayList<>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
            //System.out.println(lines.get(lines.size()-1));
        }
        ArrayList<String> Regions = new ArrayList<>();
        ArrayList<double[]> stratification = new ArrayList<>();
        for (int i = 0; i < lines.size()/5; i++) {
            String countryline = lines.get(5*i);
            /*if(i==0){
                 countryline = lines.get(5*i+1);
            }*/
            //System.out.println(countryline);
            System.out.println(true);
            String c = countryline.substring(0,countryline.indexOf(","));
            Regions.add(c);
            //System.out.println(i);
            //System.out.println(c);
            double[] s = new double[5];
            for (int i1 = 5*i; i1 <5+i*5; i1++) {
                String line = lines.get(i1);
                int ind = line.indexOf(':');
                int ind2 = line.indexOf("%");
                line = line.substring(ind+2,ind2);
                //System.out.println(line);
                double p = Double.parseDouble(line);
                //System.out.println(p);
                s[i1-5*i] = p;
            }
            stratification.add(s);
        }
        for (int i = 0; i < Regions.size(); i++) {
            System.out.print(Regions.get(i)+",");
            printer.print(Regions.get(i)+",");
            double[] S = stratification.get(i);
            for (int i1 = 0; i1 < S.length; i1++) {
                System.out.print(S[i1]+",");
                printer.print(S[i1]+",");
            }
            System.out.println();
            printer.println();
        }
        printer.close();
        printer = null;
        File file = new File("E:/Global Model/Age Stratification.csv");
        scan = new Scanner(file);
        output = new File("E:/Global Model/Ages.csv");
        printer = new PrintWriter(output);
        IO io = new IO();
        lines = new ArrayList<>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }
        ArrayList<String> c = new ArrayList<>();
        ArrayList<String> r = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String buff = lines.get(i);
            String C = buff.substring(0,buff.indexOf(","));
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
        ArrayList<String> R0c = new ArrayList<>();
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            R0c.add(IO.Countrycode.get(i));
        }
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            int ind = c.indexOf(R0c.get(i));
            if(ind!=-1){
                R0c.set(i,r.get(ind));
            }
            else {
                R0c.set(i,"25.33,15.42,40.67,9.09,9.49,");
            }
        }
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            printer.println(IO.Countrycode.get(i)+"," + R0c.get(i));
            System.out.println(IO.Countrycode.get(i)+"," + R0c.get(i));
        }
        printer.close();
    }
}
