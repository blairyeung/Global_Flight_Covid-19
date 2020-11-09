import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DensityMatch {
    public static void main(String[] args) throws Exception{
        File speedfile = new File("E:/Global Model/Spreading speed.csv");
        Scanner scan = new Scanner(speedfile);
        File densityfile = new File("E:/Global Model/Densities.csv");
        Scanner densityscana = new Scanner(densityfile);
        PrintWriter printer = new PrintWriter("E:/Global Model/Relation.csv");
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> speedlines = new ArrayList<>();
        ArrayList<String> countriesden = new ArrayList<>();
        ArrayList<String> countriesspeed= new ArrayList<>();
        ArrayList<String> dense = new ArrayList<>();
        ArrayList<String> spd = new ArrayList<>();
        while (densityscana.hasNext()) {
            lines.add(densityscana.nextLine());
        }
        for (int i = 0; i < lines.size(); i++) {
            String buffer = lines.get(i);
            String country = buffer.substring(0,buffer.indexOf(","));
            String den = buffer.substring(buffer.indexOf(",")+1);
            System.out.println(country);
            System.out.println(den);
            countriesden.add(country);
            dense.add(den);
        }

        while (scan.hasNext()) {
            speedlines.add(scan.nextLine());
        }
        for (int i = 0; i < speedlines.size(); i++) {
            String buffer = speedlines.get(i);
            String country = buffer.substring(0,buffer.indexOf(","));
            String speed = buffer.substring(buffer.indexOf(",")+1);
            System.out.println(country);
            System.out.println(speed);
            countriesspeed.add(country);
            spd.add(speed);
        }
        ArrayList<String> outputlines = new ArrayList<>();
        for (int i = 0; i < countriesden.size(); i++) {
            String tar = countriesden.get(i);
            for (int i1 = 0; i1 < countriesspeed.size(); i1++) {
                String mat = countriesspeed.get(i1);
                if(mat.contains(tar)||tar.contains(mat)){
                    if(tar.contains("(")||mat.contains("(")){
                        break;
                    }
                    String bf = dense.get(i);
                    Double d = Double.parseDouble(bf);
                    if(!spd.get(i1).isEmpty()){
                        if(Double.parseDouble(spd.get(i1))==1.0){
                            break;
                        }
                    }
                    d = Math.log10(d);
                    String buff = tar + ","+ d +"," + spd.get(i1);
                    outputlines.add(buff);
                    break;
                }
            }
        }
        for (int i = 0; i < outputlines.size(); i++) {
            System.out.println(outputlines.get(i));
            printer.println(outputlines.get(i));
        }
        printer.close();
    }
}
