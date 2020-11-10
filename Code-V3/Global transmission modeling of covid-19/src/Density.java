import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Density {
    public static void main(String[] args) throws Exception{
        File file = new File("E:/Global Model/avg.csv");
        Scanner scan = new Scanner(file);
        File output = new File("E:/Global Model/avgRank.csv");
        PrintWriter printer = new PrintWriter(output);
        ArrayList<String> country = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }
        String countryline = lines.get(0);
        String rom = countryline;
        lines.remove(0);
        while (countryline.contains(",")) {
            String buffer = countryline.substring(0,countryline.indexOf(","));
            countryline = countryline.substring(countryline.indexOf(",")+1);
            System.out.println(buffer);
            country.add(buffer);
        }
        double speed[][] = new double[country.size()][lines.size()];
        for (int i = 0; i < lines.size()-1; i++) {
            String buff = lines.get(i);
            for (int i1 = 0; i1 < country.size(); i1++) {
                String sped = buff.substring(0,buff.indexOf(","));
                buff = buff.substring(buff.indexOf(",")+1);
                speed[i1][i] = Double.parseDouble(sped);
            }
        }
        for (int i = 0; i < country.size(); i++) {
            double[] countryspeed = new double[lines.size()];
            countryspeed = speed[i];
            bubbleSort(countryspeed);
            speed[i] = countryspeed;
            /*for (int i1 = 0; i1 < countryspeed.length; i1++) {
                System.out.print(countryspeed[i1]+",");
            }*/
        }
        //printer.println(rom);
        for (int i = 0; i < country.size(); i++) {
            //System.out.print(country.get(i)+",");
            printer.print(country.get(i)+",");
            for (int i1 = 0; i1 < speed.length; i1++) {
               // System.out.print(speed[i1][i]+",");
                printer.print(speed[i][i1]+",");
            }
           // System.out.println();
            printer.println();
        }
        printer.close();

        /*for (int i = 1; i < lines.size(); i++) {
            //System.out.println(lines.get(i));
        }*/
    }

    public static void bubbleSort(double[] array) {
        int size = array.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[j + 1]) {
                    exchangeElements(array, j, j + 1);
                }
            }
        }
    }
    public static void exchangeElements(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
