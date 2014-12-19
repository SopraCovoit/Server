package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class JDBCConnector {

    private Connection connection;
    private static String urlBase;


    public void chargementDriver(){
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
        }
    }

    public static Connection getInstance(){

        String utilisateur = "sopra";
        String motDePasse = "covoit";
        Connection connexion = null;
        try {
            connexion = DriverManager.getConnection(urlBase, utilisateur, motDePasse);
            return connexion;
        } catch ( SQLException e ) {
            return null;
        } finally {
            if ( connexion != null )
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
        }
    }

}
