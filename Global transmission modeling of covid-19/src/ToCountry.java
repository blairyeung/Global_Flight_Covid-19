import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToCountry {
    /*public static void main(String[] args) throws FileNotFoundException {
        IO o = new IO();
        Scanner scan1 = new Scanner(System.in);
        int io = scan1.nextInt();
        String read = "E:/Global Flight/Infected Trail "+ io+".csv";
        String outf = "E:/Global Flight/Countries Trail "+ io+".csv";
        String firstcase = "E:/Global Flight/FIRSTCASE Trail "+ io+".csv";
        System.out.println(read);
        File file = new File(read);
        File out = new File(outf);
        PrintWriter print = new PrintWriter(out);
        PrintWriter print2 = new PrintWriter(firstcase);
        Scanner scan2 = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        while(scan2.hasNext()){
            lines.add(scan2.nextLine());
            System.out.println(lines.get(lines.size()-1));
        }
        ArrayList<String> Country = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Double> cases = new ArrayList<>();
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Boolean> rec = new ArrayList<>();
        ArrayList<String> LINE = new ArrayList<>();
        String county = lines.get(0);
        while(county.length()>6){
            String buffer = county.substring(0,county.indexOf(","));
            county = county.substring(county.indexOf(",")+1);
            System.out.println(buffer);
            if(county.length()<6){
                break;
            }
            if(Country.contains(county.substring(0,2))){
                index.set(Country.indexOf(county.substring(0,2)),index.get(Country.indexOf(county.substring(0,2)))+1);
            }
            else {
                Country.add(county.substring(0,2));
                index.add(1);
                cases.add(0.0);
                first.add(150);
                rec.add(false);
            }
        }
        String firstline = "";
        String nameline = "";
        for (int i = 0; i < Country.size(); i++) {
            firstline += Country.get(i) +",";
            System.out.println(Country.get(i));
            nameline +=  IO.Countryname.get(IO.Countrycode.indexOf(Country.get(i))) +",";
        }
        for (int i = 1; i < lines.size(); i++) {
            for (int i1 = 0; i1 < cases.size(); i1++) {
                cases.set(i1,0.0);
            }
            String aline = "";
            String linebuff;
            String linebuffering;
            linebuff = lines.get(i);
            for (int i1 = 0; i1 < Country.size(); i1++) {
                for (int integer = 0; integer < index.get(i1); integer++) {
                    //System.out.println(linebuffering);
                    System.out.println(true);
                    System.out.println(linebuff.substring(0,linebuff.indexOf(","))) ;
                    linebuffering = linebuff.substring(0,linebuff.indexOf(","));
                    linebuff = linebuff.substring(linebuff.indexOf(",")+1);
                    cases.set(i1,cases.get(i1)+Double.parseDouble(linebuffering));
                }
                //System.out.println(Country.get(i1));
                //System.out.println(cases.get(i1));
                aline += cases.get(i1).toString() +",";
                if(cases.get(i1)>0&&rec.get(i1)==false){
                    first.set(i1,i);
                    rec.set(i1,true);
                }
            }
            System.out.println(aline);
            System.out.println("LINE");
            LINE.add(aline);
        }
        System.out.println(nameline);
        print.println(nameline);
        for (int i = 0; i < LINE.size(); i++) {
            System.out.println(LINE.get(i));
            print.println(LINE.get(i));
        }
        print.close();
        print2.println(firstline);
        for (int i = 0; i < Country.size(); i++) {
            print2.print(first.get(i)+",");
        }
        print2.close();
    }*/
    public ToCountry(){

    }

    public static void main(String[] args) {
        ToCountry to = new ToCountry(1);
    }
    public ToCountry(int io){
        //IO o = new IO();
        //Scanner scan1 = new Scanner(System.in);
        //int io = scan1.nextInt();
        String read = "E:/Global Model/Global Flight/Infected Trail "+ Main.ini +" "+ io+".csv";
        String read2= "E:/Global Model/Global Flight/Death Trail "+ Main.ini +" "+ io+".csv";
        String outf = "E:/Global Model/Global Flight/Country Infected Trail "+ Main.ini +" "+ io+".csv";
        String outf2 = "E:/Global Model/Global Flight/Country Death Trail "+ Main.ini +" "+ io+".csv";
        String firstcase = "E:/Global Model/Global Flight/FIRSTCASE Trail "+ Main.ini +" "+ io+".csv";
        //System.out.println(read);
        File file = new File(read);
        File filedeath = new File(read2);
        File out = new File(outf);
        File outd = new File(outf2);
        PrintWriter print = null;
        PrintWriter print3 = null;
        try {
            print = new PrintWriter(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            print3 = new PrintWriter(outd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter print2 = null;
        try {
            print2 = new PrintWriter(firstcase);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan2 = null;
        try {
            scan2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan3 = null;
        try {
            scan3 = new Scanner(filedeath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> linesdeath = new ArrayList<>();
        while(scan2.hasNext()){
            lines.add(scan2.nextLine());
            linesdeath.add(scan3.nextLine());
            System.out.println(lines.get(lines.size()-1));
            System.out.println(linesdeath.get(lines.size()-1));
        }
        ArrayList<String> Country = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Double> cases = new ArrayList<>();
        ArrayList<Double> deaths = new ArrayList<>();
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Boolean> rec = new ArrayList<>();
        ArrayList<String> LINE = new ArrayList<>();
        ArrayList<String> LINEDeath = new ArrayList<>();
        String county = lines.get(0);
        while(county.length()>6){
            String buffer = county.substring(0,county.indexOf(","));
            county = county.substring(county.indexOf(",")+1);
            //System.out.println(buffer);
            if(county.length()<6){
                break;
            }
            if(Country.contains(county.substring(0,2))){
                index.set(Country.indexOf(county.substring(0,2)),index.get(Country.indexOf(county.substring(0,2)))+1);
            }
            else {
                Country.add(county.substring(0,2));
                index.add(1);
                cases.add(0.0);
                deaths.add(0.0);
                first.add(150);
                rec.add(false);
            }
        }
        String firstline = "";
        String nameline = "";
        for (int i = 0; i < Country.size(); i++) {
            firstline += Country.get(i) +",";
            System.out.println(Country.get(i));
            nameline +=  IO.Countryname.get(IO.Countrycode.indexOf(Country.get(i))) +",";
            //nameline +=  Country.get(i) +",";
        }
        for (int i = 1; i < lines.size(); i++) {
            for (int i1 = 0; i1 < cases.size(); i1++) {
                cases.set(i1,0.0);
                deaths.set(i1,0.0);
            }
            String aline = "";
            String aline2 = "";
            String linebuff;
            String linebuff2;
            String linebuffering;
            String linebuffering2;
            linebuff = lines.get(i);
            linebuff2 = linesdeath.get(i);
            for (int i1 = 0; i1 < Country.size(); i1++) {
                for (int integer = 0; integer < index.get(i1); integer++) {
                    //System.out.println(linebuffering);
                    //System.out.println(true);
                    System.out.println(linebuff.substring(0,linebuff.indexOf(","))) ;
                    System.out.println(linebuff2.substring(0,linebuff2.indexOf(","))) ;
                    linebuffering = linebuff.substring(0,linebuff.indexOf(","));
                    linebuffering2 = linebuff2.substring(0,linebuff2.indexOf(","));
                    linebuff = linebuff.substring(linebuff.indexOf(",")+1);
                    linebuff2 = linebuff2.substring(linebuff2.indexOf(",")+1);
                    cases.set(i1,cases.get(i1)+Double.parseDouble(linebuffering));
                    deaths.set(i1,deaths.get(i1)+Double.parseDouble(linebuffering2));
                }
                //System.out.println(Country.get(i1));
                //System.out.println(cases.get(i1));
                aline += cases.get(i1).toString() +",";
                aline2 += deaths.get(i1).toString() +",";
                if(cases.get(i1)>0&&rec.get(i1)==false){
                    first.set(i1,i);
                    rec.set(i1,true);
                }
            }
            System.out.println(aline);
            System.out.println("LINE");
            LINE.add(aline);
            LINEDeath.add(aline2);
        }
        System.out.println(nameline);
        print.println(nameline);
        print3.println(nameline);
        for (int i = 0; i < LINE.size(); i++) {
            //System.out.println(LINE.get(i));
            print.println(LINE.get(i));
            print3.println(LINEDeath.get(i));
        }
        print.close();
        print3.close();
        print2.println(firstline);
        for (int i = 0; i < Country.size(); i++) {
            print2.print(first.get(i)+",");
        }
        print2.close();
    }
}
