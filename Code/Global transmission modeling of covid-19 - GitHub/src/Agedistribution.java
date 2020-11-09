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
    public static int getPatientAge(String name, double[] AgeMatrix){
        String Country = name.substring(0,2);
        int ind = IO.Countrycode.indexOf(Country);
        double distribut[] = AgeMatrix;
        double sum = 0;
        for (int i = 0; i < distribut.length; i++) {
            sum += distribut[i];
        }
        for (int i = 0; i < distribut.length; i++) {
            distribut[i] = 1000.0*distribut[i]/sum;
        }
        sum = 0;
        for (int i = 0; i < distribut.length; i++) {
            sum += distribut[i];
        }
        if (Double.toString(AgeMatrix[0])=="NaN") {
            distribut = IO.AgeS.get(ind);
            sum = 1000;
            for (int i = 0; i < distribut.length; i++) {
                distribut[i] = (int) (distribut[i]*10.0);
            }
        }
        Random rad = new Random();
        int age = 0;
        int rado = rad.nextInt((int)sum);
        int residu = -1;
        int Chara = 0;
        int last = 0;
        for (int i = 0; i < distribut.length; i++) {
            if(rado>900){
                residu =4;
                break;
            }
            Chara += distribut[i];
            if(last<=rado&&rado<Chara) {
                residu = i;
                break;
            }
            last = Chara;
        }
        switch(residu){
            case 0: return 1;//0-15
            case 1: return 2;//15-25
            case 2: return 3;//25-45
            case 3: return 45;//45-65
            case 4: return 70;//65+
        }
        return -1;
    }
    public static double getMortalityRate(int age){
        return (0.047*Math.pow(Math.E,0.07*age));
    }
}
