import java.util.ArrayList;

public class Data {
    private double datapack[] = new double[9];
    private ArrayList<Integer> ArrayPackInt[] = new ArrayList[6];
    private ArrayList<Double> ArrayPackDouble[] = new ArrayList[2];
    public Data(){
        datapack[0] = Main.day;//=0
        datapack[1] = 100000;
        datapack[2] = 0;
        datapack[3] = 0;
        datapack[4] = 0;
        datapack[5] = 0;
        datapack[6] = 0;
        datapack[7] = 0;
        datapack[8] = 0;
        for (int i = 0; i < 6; i++) {
            ArrayPackInt[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < 2; i++) {
            ArrayPackDouble[i] = new ArrayList<Double>();
        }
    }

    public Data(double data[], ArrayList[] ArrayPackInt, ArrayList[] ArrayPackDouble){
        datapack = data;
        this.ArrayPackInt = ArrayPackInt;
        this.ArrayPackDouble = ArrayPackDouble;
    }
    public void setDatapack(double data[]){
        datapack = data;
    }
    public void setArrayPackInt(ArrayList[] ArrayPackInt){
        this.ArrayPackInt = ArrayPackInt;
    }
    public void setArrayPackDouble(ArrayList[] ArrayPackDouble){
        this.ArrayPackDouble = ArrayPackDouble;
    }
    public double[] getDataPack(){
        return datapack;
    }
    public ArrayList[] getIntPack(){
        return ArrayPackInt;
    }
    public ArrayList[] getDoublePack(){
        return ArrayPackDouble;
    }
}
