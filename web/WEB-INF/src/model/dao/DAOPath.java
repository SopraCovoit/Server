package model.dao;

import model.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class DAOPath extends DAO {

    Statement statement;

    public DAOPath(){
        super();
        try {
            statement = this.connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Path find(long id) {
        return null;
    }

    @Override
    public boolean create(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        try {
            this.connect
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                            "DROP toto"
                    );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
