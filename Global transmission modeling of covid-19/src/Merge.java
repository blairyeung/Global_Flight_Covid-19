import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Merge {
    public static void main(String[] args) throws Exception{
        String str = "Afghanistan,Aland Islands,Albania,Algeria,American Samoa,Andorra,Angola,Anguilla,Antarctica,Antigua and Barbuda,Argentina,Armenia,Aruba,Australia,Austria,Azerbaijan,Bahamas,Bahrain,Bangladesh,Barbados,Belarus,Belgium,Belize,Benin,Bermuda,Bhutan,Bolivia,Bonaire Sint Eustatius and Saba,Bosnia and Herzegovina,Botswana,Bouvet Island,Brazil,British Indian Ocean Territory,Brunei Darussalam,Bulgaria,Burkina Faso,Burundi,Cambodia,Cameroon,Canada,Cape Verde,Cayman Islands,Central African Republic,Chad,Chile,China,Christmas Island,Cocos (Keeling) Islands,Colombia,Comoros,Congo,Congo the Democratic Republic of the,Cook Islands,Costa Rica,C??te d'Ivoire,Croatia,Cuba,Curacao,Cyprus,Czechia,Denmark,Djibouti,Dominica,Dominican Republic,Ecuador,Egypt,El Salvador,Equatorial Guinea,Eritrea,Estonia,Ethiopia,Falkland Islands (Malvinas),Faroe Islands,Fiji,Finland,France,French Guiana,French Polynesia,French Southern Territories,Gabon,Gambia,Georgia,Germany,Ghana,Gibraltar,Greece,Greenland,Grenada,Guadeloupe,Guam,Guatemala,Guernsey,Guinea,Guinea-Bissau,Guyana,Haiti,Heard Island and McDonald Islands,Holy See (Vatican City State),Honduras,China Hong Kong,Hungary,Iceland,India,Indonesia,Iran,Iraq,Ireland,Isle of Man,Israel,Italy,Jamaica,Japan,Jersey,Jordan,Kazakhstan,Kenya,Kiribati,North Korea,Kosovo,Korea,Kuwait,Kyrgyzstan,Lao People's Democratic Republic,Latvia,Lebanon,Lesotho,Liberia,Libya,Liechtenstein,Lithuania,Luxembourg,China Macao,Macedonia,Madagascar,Malawi,Malaysia,Maldives,Mali,Malta,Marshall Islands,Martinique,Mauritania,Mauritius,Mayotte,Mexico,Micronesia,Moldova,Monaco,Mongolia,Montenegro,Montserrat,Morocco,Mozambique,Myanmar,Namibia,Nauru,Nepal,Netherlands,New Caledonia,New Zealand,Nicaragua,Niger,Nigeria,Niue,Norfolk Island,Northern Mariana Islands,Norway,Oman,Pakistan,Palau,Palestine State of,Panama,Papua New Guinea,Paraguay,Peru,Philippines,Pitcairn,Poland,Portugal,Puerto Rico,Qatar,Reunion,Romania,Russian Federation,Rwanda,Saint Barth??lemy,Saint Helena Ascension and Tristan da Cunha,Saint Kitts and Nevis,Saint Lucia,Saint Martin (French part),Saint Pierre and Miquelon,Saint Vincent and the Grenadines,Samoa,San Marino,Sao Tome and Principe,Saudi Arabia,Senegal,Serbia,Seychelles,Sierra Leone,Singapore,Sint Maarten (Dutch part),Slovakia,Slovenia,Solomon Islands,Somalia,South Africa,South Georgia and the South Sandwich Islands,South Sudan,Spain,Sri Lanka,Sudan,Suriname,Svalbard and Jan Mayen,Swaziland,Sweden,Switzerland,Syrian Arab Republic,China Taiwan,Tajikistan,Tanzania,Thailand,Timor-Leste,Togo,Tokelau,Tonga,Trinidad and Tobago,Tunisia,Turkey,Turkmenistan,Turks and Caicos Islands,Tuvalu,Uganda,Ukraine,United Arab Emirates,United Kingdom,United States,United States Minor Outlying Islands,Uruguay,Uzbekistan,Vanuatu,Venezuela,Viet Nam,Virgin Islands British,Virgin Islands U.S.,Wallis and Futuna,Western Sahara,Yemen,Zambia,Zimbabwe";
        File file = new File("E:/DeathFile.csv");
        Scanner scan = new Scanner(file);
        ArrayList<String> arr = new ArrayList<>();
        while (str.contains(",")) {
            String country = str.substring(0,str.indexOf(","));
            str = str.substring(str.indexOf(",")+1);
            arr.add(country);
        }
        System.out.println(arr);
        String country = scan.nextLine();
        String numbers  = scan.nextLine();
        System.out.println(country);
        System.out.println(numbers);
        ArrayList<String> countries = new ArrayList<>();
        ArrayList<Integer> Numbers = new ArrayList<>();
        while (country.contains(",")) {
            String Coun = country.substring(0,country.indexOf(","));
            country = country.substring(country.indexOf(",")+1);
            countries.add(Coun);
            int buff = Integer.parseInt(numbers.substring(0,numbers.indexOf(",")));
            numbers = numbers.substring(numbers.indexOf(",")+1);
            Numbers.add(buff);
        }
        System.out.println(countries);
        System.out.println(Numbers);
        ArrayList<Integer> first = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            first.add(0);
        }
        for (int i = 0; i < countries.size(); i++) {
            int index = arr.indexOf(countries.get(i));
            System.out.println(index);
            if(index!=-1){
                first.set(index,Numbers.get(i));
            }
        }
        System.out.println(arr);
        System.out.println(first);
        File fi = new File("E:/Global Model/NewOrg.csv");
        PrintWriter print = new PrintWriter(fi);
        for (int i = 0; i < arr.size(); i++) {
            print.print(arr.get(i)+",");
        }
        print.println();
        for (int i = 0; i < arr.size(); i++) {
            print.print(first.get(i)+",");
        }
        print.close();
    }
}
