import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ByContinent {
    /*public static void main(String[] args) throws FileNotFoundException {
        IO io = new IO();
        ArrayList<String> countrycode = IO.Countrycode;
        ArrayList<String> country = IO.Countryname;
        ArrayList<String> Lines = new ArrayList<>();
        File files[] = new File[country.size()];
        Scanner countryscan[] = new Scanner[country.size()];
        String GlobalDeathPath = "E:/gl.csv";
        File GlobalDeathFile = new File(GlobalDeathPath);
        Scanner DeathFileScanner = new Scanner(GlobalDeathFile);
        while (DeathFileScanner.hasNext()) {
            Lines.add(DeathFileScanner.nextLine());
        }
        int counter = 0;
        String buffer = Lines.get(0);
        while (buffer.contains(",")) {
            counter++;
            buffer = buffer.substring(buffer.indexOf(",")+1);
        }
        System.out.println(counter);
        ArrayList<String[]> cases = new ArrayList<>();
        ArrayList<String> CountryDeath = new ArrayList<>();
        for (int i = 0; i < Lines.size(); i++) {
            cases.add(new String[counter+1]);
        }
        for (int i = 0; i < Lines.size(); i++) {
            String countries;
            String buffering = Lines.get(i);
            countries = buffering.substring(0,buffering.indexOf(","));
            buffering = buffering.substring(buffering.indexOf(",")+1);
            System.out.println(countries);
            CountryDeath.add(countries);
            for (int i1 = 0; i1 < counter-1; i1++) {
                String number = buffering.substring(0,buffering.indexOf(","));
                System.out.print(number+",");
                String[] buff = cases.get(i);
                buff[i1] = number;
                cases.set(i,buff);
                buffering = buffering.substring(buffering.indexOf(",")+1);
            }
        }
        for (int i = 0; i < Lines.size(); i++) {
            System.out.print(CountryDeath.get(i)+",");
            int index = country.indexOf(CountryDeath.get(i));
            for (int i1 = 0; i1 < counter-1; i1++) {
                System.out.print(cases.get(i)[i1]+",");
            }
            System.out.println();
        }
        ArrayList<Integer> firstdeath = new ArrayList<>();
        for (int i = 0; i < country.size(); i++) {
            firstdeath.add(1000);
        }
        System.out.println(country);
        for (int i = 0; i < Lines.size(); i++) {
            System.out.print(CountryDeath.get(i)+",");
            int index = country.indexOf(CountryDeath.get(i));
            System.out.println(i);
            for (int i1 = 0; i1 < counter-1; i1++) {
                int n = Integer.parseInt(cases.get(i)[i1]);
                System.out.print(n+",");
                if(n>0&&i1<=firstdeath.get(i)){
                    firstdeath.set(i,i1);
                    break;
                }
            }
            System.out.println();
        }
        System.out.println(country);
        System.out.println(firstdeath);
        File out = new File("E:/DeathFile.csv");
        PrintWriter print = new PrintWriter(out);
        for (int i = 0; i < Lines.size(); i++) {
            System.out.print(CountryDeath.get(i)+",");
            print.print(CountryDeath.get(i)+",");
        }
        System.out.println();
        print.println();
        for (int i = 0; i < Lines.size(); i++) {
            System.out.print((firstdeath.get(i)+106)+",");
            print.print((firstdeath.get(i)+106)+",");
        }
        print.close();
    }*/
    public static void main(String[] args) throws Exception{
        String[] continent = {"Asia","Europe","Africa","North America","South America","Oceania"};
        File files[] = new File[6];
        Scanner scans[] = new Scanner[6];
        File output[] = new File[6];
        PrintWriter printers[] = new PrintWriter[6];
        for (int i = 0; i < 6; i++) {
            String path1 = "E:/Global Model/Continent/";
            String path2 = ".csv";
            String path = path1 + continent[i] + path2;
            files[i] = new File(path);
            scans[i] = new Scanner(files[i]);
            String outputpath = path1 + continent[i]+" Deathsss"+ path2;
            output[i] = new File(outputpath);
            printers[i] = new PrintWriter(output[i]);
        }
        File Global = new File("E:/Global Model/sample.csv");
        Scanner GlobalScan = new Scanner(Global);
        ArrayList<String> Country = new ArrayList<>();
        ArrayList<int[]> Dates = new ArrayList<>();
        String Line;
        /*while (Line.contains(",")) {
            String Coun = Line.substring(0,Line.indexOf(","));
            Line = Line.substring(Line.indexOf(",")+1);
            Country.add(Coun);
        }*/
        for (int i = 0; i < 250; i++) {
            Dates.add(new int[223]);
        }
        ArrayList<String> arr = new ArrayList<>();
        int Countrycounter = 0;
        while (GlobalScan.hasNext()) {
            Line = GlobalScan.nextLine();
            int counter = 0;
            String Countryname = Line.substring(0,Line.indexOf(","));
            Country.add(Countryname);
            Line = Line.substring(Line.indexOf(",")+1);
            arr.add(Line);
            System.out.println(Countryname);
            do{
                String Coun = Line.substring(0,Line.indexOf(","));
                Line = Line.substring(Line.indexOf(",")+1);
                int n;
                if(!Coun.isEmpty()){
                     n = (int)Double.parseDouble(Coun);
                }
                else {
                     n = 0;
                }
                int buffer[] = Dates.get(Countrycounter);
                buffer[counter] = n;
                System.out.print(n+",");
                Dates.set(Countrycounter,buffer);
                counter++;
            }while(Line.contains(","));
            Countrycounter++;
            System.out.println();
        }
        for (int i = 0; i < Dates.size(); i++) {
            //System.out.print(Country.get(i)+",");
            for (int i1 = 0; i1 < Dates.get(i).length; i1++) {
                System.out.print(Dates.get(i)[i1]+",");
            }
            System.out.println();
        }
        for (int i = 0; i < 6; i++) {
            System.out.println(continent[i]);
            ArrayList<String> COUNTRIES = new ArrayList<>();
            ArrayList<Integer> DateNum = new ArrayList<>();
            while (scans[i].hasNext()) {
                String line = scans[i].nextLine();
                String thisCountry = line.substring(0,line.indexOf(","));
                System.out.println(thisCountry);
                int ind = Country.indexOf(thisCountry);
                System.out.println(ind);
                //System.out.println(ind);
                if(ind!=-1){
                    System.out.print(Country.get(ind)+",");
                    printers[i].print(Country.get(ind)+",");
                    System.out.println(arr.get(ind));
                    printers[i].print(arr.get(ind)+",");
                    /*for (int i1 = 0; i1 < Dates.get(ind).length; i1++) {
                        System.out.print(Dates.get(ind)[i1]+",");
                        printers[i].print(Dates.get(ind)[i1]+",");
                    }*/
                    System.out.println();
                    printers[i].println();
                }
            }
            printers[i].close();
        }
    }
}
