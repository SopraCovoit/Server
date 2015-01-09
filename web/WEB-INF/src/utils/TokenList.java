package utils;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by julescantegril on 08/01/2015.
 */
public class TokenList {

    public static long getNewToken(){
        long r = (long)Math.random();
        while(existToken(r)){
            r = (long)Math.random();
        }
        tokenList.add(r);
        return r;
    }

    protected static ArrayList<Long> tokenList = new ArrayList<Long>();

    public static boolean existToken(Long token){
        if(tokenList.contains(token)){
            return true;
        }else{
            return false;
        }
    }

    public static void deleteToken(Long token){
        tokenList.remove(token);
    }

}
