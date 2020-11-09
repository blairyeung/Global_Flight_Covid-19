import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IO extends Flight{
    public static ArrayList<String> Countryname = new ArrayList<>();
    public static ArrayList<String> Countrycode = new ArrayList<>();
    public static ArrayList<Double> BasicReproduction = new ArrayList<>();
    public static ArrayList<double[]> AgeS = new ArrayList<>();
    public static ArrayList<String> Event = new ArrayList<>();
    public static ArrayList<double[][]> Matrices = new ArrayList<>();
    public static ArrayList<Integer> Countrypopulation = new ArrayList<>();

    public IO(){
        File file = new File("E:/Global Model/FlightCity/WPP2019_TotalPopulationBySex.csv");
        File Codefile = new File("E:/Global Model/FlightCity/Countrycode.csv");
        File EVENT = new File("E:/Global Model/FlightCity/Countrycode.csv");
        File fileR0 = new File("E:/Global Model/FlightCity/R File.csv");
        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scan = new Scanner(Codefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scan.hasNextLine()) {
            String buffer = scan.nextLine();
            int comma = buffer.indexOf(",");
            String sub1 = buffer.substring(comma+1);
            String sub2 = buffer.substring(0,comma);
            System.out.println(sub1);
            String c = sub1.substring(0,sub1.indexOf(","));
            sub1 = sub1.substring(sub1.indexOf(",")+1);
            String r = sub1.substring(0,sub1.indexOf(","));
            sub1 = sub1.substring(sub1.indexOf(",")+1);
            double[] ages = new double[5];
            for (int i = 0; i < 5; i++) {
                String b;
                if(i==4){
                    b = sub1;
                }
                else {
                    b = sub1.substring(0,sub1.indexOf(","));
                }
                System.out.println(b);
                sub1 = sub1.substring(sub1.indexOf(",")+1);
                double d = Double.parseDouble(b);
                ages[i] = d;
            }
            Countryname.add(sub2);
            Countrycode.add(c);
            BasicReproduction.add(Double.parseDouble(r));
            AgeS.add(ages);
        }
        for (int i = 0; i < Countrycode.size(); i++) {
            System.out.print(Countryname.get(i) + "  Code: "+ Countrycode.get(i) + "  R0：" + BasicReproduction.get(i) +"  Age Stratification: ");
            for (int i1 = 0; i1 < 5; i1++) {
                System.out.print(AgeS.get(i)[i1]+",");
            }
            System.out.println();
        }
        System.out.println(Countryname.size() + " Countries imported");
        System.out.println(Flight.CountyName.size() + " Counties imported");
        File[] SocialMix = new File[Countrycode.size()];
        Scanner[] SocialScans = new Scanner[SocialMix.length];
        for (int i = 0; i < SocialMix.length; i++) {
            String Suf = " SocialMixing.csv";
            String name = "E:/Global Model/Social Mixing/"+Countrycode.get(i)+Suf;
            SocialMix[i] = new File(name);
            try {
                SocialScans[i] = new Scanner(SocialMix[i]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < SocialMix.length; i++) {
            double[][] Matrix = new double[5][5];
            System.out.println(Countrycode.get(i));
            String Empty = SocialScans[i].nextLine();
            for (int i1 = 0; i1 < 5; i1++) {
                String line = SocialScans[i].nextLine();
                line = line.substring(line.indexOf(",")+1);
                String end = null;
                for (int i2 = 0; i2 < 4; i2++) {
                    String d = line.substring(0,line.indexOf(","));
                    double D = Double.parseDouble(d);
                    line = line.substring(line.indexOf(",")+1);
                    end = line;
                    Matrix[i1][i2] = D;
                }
                if(end.contains(",")){
                    end = end.substring(0,end.indexOf(","));
                }
                double e = Double.parseDouble(end);
                Matrix[i1][4] = e;
            }
            Matrices.add(Matrix);
        }
        for (int i = 0; i < SocialMix.length; i++) {
            System.out.println(Countrycode.get(i));
            for (int i1 = 0; i1 < 5; i1++) {
                for (int i2 = 0; i2 < 5; i2++) {
                    System.out.print(Matrices.get(i)[i1][i2]+",");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) {
        IO io = new IO();
    }
    public ArrayList[] getList(){
        ArrayList[] list = {Countryname,Countrycode,Countrypopulation};
        return list;
    }
}
