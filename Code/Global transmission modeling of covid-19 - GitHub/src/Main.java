import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class Main {
    static Output out = new Output();
    static int Filecode;
    static public int day = 0;
    final static public int ObservationRange = 210;
    static public IO io = new IO();
    static County counties[][] = new County[IO.Countryname.size()][];
    static String NAMING[][] = new String[IO.Countryname.size()][];
    static ArrayList<String> countiesname    = new ArrayList<>();
    static ArrayList<String> CountyinCountry[] = new ArrayList[IO.Countryname.size()];
    static ArrayList<Integer> PopCountyinCountry[] = new ArrayList[IO.Countryname.size()] ;
    static ArrayList<String> AirportinCountyandCountry[][] = new ArrayList[IO.Countryname.size()][];
    static ArrayList<String> Menu[][] = new ArrayList[IO.Countryname .size()][];
    static ArrayList<String> AirportinCounty[] = null;
    static ArrayList<String> CountyList = new ArrayList<>();
    static ArrayList<String> AirportList = new ArrayList<>();
    static ArrayList<String> buffering[] = new ArrayList[CountyList.size()];

    public static ArrayList[][][][] Departfromcity= new ArrayList[2][12][31][];//Arraylist to store flight time
    public static ArrayList[][][][] Arrivetocity= new ArrayList[2][12][31][];//Arrayli st to store flight time
    public static ArrayList<String> Departfromcitylist = new ArrayList<>();
    public static ArrayList<String> Arrivetocitylist = new ArrayList<>();
    public static ArrayList<Integer> FlightsonDay = new ArrayList<>();
    static String ini;
    public  static void main(String[] args) {
        Events even = new Events();
        Scanner input = new Scanner(System.in);
        int length = 1;//define length (Number of countries)
        ArrayList[][] acc = CountyToCountry();
        System.out.println(CountyList.size());
        System.out.println(CountyList);
        File popfile = new File("E:/Global Model/FlightCity/Popfile.csv");
        System.out.println("Initial County (Code)?");

        ini = "CN-42";//ORIGIN
        Filecode = out.ReadFile();
        System.out.println(Filecode);
        while(Filecode<500){
            PopulatoinS();
            Filecode = out.ReadFile();
            Globe world = new Globe();
            ToCountry to = new ToCountry(Filecode);
            CountyToCountry();
            world.GarbageC();
            out.OutFile();
        }
    }

    public static void PopulatoinS(){
        File popfile = new File("E:/Global Model/FlightCity/Popfile.csv");
        try {
            Scanner scan2 = new Scanner(popfile);
            while (scan2.hasNext()) {
                String scanbuff = scan2.next();
                String County = scanbuff.substring(0,scanbuff.indexOf(","));
                String Country = County.substring(0,2);
                int pop;
                System.out.println(scanbuff.substring(scanbuff.indexOf(",")+1));
                if(scanbuff.substring(scanbuff.indexOf(",")+1).isEmpty()){
                    pop = 1000000;
                }
                else {
                    pop = Integer.parseInt(scanbuff.substring(scanbuff.indexOf(",")+1));
                }
                int countryind = IO.Countrycode.indexOf(Country);
                int countyind = CountyinCountry[countryind].indexOf(County);
                System.out.println(County);
                if(countyind==-1){
                    continue;
                }
                System.out.println(CountyinCountry[countryind]);
                PopCountyinCountry[countryind].set(countyind,pop);
                System.out.println(CountyinCountry[countryind].get(countyind));
                System.out.println(PopCountyinCountry[countryind].get(countyind));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Day getSeries() {
        return new Day();//Change
    }

    public static ArrayList[][] CountyToCountry() {
        for (int i = 0; i < Flight.CountyName.size(); i++) {
            if(CountyList.indexOf(Flight.CountyName.get(i))==-1){
                CountyList.add(Flight.CountyName.get(i));
            }
        }
        AirportinCounty = new ArrayList[CountyList.size()];
        CountyinCountry = new ArrayList[IO.Countryname.size()];
        AirportinCountyandCountry = new ArrayList[IO.Countryname.size()][];

        for (int i = 0; i < IO.Countryname.size(); i++) {
            CountyinCountry[i] = new ArrayList<>();
            PopCountyinCountry[i] = new ArrayList<>();
            for (int i1 = 0; i1 < CountyList.size(); i1++) {
                String buffer = CountyList.get(i1).substring(0,2);
                if(IO.Countrycode.get(i).equals(buffer)){
                    CountyinCountry[i].add(CountyList.get(i1));
                    PopCountyinCountry[i].add(1000000);
                }
            }
        }

        for (int i = 0; i < CountyinCountry.length; i++) {
            counties[i] = new County[CountyinCountry[i].size()];
            NAMING[i] = new String[CountyinCountry[i].size()];
            for (int i1 = 0; i1 < CountyinCountry[i].size(); i1++) {
                counties[i][i1] = new County();
                NAMING[i][i1] = null;
            }
        }

        for (int i = 0; i < IO.Countryname.size(); i++) {
            AirportinCountyandCountry[i] = new ArrayList[CountyinCountry[i].size()];
            for (int i1 = 0; i1 < CountyinCountry[i].size(); i1++) {
                AirportinCountyandCountry[i][i1] = new ArrayList<>();
                for (int i2 = 0; i2 < Flight.IATACode.size(); i2++) {
                    if(Flight.CountyName.get(i2).equals(CountyinCountry[i].get(i1))){
                        AirportinCountyandCountry[i][i1].add(Flight.IATACode.get(i2));
                    }
                }
                System.out.println("Country: "+IO.Countryname.get(i));
                System.out.println("County: "+CountyinCountry[i].get(i1));
                System.out.println(AirportinCountyandCountry[i][i1]);
            }
        }

        return AirportinCountyandCountry ;

    }

    public static void FlightSearch() {
        Scanner scan = new Scanner(System.in);
        String target = scan.nextLine();
        for (int i = 0; i < CountyinCountry.length; i++) {
            for (int i1 = 0; i1 < CountyinCountry[i].size(); i1++) {
                if(AirportinCountyandCountry[i][i1].contains(target)){
                    System.out.println("Target: " + target + ";Country and region: " + IO.Countryname.get(i) +" "+ CountyinCountry[i].get(i1));
                }
            }
        }
    }

    public static void FlightToCounty() {
        String depart = null;
        String arrive = null;
    }
}
