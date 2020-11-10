import java.util.ArrayList;
import java.util.Random;

public class Transmission extends County{

    private double isolation = 0;

    private double R0 = 2.68;

    private String name = "CN-42";

    public Data Model(Data data){
        double[] datapack = data.getDataPack();
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();

        ArrayList<Integer> ArrayPackInt[] = super.DATA.getIntPack();
        ArrayList<Double> ArrayPackDouble[] = super.DATA.getDoublePack();
        R0 = data.getR0();
        name = data.getName();
        inf = ArrayPackInt[0];
        aftinf = ArrayPackInt[1];
        property = ArrayPackInt[2];
        patientage = ArrayPackInt[3];
        ability = ArrayPackInt[4];
        immun = ArrayPackInt[5];
        randomvalue = ArrayPackDouble[0];
        randomcure = ArrayPackDouble[1];

        int day = (int) datapack[0];
        int population = (int) datapack[1];
        int infected = (int) datapack[2];
        int exposed = (int) datapack[3];
        int activecases = (int) datapack[4];
        int Resolved = (int) datapack[5];
        int death = (int) datapack[6];
        int lockdown = (int) datapack[7];
        int immuned = (int) datapack[8];

        double[] AgeMatrix = super.DATA.getAgeMatrix();
        double sums = 0;
        for (int i = 0; i < AgeMatrix.length; i++) {
            sums += AgeMatrix[i];
        }
        for (int i = 0; i < AgeMatrix.length; i++) {
            AgeMatrix[i] = 10000.0*AgeMatrix[i]/sums;
            System.out.print((int)(AgeMatrix[i]/100)+"%,");
        }
        System.out.println();

        double R,constant = 1;
        Random radchange = new Random();
        int newcase = 0;
        int caninfect =population;
        int contact = 0;
        double immunity;
        if(death>=lockdown&&constant<0.15){
            constant =0.15;
        }
        double sum = 1.1;
        for (int i1 = 0; i1 < infected; i1++) {
            while (inf.size()<=infected) {
                inf.add(0);
                aftinf.add(0);
                property.add(-1);
                randomvalue.add(0.0);
                randomcure.add(0.0);
                patientage.add(Agedistribution.getPatientAge(name,AgeMatrix));//A new excel to store these
                immun.add(0);
            }
            inf.set(i1,1);
            randomvalue.set(i1,radchange.nextGaussian());
            aftinf.set(i1,aftinf.get(i1)+1);//days after being infected
            if(aftinf.get(i1)>=5+randomvalue.get(i1)&&property.get(i1)==-1) {
                exposed++;
                property.set(i1,0);
                continue;
            }
            if(aftinf.get(i1)>6+randomvalue.get(i1)&&property.get(i1)==0){
                newcase++;
                activecases++;
                property.set(i1, 1);
                continue;
            }
            if(aftinf.get(i1)>11&&property.get(i1)==1) {
                if (radchange.nextInt(1000) < (patientage.get(i1))) {
                    property.set(i1,4);
                }
                else {
                    property.set(i1, 3);
                    activecases--;
                    exposed--;
                    Resolved++;
                    continue;
                }

            }
            if(aftinf.get(i1)>18.8&&(property.get(i1)==4)) {//canada 12
                property.set(i1, 2);
                death += 1;
                activecases--;
                exposed--;
                continue;
            }
        }
        AgeMatrix = new double[16];
        for(int i4=(death+Resolved);i4<exposed+death+Resolved;i4++){
            contact = (int)((double)Contact(constant, randomvalue.get(i4),name,patientage.get(i4),AgeMatrix));
            immunity = 1.0-((double)immuned/(double)population);
            if(radchange.nextInt(100)>isolation*100){
                for (int i2 = 0; i2 < immunity*(double)contact-1; i2++) {
                    if((100>radchange.nextInt(100)+1)){
                        if(radchange.nextInt(10000)<(int)(10000.0*R0/(108))){
                            infected += 1;
                            immuned += 1;
                            while(i4>=ability.size()){
                                ability.add(0);
                            }
                            ability.set(i4,ability.get(i4)+1);
                        }
                    }
                }
            }
            while (infected>randomvalue.size()){
                randomvalue.add(0.0);
            }
        }
        System.out.println("Day: " + day +"    Population: " + population +"    Infected: " + infected + "    cases: " +activecases +"    Exposed:"+exposed +"    Recovered:" +Resolved +"    Death:" +death +"    R0:" + R0 + "    CFR:" + 100*((double)death/(double)infected)/0.8 +"%");
        day++;
        datapack[0] = day;
        datapack[1] = population;
        datapack[2] = infected;
        datapack[3] = exposed;
        datapack[4] = activecases;
        datapack[5] = Resolved;
        datapack[6] = death;
        datapack[7] = lockdown;
        datapack[8] = immuned;
        ArrayPackInt[0] = inf;
        ArrayPackInt[1] = aftinf;
        ArrayPackInt[2] = property;
        ArrayPackInt[3] = patientage;
        ArrayPackInt[4] = ability;
        ArrayPackInt[5] = immun;
        ArrayPackDouble[0] = randomvalue;
        ArrayPackDouble[1] = randomcure;
        super.DATA.setDatapack(datapack);
        super.DATA.setArrayPackInt(ArrayPackInt);
        super.DATA.setArrayPackDouble(ArrayPackDouble);
        super.DATA.setAgeMatrix(AgeMatrix);
        return DATA;
    }

    public Transmission() {

    }

    public Transmission(Data DATA) {
        double datapack[] = new double[9];
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();
        Day days[] = new Day[100];
        days[Main.day] = new Day(1,10000,1,0,0,0,0,0,0);
        datapack[0] = days[Main.day].getDay();
        datapack[1] = days[Main.day].getPopulation();
        datapack[2] = days[Main.day].getInfected();
        datapack[3] = days[Main.day].getExposed();
        datapack[4] = days[Main.day].getActivecases();
        datapack[5] = days[Main.day].getResolved();
        datapack[6] = days[Main.day].getDeath();
        datapack[7] = days[Main.day].getLockdown();
        datapack[8] = days[Main.day].getImmuned();
        DATA = Model(DATA);
        days[Main.day+1] = new Day((int)datapack[0],(int)datapack[1],(int)datapack[2],(int)datapack[3],(int)datapack[4],(int)datapack[5],(int)datapack[6],datapack[7],(int)datapack[8]);
        return;
    }

    public Data Model(Data data, Event localevent){
        double[] datapack = data.getDataPack();
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();

        ArrayList<Integer> ArrayPackInt[] = super.DATA.getIntPack();
        ArrayList<Double> ArrayPackDouble[] = super.DATA.getDoublePack();
        R0 =data.getR0();
        name = data.getName();
        inf = ArrayPackInt[0];
        aftinf = ArrayPackInt[1];
        property = ArrayPackInt[2];
        patientage = ArrayPackInt[3];
        ability = ArrayPackInt[4];
        immun = ArrayPackInt[5];
        randomvalue = ArrayPackDouble[0];
        randomcure = ArrayPackDouble[1];

        int day = (int) datapack[0];
        int population = (int) datapack[1];
        int infected = (int) datapack[2];
        int exposed = (int) datapack[3];
        int activecases = (int) datapack[4];
        int Resolved = (int) datapack[5];
        int death = (int) datapack[6];
        int lockdown = (int) datapack[7];
        int immuned = (int) datapack[8];

        double[] AgeMatrix = super.DATA.getAgeMatrix();
        double sums = 0;
        for (int i = 0; i < AgeMatrix.length; i++) {
            sums += AgeMatrix[i];
        }
        for (int i = 0; i < AgeMatrix.length; i++) {
            AgeMatrix[i] = 10000.0*AgeMatrix[i]/sums;
            System.out.print((int)(AgeMatrix[i]/100)+"%,");
        }
        System.out.println();

        double constant = 1;
        Random radchange = new Random();
        int newcase = 0;
        int caninfect =population;
        int contact = 0;
        double immunity;
        caninfect  = (int) (population - immuned);
        if(death>localevent.getDeath()){
            lockdown++;
        }
        constant = 1.0- (double)lockdown*(1.0/(double)localevent.getSpeed());
        if(death>localevent.getDeath()&&constant<0.15){
            constant =0.15;
        }
        for (int i1 = 0; i1 < infected; i1++) {
            while (inf.size()<=infected) {
                inf.add(0);
                aftinf.add(0);
                property.add(-1);
                randomvalue.add(0.0);
                randomcure.add(0.0);
                patientage.add(Agedistribution.getPatientAge(name,AgeMatrix));//A new excel to store these
                immun.add(0);
            }
            inf.set(i1,1);
            randomvalue.set(i1,(radchange.nextGaussian()));
            aftinf.set(i1,aftinf.get(i1)+1);//days after being infected
            if(aftinf.get(i1)>=5&&property.get(i1)==-1) {
                exposed++;
                property.set(i1,0);
                continue;
            }
            if(aftinf.get(i1)>6&&property.get(i1)==0){
                newcase++;
                activecases++;
                property.set(i1, 1);
                continue;
            }
            if(aftinf.get(i1)>11&&property.get(i1)==1) {
                if (radchange.nextInt(1000) < (patientage.get(i1))) {
                    property.set(i1,4);
                }
                else {
                    property.set(i1, 3);
                    activecases--;
                    exposed--;
                    Resolved++;
                    continue;
                }

            }
            if(aftinf.get(i1)>18.8&&(property.get(i1)==4)) {//canada 12
                property.set(i1, 2);
                death += 1;
                activecases--;
                exposed--;
                continue;
            }
        }
        AgeMatrix = new double[16];
        System.out.println("Day: " + day +"    Population: " + population +"    Infected: " + infected + "    cases: " +activecases +"    Exposed:"+exposed +"    Recovered:" +Resolved +"    Death:" +death +"    R0:" + R0 + "    CFR:" + 100*((double)death/(double)infected)/0.8 +"%");
        for(int i4=(death+Resolved);i4<exposed+death+Resolved;i4++){
            contact = (int)((double)Contact(constant, randomvalue.get(i4),name,patientage.get(i4),AgeMatrix));
            immunity = 1.0-((double)immuned/(double)population);
            if(radchange.nextInt(100)>isolation*100){
                for (int i2 = 0; i2 < immunity*(double)contact-1; i2++) {
                    if((100>radchange.nextInt(100)+1)){
                        if(radchange.nextInt(10000)<(int)(10000.0*R0/(108))){
                            infected += 1;
                            immuned += 1;
                            while(i4>=ability.size()){
                                ability.add(0);
                            }
                            ability.set(i4,ability.get(i4)+1);
                        }
                    }
                }
            }
            while (infected>randomvalue.size()){
                randomvalue.add(0.0);
            }
        }
        day++;
        datapack[0] = day;
        datapack[1] = population;
        datapack[2] = infected;
        datapack[3] = exposed;
        datapack[4] = activecases;
        datapack[5] = Resolved;
        datapack[6] = death;
        datapack[7] = lockdown;
        datapack[8] = immuned;
        ArrayPackInt[0] = inf;
        ArrayPackInt[1] = aftinf;
        ArrayPackInt[2] = property;
        ArrayPackInt[3] = patientage;
        ArrayPackInt[4] = ability;
        ArrayPackInt[4] = ability;
        ArrayPackInt[5] = immun;
        ArrayPackDouble[0] = randomvalue;
        ArrayPackDouble[1] = randomcure;
        super.DATA.setDatapack(datapack);
        super.DATA.setArrayPackInt(ArrayPackInt);
        super.DATA.setArrayPackDouble(ArrayPackDouble);
        super.DATA.setAgeMatrix(AgeMatrix);
        return DATA;
    }

    public static int Contact(double lockdown,double random, String name, int Age, double[] AgeMatrix) {
        double[][] Matrix = IO.Matrices.get(IO.Countrycode.indexOf(name.substring(0,2)));
        Random rad = new Random();
        double poss = -1.0;
        do {
            poss = rad.nextGaussian();
        }while(poss<-4.0);
        int contact = ((int) (2*(4.0+random+0.2*poss)*6*(lockdown)/2.8));
        int Ageind = 15;
        int list[] = {1,2,2,3,3,3,3,3,4,6,7,8,16,21,81,121};
        for (int i = 0; i < list.length; i++) {
            if(Age==list[i]){
                Ageind=i;
                break;
            }
        }
        for (int i = 0; i < AgeMatrix.length; i++) {
            double s = Matrix[Ageind][i];
            AgeMatrix[i] += s*(1+0.1*random);
        }
        return contact;
    }

}
