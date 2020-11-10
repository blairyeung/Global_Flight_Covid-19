import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Filter;

public class Rank {
    public static void main(String[] args) throws Exception {
        File input = new File("E:/Global Model/Pearson.csv");
        File output = new File("E:/Global Model/Pearson 1.csv");
        Scanner scan = new Scanner(input);
        PrintWriter printer = new PrintWriter(output);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<Double> goodness = new ArrayList<>();
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }
        for (int i = 0; i < lines.size(); i++) {
            String buffering = lines.get(i);
            String country = buffering.substring(0, buffering.indexOf(","));
            String num = buffering.substring(buffering.indexOf(",") + 1);
            double number = Double.parseDouble(num);
            System.out.println(country);
            System.out.println(number);
            countries.add(country);
            goodness.add(number);
        }
        int size = goodness.size();
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (goodness.get(j) < goodness.get(j + 1)) {
                    exchangeElements(goodness, countries, j, j + 1);
                }
            }
        }
        System.out.println(goodness);
        System.out.println(countries);
        for (int i = 0; i < goodness.size(); i++) {
            printer.print(countries.get(i)+",");
        }
        printer.println();
        for (int i = 0; i < goodness.size(); i++) {
            printer.print(goodness.get(i)+",");
        }
        printer.close();
    }

    public static void exchangeElements(ArrayList<Double> array, ArrayList<String> countries, int index1, int index2) {
        double temp = array.get(index1);
        String ram = countries.get(index1);
        array.set(index1, array.get(index2));
        countries.set(index1, countries.get(index2));
        array.set(index2, temp);
        countries.set(index2, ram);
    }
}
