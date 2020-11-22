import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Globe extends Main{
    File file = new File(Parameters.PathDefault+"OVERALL.csv");
    static PrintWriter print = null;
    public boolean run[][] = null;
    public int Exported[][] = null;
    public static Random rad = new Random();
    public static ArrayList<String[]> overall = new ArrayList<>();
    public static ArrayList<String> ind = new ArrayList<String>();
    public void GarbageC(){
        for (int i1 = 0; i1 < Main.NAMING.length; i1++) {
            for (int i2 = 0; i2 < Main.NAMING[i1].length; i2++) {
                if(run[i1][i2]==true){
                    //Globe.counties[i1][i2] = new County();
                    Globe.counties[i1][i2].Restore();
                    countiesname.clear();
                    run[i1][i2] = false;
                }
            }
        }
    }
    public Globe(){
        countiesname.add(ini);
        System.out.println(ini);
        //System.out.println(IO.Countrycode.indexOf(ini.substring(0,2)));
        //System.out.println(CountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))]);
        //System.out.println(CountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))].indexOf(ini));
        counties[IO.Countrycode.indexOf(ini.substring(0,2))][CountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))].indexOf(ini)] = new County(ini,Main.ObservationRange,1,Main.PopCountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))].get(CountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))].indexOf(ini)));
        NAMING[IO.Countrycode.indexOf(ini.substring(0,2))][CountyinCountry[IO.Countrycode.indexOf(ini.substring(0,2))].indexOf(ini)] = ini;
        //counties[0][0] = new County("United States",100,10);
        run = new boolean[IO.Countrycode.size()][];
        Exported = new int[IO.Countrycode.size()][];
        for (int i1 = 0; i1 < IO.Countrycode.size(); i1++) {
            run[i1] = new boolean[counties[i1].length];
            Exported[i1] = new int[counties[i1].length];
            for (int i2 = 0; i2 < counties[i1].length; i2++) {
                run[i1][i2] = false;
                Exported[i1][i2] = 0;
            }
        }
        /*for (int i1 = 0; i1 < IO.Countrycode.size(); i1++) {
            run[i1] = new boolean[counties[i1].length];
            for (int i2 = 0; i2 < counties[i1].length; i2++) {
                run[i1][i2] = false;
            }
        }*/
        for (int i = 0; i < Main.ObservationRange; i++) {
            ArrayList<String> Exportnewcases = new ArrayList<>();
            for (int i1 = 0; i1 < IO.Countrycode.size(); i1++) {
                for (int i2 = 0; i2 < counties[i1].length; i2++) {
                    run[i1][i2] = false;
                    Exported[i1][i2] = 0;
                }
            }
            for (int i1 = 0; i1 < IO.Countrycode.size(); i1++) {
                for (int i2 = 0; i2 < counties[i1].length; i2++) {
                    if(counties[i1][i2].getInfected()!=0){
                        run[i1][i2] = true;
                        //break;
                    }
                }
            }
            for (int i1 = 0; i1 < IO.Countrycode.size(); i1++) {
                for (int i2 = 0; i2 < counties[i1].length; i2++) {
                    if(run[i1][i2]){
                        counties[i1][i2].Resume(i, Exported[i1][i2]);
                        int FLightcount = FlightCheck(i,i1,i2,CountyinCountry[i1].get(i2));
                        double Proportion = 1 * (double) FLightcount*300/ (double) counties[i1][i2].getPopulation();//ADJUST
                        Exportnewcases = RandomAssignArrival(i,i1,i2,CountyinCountry[i1].get(i2),((counties[i1][i2].getExposed()*0.8)*Proportion));
                        double[] bufferpack = counties[i1][i2].DATA.getDataPack();
                        bufferpack[10] += Exportnewcases.size();
                        counties[i1][i2].DATA.setDatapack(bufferpack);
                        Modification(i1,i2,Exportnewcases);
                    }
                }
            }
        }
        PrintResult print = new PrintResult();
        print.PRINTOUT();
    }

    public void Modification(int i1,int i2,ArrayList<String> Export){


        /*ArrayList<Integer> ArrayPackInt[][] = counties[i1][i2].DATA.getIntPack();

        for (int i = 0; i < ArrayPackInt.length; i++) {
            for (int i3 = 0; i3 < ArrayPackInt[i].length; i3++) {
                System.out.println(ArrayPackInt[i][i3]);
            }
        }*/
        /*for (int i = 1; i < Export.size(); i++) {
            //System.out.println(ArrayPackInt[i][6]);
            ArrayPackInt[i][6].remove(ArrayPackInt[i][6].size()-1);
        }*/

        Exported[i1][i2] = Export.size();
        for (int i = 0; i < Export.size(); i++) {//Remove the patients from that city
            int country;
            int county;
            if(IO.IATACode.indexOf(Export.get(i))==-1){
                continue;
            }
            String buffer = IO.CountyName.get(IO.IATACode.indexOf(Export.get(i)));
            //System.out.println(buffer);
            //country = AirportinCountyandCountry[IO.Countrycode.indexOf(buffer.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Export.get(i).substring(0, 2))].indexOf(buffer)].indexOf(buffer);
            //System.out.println(IO.CountrycodeCN.size());
            country = IO.Countrycode.indexOf(buffer.substring(0,2));
            county = CountyinCountry[IO.Countrycode.indexOf(buffer.substring(0,2))].indexOf(buffer);
            if(country==-1||county==-1){
                continue;
            }
            if(country==i1&&county==i2){
                continue;
            }
            if(counties[country][county].getInfected()==0){
                //System.out.println(CountyinCountry[country].get(county));
                counties[country][county] = new County(buffer,i,1,Main.PopCountyinCountry[IO.Countrycode.indexOf(buffer.substring(0,2))].get(CountyinCountry[IO.Countrycode.indexOf(buffer.substring(0,2))].indexOf(buffer)));
                NAMING[country][county] = buffer;
            }
            else{
                double[] Modify = counties[country][county].DATA.getDataPack();
                Modify[9] += 1;
                if(Modify[2]>10){
                    Modify[2] += 1;
                    Modify[3] += 1;
                    Modify[4] += 1;
                    Modify[8] += 1;
                }
                counties[country][county].DATA.setDatapack(Modify);
            }
        }
    }
    public int FlightCheck(int i,int i1,int i2, String Name){
        int flightcount = 0;
        for (int i3 = 0; i3 < AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].size(); i3++) {
            //System.out.println(Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(ini.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(ini.substring(0, 2))].indexOf(ini)].get(i3)));
            if((Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3)))!=-1){
                for (int i4 = 0; i4 < Flight.Flights[0][8][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].size(); i4++) {
                    flightcount++;
                }
            }
        }
        //System.out.println(flightcount + " FLIGHTS");
        return flightcount;
    }

    public ArrayList RandomAssignArrival(int i, int i1, int i2, String Name, double OnBoard) {
        //OnBoard = 0;
        ArrayList<String> Places = new ArrayList<>();
        ArrayList<Integer> CODE = new ArrayList<>();
        ArrayList<Integer> DEPAR = new ArrayList<>();
        int Counter = 0;
        Random rad = new Random();
        ArrayList<String> choices = AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0,2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0,2))].indexOf(Name)];
        for (int i3 = 0; i3 < choices.size(); i3++) {
            if(!Flight.MenuIndex.contains(choices.get(i3))){
                choices.remove(i3);
            }
        }
        for (int i3 = 0; i3 < (int) OnBoard*(1+0.1*rad.nextGaussian()); i3++) {
            DEPAR.add(rad.nextInt(choices.size()));
        }
        if(DEPAR.size()==0){
            return new ArrayList();
        }
        int date = Main.day;
        int week = Main.day%7;
        for (int i3 = 0; i3 < DEPAR.size(); i3++) {
            if (Flight.MenuIndex.indexOf(choices.get(DEPAR.get(i3)))!=-1) {
                int Limit = Flight.Flights[0][8][0][Flight.MenuIndex.indexOf(choices.get(DEPAR.get(i3)))].size();
                if(Limit!=0){
                    CODE.add(rad.nextInt(Limit));
                }
                if(Flight.MenuIndex.indexOf(choices.get(DEPAR.get(i3)))>=Flight.Flights[0][8][week].length){
                    for (int i4 = 0; i4 < Flight.Flights[0][8][week].length; i4++) {
                        for (int i5 = 0; i5 < Flight.Flights[0][8][week][i4].size(); i5++) {
                            //System.out.println(Flight.Flights[0][8][week][i4].get(i5).getArrive());
                        }
                    }
                    //System.out.println(Name);
                }
                if(i3<CODE.size()) {
                    int ind = CODE.get(i3);
                    Places.add(Flight.Flights[0][8][week][Flight.MenuIndex.indexOf(choices.get(DEPAR.get(i3)))].get(ind).getArrive());
                }
            }
        }
        return Places;
    }
}