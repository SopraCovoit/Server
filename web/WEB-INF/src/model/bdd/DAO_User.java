package model.bdd;

import java.sql.SQLException;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class DAO_User extends DAO {

    public DAO_User(){
        super();
    }

    public void creerUser(){
        try {
            this.statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object find(long id) {
        return null;
    }

    @Override
    public Object create(Object obj) {
        return null;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public void delete(Object obj) {

    }
}
