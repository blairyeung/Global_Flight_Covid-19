import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CompleteEvent {
    public static void main(String[] args) throws Exception{
        File eventfile = new File("E:/Global Model/FlightCity/Event.csv");
        File popfile = new File("E:/Global Model/FlightCity/Popfile.csv");
        File output = new File("E:/Global Model/FlightCity/EventsCom.csv");
        PrintWriter printer = new PrintWriter(output);
        Scanner scan1 = new Scanner(eventfile);
        Scanner scan2 = new Scanner(popfile);
        ArrayList<String> code = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> speed = new ArrayList<>();
        while (scan2.hasNext()) {
            String buffer = scan2.nextLine();
            buffer = buffer.substring(0,buffer.indexOf(","));
            System.out.println(buffer);
            code.add(buffer);
            time.add("3");
            speed.add("100");
        }
        ArrayList<String> buffering = new ArrayList<>();
        while (scan1.hasNext()) {
            String buffer = scan1.nextLine();
            String county = buffer.substring(0,buffer.indexOf(","));
            buffer = buffer.substring(buffer.indexOf(",")+1);
            String tim = buffer.substring(0,buffer.indexOf(","));
            String sped = buffer.substring(buffer.indexOf(",")+1);
            System.out.println(county);
            System.out.println(tim);
            System.out.println(sped);
            int ind = code.indexOf(county);
            if(ind!=-1){
                time.set(ind,tim);
                speed.set(ind,sped);
            }
        }
        for (int i = 0; i < code.size(); i++) {
            printer.println(code.get(i)+","+time.get(i)+","+speed.get(i));
        }
        printer.close();
    }
}
