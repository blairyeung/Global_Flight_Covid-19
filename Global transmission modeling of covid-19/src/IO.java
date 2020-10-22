import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class IO extends Flight{
    public static ArrayList<String> Countryname = new ArrayList<>();
    public static ArrayList<String> Countrycode = new ArrayList<>();
    public static ArrayList<String> Event = new ArrayList<>();
    //public Flight flight = new Flight();
    public static ArrayList<Integer> Countrypopulation = new ArrayList<>();

    public IO(){
        File file = new File("E:/Global Model/FlightCity/WPP2019_TotalPopulationBySex.csv");
        File Codefile = new File("E:/Global Model/FlightCity/Countrycode.csv");
        File EVENT = new File("E:/Global Model/FlightCity/Countrycode.csv");
        Scanner scan = null;
        Scanner eventscanner = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            eventscanner = new Scanner(EVENT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*while(scan.hasNextLine()){
            String buffer = scan.nextLine();
            int comma = buffer.indexOf(",");
            String sub1 = buffer.substring(comma+1);
            String sub2 = buffer.substring(0,comma);
            boolean repeat = false;
            if(sub2.equals("2020")){
                repeat = false;
                comma = sub1.indexOf(",");
                sub2 = sub1.substring(0,comma);
                sub1 = sub1.substring(comma+1);
                for (int i = 0; i < Countryname.size(); i++) {
                    if(Countryname.get(i).equals(sub2)){
                        repeat = true;
                        break;
                    }
                }
                if(!repeat){
                    Countryname.add(sub2);
                    Countrypopulation.add((int)(1000.0*Double.parseDouble(sub1)));
                }
            }
        }*/
        try {
            scan = new Scanner(Codefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i < Countryname.size(); i++) {
            Countrycode.add("0");
        }*/
        while (scan.hasNextLine()) {
            String buffer = scan.nextLine();
            int comma = buffer.indexOf(",");
            String sub1 = buffer.substring(comma+1);
            String sub2 = buffer.substring(0,comma);
            Countryname.add(sub2);
            Countrycode.add(sub1);
            //System.out.println(Countrycode);
            /*for (int i = 0; i < Countryname.size(); i++) {
                if(sub2.equals(Countryname.get(i))){

                    System.out.println(Countrycode);
                    break;
                }
            }*/
        }
        /*for (int i = 0; i < Countryname.size(); i++) {
            if(Countrycode.get(i).equals("0")){
                Countrycode.remove(i);
                Countryname.remove(i);
                Countrypopulation.remove(i);
                i--;
            }
        }*/

        for (int i = 0; i < Countrycode.size(); i++) {
            System.out.println(Countryname.get(i) + "  Code: "+ Countrycode.get(i));
        }
        System.out.println(Countryname.size() + " Countries imported");
        System.out.println(Flight.CountyName.size() + " Counties imported");
    }
    public static void main(String[] args) {
        IO io = new IO();
    }
    public ArrayList[] getList(){
        ArrayList[] list = {Countryname,Countrycode,Countrypopulation};
        return list;
    }
}
