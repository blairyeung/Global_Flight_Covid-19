import java.util.Random;

public class Possibility {
    public Possibility(){

    }
    public Possibility(double poss){
        possibility(poss);
    }
    public boolean POSS(double fraction,double Integral){
        Random rad = new Random();
        int RaD = rad.nextInt((int) Integral);
        if(fraction>RaD){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean possibility(double poss){
        Random rad = new Random();
        int RaD = rad.nextInt(1000000000);
        if(poss*1000000000<RaD){
            return true;
        }
        else {
            return false;
        }
    }
}
