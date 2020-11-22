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
    private ArrayList<Integer> ArrayPackInt[][] = new ArrayList[6][16];
    private ArrayList<Double> ArrayPackDouble[][] = new ArrayList[1][16];
    Day days[] = new Day[day];

    private String NAMING;
    private String CODE;
    private String Airports[];
    public County(){
        DATA = new Data();
        Printer = null;
    }

    public void Restore(){
        double datapack[] = new double[11];
        datapack[0] = 1;//Day
        datapack[1] = 0;//Population
        datapack[2] = 0;//Infected
        datapack[3] = 0;//Exposed
        datapack[4] = 0;//Active
        datapack[5] = 0;//Resolved
        datapack[6] = 0;//Death
        datapack[7] = 0;//Lockdown
        datapack[8] = 0;//Immuned
        /*String country = NAMING.substring(0,2);
        int ind = IO.Countrycode.indexOf(country);
        double R0 = IO.BasicReproduction.get(ind);*/
        DATA = new Data("Unknown",datapack,ArrayPackInt,ArrayPackDouble, 2, new double[5], new double[16], new ArrayList<Integer>());
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

        ArrayPackInt[0] = new ArrayList[16];
        ArrayPackInt[1] = new ArrayList[16];
        ArrayPackInt[2] = new ArrayList[16];
        ArrayPackInt[3] = new ArrayList[16];
        ArrayPackInt[4] = new ArrayList[16];
        ArrayPackInt[5] = new ArrayList[16];
        ArrayPackDouble[0] = new ArrayList[16];
        this.NAMING = null;
        DATA = new Data();
        //Resume(1);
    }

    PrintWriter Printer = null;
    //PrintWriter Printer2 = null;

    public void Resume(int day, int Exported){
        //System.out.println("County: " + NAMING + " Day:" + day);
        if (Events.Ind.contains(NAMING)) {
            Event localevent = Events.Even.get(Events.Ind.indexOf(NAMING));
            transmission.Model(DATA,localevent,Exported);
        }
        /*else if(Events.Ind.contains(NAMING.substring(0,3)+"42")){

        }*/
        else {
            transmission.Model(DATA,new Event("Unknown",0,10),Exported);
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
        Days  = new double[Main.ObservationRange+1][11];
        for (int i = 0; i < Main.ObservationRange; i++) {
            for (int i1 = 0; i1 < 11; i1++) {
                Days[i][i1] = 0;
            }
        }
        this.NAMING = NAME;
        String path = Parameters.PathDefault + "/County Output/" +NAMING +" Trail "+Main.Filecode+".csv";
        File file = new File(path);
        //File file2 = new File("E:/Global Model/R Test/" +NAMING +" Patient "+Main.Filecode+".csv");
        try {
            Printer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*try {
            Printer2 = new PrintWriter(file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //System.out.println("Country: " + NAMING);
        this.population = Population;
        double datapack[] = new double[11];
        datapack[0] = 1;//Day
        datapack[1] = Population;//Population
        datapack[2] = 1;//Infected
        datapack[3] = 0;//Exposed
        datapack[4] = 0;//Active
        datapack[5] = 0;//Resolved
        datapack[6] = 0;//Death
        datapack[7] = 0;//Lockdown
        datapack[8] = 0;//Immuned
        datapack[9] = 0;//Imported
        datapack[10] = 0;//Exported
        String country = NAME.substring(0,2);
        int ind = IO.Countrycode.indexOf(country);
        double R0 = IO.BasicReproduction.get(ind);
        double[] AgePopulationStratification  = IO.AgeS.get(IO.Countrycode.indexOf(country));

        DATA = new Data(NAME,datapack,ArrayPackInt,ArrayPackDouble, R0, new double[16],AgePopulationStratification, new ArrayList<Integer>());
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

        ArrayPackInt[0] = new ArrayList[16];;
        ArrayPackInt[1] = new ArrayList[16];;
        ArrayPackInt[2] = new ArrayList[16];;
        ArrayPackInt[3] = new ArrayList[16];;
        ArrayPackInt[4] = new ArrayList[16];;
        ArrayPackInt[5] = new ArrayList[16];;
        ArrayPackDouble[0] = new ArrayList[16];

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
        Print((int) DATA.getDataPack()[0],DATA.getDataPack(),day);
        /* wfor (int i = 0; i < ability.size(); i++) {
            Printer2.println(ability.get(i)+",");
        }
        Printer2.close();*/
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
        if(DAY==2){
            Printer.println("Day,Infected,Exposed,Active Cases,Resolved,Deaths,Immuned,Imported,Exported");
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
            Printer.print(datapack[8]+",");
            Printer.print(datapack[9]+",");
            Printer.println(datapack[10]);
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
            Days[day][9] = datapack[9];
            Days[day][10] = datapack[10];
        }
    }
    public int getPopulation(){
        return population;
    }
    public int getExposed(){
        return (int) DATA.getDataPack()[3];
    }
}