package model.dao;

import model.Location;
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
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.pathTable+" WHERE "+this.id+" = "+id);
            return new Path(
                    new Location(resultatQuery.getDouble(this.latitude),resultatQuery.getDouble(this.longitude)),
                    resultatQuery.getString(this.departureHour),
                    resultatQuery.getInt(this.workplaceId),
                    resultatQuery.getString(this.direction),
                    resultatQuery.getInt(this.userId),
                    resultatQuery.getInt(this.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Path create(Object obj) {
        Path pathtoAdd = (Path)obj;
        try {
            if(this.statement.executeUpdate("INSERT INTO "+this.pathTable+" VALUES ("+
                    pathtoAdd.getId()+","+
                    pathtoAdd.getLocation().getLatitude()+","+
                    pathtoAdd.getLocation().getLongitude()+","+
                    pathtoAdd.getWorkPlaceId()+",'"+
                    pathtoAdd.getDepartureHour()+"','"+
                    pathtoAdd.getDirection()+"',"+
                    pathtoAdd.getUserId()+")") == 1){
                return pathtoAdd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Object obj) {
        Path pathToUpdate = (Path)obj;
        try {
            if( this.statement.executeUpdate("UPDATE "+this.pathTable+
                    " SET "+this.latitude+" = "+pathToUpdate.getLocation().getLatitude()+", "+
                    this.longitude +" = "+pathToUpdate.getLocation().getLongitude()+", "+
                    this.departureHour +" = '"+pathToUpdate.getDepartureHour()+"', "+
                    this.direction +" = '"+pathToUpdate.getDirection()+"', "+
                    this.userId +" = "+pathToUpdate.getUserId()+", "+
                    this.workplaceId+" = "+pathToUpdate.getWorkPlaceId()+" WHERE "+this.id+" = "+pathToUpdate.getId()) != 0){
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
        Path pathToDelete = (Path)obj;
        try {
            return this.statement.execute("DELETE FROM "+this+pathTable+" WHERE "+this.id+" = "+pathToDelete.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
