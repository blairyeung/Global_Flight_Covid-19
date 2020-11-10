import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InfectedREADCSV {
    static  int w = 50;
    public static void main(String[] args) throws Exception{
        IO io = new IO();
        int empty = 0;
        int observation = 244;
        //int observation = 236;
        int datenum = 4;
        double[] avg = new double[observation];
        double[] corr = new double[observation];
        ArrayList<String> countrycode = IO.Countrycode;
        ArrayList<String> country = IO.Countryname;
        ArrayList<String> Lines = new ArrayList<>();
        ArrayList<Double> PearsonProduct = new ArrayList<>();
        File files[] = new File[country.size()];
        File outpfiles[] = new File[country.size()];
        Scanner countryscan[] = new Scanner[country.size()];
        PrintWriter printers[] = new PrintWriter[country.size()];
        String GlobalDeathPath = "E:/Global Model/gl.csv";
        File datefile = new File("E:/Date.csv");
        Scanner datescan = new Scanner(datefile);
        ArrayList<String> dates = new ArrayList<>();
        File GlobalDeathFile = new File(GlobalDeathPath);
        Scanner DeathFileScanner = new Scanner(GlobalDeathFile);
        String date[] = {"Sep.1","Sep.15","Oct.1","Oct.15","Nov.1"};
        //String date[] = {"Sep.7","Sep.22","Oct.7","Oct.22"};
        String outdate[] = {"9.1","9.15","10.1","10.15","11.1"};
        //String outdate[] = {"9.7","9.22","10.7","10.22"};
        observation -= datenum*15;
        while (DeathFileScanner.hasNext()) {
            Lines.add(DeathFileScanner.nextLine());
        }
        while (datescan.hasNext()) {
            dates.add(datescan.nextLine());
        }
        int counter = 0;
        String buffer = Lines.get(0);
        while (buffer.contains(",")) {
            counter++;
            buffer = buffer.substring(buffer.indexOf(",")+1);
        }
        System.out.println(counter);
        ArrayList<String[]> cases = new ArrayList<>();
        ArrayList<String> CountryDeath = new ArrayList<>();
        for (int i = 0; i < Lines.size(); i++) {
            cases.add(new String[counter+1]);
        }
        for (int i = 0; i < Lines.size(); i++) {
            String countries;
            String buffering = Lines.get(i);
            countries = buffering.substring(0,buffering.indexOf(","));
            buffering = buffering.substring(buffering.indexOf(",")+1);
            System.out.println(countries);
            CountryDeath.add(countries);
            for (int i1 = 0; i1 < counter - 1; i1++) {
                String number = buffering.substring(0,buffering.indexOf(","));
                System.out.print(number+",");
                String[] buff = cases.get(i);
                buff[i1] = number;
                cases.set(i,buff);
                buffering = buffering.substring(buffering.indexOf(",")+1);
            }
        }
        for (int i = 0; i < Lines.size()-1; i++) {
            System.out.print(CountryDeath.get(i)+",");
            for (int i1 = 0; i1 < counter - 1; i1++) {
                System.out.print(cases.get(i)[i1]+",");
            }
            System.out.println();
        }
        for (int i = 0; i < countrycode.size(); i++) {
            String path1 = "E:/Global Model/"+outdate[datenum]+" Infected/";
            String path2 = ".csv";
            String code = countrycode.get(i);
            String path =path1 + code + path2;
            String outpath =path1 + code + "TR" + path2;
            files[i] = new File(path);
            outpfiles[i] = new File(outpath);
            countryscan[i] = new Scanner(files[i]);
            printers[i] = new PrintWriter(outpfiles[i]);
        }

        for (int i = 0; i < countrycode.size(); i++) {
            ArrayList<String> line = new ArrayList<>();
            while (countryscan[i].hasNext()) {
                line.add(countryscan[i].nextLine());
            }
            ArrayList<String[]> Trails = new ArrayList<>();
            for (int i1 = 0; i1 < w; i1++) {
                Trails.add(new String[observation]);
            }
            String[] names = new String[w];
            for (int i1 = 0; i1 < line.size(); i1++) {
                String buffering = line.get(i1);
                String name = buffering.substring(0,buffering.indexOf(","));
                buffering.substring(buffering.indexOf(",")+1);
                names[i1] = name;
                for (int i2 = 0; i2 < observation; i2++) {
                    String number = buffering.substring(0,buffering.indexOf(","));
                    String[] buff = Trails.get(i1);
                    buff[i2] = number;
                    buffering  = buffering.substring(buffering.indexOf(",")+1);
                    Trails.set(i1,buff);
                }
            }
            String naming = names[0].substring(0,names[0].indexOf("Trail")-1);
            System.out.println(naming);
            for (int i1 = 0; i1 < observation; i1++) {
                int Mean[] = new int[w];
                double lineaverage = 0;
                if(i1==0){
                    printers[i].print("Date");
                }
                else {
                    printers[i].print(dates.get(i1+15*datenum)+",");
                }
                for (int i2 = 0; i2 < w; i2++) {
                    System.out.print(Trails.get(i2)[i1]);
                    printers[i].print(Trails.get(i2)[i1]);
                    if(i1!=0){
                        lineaverage += Double.parseDouble(Trails.get(i2)[i1])/(double)(w-empty);
                        Mean[i2] = (int)Double.parseDouble(Trails.get(i2)[i1]);
                    }
                    System.out.print(",");
                    printers[i].print(",");
                }
                avg[i1] = lineaverage;
                avg[i1] = median(Mean);
                if(i1>(136-15*datenum)) {
                    int index = CountryDeath.indexOf(naming);
                    if (index != -1) {
                        System.out.print(cases.get(index)[i1 - (136-15*datenum)]+",");
                        corr[i1-(136-15*datenum)] = (int)Double.parseDouble(cases.get(index)[i1-(136-15*datenum)]);
                        printers[i].print(cases.get(index)[i1 - (136-15*datenum)]+",");
                    }
                    else{
                        System.out.print("0,");
                        printers[i].print("0,");
                    }
                }
                else{
                    if(i1==0){
                        printers[i].print("Actual,Median");
                    }
                    else {
                        System.out.print("0,");
                        printers[i].print("0,");
                    }
                }
                if(i1!=0&&i1!=observation-1){
                    char c = '"';
                    System.out.print(lineaverage+w+",");
                    printers[i].print(Math.round(lineaverage)+",");
                    System.out.println("=RSQ(AY:AY241,AZ112:AZ241)");
                    printers[i].println();
                }
                if(i1==0){
                    System.out.println();
                    printers[i].println();
                }
                else if(i1==observation-1){
                    double[] bufferd = new double[observation-(136-15*datenum)];
                    double[] bufferd2 = new double[observation-(136-15*datenum)];
                    for (int i2 = 0; i2 < bufferd.length; i2++) {
                        bufferd[i2] = avg[i2+(136-15*datenum)];
                        bufferd2[i2] = corr[i2];
                    }
                    for (int i2 = 0; i2 < bufferd.length; i2++) {
                        System.out.println(bufferd[i2]);
                        System.out.println(bufferd2[i2]);
                        System.out.println(true);
                    }
                    double pearson = Math.pow(getPearsonCorrelationScore(bufferd,bufferd2),2);
                    if(bufferd2[bufferd2.length-1]==0||bufferd[bufferd.length-1]==0){
                        pearson = 0;
                    }
                    System.out.println(pearson);
                    System.out.println("PEARSON");
                    PearsonProduct.add(pearson);
                    printers[i].print(Math.round(lineaverage)+",");
                    //printers[i].println(pearson);
                }
            }
        }
        System.out.println(country);
        System.out.println(PearsonProduct);
    }

    public static double getPearsonCorrelationScore(double[] xData, double[] yData) {
        if (xData.length != yData.length)
            throw new RuntimeException("数据不正确！");
        double xMeans;
        double yMeans;
        double numerator = 0;// 求解皮尔逊的分子
        double denominator = 0;// 求解皮尔逊系数的分母

        double result = 0;
        // 拿到两个数据的平均值
        xMeans = getMeans(xData);
        yMeans = getMeans(yData);
        // 计算皮尔逊系数的分子
        numerator = generateNumerator(xData, xMeans, yData, yMeans);
        // 计算皮尔逊系数的分母
        denominator = generateDenomiator(xData, xMeans, yData, yMeans);
        // 计算皮尔逊系数
        result = numerator / denominator;
        return result;
    }

    /**
     * 计算分子
     *
     * @param xData
     * @param xMeans
     * @param yData
     * @param yMeans
     * @return
     */
    private static double generateNumerator(double[] xData, double xMeans, double[] yData, double yMeans) {
        double numerator = 0.0;
        for (int i = 0; i < xData.length; i++) {
            numerator += (xData[i] - xMeans) * (yData[i] - yMeans);
        }
        return numerator;
    }

    /**
     * 生成分母
     *
     * @param yMeans
     * @param yData
     * @param xMeans
     * @param xData
     * @return 分母
     */
    private static double generateDenomiator(double[] xData, double xMeans, double[] yData, double yMeans) {
        double xSum = 0.0;
        for (int i = 0; i < xData.length; i++) {
            xSum += (xData[i] - xMeans) * (xData[i] - xMeans);
        }
        double ySum = 0.0;
        for (int i = 0; i < yData.length; i++) {
            ySum += (yData[i] - yMeans) * (yData[i] - yMeans);
        }
        return Math.sqrt(xSum) * Math.sqrt(ySum);
    }

    /**
     * 根据给定的数据集进行平均值计算
     *
     * @param
     * @return 给定数据集的平均值
     */
    private static double getMeans(double[] datas) {
        double sum = 0.0;
        for (int i = 0; i < datas.length; i++) {
            sum += datas[i];
        }
        return sum / datas.length;
    }
    public static int median(int []nums){
        int nums2[]  = nums;
        bubbleSort(nums2);
        return nums2[w/2];
    }

    public static void bubbleSort(int[] array) {
        int size = array.length;
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    exchangeElements(array, j, j + 1);
                }
            }
        }
    }
    public static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }



    private static int partition(int nums[], int start, int end){
        int left=start;
        int right=end;
        int pivot=nums[left];
        while(left<right){
            while(left<right&&nums[right]>=pivot){
                right--;
            }
            if(left<right){
                nums[left]=nums[right];
                left++;
            }
            while(left<right&&nums[left]<=pivot){
                left++;
            }
            if(left<right){
                nums[right]=nums[left];
                right--;
            }
        }
        nums[left]=pivot;
        return left;
    }

}

