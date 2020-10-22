import java.util.Random;

public class Agedistribution {
    public static int getPatientAge(double distribut[]){
        Random rad = new Random();
        int age=0;
        double rado = rad.nextInt(1000);
        if(rado<distribut[0]){
            age = 20-(int)Math.round(Math.abs((3*rad.nextGaussian())));
            return age;
        }
        if(rado>distribut[6]){
            age = 80+(int)Math.round(Math.abs((3*rad.nextGaussian())));
            return age;
        }
        for (int i = 1; i < 7; i++) {
            if(rado>distribut[i]){
                age = rad.nextInt(10)+ 10+i*10;
            }
        }
        return age;
    }

    public static int getPatientAge(){
        double distribut[] =  {2.1,8.1,17.0,19.2,22.4,19.2,8.8,3.2};
        Random rad = new Random();
        int age=0;
        double rado = rad.nextInt(1000);
        if(rado<distribut[0]){
            age = 20-(int)Math.round(Math.abs((3*rad.nextGaussian())));
            return age;
        }
        if(rado>distribut[6]){
            age = 80+(int)Math.round(Math.abs((3*rad.nextGaussian())));
            return age;
        }
        for (int i = 1; i < 7; i++) {
            if(rado>distribut[i]){
                age = rad.nextInt(10)+ 10+i*10;
            }
        }
        return age;
    }
    public static double getMortalityRate(int age){
        return (0.047*Math.pow(Math.E,0.07*age));
    }
}
