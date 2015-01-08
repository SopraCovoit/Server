package model.dao;

import model.Location;
import model.Workplace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                    new Location(resultatQuery.getDouble(this.latitude),resultatQuery.getDouble(this.longitude)),
                    resultatQuery.getInt(this.id),
                    resultatQuery.getString(this.name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Workplace> findAll() {
        ArrayList<Workplace> toReturn = new ArrayList<Workplace>() ;
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.workplaceTable);
            boolean rowExist = resultatQuery.first();
            while(rowExist){
                toReturn.add(new Workplace(
                        new Location(resultatQuery.getDouble(this.latitude), resultatQuery.getDouble(this.longitude)),
                        resultatQuery.getInt(this.id),
                        resultatQuery.getString(this.name)));
                rowExist = resultatQuery.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toReturn;
    }


    @Override
    public Workplace create(Object obj) {
        Workplace workplacetoAdd = (Workplace)obj;
        try {
            if(this.statement.execute("INSERT INTO "+this.workplaceTable+" VALUES ("+
                    workplacetoAdd.getId()+","+
                    workplacetoAdd.getName()+","+
                    workplacetoAdd.getLocation().getLatitude()+","+
                    workplacetoAdd.getLocation().getLongitude())){
                return workplacetoAdd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Object obj) {
        Workplace workplaceToUpdate = (Workplace)obj;
        try {
            if( this.statement.executeUpdate("UPDATE "+this.workplaceTable+
                    " SET "+this.name+" = "+workplaceToUpdate.getName()+", "+
                    this.latitude +" = "+workplaceToUpdate.getLocation().getLatitude()+", "+
                    this.longitude+" = "+workplaceToUpdate.getLocation().getLongitude()+" WHERE "+this.id+" = "+workplaceToUpdate.getId()) != 0){
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
            return this.statement.execute("DELETE " + this.workplaceTable + " WHERE " + this.id + " = " + workplacetoAdd.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
