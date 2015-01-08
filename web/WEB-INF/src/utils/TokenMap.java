package utils;

import model.User;

import java.util.HashMap;

/**
 * Created by julescantegril on 08/01/2015.
 */
public class TokenMap {
    static HashMap<Long, User> tokenMap = new HashMap<Long, User>();

    public static boolean existToken(long token){
        if(tokenMap.get(token)!= null){
            return true;
        }else{
            return false;
        }
    }

    public static void

}
