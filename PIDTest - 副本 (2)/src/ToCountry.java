    import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ToCountry {
    public ToCountry(){

    }

    public static void main(String[] args) {
        IO io = new IO();
        ToCountry to = new ToCountry(2);
    }
    public ToCountry(int io) {
        String O = "CN-42";
        String read = Parameters.PathDefault+"Output/Infected Trail " + O + " " + io + ".csv";
        String read2 = Parameters.PathDefault+"Output/Death Trail " + O + " " + io + ".csv";
        String read3 = Parameters.PathDefault+"Output/Importation Trail " + O + " " + io + ".csv";
        String read4 = Parameters.PathDefault+"Output/Exportation Trail " + O + " " + io + ".csv";
        String outf = Parameters.PathDefault+"Output/Country Infected Trail "+ O +" "+ io+".csv";
        String outf2 = Parameters.PathDefault+"Output/Country Death Trail "+ O +" "+ io+".csv";
        String outf3 = Parameters.PathDefault+"Output/Country Importation Trail "+ O +" "+ io+".csv";
        String outf4 = Parameters.PathDefault+"Output/Country Exportation Trail "+ O +" "+ io+".csv";
        String firstcase = Parameters.PathDefault+"Output/FIRSTCASE Trail "+ O +" "+ io+".csv";
        ////System.out.println(read);
        File file = new File(read);
        File filedeath = new File(read2);
        File fileImport = new File(read3);
        File fileExport = new File(read4);
        File out = new File(outf);
        File outd = new File(outf2);
        File outIm = new File(outf3);
        File outEx = new File(outf4);
        PrintWriter print = null;
        PrintWriter print2 = null;
        PrintWriter print3 = null;
        PrintWriter printIm = null;
        PrintWriter printEx = null;

        Scanner scan2 = null;
        Scanner scan3 = null;
        Scanner Importscan = null;
        Scanner ExportScan = null;
        try {
            Importscan = new Scanner(fileImport);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ExportScan = new Scanner(fileExport);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            print = new PrintWriter(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            printIm = new PrintWriter(outIm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            printEx = new PrintWriter(outEx);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            print3 = new PrintWriter(outd);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            print2 = new PrintWriter(firstcase);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scan2 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scan3 = new Scanner(filedeath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> linesdeath = new ArrayList<>();
        ArrayList<String> linesimport = new ArrayList<>();
        ArrayList<String> linesexport = new ArrayList<>();
        while (scan2.hasNext()) {
            lines.add(scan2.nextLine());
            linesdeath.add(scan3.nextLine());
            linesimport.add(Importscan.nextLine());
            linesexport.add(ExportScan.nextLine());
            System.out.println(lines.get(lines.size() - 1));
            //System.out.println(linesdeath.get(lines.size() - 1));
        }
        ArrayList<String> Country = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Double> cases = new ArrayList<>();
        ArrayList<Double> deaths = new ArrayList<>();
        ArrayList<Double> Imports = new ArrayList<>();
        ArrayList<Double> Exports = new ArrayList<>();
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Boolean> rec = new ArrayList<>();
        ArrayList<String> LINE = new ArrayList<>();
        ArrayList<String> LINEDeath = new ArrayList<>();
        ArrayList<String> LINEImport = new ArrayList<>();
        ArrayList<String> LINEExport = new ArrayList<>();
        System.out.println(lines);
        String county = lines.get(0);
        while (county.length() > 6) {
            String buffer = county.substring(0, county.indexOf(","));
            String buffer2 = buffer.substring(0,2);
            county = county.substring(county.indexOf(",") + 1);
            if (county.length() < 6) {
                break;
            }
            if (Country.contains(buffer2)) {
                index.set(Country.indexOf(buffer2), index.get(Country.indexOf(buffer2)) + 1);
            } else {
                Country.add(buffer2);
                index.add(1);
                cases.add(0.0);
                deaths.add(0.0);
                Imports.add(0.0);
                Exports.add(0.0);
                first.add(150);
                rec.add(false);
            }
        }
        String firstline = "";
        String nameline = "";
        String nameline2 = "";
        for (int i = 0; i < Country.size(); i++) {
            firstline += Country.get(i) + ",";
            //System.out.println(Country.get(i));
            nameline += IO.Countryname.get(IO.Countrycode.indexOf(Country.get(i))) + ",";
            nameline2 += IO.Countrycode.get(IO.Countrycode.indexOf(Country.get(i))) + ",";
            //nameline +=  Country.get(i) +",";
        }
        for (int i = 1; i < lines.size(); i++) {
            for (int i1 = 0; i1 < cases.size(); i1++) {
                cases.set(i1, 0.0);
                deaths.set(i1, 0.0);
                Imports.set(i1, 0.0);
                Exports.set(i1, 0.0);
            }
            String aline = "";
            String aline2 = "";
            String aline3 = "";
            String aline4 = "";
            String linebuff;
            String linebuff2;
            String linebuff3;
            String linebuff4;
            String linebuffering;
            String linebuffering2;
            String linebuffering3;
            String linebuffering4;
            linebuff = lines.get(i);
            linebuff2 = linesdeath.get(i);
            linebuff3 = linesimport.get(i);
            linebuff4 = linesexport.get(i);
            for (int i1 = 0; i1 < Country.size(); i1++) {
                for (int integer = 0; integer < index.get(i1); integer++) {
                    ////System.out.println(linebuffering);
                    ////System.out.println(true);
                    //System.out.println(linebuff.substring(0, linebuff.indexOf(",")));
                    //System.out.println(linebuff2.substring(0, linebuff2.indexOf(",")));
                    linebuffering = linebuff.substring(0, linebuff.indexOf(","));
                    linebuffering2 = linebuff2.substring(0, linebuff2.indexOf(","));
                    linebuffering3 = linebuff3.substring(0, linebuff3.indexOf(","));
                    linebuffering4 = linebuff4.substring(0, linebuff4.indexOf(","));
                    linebuff = linebuff.substring(linebuff.indexOf(",") + 1);
                    linebuff2 = linebuff2.substring(linebuff2.indexOf(",") + 1);
                    linebuff3 = linebuff3.substring(linebuff3.indexOf(",") + 1);
                    linebuff4 = linebuff4.substring(linebuff4.indexOf(",") + 1);
                    cases.set(i1, cases.get(i1) + Double.parseDouble(linebuffering));
                    deaths.set(i1, deaths.get(i1) + Double.parseDouble(linebuffering2));
                    Imports.set(i1, deaths.get(i1) + Double.parseDouble(linebuffering3));
                    Exports.set(i1, deaths.get(i1) + Double.parseDouble(linebuffering4));
                }
                ////System.out.println(Country.get(i1));
                ////System.out.println(cases.get(i1));
                aline += cases.get(i1).toString() + ",";
                aline2 += deaths.get(i1).toString() + ",";
                aline3 += Imports.get(i1).toString() + ",";
                aline4 += Exports.get(i1).toString() + ",";
                if (cases.get(i1) > 0 && rec.get(i1) == false) {
                    first.set(i1, i);
                    rec.set(i1, true);
                }
            }
            //System.out.println(aline);
            //System.out.println("LINE");
            LINE.add(aline);
            LINEDeath.add(aline2);
            LINEImport.add(aline3);
            LINEExport.add(aline4);
        }
        //System.out.println(nameline);
        print.println(nameline);
        print.println(nameline2);
        print3.println(nameline);
        print3.println(nameline2);
        printIm.println(nameline);
        printIm.println(nameline2);
        printEx.println(nameline);
        printEx.println(nameline2);
        for (int i = 0; i < LINE.size(); i++) {
            ////System.out.println(LINE.get(i));
            print.println(LINE.get(i));
            print3.println(LINEDeath.get(i));
            printIm.println(LINEImport.get(i));
            printEx.println(LINEExport.get(i));
        }
        print.close();
        print3.close();
        printIm.close();
        printEx.close();
        print2.println(firstline);
        for (int i = 0; i < Country.size(); i++) {
            print2.print(first.get(i)+",");
        }
        print2.close();
    }
}