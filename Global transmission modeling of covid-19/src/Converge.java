;import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Converge {
    public static void main(String[] args) throws Exception{
        FirstCase();
    }
    public Converge(){
        Scanner scan =  new Scanner(System.in);
        int Range = scan.nextInt(); 

    }
    public static void FirstCase() throws FileNotFoundException {
        ArrayList<int[]> firstcase = new ArrayList<>();
        ArrayList<String > country = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        File outputfile = new File("E:/Global Model/Global Flight/Firstcases Graphfile.csv");
        PrintWriter print = new PrintWriter(outputfile);
        int Ob = 100;//scan
        IO io = new IO();
        Scanner ScanList[] = new Scanner[Ob];
        File Filelist[] = new File[Ob];
        for (int i = 0; i < Ob; i++) {
            String path = "E:/Global Model/Global Flight/FIRSTCASE Trail "+ (i+1) +".csv";
            Filelist[i] = new File(path);
            ScanList[i] = new Scanner(Filelist[i]);
        }
        for (int i = 0; i < IO.Countrycode.size(); i++) {
            firstcase.add(new int[Ob]);
            country.add(IO.Countrycode.get(i));
        }
        ArrayList<String> line1 = new ArrayList<>();
        ArrayList<String> line2 = new ArrayList<>();
        for (int i = 0; i < Ob; i++) {
            line1.add(ScanList[i].nextLine());
            //System.out.println(line1.get(line1.size()-1));
            if(ScanList[i].hasNext()){
                line2.add(ScanList[i].nextLine());
                System.out.println(true);
            }
            else{
                line1.remove(line1.size()-1);
                line1.add("CN,");
                line2.add("0,");
            }
        }
        for (int i = 0; i < Ob; i++) {
            String buffer = line1.get(i);
            String buffercase = line2.get(i);
            while(buffer.length()>2){
                String selected = buffer.substring(0,buffer.indexOf(","));
                String day = buffercase.substring(0,buffercase.indexOf(","));
                buffer = buffer.substring(buffer.indexOf(",")+1);
                buffercase = buffercase.substring(buffercase.indexOf(",")+1);
                //System.out.println(selected);
                //System.out.println(day);
                //System.out.println(country);
                int ind = country.indexOf(selected);
                firstcase.get(ind)[i] = Integer.parseInt(day);
            }
        }
        /*for (int i = 0; i < country.size(); i++) {
            System.out.println(country.get(i));
            for (int i1 = 0; i1 < Ob; i1++) {
                System.out.print(firstcase.get(i)[i1]+",");
            }
           System.out.println();
        }*/
        /*System.out.println(country);
        System.out.println(true);*/
        for (int i = 0; i < country.size(); i++) {
            print.print(IO.Countryname.get(i)+",");
        }
        print.println();
        for (int i = 0; i < Ob; i++) {
            for (int i1 = 0; i1 < country.size(); i1++) {
                System.out.print(firstcase.get(i1)[i]+",");
                print.print(firstcase.get(i1)[i]+",");
            }
            System.out.println();
            print.println();
        }
        print.close();
    }
}
