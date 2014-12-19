package model.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by root on 19/12/14.
 */
    public class ConnectionPostgreSQL{

        /**
         * URL de connection
         */
        private static String url = "jdbc:postgresql://localhost:5432/Societe";
        /**
         * Nom du user
         */
        private static String user = "postgres";
        /**
         * Mot de passe du user
         */
        private static String passwd = "postgres";
        /**
         * Objet Connection
         */
        private static Connection connect;

        /**
         * Méthode qui va nous retourner notre instance
         * et la créer si elle n'existe pas...
         * @return
         */
        public static Connection getInstance(){
            if(connect == null){
                try {
                    connect = DriverManager.getConnection(url, user, passwd);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return connect;
        }

}
