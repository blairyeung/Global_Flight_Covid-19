import org.w3c.dom.CDATASection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Transmission extends County{

    private double isolation = 0;

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

        int populationdensity = 6;
        double R,constant = 1;
        Random radchange = new Random();
        int immune = 0;
        double infectible = 0;
        int newcase = 0;
        int caninfect =population;
        int contact = 0;
        double immunity;
        caninfect  = (int) (population - immuned);
        immunity = ((double)caninfect/(double)population);
        R = Parameters.R0*(immunity)*(populationdensity/6.3545)/0.9240798353415315;
        double rate = R/(15*21);//Average time need to recover
        if(death>=lockdown&&constant<0.15){
            constant =0.15;
        }
        double sum = 1.1;
        //integral(activecases);;
        for (int i1 = 0; i1 < infected; i1++) {
            while (inf.size()<=infected) {
                inf.add(0);
                aftinf.add(0);
                property.add(-1);
                randomvalue.add(0.0);
                randomcure.add(0.0);
                //patientage.add(50);//A new excel to store these
                immun.add(0);
            }
            inf.set(i1,1);
            randomvalue.set(i1, (double)0.7*(radchange.nextGaussian()+radchange.nextGaussian()));
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
            if(aftinf.get(i1)>13+1.2*randomvalue.get(i1)&&(property.get(i1)==1||property.get(i1)==4||property.get(i1)==5)) {//canada 12
                if(property.get(i1)==1) {
                    if (radchange.nextInt(1000) < (16*sum)) {
                        property.set(i1, 2);
                        death += 1;
                        activecases--;
                        exposed--;
                        continue;
                    } else {
                        property.set(i1, 3);
                    }
                }
            }
            if(aftinf.get(i1)>15.5+(2+1.5*randomvalue.get(i1))&&property.get(i1)==3) {//10.5 for china/germany 12.5 for canada
                if(radchange.nextInt(100)<integraldischarge(aftinf.get(i1))){
                    property.set(i1,2);
                    activecases--;
                    exposed--;
                    Resolved++;
                    continue;
                }
            }
        }
        //System.out.println("R: " + R);
        double news = 0;
        immunity = 1.0-((double)immuned/(double)population);
        //System.out.println("IMMUNITY" + immunity);
        //System.out.println("IMM" + immuned);
        for(int i4=(death+Resolved);i4<exposed+death+Resolved;i4++){
            contact = (int)((double)Contact(constant,populationdensity));
            immunity = 1.0-((double)immuned/(double)population);
            if(radchange.nextInt(100)>isolation*100){
                for (int i2 = 0; i2 < immunity*(double)contact-1; i2++) {
                    if((100>radchange.nextInt(100)+1)){
                        if(radchange.nextInt(10000)<(int)(10000.0*Parameters.R0/(15.0*9.0))){
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
            int infectppl = death+Resolved;
        }
        //System.out.println(contact);
        //System.out.println("Day: " + day +"    Population: " + population +"    Infected: " + infected + "    cases: " +activecases +"    Exposed:"+exposed +"    Recovered:" +Resolved +"    Death:" +death);
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
        //ArrayList<Integer> immarr = new ArrayList<>();
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

        int populationdensity = 6;
        double R,constant = 1;
        Random radchange = new Random();
        int immune = 0;
        double infectible = 0;
        int newcase = 0;
        int caninfect =population;
        int contact = 0;
        double immunity;
        caninfect  = (int) (population - immuned);
        immunity = ((double)caninfect/(double)population);
        R = Parameters.R0*(immunity)*(populationdensity/6.3545)/0.9240798353415315;
        double rate = R/(15*21);//Average time need to recover
        if(death>localevent.getDeath()){
            lockdown++;
        }
        //System.out.println("LOCKED, Consntat is");
        constant = 1.0- (double)lockdown*(1.0/(double)localevent.getSpeed());
        if(death>localevent.getDeath()&&constant<0.15){
            constant =0.15;
        }
        //System.out.println(constant);
        double sum = integral(activecases);;
        for (int i1 = 0; i1 < infected; i1++) {
            while (inf.size()<=infected) {
                inf.add(0);
                aftinf.add(0);
                property.add(-1);
                randomvalue.add(0.0);
                randomcure.add(0.0);
                //patientage.add(50);//A new excel to store these
                immun.add(0);
            }
            inf.set(i1,1);
            randomvalue.set(i1, (double)0.7*(radchange.nextGaussian()+radchange.nextGaussian()));
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
            if(aftinf.get(i1)>13+1.2*randomvalue.get(i1)&&(property.get(i1)==1||property.get(i1)==4||property.get(i1)==5)) {//canada 12
                if(property.get(i1)==1) {
                    if (radchange.nextInt(1000) < (16*sum)) {
                        property.set(i1, 2);
                        death += 1;
                        activecases--;
                        exposed--;
                        continue;
                    } else {
                        property.set(i1, 3);
                    }
                }
            }
            if(aftinf.get(i1)>15.5+(2+1.5*randomvalue.get(i1))&&property.get(i1)==3) {//10.5 for china/germany 12.5 for canada
                if(radchange.nextInt(100)<integraldischarge(aftinf.get(i1))){
                    property.set(i1,2);
                    activecases--;
                    exposed--;
                    Resolved++;
                    continue;
                }
            }
        }
        //System.out.println("R: " + R);
        double news = 0;
        immunity = 1.0-((double)immuned/(double)population);
        //System.out.println("IMMUNITY" + immunity);
        //System.out.println("IMM" + immuned);
        for(int i4=(death+Resolved);i4<exposed+death+Resolved;i4++){
            contact = (int)((double)Contact(constant,populationdensity));
            immunity = 1.0-((double)immuned/(double)population);
            if(radchange.nextInt(100)>isolation*100){
                for (int i2 = 0; i2 < immunity*(double)contact-1; i2++) {
                    if((100>radchange.nextInt(100)+1)){
                        if(radchange.nextInt(10000)<(int)(10000.0*Parameters.R0/(15.0*8.4))){
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
            int infectppl = death+Resolved;
        }
        //System.out.println("Day: " + Main.day +"    Population: " + population +"    Infected: " + infected + "    cases: " +activecases +"    Exposed:"+exposed +"    Recovered:" +Resolved +"    Death:" +death);
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
        return DATA;
    }
    public Transmission(Data DATA, Event localevent) {
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
        //ArrayList<Integer> immarr = new ArrayList<>();
        datapack[0] = days[Main.day].getDay();
        datapack[1] = days[Main.day].getPopulation();
        datapack[2] = days[Main.day].getInfected();
        datapack[3] = days[Main.day].getExposed();
        datapack[4] = days[Main.day].getActivecases();
        datapack[5] = days[Main.day].getResolved();
        datapack[6] = days[Main.day].getDeath();
        datapack[7] = days[Main.day].getLockdown();
        datapack[8] = days[Main.day].getImmuned();
        DATA = Model(DATA, localevent);
        days[Main.day+1] = new Day((int)datapack[0],(int)datapack[1],(int)datapack[2],(int)datapack[3],(int)datapack[4],(int)datapack[5],(int)datapack[6],datapack[7],(int)datapack[8]);
        return;
    }
    public Transmission(Day days[], ArrayList[] ArrayPackInt, ArrayList[] ArrayPackDouble) {
        double datapack[] = new double[9];
        ArrayList<Integer> inf = new ArrayList<Integer>();
        ArrayList<Integer> aftinf = new ArrayList<Integer>();
        ArrayList<Integer> property = new ArrayList<Integer>();
        ArrayList<Double> randomvalue = new ArrayList<Double>();
        ArrayList<Double> randomcure = new ArrayList<Double>();
        ArrayList<Integer> patientage = new ArrayList<Integer>();
        ArrayList<Integer> ability = new ArrayList<>();
        ArrayList<Integer> immun = new ArrayList<>();
        //ArrayList<Integer> immarr = new ArrayList<>();
        //Data DATA = new Data(datapack,ArrayPackInt,ArrayPackDouble);
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

    public static void main(String[] args) {
    }

    public static double integraldischarge(int days) {
        double differenttiation = (double)days - 22.9;
        double sum = 0;
        for(double i = 0;i<differenttiation ;i+=0.001){
            sum += 0.001*Math.pow(Math.E,(-(Math.pow((Math.log10(0.86*i)),2))/(Math.pow(Math.log10(Math.E),2))));
        }
        sum = sum/2.437040;
        return (sum*1000);
    }

    public static double integral(int cases){
        double differenttiation = ((double)cases/(double)Parameters.Overwhelm) - 1.5;
        double sum = 0;
        for(double i = 0;i<Math.abs(differenttiation);i+=0.001){
            if(differenttiation>0) {
                sum += (0.001) * Math.pow(Math.E, (-Math.pow(i, 2)));
            }
            else{
                sum -= ((0.001) * Math.pow(Math.E, (-Math.pow(i, 2))));
            }
        }
        sum = 2*(sum/Math.pow(Math.PI,0.5)+1.5)-1;
        return sum;
    }

    public static int Contact(double lockdown,double populationdensity) {
        Random rad = new Random();
        double poss = -1.0;
        do {
            poss = rad.nextGaussian();
        }while(poss<-4.0);
        int contact = ((int) (2*(4.0+poss)*populationdensity*(lockdown)/2.79));
        return contact;
    }

}
