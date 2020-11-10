import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToName {
    public static void main(String[] args) throws Exception{
        Scanner user = new Scanner(System.in);
        String io = user.nextLine();
        File scanfile = new File("E:/Global Model/FlightCity/Codec.txt");
        File scanfile2 = new File("E:/Global Model/Death Trail CN-42 "+ io+".csv");
        File file = new File("E:/Global Model/Graph Trail "+ io+".csv");
        File logfile = new File("E:/Global Model/Scale Trail "+ io+".csv");
        Scanner scan = new Scanner(scanfile);
        Scanner scan2 = new Scanner(scanfile2);
        PrintWriter print = new PrintWriter(file);
        PrintWriter print2 = new PrintWriter(logfile);
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> Code = new ArrayList<>();
        ArrayList<String> Name = new ArrayList<>();
        while (scan.hasNext()) {
            line.add(scan.nextLine());
        }
        char cha = '"';
        for (int i = 1; i < line.size(); i++) {
            //System.out.println(line.get(i));
            String buffer = line.get(i);
            String code;
            String name;
            for (int i1 = 0; i1 < 5; i1++) {
                buffer = buffer.substring(buffer.indexOf(cha)+1);
                System.out.println(buffer);
            }
            code = buffer.substring(0,buffer.indexOf(cha));
            System.out.println(code);
            Code.add(code);
            for (int i1 = 0; i1 < 4; i1++) {
                buffer = buffer.substring(buffer.indexOf(cha)+1);
                System.out.println(buffer);
            }
            //
            name = buffer.substring(0,buffer.indexOf(cha));
            System.out.println(name);
            Name.add(name);
        }
        String firstline = scan2.nextLine();
        ArrayList<String> out = new ArrayList<>();
        while (firstline.length() > 4) {
            String buffer = firstline.substring(0,firstline.indexOf(","));
            firstline = firstline.substring(firstline.indexOf(",")+1);
            buffer = buffer.substring(0,buffer.indexOf("Death")-1);
            System.out.println(buffer);
            if(Code.contains(buffer)){
                out.add(Name.get(Code.indexOf(buffer)));
            }
            else {
                System.out.println(buffer);
                out.add("N/A");
            }
        }
        System.out.println(out);
        String LINE = "";
        for (int i = 0; i < out.size(); i++) {
            System.out.print(out.get(i)+",");
            LINE +=out.get(i)+",";
        }
        print.println(LINE);
        print2.println(LINE);
        ArrayList<String> logscale = new ArrayList<>();
        while (scan2.hasNext()) {
            String str = scan2.nextLine();
            String logline = "";
            while (str.contains(",")) {
                int i = (int)Double.parseDouble(str.substring(0,str.indexOf(",")));
                if(i==0){
                    logline+= "0,";
                }
                else {
                    logline += Double.toString(1+Math.log10((double) i)) +",";
                }
                str = str.substring(str.indexOf(",")+1);
                //System.out.println(str);
            }
            logscale.add(logline);
            System.out.println(logline);
            print2.println(logline);
        }
        print.close();
    }
}
