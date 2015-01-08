package utils;

import model.User;

import java.util.HashMap;

/**
 * Created by julescantegril on 08/01/2015.
 */
public class TokenMap {

    public static long getNewToken(){
        long r = (long)Math.random();
        while(existToken(r)){
            r = (long)Math.random();
        }
        return r;
    }

    protected static HashMap<Long, User> tokenMap = new HashMap<Long, User>();

    public static boolean existToken(long token){
        if(tokenMap.get(token)!= null){
            return true;
        }else{
            return false;
        }
    }

    public static void deleteToken(long token){
        tokenMap.remove(token);
    }

    public static void addToken(long token,User user){
        tokenMap.put(token,user);
    }

}
