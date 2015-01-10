package utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by julescantegril on 08/01/2015.
 */
public class TokenList {

    protected static ArrayList<String> tokenList = new ArrayList<String>();
    protected static String  tokenAdmin = "42zobs";


    public static String getNewToken(){
        Random rn = new Random();
        String r = String.valueOf(rn.nextInt());
        while(leverage(r)){
            r = String.valueOf(Math.random());
        }
        tokenList.add(r);
        return r;
    }



    public static boolean leverage(String token){
        boolean ret;
        if(tokenList.contains(token)){
            ret = true;
        }else{
            ret = isAdmin(token);
        }
        return ret;
    }
    public static boolean isAdmin(String token){
        return token.equals(tokenAdmin);
    }

    public static void deleteToken(String token){
        tokenList.remove(token);
    }

}
