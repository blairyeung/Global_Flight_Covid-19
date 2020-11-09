import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) throws Exception{
        //String[] continent = {"Asia","Europe","Africa","North America","South America","Oceania"};
        //String[] continent = {"America","Asia&Oceania"};
        String[] continent = {"sample"};
        File files[] = new File[continent.length];
        Scanner scans[] = new Scanner[continent.length];
        File output[] = new File[continent.length];
        PrintWriter printers[] = new PrintWriter[continent.length];
        for (int i = 0; i < continent.length; i++) {
            String path1 = "E:/Global Model/Continent/";
            String path2 = ".csv";
            String path = path1 + continent[i] +  path2;
            files[i] = new File(path);
            scans[i] = new Scanner(files[i]);
            String outputpath = path1 + continent[i]+" Death Ranked111"+ path2;
            output[i] = new File(outputpath);
            printers[i] = new PrintWriter(output[i]);
        }
        for (int i = 0; i < continent.length; i++) {
            ArrayList<String> arr = new ArrayList<>();
            ArrayList<Integer> days = new ArrayList<>();
            while (scans[i].hasNext()) {
                String buffer = scans[i].nextLine();
                arr.add(buffer);
                buffer = buffer.substring(buffer.indexOf(",")+1);
                buffer = buffer.substring(buffer.indexOf(",")+1);
                if(!buffer.substring(0,buffer.indexOf(",")).isEmpty()){
                    int day = (int)Double.parseDouble(buffer.substring(0,buffer.indexOf(",")));
                    days.add(day);
                }
               else days.add(0);
            }
            int[] d = new int[arr.size()];
            String[] lines = new String[arr.size()];
            for (int i1 = 0; i1 < arr.size(); i1++) {
                lines[i1] = arr.get(i1);
                d[i1] = days.get(i1);
            }
            bubbleSort(d,lines);
            for (int i1 = 0; i1 < lines.length; i1++) {
                printers[i].println(lines[i1]);
            }
            printers[i].close();
        }
    }
    public static void bubbleSort(int[] array,String[] lines) {
        int size = array.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    exchangeElements(array,lines, j, j + 1);
                }
            }
        }
    }
    public static void exchangeElements(int[] array, String[] liens , int index1, int index2) {
        int temp = array[index1];
        String st = liens[index1];
        array[index1] = array[index2];
        liens[index1] = liens[index2];
        array[index2] = temp;
        liens[index2] = st;
    }
}
