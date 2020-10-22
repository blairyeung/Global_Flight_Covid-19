import javax.management.modelmbean.ModelMBean;
import javax.print.attribute.standard.MediaSize;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NavigableMap;

public class County extends Main{
    private String CountyName;
    private int CountyCode;
    private int day = 1;
    private int population;
    private int infected = 0;
    private int exposed = 1;
    private int activecases;
    private int resovled;
    private int death;
    private double LOCKDOWN;
    private double PossibilityOfPatientOnBoard;
    private Transmission transmission = null;
    public Data DATA = null;
    static ArrayList<String> Countryname = new ArrayList<>();
    static ArrayList<String> Countrycode = new ArrayList<>();
    static ArrayList<Integer> Countrypopulation = new ArrayList<>();

    public double[][] Days = null;
    private ArrayList<Integer> ArrayPackInt[] = new ArrayList[6];
    private ArrayList<Double> ArrayPackDouble[] = new ArrayList[2];
    Day days[] = new Day[day];

    private String NAMING;
    private String CODE;
    private String Airports[];
    public County(){
        DATA = new Data();
        Printer = null;
    }

    public void Restore(){
        double datapack[] = new double[9];
        datapack[0] = 1;//Day
        datapack[1] = 0;//Population
        datapack[2] = 0;//Infected
        datapack[3] = 0;//Exposed
        datapack[4] = 0;//Active
        datapack[5] = 0;//Resolved
        datapack[6] = 0;//Death
        datapack[7] = 0;//Lockdown
        datapack[8] = 0;//Immuned
        DATA = new Data(datapack,ArrayPackInt,ArrayPackDouble);
        transmission = new Transmission();
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();
        //ArrayList<Integer> immarr = new ArrayList<>();

        ArrayPackInt[0] = inf;
        ArrayPackInt[1] = aftinf;
        ArrayPackInt[2] = property;
        ArrayPackInt[3] = patientage;
        ArrayPackInt[4] = ability;
        ArrayPackInt[5] = immun;
        ArrayPackDouble[0] = randomvalue;
        ArrayPackDouble[1] = randomcure;
        this.NAMING = null;
        DATA = new Data();
        //Resume(1);
    }

    PrintWriter Printer = null;

    public void Resume(int day){
        System.out.println("County: " + NAMING + " Day:" + day);
        if (Events.Ind.contains(NAMING)) {
            System.out.println("Event detected");
            Event localevent = Events.Even.get(Events.Ind.indexOf(NAMING));
            transmission.Model(DATA,localevent);
        }
        /*else if(Events.Ind.contains(NAMING.substring(0,3)+"42")){

        }*/
        else {
            transmission.Model(DATA);
        }
        Print((int)DATA.getDataPack()[0],DATA.getDataPack(),day);
    }

    public int getInfected(){
        return (int) DATA.getDataPack()[2];
    }

    public String getNAMING(){
        return NAMING;
    }

    public County(String NAME,int day, int iniInfe,int Population) {
        Days  = new double[Main.ObservationRange+1][9];
        for (int i = 0; i < Main.ObservationRange; i++) {
            for (int i1 = 0; i1 < 8; i1++) {
                Days[i][i1] = 0;
            }
        }
        this.NAMING = NAME;
        String path = "E:/Global Model/Global Flight/County/" +NAMING +" Trail "+Main.Filecode+".csv";
        File file = new File(path);
        try {
            Printer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Country: " + NAMING);
        this.population = Population;
        double datapack[] = new double[9];
        datapack[0] = 1;//Day
        datapack[1] = Population;//Population
        datapack[2] = 1;//Infected
        datapack[3] = 0;//Exposed
        datapack[4] = 0;//Active
        datapack[5] = 0;//Resolved
        datapack[6] = 0;//Death
        datapack[7] = 0;//Lockdown
        datapack[8] = 0;//Immuned
        DATA = new Data(datapack,ArrayPackInt,ArrayPackDouble);
        transmission = new Transmission();
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();
        //ArrayList<Integer> immarr = new ArrayList<>();

        ArrayPackInt[0] = inf;
        ArrayPackInt[1] = aftinf;
        ArrayPackInt[2] = property;
        ArrayPackInt[3] = patientage;
        ArrayPackInt[4] = ability;
        ArrayPackInt[5] = immun;
        ArrayPackDouble[0] = randomvalue;
        ArrayPackDouble[1] = randomcure;
        this.NAMING = NAME;
        //FlightToCounty();
        //AirportCheck();
        days[0] = new Day(1,100000,iniInfe,0,0,0,0,1,1);
        Transmission test = new Transmission(DATA);
        /*for (int i = 0; i < day; i++) {
            Transmission test = new Transmission(days,ArrayPackInt,ArrayPackDouble);
            Main.day++;
            if(Main.day==day-1){
                break;
            }
        }*/
        Possibility possibility = new Possibility();
        System.out.println("Complete");
        Print((int) DATA.getDataPack()[0],DATA.getDataPack(),day);
    }

    public int Export(){
        double[] Buffer = DATA.getDataPack();
        //DATA.setDatapack(Buffer);
        return (int) Buffer[3];
    }

    public double[][] getPrintPack(){
        return Days;
    }

    public void Print(int DAY, double[] datapack, int day){
        //System.out.println("DAYSSS" + DAY);
        if(DAY==2){
            Printer.println("Day,Infected,Exposed,Active Cases,Resolved,Deaths,Immuned");
            Globe.ind.add(NAMING);
        }
        else{
            Printer.print(day + ",");
            //Printer.print(datapack[1]+",");
            Printer.print(datapack[2]+",");
            Printer.print(datapack[3]+",");
            Printer.print(datapack[4]+",");
            Printer.print(datapack[5]+",");
            Printer.print(datapack[6]+",");
            //Printer.print(datapack[7]+",");
            Printer.println(datapack[8]);
            if(Days[day][2]==0){
                //Days[day] = datapack;
            }
            Days[day][0] = datapack[0];
            Days[day][1] = datapack[2];
            Days[day][2] = datapack[3];
            Days[day][4] = datapack[4];
            Days[day][5] = datapack[5];
            Days[day][6] = datapack[6];
            Days[day][7] = datapack[7];
            Days[day][8] = datapack[8];
            /*System.out.println("DAY on" + day);
            System.out.println("datapack updated");
            System.out.println(Days[day][2]);*/
        }
    }
    public int getPopulation(){
        return population;
    }
    public int getExposed(){
        return (int) DATA.getDataPack()[3];
    }
 }