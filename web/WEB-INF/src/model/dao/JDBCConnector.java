package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class JDBCConnector {

    private static boolean driverLoaded = false;
   /* private static String urlBase ="jdbc:mysql://localhost:8889/sopracovoit";
    private static String utilisateur = "a";
    private static String motDePasse = "a";*/

    private static String urlBase ="jdbc:mysql://localhost:3306/sopracovoit";
    private static String utilisateur = "sopra";
    private static String motDePasse = "covoit";

    public static void chargementDriver(){
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch ( ClassNotFoundException e ) {

        }
    }

    public static Connection getInstance(){

        if (!driverLoaded){
            chargementDriver();
            driverLoaded = true;
        }

        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(urlBase, utilisateur, motDePasse);
            System.out.println("no error ");
            return connexion;
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.out.println("erreur sql ");
            return null;
        } finally {
            if ( connexion != null ) {

            }else{
                System.out.println("erreur ici ");
            }
        }
    }

}
