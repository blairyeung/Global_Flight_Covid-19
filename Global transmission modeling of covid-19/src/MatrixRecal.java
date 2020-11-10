import java.io.File;
import java.util.Scanner;

public interface MatrixRecal {
    public static void main(String[] args) throws Exception{
        File file = new File("E:/Global Model/FlightCity/EventsAj.csv");
        Scanner scan = new Scanner(file);
        File output = new File("E:/Global Model/NewAj.csv");
    }
}
