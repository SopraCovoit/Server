package model.dao;

import model.Path;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class DAOPath extends DAO {



    @Override
    public Path find(long id) {
        return null;
    }

    @Override
    public Path create(Object obj) {
        return null;
    }

    @Override
    public Path update(Object obj) {
        return null;
    }

    @Override
    public void delete(Object obj) {
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

    }

}
