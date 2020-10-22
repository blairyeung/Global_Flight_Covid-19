import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintResult{
    static PrintWriter PRINT = null;
    static PrintWriter PRINT2 = null;
    static PrintWriter COUNTRYPRINTER = null;
    public PrintResult(){
    }
    public void PRINTOUT(){
        //"Day,Infected,Exposed,Active Cases,Resolved,Deaths,Immuned"
        String toPrint[] = {"Infected","Exposed","Active Cases","Resolved","Death","Immuned"};
        String firstline = "";
        String firstline2 = "";
        System.out.print("Day,");
        File file = new File("E:/Global Model/Global Flight/Infected Trail "+ Main.ini +" "+Main.Filecode+".csv");
        File filed = new File("E:/Global Model/Global Flight/Death Trail "+ Main.ini +" "+Main.Filecode+".csv");
        //File filebycountry = new File("E:/Global Model/Global Flight/Countrytest Trail "+ Main.ini +" "+Main.Filecode+".csv");
        try {
            PRINT = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PRINT2 = new PrintWriter(filed);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*try {
            COUNTRYPRINTER = new PrintWriter(filebycountry);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        for (int i1 = 0; i1 < Main.NAMING.length; i1++) {
            for (int i2 = 0; i2 < Main.NAMING[i1].length; i2++) {
                if(Globe.NAMING[i1][i2]!=null){
                    //String countyn = super.counties[i1][i2].getNAMING();
                    firstline += Main.NAMING[i1][i2] + " " + toPrint[0]+",";
                    firstline2 += Main.NAMING[i1][i2] + " " + toPrint[4]+",";
                    Globe.counties[i1][i2].Printer.close();
                }
            }
        }
        //System.out.println(firstline);
        PRINT.println(firstline);
        PRINT2.println(firstline2);
        System.out.println(firstline);
        String line = null;
        String line2 = null;
        for (int i = 0; i < Main.ObservationRange; i++) {
            String buffer;
            String buffer2;
            line ="";
            line2 ="";
            for (int i1 = 0; i1 < Main.NAMING.length; i1++) {
                for (int i2 = 0; i2 < Main.NAMING[i1].length; i2++) {
                    if(Globe.NAMING[i1][i2]!=null){
                        /*if(Globe.counties[i1][i2].getPrintPack()[i]==null){
                            continue;
                        }*/
                        buffer = Double.toString(Globe.counties[i1][i2].getPrintPack()[i][1]);
                        buffer2 = Double.toString(Globe.counties[i1][i2].getPrintPack()[i][6]);
                        //System.out.println(buffer +"BUFFER");
                        if(buffer!=null){
                            line += buffer+",";
                            line2 += buffer2+",";
                        }
                        else {
                            System.out.println("EMPTY");
                        }
                    }
                }
            }
            //System.out.println(line);
            PRINT.println(line);
            PRINT2.println(line2);
        }

        PRINT.close();
        PRINT2.close();
        /*int COUNTRY;
        for (int i = 0; i < Main.ObservationRange; i++) {
            String buffer;
            line ="";

            for (int i1 = 0; i1 < Main.NAMING.length; i1++) {
                ArrayList<String> AFFECTEDCOUNTRY = new ArrayList();
                ArrayList<Double> index = new ArrayList();
                for (int i2 = 0; i2 < Main.NAMING[i1].length; i2++) {
                    if(Globe.NAMING[i1][i2]!=null){
                        if(!AFFECTEDCOUNTRY.contains(Globe.NAMING[i1][i2].substring(0,2))){
                            AFFECTEDCOUNTRY.add(Globe.NAMING[i1][i2].substring(0,2));
                            index.add(0.0);
                        }
                        else {
                            index.set(AFFECTEDCOUNTRY.indexOf(Globe.NAMING[i1][i2].substring(0,2)),index.get(AFFECTEDCOUNTRY.indexOf(Globe.NAMING[i1][i2].substring(0,2)))+Globe.counties[i1][i2].getPrintPack()[i][2]);
                        }
                        buffer = Double.toString(Globe.counties[i1][i2].getPrintPack()[i][2]);
                        //System.out.println(buffer +"BUFFER");
                        if(buffer!=null){
                            line += buffer+",";
                        }
                    }
                }
            }
            //System.out.println(line);
            COUNTRYPRINTER.println(line);
        }

        COUNTRYPRINTER.close();*/
    }
}
