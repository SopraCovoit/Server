package model.bdd;

import java.sql.SQLException;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class DAO_User extends DAO {

    public DAO_User(String urlBase){
        super(urlBase);
    }

    public void creerUser(String string){
        try {
            this.statement.execute(string);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
