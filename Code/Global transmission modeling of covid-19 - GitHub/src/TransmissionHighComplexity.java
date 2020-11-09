import org.w3c.dom.CDATASection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TransmissionHighComplexity {

    static protected ArrayList<Integer> inf = new ArrayList<Integer>();
    static protected ArrayList<Integer> aftinf = new ArrayList<Integer>();
    static protected ArrayList<Integer> property = new ArrayList<Integer>();
    static protected ArrayList<Double> randomvalue = new ArrayList<Double>();
    static protected ArrayList<Double> randomcure = new ArrayList<Double>();
    static protected ArrayList<Integer> patientage = new ArrayList<Integer>();
    static protected ArrayList<Integer> ability = new ArrayList<>();
    static protected ArrayList<Integer> immun = new ArrayList<>();
    static protected ArrayList<Integer> immarr = new ArrayList<>();

    private double isolation = 0;
    public double[] Model(double datapack[]){
        int day = (int) datapack[0];
        int population = (int) datapack[1];
        int infected = (int) datapack[2];
        int exposed = (int) datapack[3];
        int activecases = (int) datapack[4];
        int Resolved = (int) datapack[5];
        int death = (int) datapack[6];
        int lockdown = (int) datapack[7];
        int immuned = (int) datapack[8];
        int populationdensity = 5;
        double R,constant = 1;
        Random radchange = new Random();
        int immune = 0;
        double infectible = 0;
        int newcase = 0;
        int caninfect =population;
        int contact = 0;//患病者每天与这么多人接触
        double immunity;
        caninfect  = (int) (population - immuned);
        immunity = ((double)caninfect/(double)population);
        R = Parameters.R0*(immunity)*(6.3545/6.3545)/0.9240798353415315;
        double rate = R/(15*21);//Average time need to recoer
        if(infected>lockdown&&constant<0.15){
            constant =0.15;
        }
        double sum = integral(activecases);;
        for (int i1 = 0; i1 < infected; i1++) {
            while (inf.size()<=infected) {
                inf.add(0);
                aftinf.add(0);
                property.add(-1);
                randomvalue.add(0.0);
                randomcure.add(0.0);
                patientage.add(Agedistribution.getPatientAge());//A new excel to store these
                immun.add(0);
            }
            inf.set(i1,1);
            aftinf.set(i1,aftinf.get(i1)+1);//days after being infected
            if(aftinf.get(i1)>=3.1+randomvalue.get(i1)&&property.get(i1)==-1) {
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
            /*if(aftinf.get(i1)>7+Math.abs(2+1.2*randomvalue.get(i1))&&(property.get(i1)==0)){
                if(radchange.nextInt(1000)<(13.8*0.2)){
                    activecases++;
                    newcase++;
                    property.set(i1,1);
                    continue;
                }
            }*/
            /*if((aftinf.get(i1)>8+Math.abs(2+1.2*randomvalue.get(i1)))&&property.get(i1)==0){
                property.set(i1,-2);
            }*/
            if(aftinf.get(i1)>18+1.2*randomvalue.get(i1)&&(property.get(i1)==1||property.get(i1)==4||property.get(i1)==5)) {//canada 12
                if(property.get(i1)==1) {
                    if (radchange.nextInt(1000) < (10*Agedistribution.getMortalityRate(patientage.get(i1))*sum)) {
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
            /*if(aftinf.get(i1)>60+15.5+(2+1.5*randomvalue.get(i1))&&property.get(i1)==2&&immun.get(i1)==0){
                if(radchange.nextInt(1000)<2){
                    immuned-=1;
                    immun.set(i1,1);
                }
            }*/
        }
        System.out.println("R: " + R);
        double news = 0;
        immunity = 1.0-((double)immuned/(double)population);
        System.out.println("IMMUNITY" + immunity);
        System.out.println("IMM" + immuned);
        for(int i4=(death+Resolved);i4<exposed+death+Resolved;i4++){
            contact = (int)((double)Contact(constant,populationdensity));
            immunity = 1.0-((double)immuned/(double)population);
            if(radchange.nextInt(100)>isolation*100){
                for (int i2 = 0; i2 < immunity*(double) contact; i2++) {
                    if((100>radchange.nextInt(100)+1)){
                        if(radchange.nextInt(10000)<(int)(10000.0*Parameters.R0/(15.0*18.0))){
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
            for (int i1 = infectppl; i1 < infected; i1++) {
                randomvalue.set(i1, (double)0.7*(radchange.nextGaussian()+radchange.nextGaussian()));
            }
        }
        System.out.println("Day: " + day + "    Infected: " + infected + "    cases: " +activecases +"    Exposed:"+exposed +"    Recovered:" +Resolved +"    Death:" +death);
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
        return datapack;
    }

    public TransmissionHighComplexity() {

    }

    public TransmissionHighComplexity(Day days[]) {
        double datapack[] = new double[9];
        datapack[0] = days[Main.day].getDay();
        datapack[1] = days[Main.day].getPopulation();
        datapack[2] = days[Main.day].getInfected();
        datapack[3] = days[Main.day].getExposed();
        datapack[4] = days[Main.day].getActivecases();
        datapack[5] = days[Main.day].getResolved();
        datapack[6] = days[Main.day].getDeath();
        datapack[7] = days[Main.day].getLockdown();
        datapack[8] = days[Main.day].getImmuned();
        datapack = Model(datapack);
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
        int contact = ((int) (2*(4.0+poss)*populationdensity*(lockdown)/Parameters.R0));
        return contact;
    }

}
