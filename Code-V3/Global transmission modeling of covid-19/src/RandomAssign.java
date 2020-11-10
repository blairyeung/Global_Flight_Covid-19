import java.util.ArrayList;
import java.util.Random;

public class RandomAssign extends Globe{

    public RandomAssign(){

    }
    public ArrayList RandomAssignArrival(int i, int i1, int i2, String Name, double OnBoard) {
        ArrayList<String> Places = new ArrayList<>();
        ArrayList<Integer> CODE = new ArrayList<>();
        int Counter = 0;
        Random rad = new Random();
        int Limit = Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].indexOf(Name))].size();
        System.out.println("LIMITR" + Limit);
        for (int i3 = 0; i3 < (int) OnBoard+rad.nextGaussian(); i3++) {
            CODE.add(rad.nextInt(Limit));
        }
        for (int i3 = 0; i3 < CODE.size(); i3++) {
            Places.add(Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].get(CODE.get(i)).getArrive());
            System.out.println("EXPORTED");
        }
        //OnBoard /= AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].size();
        /*for (int i3 = 0; i3 < AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].size(); i3++) {
            if ((Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))) != -1) {
                for (int i4 = 0; i4 < Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].size(); i4++) {
                    Limit = Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].size();
                    if(poss.POSS((int)OnBoard,Limit)){
                        //System.out.println("Flight Information");
                        Counter++;
                        if(Counter>OnBoard){
                            return Places;
                        }
                        //System.out.println(Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].get(i4).getCode());
                        //System.out.println(Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].get(i4).getDepart());
                        //System.out.println(Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].get(i4).getArrive());
                        Places.add(Flight.Flights[0][11][0][Flight.MenuIndex.indexOf(AirportinCountyandCountry[IO.Countrycode.indexOf(Name.substring(0, 2))][CountyinCountry[IO.Countrycode.indexOf(Name.substring(0, 2))].indexOf(Name)].get(i3))].get(i4).getArrive());
                    }
                }
            }
        }*/
        return Places;
    }
}
