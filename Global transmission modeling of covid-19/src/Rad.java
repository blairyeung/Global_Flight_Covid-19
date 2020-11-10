import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class Rad {
    public static void main(String[] args) throws Exception{
        File out = new File("E:r.csv");
        PrintWriter print = new PrintWriter(out);
        Random rad = new Random();
        for (int i = 0; i < 1000; i++) {
            int counter = 0;
            for (int i1 = 0; i1 < 2000; i1++) {
                int r = rad.nextInt(1000);
                if(r<10){
                    counter++;
                }
            }
            System.out.println(counter);
            print.println(counter);
        }
        print.close();
    }
}
