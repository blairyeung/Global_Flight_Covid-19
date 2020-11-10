import java.util.Random;

public class Agedistribution {
    public static int getPatientAge(String name, double[] AgeMatrix){
        String Country = name.substring(0,2);
        int ind = IO.Countrycode.indexOf(Country);
        double distribut[] = AgeMatrix;

        double sum = 0;
        for (int i = 0; i < AgeMatrix.length; i++) {
            sum += AgeMatrix[i];
        }
        if (Double.toString(AgeMatrix[15])=="NaN") {
            distribut = IO.AgeS.get(ind);
            sum = 10000;
            for (int i = 0; i < distribut.length; i++) {
                distribut[i] = (int) (distribut[i]*100.0);
            }
        }
        Random rad = new Random();
        int age = 0;
        int rado = rad.nextInt((int)sum);
        int residu = -1;
        int Chara = 0;
        int last = 0;
        for (int i = 0; i < distribut.length; i++) {
            /*if(rado>9900){
                residu =14;
                break;
            }*/
            Chara += distribut[i];
            if(last<=rado&&rado<Chara) {
                residu = i;
                break;
            }
            last = Chara;
        }
        switch(residu){
            case 0: return 1;//0-4
            case 1: return 2;//5-9
            case 2: return 2;//10-14
            case 3: return 3;//15-19
            case 4: return 3;//20-24
            case 5: return 3;//25-29
            case 6: return 3;//30-34
            case 7: return 3;//34-39
            case 8: return 4;//40-44
            case 9: return 6;//45-49
            case 10: return 7;//50-54
            case 11: return 8;//55-59
            case 12: return 17;//60-64
            case 13: return 21;//65-69
            case 14: return 81;//70-74
            case 15: return 121;//75+
        }
        return -1;
    }
    public static double getMortalityRate(int age){
        return (0.047*Math.pow(Math.E,0.07*age));
    }
}
