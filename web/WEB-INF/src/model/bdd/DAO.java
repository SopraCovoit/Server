package model.bdd;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class DAO extends JDBCConnector {

    Statement statement;
    public DAO(String urlBase){
        super(urlBase);
        try {
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
