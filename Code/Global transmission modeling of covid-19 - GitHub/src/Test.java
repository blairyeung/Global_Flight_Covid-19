public class Test {
static String str = "auvicinfected aunswinfected kh8infected kh17infected kh18infected caoninfected cabcinfected cn15infected cn51infected cn13infected cn64infected cn53infected cn50infected cn54infected cn44infected cn36infected cn45infected cn37infected cn21infected cn65infected cn62infected cn32infected cn22infected cn23infected cn63infected cn41infected cn34infected cn35infected cn43infected cn11infected cn14infected cn42infected cn61infected cn12infected cn46infected cnuainfected cn33infected cn31infected cn52infected hkuainfected mouainfected twxkminfected twkhhinfected twtxginfected twpeninfected twtpeinfected twtaoinfected deheinfected denwinfected debyinfected indlinfected idsainfected jp12infected jp13infected jp27infected jp01infected jp40infected jp23infected my12infected my14infected mm06infected npbainfected phuainfected phcebinfected qadainfected kr49infected kr28infected kr26infected kr11infected kr27infected rukdainfected rumosinfected sg04infected seabinfected th10infected th50infected th83infected tr34infected aeduinfected uscainfected uswainfected usnyinfected usnjinfected usgainfected vn34infected vn15infected vn23infected reuainfected";

    public static void main(String[] args) {
        while(true){
            if(str.length()>8){
                String buffer = str.substring(0,str.indexOf("infected")+8);
                str = str.substring(str.indexOf("infected")+9);
                char cha = '"';
                String buff = cha+""+cha;
                System.out.print("(line "+buffer +" day)");
            }
            else{
                break;
            }
        }
    }
}
