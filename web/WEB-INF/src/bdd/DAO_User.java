package bdd;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class DAO_User extends JDBCConnector {

    Statement statement;
    public DAO_User(String urlBase){
        super(urlBase);
        try {
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void creerUser(String string){
        try {
            this.statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
