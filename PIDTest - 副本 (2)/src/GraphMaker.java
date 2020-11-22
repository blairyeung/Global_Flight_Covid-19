import java.io.*;
import java.util.*;
public class GraphMaker {
    /*public GraphMaker(){
        int ini = 0;
        int end = Main.de;
        int diff = end - ini;
        int datenum = 3;
        int ObservationRange = Main.ObservationRange;
        int Conutries;
        ArrayList<int[][]> DATA = new ArrayList<>();
        File files[] = new File[end-ini];
        Scanner scan[] = new Scanner[end-ini];
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        String outdate[] = {"9.7","9.22","10.7","10.22"};
        String part1 = Parameters.PathDefault+"Output/Infected Trail CN-42 ";
        String part2 = ".csv";
        String output1 = Parameters.PathDefault+"Output/";
        File sumfile = new File(Parameters.PathDefault+"Output/"+"Sum.csv");
        int Sum[] = new int[diff];
        String output2;
        //IO io = new IO();
        for (int i = ini; i < end; i++) {
            String path = part1 + (i+1) +part2;
            files[i-ini] = new File(path);
            try {
                scan[i-ini] = new Scanner(files[i-ini]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> arr = IO.Countryname;
        ArrayList<String> arr2 = IO.Countrycode;
        File outputfiles[] = new File[arr.size()];
        PrintWriter printers[] = new PrintWriter[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            int[][] buffer = new int[end-ini][ObservationRange];
            DATA.add(buffer);
            output2 = arr2.get(i) + " T .csv";
            String path = output1 + output2;
            outputfiles[i] = new File(path);
            try {
                printers[i] = new PrintWriter(outputfiles[i]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < diff; i++) {
            ArrayList<String> CountryScanBuffer = new ArrayList<>();
            ArrayList<int[]> DATASET= new ArrayList<>();
            String firstline = scan[i].nextLine();
            while (firstline.length() > 1) {
                String country = firstline.substring(0,firstline.indexOf(","));
                firstline = firstline.substring(firstline.indexOf(",")+1);
                //Syestem.out.println(country);
                CountryScanBuffer.add(country);
                DATASET.add(new int[ObservationRange]);
            }
            for (int i1 = 0; i1 < ObservationRange; i1++) {
                String buffer = scan[i].nextLine();
                int counter = 0;
                int sum = 0;
                while (buffer.length() > 1) {
                    String number = buffer.substring(0,buffer.indexOf(","));
                    buffer = buffer.substring(buffer.indexOf(",")+1);
                    ////Syestem.out.println(CountryScanBuffer.get(counter));
                    ////Syestem.out.print(number);
                    int buff[] = DATASET.get(counter);
                    buff[i1] = (int) Double.parseDouble(number);
                    DATASET.set(counter,buff);
                    counter++;
                    if(ObservationRange-1==(i1)){
                        sum+=(int)Double.parseDouble(number);
                    }
                }
                Sum[i] = sum;
                ////Syestem.out.println();
            }

            /*for (int i1 = 0; i1 < CountryScanBuffer.size(); i1++) {
                int index = arr.indexOf("China");
                int[] buff = DATASET.get(i1);
                int DATAbuff[][] = DATA.get(index);
                DATAbuff[i] = buff;
                DATA.set(index,DATAbuff);
            }*/
            //}
        /*for (int i = 0; i < arr.size(); i++) {
            ////Syestem.out.print(arr.get(i)+",");
            for (int i1 = 0; i1 < diff; i1++) {
                //Syestem.out.print(arr.get(i)+ " Trail "+i1 +",");
                printers[i].print(arr.get(i)+ " Trail "+i1 +",");
                for (int i2 = 0; i2 < ObservationRange; i2++) {
                    //Syestem.out.print(DATA.get(i)[i1][i2]+",");
                    printers[i].print(DATA.get(i)[i1][i2]+",");
                }
                //Syestem.out.println();
                printers[i].println();
            }
            printers[i].close();
        }
        PrintWriter print = null;
        try {
            print = new PrintWriter(sumfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Sum.length; i++) {
            //Syestem.out.print("Trail " + i +",");
            print.print("Trail " + i +",");
        }
        //Syestem.out.println();
        print.println("Actual"+",");
        for (int i = 0; i < Sum.length; i++) {
            //Syestem.out.print(Sum[i]+",");
            print.print(Sum[i]+",");
        }
        print.print("305875,");
        print.close();
    }*/
    public GraphMaker() throws Exception {
        int ini = 0;
        int end = Main.de;
        int diff = end - ini;
        int datenum = 3;
        //int ObservationRange = 244 - datenum*15;
        int ObservationRange = Main.ObservationRange;
        int Conutries;
        ArrayList<int[][]> DATA = new ArrayList<>();
        File files[] = new File[end - ini];
        Scanner scan[] = new Scanner[end - ini];
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        //String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        //String outdate[] = {"9.7","9.22","10.7","10.22"};
        //ini += datenum*200+100;
        //end += datenum*200+100;

        String Input[] = {"Infected","Exportation","Importation","Death"};
        for (int S = 0; S < 4; S++) {

        String part1 = Parameters.PathDefault + "Output/Country " + Input[S] + " Trail CN-42 ";

        String part2 = ".csv";
        String output1 = Parameters.PathDefault + "Output/Country " + Input[S] + "/";
        File sumfile = new File("E:/Global Model/Sum/" + "Sums.csv");
        int Sum[] = new int[diff];
        String output2;
        for (int i = ini; i < end; i++) {
            String path = part1 + (i + 1) + part2;
            files[i - ini] = new File(path);
            files[i - ini] = new File(path);
            scan[i - ini] = new Scanner(files[i - ini]);
        }
        ArrayList<String> arr = IO.Countryname;
        ArrayList<String> arr2 = IO.Countrycode;
        File outputfiles[] = new File[arr.size()];
        File Exoutputfiles[] = new File[arr.size()];
        File Imoutputfiles[] = new File[arr.size()];

        PrintWriter printers[] = new PrintWriter[arr.size()];
        PrintWriter Exprinters[] = new PrintWriter[arr.size()];
        PrintWriter Imprinters[] = new PrintWriter[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            int[][] buffer = new int[end - ini][ObservationRange];
            DATA.add(buffer);
            output2 = arr.get(i) + "  " + arr2.get(i) + ".csv";
            if(arr2.get(i).equals("CI")||arr2.get(i).equals("BL")){
                System.out.println(true);
                output2 = arr2.get(i) + ".csv";
            }
            String path = output1 + output2;
            outputfiles[i] = new File(path);
            printers[i] = new PrintWriter(outputfiles[i]);
        }

        for (int i = 0; i < diff; i++) {
            ArrayList<String> CountryScanBuffer = new ArrayList<>();

            ArrayList<int[]> DATASET = new ArrayList<>();

            String firstline = scan[i].nextLine();
            String secondline = scan[i].nextLine();

            while (firstline.length() > 1) {
                String country = firstline.substring(0, firstline.indexOf(","));
                firstline = firstline.substring(firstline.indexOf(",") + 1);
                System.out.println(country);
                CountryScanBuffer.add(country);
                DATASET.add(new int[ObservationRange]);
            }

            for (int i1 = 0; i1 < ObservationRange; i1++) {
                String buffer = scan[i].nextLine();
                int counter = 0;
                int sum = 0;
                while (buffer.length() > 1) {
                    String number = buffer.substring(0, buffer.indexOf(","));
                    buffer = buffer.substring(buffer.indexOf(",") + 1);
                    //System.out.println(CountryScanBuffer.get(counter));
                    //System.out.print(number);
                    int buff[] = DATASET.get(counter);
                    buff[i1] = (int) Double.parseDouble(number);
                    DATASET.set(counter, buff);
                    counter++;
                    if (ObservationRange - 1 == (i1)) {
                        sum += (int) Double.parseDouble(number);
                    }
                }
                Sum[i] = sum;
                //System.out.println();
            }

            for (int i1 = 0; i1 < CountryScanBuffer.size(); i1++) {
                int index = arr.indexOf(CountryScanBuffer.get(i1));
                int[] buff = DATASET.get(i1);
                int DATAbuff[][] = DATA.get(index);
                DATAbuff[i] = buff;
                DATA.set(index, DATAbuff);
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            //System.out.print(arr.get(i)+",");
            for (int i1 = 0; i1 < diff; i1++) {
                System.out.print(arr.get(i) + " Trail " + i1 + ",");
                printers[i].print(arr.get(i) + " Trail " + i1 + ",");
                for (int i2 = 0; i2 < ObservationRange; i2++) {
                    System.out.print(DATA.get(i)[i1][i2] + ",");
                    printers[i].print(DATA.get(i)[i1][i2] + ",");
                }
                System.out.println();
                printers[i].println();
            }
            printers[i].close();
        }
        PrintWriter print = new PrintWriter(sumfile);
        for (int i = 0; i < Sum.length; i++) {
            System.out.print("Trail " + i + ",");
            print.print("Trail " + i + ",");
        }
        System.out.println();
        print.println("Actual" + ",");
        for (int i = 0; i < Sum.length; i++) {
            System.out.print(Sum[i] + ",");
            print.print(Sum[i] + ",");
        }
        print.print("305875,");
        print.close();
    }
    }

    public static void main(String[] args) throws Exception{
        IO io = new IO();
        GraphMaker g = new GraphMaker();
    }
    /*public static void main(String[] args) throws Exception{
        int ini = 0;
        int end = Main.de;
        int diff = end - ini;
        int datenum = 3;
        //int ObservationRange = 244 - datenum*15;
        int ObservationRange = Main.ObservationRange;
        int Conutries;
        ArrayList<int[][]> DATA = new ArrayList<>();
        File files[] = new File[end-ini];
        Scanner scan[] = new Scanner[end-ini];
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        String outdate[] = {"9.7","9.22","10.7","10.22"};
        //ini += datenum*200+100;
        //end += datenum*200+100;
        String part1 = Parameters.PathDefault+"Output/Country Death Trail CN-42 ";
        String part2 = ".csv";
        String output1 = Parameters.PathDefault+"Output/";
        File sumfile = new File("E:/Global Model/Sum/"+"Sums.csv");
        int Sum[] = new int[diff];
        String output2;
        IO io = new IO();
        for (int i = ini; i < end; i++) {
            String path = part1 + (i+1) +part2;
            files[i-ini] = new File(path);
            scan[i-ini] = new Scanner(files[i-ini]);
        }
        ArrayList<String> arr = IO.Countryname;
        ArrayList<String> arr2 = IO.Countrycode;
        File outputfiles[] = new File[arr.size()];
        PrintWriter printers[] = new PrintWriter[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            int[][] buffer = new int[end-ini][ObservationRange];
            DATA.add(buffer);
            output2 = arr2.get(i)+".csv";
            String path = output1 + output2;
            outputfiles[i] = new File(path);
            printers[i] = new PrintWriter(outputfiles[i]);
        }
        for (int i = 0; i < diff; i++) {
            ArrayList<String> CountryScanBuffer = new ArrayList<>();
            ArrayList<int[]> DATASET= new ArrayList<>();
            String firstline = scan[i].nextLine();
            String secondline = scan[i].nextLine();
            while (firstline.length() > 1) {
                String country = firstline.substring(0,firstline.indexOf(","));
                firstline = firstline.substring(firstline.indexOf(",")+1);
                System.out.println(country);
                CountryScanBuffer.add(country);
                DATASET.add(new int[ObservationRange]);
            }
            for (int i1 = 0; i1 < ObservationRange; i1++) {
                String buffer = scan[i].nextLine();
                int counter = 0;
                int sum = 0;
                while (buffer.length() > 1) {
                    String number = buffer.substring(0,buffer.indexOf(","));
                    buffer = buffer.substring(buffer.indexOf(",")+1);
                    //System.out.println(CountryScanBuffer.get(counter));
                    //System.out.print(number);
                    int buff[] = DATASET.get(counter);
                    buff[i1] = (int) Double.parseDouble(number);
                    DATASET.set(counter,buff);
                    counter++;
                    if(ObservationRange-1==(i1)){
                        sum+=(int)Double.parseDouble(number);
                    }
                }
                Sum[i] = sum;
                //System.out.println();
            }

            for (int i1 = 0; i1 < CountryScanBuffer.size(); i1++) {
                int index = arr.indexOf(CountryScanBuffer.get(i1));
                int[] buff = DATASET.get(i1);
                int DATAbuff[][] = DATA.get(index);
                DATAbuff[i] = buff;
                DATA.set(index,DATAbuff);
            }
        }
        for (int i = 0; i < arr.size(); i++) {
            //System.out.print(arr.get(i)+",");
            for (int i1 = 0; i1 < diff; i1++) {
                System.out.print(arr.get(i)+ " Trail "+i1 +",");
                printers[i].print(arr.get(i)+ " Trail "+i1 +",");
                for (int i2 = 0; i2 < ObservationRange; i2++) {
                    System.out.print(DATA.get(i)[i1][i2]+",");
                    printers[i].print(DATA.get(i)[i1][i2]+",");
                }
                System.out.println();
                printers[i].println();
            }
            printers[i].close();
        }
        PrintWriter print = new PrintWriter(sumfile);
        for (int i = 0; i < Sum.length; i++) {
            System.out.print("Trail " + i +",");
            print.print("Trail " + i +",");
        }
        System.out.println();
        print.println("Actual"+",");
        for (int i = 0; i < Sum.length; i++) {
            System.out.print(Sum[i]+",");
            print.print(Sum[i]+",");
        }
        print.print("305875,");
        print.close();
    }*/

    /*public static void main(String[] args) throws Exception{
        int ini = 0;
        int end = 40;
        int diff = end - ini;
        int datenum = 3;
        int ObservationRange = Main.ObservationRange;
        int Conutries;
        ArrayList<int[][]> DATA = new ArrayList<>();
        File files[] = new File[end-ini];
        Scanner scan[] = new Scanner[end-ini];
        //String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        //String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        String outdate[] = {"9.7","9.22","10.7","10.22"};
        String part1 = Parameters.PathDefault+"Output/Country Death Trail CN-42 ";
        String part2 = ".csv";
        String output1 = Parameters.PathDefault+"Output/";
        File sumfile = new File(Parameters.PathDefault+"Output/"+"Sum.csv");
        int Sum[] = new int[diff];
        String output2;
        IO io = new IO();
        for (int i = ini; i < end; i++) {
            String path = part1 + (i+1) +part2;
            files[i-ini] = new File(path);
            scan[i-ini] = new Scanner(files[i-ini]);
        }
        ArrayList<String> arr = IO.Countryname;
        ArrayList<String> arr2 = IO.Countrycode;
        File outputfiles[] = new File[arr.size()];
        PrintWriter printers[] = new PrintWriter[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            int[][] buffer = new int[end-ini][ObservationRange];
            DATA.add(buffer);
            output2 = arr2.get(i)+".csv";
            String path = output1 + output2;
            outputfiles[i] = new File(path);
            printers[i] = new PrintWriter(outputfiles[i]);
        }
        for (int i = 0; i < diff; i++) {
            ArrayList<String> CountryScanBuffer = new ArrayList<>();
            ArrayList<int[]> DATASET= new ArrayList<>();
            String firstline = scan[i].nextLine();
            while (firstline.length() > 1) {
                String country = firstline.substring(0,firstline.indexOf(","));
                firstline = firstline.substring(firstline.indexOf(",")+1);
                System.out.println(country);
                CountryScanBuffer.add(country);
                DATASET.add(new int[ObservationRange]);
            }
            for (int i1 = 0; i1 < ObservationRange; i1++) {
                //System.out.println(i1);
                String buffer = scan[i].nextLine();
                int counter = 0;
                int sum = 0;
                while (buffer.length() > 1) {
                    String number = buffer.substring(0,buffer.indexOf(","));
                    buffer = buffer.substring(buffer.indexOf(",")+1);
                    ////Syestem.out.println(CountryScanBuffer.get(counter));
                    ////Syestem.out.print(number);
                    int buff[] = DATASET.get(counter);
                    buff[i1] = (int) Double.parseDouble(number);
                    DATASET.set(counter,buff);
                    counter++;
                    if(ObservationRange-1==(i1)){
                        sum+=(int)Double.parseDouble(number);
                    }
                }
                Sum[i] = sum;
                ////Syestem.out.println();
            }

            /*for (int i1 = 0; i1 < CountryScanBuffer.size(); i1++) {
                int index = arr.indexOf("China");
                int[] buff = DATASET.get(i1);
                int DATAbuff[][] = DATA.get(index);
                DATAbuff[i] = buff;
                DATA.set(index,DATAbuff);
            }*/
        }
        /*for (int i = 0; i < arr.size(); i++) {
            ////Syestem.out.print(arr.get(i)+",");
            for (int i1 = 0; i1 < diff; i1++) {
                //Syestem.out.print(arr.get(i)+ " Trail "+i1 +",");
                //System.out.println(i);
                printers[i].print(arr.get(i)+ " Trail "+i1 +",");
                for (int i2 = 0; i2 < ObservationRange; i2++) {
                    //Syestem.out.print(DATA.get(i)[i1][i2]+",");
                    printers[i].print(DATA.get(i)[i1][i2]+",");
                }
                //Syestem.out.println();
                printers[i].println();
            }
            //printers[i].println("=MEDIAN(IG1:IG5)");
            printers[i].close();
        }
        PrintWriter print = new PrintWriter(sumfile);
        for (int i = 0; i < Sum.length; i++) {
            //Syestem.out.print("Trail " + i +",");
            print.print("Trail " + i +",");
        }
        //Syestem.out.println();
        print.println("Actual"+",");
        for (int i = 0; i < Sum.length; i++) {
            //Syestem.out.print(Sum[i]+",");
            print.print(Sum[i]+",");
        }
        print.print("305875,");
        print.close();
    }*/
