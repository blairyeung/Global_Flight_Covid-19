import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class USNYT {
    public static void main(String[] args) throws Exception{
        File Input = new File("E:/us-states.csv");
        File Output = new File("E:/USDeath.csv");
        Scanner scan = new Scanner(Input);
        PrintWriter printer = new PrintWriter(Output);
        ArrayList<String> date = new ArrayList<>();
        ArrayList<String> County = new ArrayList<>();
        ArrayList<String> States = new ArrayList<>();
        ArrayList<String> Deaths = new ArrayList<>();
        ArrayList<String> Lines = new ArrayList<>();
        while (scan.hasNext()) {
            Lines.add(scan.nextLine());
        }
        for (int i = 0; i < Lines.size(); i++) {
            String buffer = Lines.get(i);
            String Date = buffer.substring(0,buffer.indexOf(","));
            buffer = buffer.substring(buffer.indexOf(",")+1);
            String State = buffer.substring(0,buffer.indexOf(","));
            if(!date.contains(Date)){
                date.add(Date);
            }
            if(!County.contains(State)){
                County.add(State);
            }
        }
        for (int i = 0; i < date.size(); i++) {
            //printer.println(date.get(i));
        }
        for (int i = 0; i < County.size(); i++) {
           // printer.println(County.get(i));
        }
        int Matrix[][] = new int[date.size()][County.size()];

        for (int i = 0; i < Lines.size(); i++) {
            //printer.println(Lines.get(i));
            String buffer = Lines.get(i);
            String Date = buffer.substring(0,buffer.indexOf(","));
            buffer = buffer.substring(buffer.indexOf(",")+1);
            String State = buffer.substring(0,buffer.indexOf(","));
            buffer = buffer.substring(buffer.indexOf(",")+1);
            String Cases = buffer.substring(0,buffer.indexOf(","));
            int c = Integer.parseInt(Cases);
            String Death = buffer.substring(buffer.indexOf(",")+1);
            int d = Integer.parseInt(Death);
            /*printer.println(Date);
            printer.println(State);
            printer.println(Cases);
            printer.println(Death);*/
            int dateindex = date.indexOf(Date);
            int Countyindex = County.indexOf(State);
            Matrix[dateindex][Countyindex] = d;
        }
        printer.print("Date,");
        for (int i = 0; i < County.size(); i++) {
            printer.print(County.get(i)+",");
        }
        printer.println();
        for (int i = 0; i < date.size(); i++) {
            printer.print(date.get(i)+",");
            for (int i1 = 0; i1 < County.size(); i1++) {
                printer.print(Matrix[i][i1]+",");
            }
            printer.println();
        }
        printer.close();
    }
}
