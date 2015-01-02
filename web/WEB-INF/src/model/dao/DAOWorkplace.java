package model.dao;

import model.Location;
import model.Workplace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class DAOWorkplace extends DAO {

    Statement statement;

    public DAOWorkplace(){
        super();
        try {
            statement = this.connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Workplace find(long id) {
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.workplaceTable+" WHERE "+this.id+" = "+id);
            return new Workplace(
                    new Location(resultatQuery.getLong(this.latitude),resultatQuery.getLong(this.longitude)),
                    resultatQuery.getInt(this.id),
                    resultatQuery.getString(this.name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Object obj) {
        Workplace workplacetoAdd = (Workplace)obj;
        try {
            return this.statement.execute("INSERT INTO "+this.workplaceTable+" VALUES ("+
                    workplacetoAdd.getWorkplaceId()+","+
                    workplacetoAdd.getName()+","+
                    workplacetoAdd.getLocation().getLatitude()+","+
                    workplacetoAdd.getLocation().getLongitude());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object obj) {
        Workplace workplaceToUpdate = (Workplace)obj;
        try {
            if( this.statement.executeUpdate("UPDATE "+this.workplaceTable+
                    " SET "+this.name+" = "+workplaceToUpdate.getName()+", "+
                    this.latitude +" = "+workplaceToUpdate.getLocation().getLatitude()+", "+
                    this.longitude+" = "+workplaceToUpdate.getLocation().getLongitude()+" WHERE "+this.id+" = "+workplaceToUpdate.getWorkplaceId()) != 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        Workplace workplacetoAdd = (Workplace)obj;
        try {
            return this.statement.execute("DELETE " + this.workplaceTable + " WHERE " + this.id + " = " + workplacetoAdd.getWorkplaceId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
