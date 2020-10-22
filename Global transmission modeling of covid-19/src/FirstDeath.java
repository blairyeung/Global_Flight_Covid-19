import java.io.File;
import java.io.FileFilter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstDeath {
    static int w = 50;
    public static void main(String[] args) throws Exception{
        IO io = new IO();
        int datenum = 3;
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        String outdate[] = {"9.7","9.22","10.7","10.22"};

        File files[] = new File[50];
        Scanner scans[] = new Scanner[50];
        ArrayList<String> code = IO.Countrycode;
        ArrayList<String> countryname = IO.Countryname;
        ArrayList<String> Imported = new ArrayList<>() ;
        ArrayList<double[]> death = new ArrayList<>();
        ArrayList<Double> firstdeath = new ArrayList<>();
        File out = new File("E:/Global Model/Graphfile/"+date[datenum]+".csv");
        PrintWriter printer = new PrintWriter(out);
        for (int i = 0; i < 50; i++) {
            String path1 = "E:/Global Model/"+date[datenum]+"/Country Death Trail CN-42 ";
            String path2 = ".csv";
            String path = path1 + (i+datenum*200+1+100) + path2;
            files[i] = new File(path);
            scans[i] = new Scanner(files[i]);
        }
        for (int i = 0; i < countryname.size(); i++) {
            firstdeath.add(999999.0);
            death.add(new double[50]);
        }
        for (int i = 0; i < 50; i++) {
            firstdeath = new ArrayList<>();
            Imported = new ArrayList<>();
            for (int i1 = 0; i1 < countryname.size(); i1++) {
                firstdeath.add(999999.0);
            }
            ArrayList<String> lines = new ArrayList<>();
            String firstline = scans[i].nextLine();
            int count = 0;
            while (firstline.contains(",")) {
                String country = firstline.substring(0,firstline.indexOf(","));
                firstline =  firstline.substring(firstline.indexOf(",")+1);
                Imported.add(country);
                count++;
            }
            while (scans[i].hasNext()) {
                lines.add(scans[i].nextLine());
                //System.out.println(lines.get(lines.size()-1));
            }
            for (int i1 = 0; i1 < lines.size(); i1++) {
                int counter = 0;
                String buffer = lines.get(i1);
                while (buffer.contains(",")) {
                    String num = buffer.substring(0,buffer.indexOf(","));
                    buffer = buffer.substring(buffer.indexOf(",")+1);
                    double numberbuffer = Double.parseDouble(num);
                    //System.out.println(Imported.get(counter));
                    //System.out.println(num);
                    int index = countryname.indexOf(Imported.get(counter));
                    if(numberbuffer>0&&i1<=firstdeath.get(index)){
                        firstdeath.set(index,(double)i1);
                        //System.out.println(Imported.get(counter));
                        //System.out.println(numberbuffer);
                    }
                    counter++;
                }
            }
            double[] bufferarray;
            for (int i1 = 0; i1 < Imported.size(); i1++) {
                System.out.println(Imported.get(i1));
                int index = countryname.indexOf(Imported.get(i1));
                bufferarray = death.get(index);
                bufferarray[i] = firstdeath.get(index);
                System.out.println(firstdeath.get(index));
                death.set(index,bufferarray);
            }
        }
        System.out.println(Imported);
        for (int i = 0; i < countryname.size(); i++) {
            printer.print(countryname.get(i)+",");
        }
        printer.println();
        for (int i = 0; i < 50; i++) {
            for (int i1 = 0; i1 < countryname.size(); i1++) {
                System.out.print(death.get(i1)[i]+",");
                printer.print(death.get(i1)[i]+",");
            }
            System.out.println();
            printer.println();
        }
        File newo = new File("E:/NewOrg.csv");
        Scanner scan = new Scanner(newo);
        ArrayList<Integer> dateee = new ArrayList<>();
        while (scan.hasNext()) {
            String a = scan.nextLine();
            String line = scan.nextLine();
            while (line.contains(",")) {
                 String buffer = line.substring(0,line.indexOf(","));
                 line = line.substring(line.indexOf(",")+1);
                 int d = Integer.parseInt(buffer) - datenum*15;
                System.out.println(d);
                 dateee.add(d);
            }
        }
        for (int i = 0; i < dateee.size(); i++) {
            printer.print(dateee.get(i)+",");
        }
        for (int i = 0; i < countryname.size(); i++) {
            for (int i1 = 0; i1 < 50; i1++) {

            }
        }
        printer.close();
    }

    public static int median(int []nums){
        int nums2[]  = nums;
        bubbleSort(nums2);
        return nums2[w/2];
    }

    public static void bubbleSort(int[] array) {
        int size = array.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    exchangeElements(array, j, j + 1);
                }
            }
        }
    }
    public static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
