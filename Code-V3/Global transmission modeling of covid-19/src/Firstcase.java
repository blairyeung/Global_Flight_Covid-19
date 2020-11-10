import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Firstcase {
    public static void main(String[] args) throws FileNotFoundException {
        IO io = new IO();
        ArrayList<String> countrycode = IO.Countrycode;
        ArrayList<String> country = IO.Countryname;
        ArrayList<String> Lines = new ArrayList<>();
        //File files[] = new File[country.size()];
        //File outpfiles[] = new File[country.size()];
        Scanner countryscan[] = new Scanner[country.size()];
        String GlobalDeathPath = "E:/Cas.csv";
        int datenum = 0;
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        String outdate[] = {"9.7","9.22","10.7","10.22"};

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
        ArrayList<Integer> First = new ArrayList<>();
        for (int i = 0; i < Lines.size(); i++) {
            cases.add(new String[counter+1]);
            First.add(1000000);
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
                number = Integer.toString((Integer.parseInt(number)+143 - 15*datenum));
                System.out.print(number+",");
                String[] buff = cases.get(i);
                buff[i1] = number;
                cases.set(i,buff);
                buffering = buffering.substring(buffering.indexOf(",")+1);
            }
        }
        for (int i = 0; i < Lines.size(); i++) {
            System.out.print(CountryDeath.get(i)+",");
            for (int i1 = 0; i1 < counter-1; i1++) {
                System.out.print(cases.get(i)[i1]+",");
                if((Integer.parseInt(cases.get(i)[i1]))!=0){
                    if(First.get(i)>i1){//+106
                        First.set(i,i1);
                    }
                }
            }
            System.out.println();
        }
        System.out.println(CountryDeath);
        System.out.println(First);
        File output = new File("E:/Global Model/FirstCase.csv");
        PrintWriter print = new PrintWriter(output);
        for (int i = 0; i < First.size(); i++) {
            System.out.print(CountryDeath.get(i)+",");
            print.print(CountryDeath.get(i)+",");
        }
        System.out.println();
        print.println();
        for (int i = 0; i < First.size(); i++) {
            System.out.print(First.get(i)+",");
            print.print(First.get(i)+106+",");
        }
        print.close();
    }
}
