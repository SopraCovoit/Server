package model.dao;

import model.Location;
import model.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            if(this.statement.executeUpdate("INSERT INTO "+this.pathTable+
                    " ( "+this.latitude+","+this.longitude+","+this.workplaceId+","+this.departureHour+","+this.direction+","+this.userId+" ) VALUES ("+
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

    public ArrayList<Path> findAllUserPath(long id){
        try {
            ArrayList<Path> toReturn = new ArrayList<Path>() ;
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.pathTable+" WHERE "+this.userId+" = "+id);
            boolean rowExist = resultatQuery.first();
            while(rowExist){
                resultatQuery.first();
                toReturn.add(new Path(
                    new Location(resultatQuery.getDouble(this.latitude),resultatQuery.getDouble(this.longitude)),
                        resultatQuery.getString(this.departureHour),
                        resultatQuery.getInt(this.workplaceId),
                        resultatQuery.getString(this.direction),
                        resultatQuery.getInt(this.userId),
                        resultatQuery.getInt(this.id)));
                rowExist = resultatQuery.next();
            }
            return toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Path> findAllWorkPlacePath(long id){
        try {
            ArrayList<Path> toReturn = new ArrayList<Path>() ;
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.pathTable+" WHERE "+this.workplaceId+" = "+id);
            boolean rowExist = resultatQuery.first();
            while(rowExist){
                resultatQuery.first();
                toReturn.add(new Path(
                        new Location(resultatQuery.getDouble(this.latitude),resultatQuery.getDouble(this.longitude)),
                        resultatQuery.getString(this.departureHour),
                        resultatQuery.getInt(this.workplaceId),
                        resultatQuery.getString(this.direction),
                        resultatQuery.getInt(this.userId),
                        resultatQuery.getInt(this.id)));
                rowExist = resultatQuery.next();
            }
            return toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Path> findAllPath(long wpId,double latitude, double longitude, int distance){
        try {
            ArrayList<Path> allPath = new ArrayList<Path>() ;
            ArrayList<Path> toReturn = new ArrayList<Path>() ;

            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.pathTable);
            boolean rowExist = resultatQuery.first();
            while(rowExist){
                resultatQuery.first();
                allPath.add(new Path(
                        new Location(resultatQuery.getDouble(this.latitude),resultatQuery.getDouble(this.longitude)),
                        resultatQuery.getString(this.departureHour),
                        resultatQuery.getInt(this.workplaceId),
                        resultatQuery.getString(this.direction),
                        resultatQuery.getInt(this.userId),
                        resultatQuery.getInt(this.id)));
                rowExist = resultatQuery.next();
            }

            for(int i = 0;i<allPath.size();i++){
                if(allPath.get(i).getWorkPlaceId() == wpId && getDistance(latitude,longitude,allPath.get(i).getLocation().getLatitude(),allPath.get(i).getLocation().getLongitude())<=distance){
                    allPath.get(i).setDistance(getDistance(latitude,longitude,allPath.get(i).getLocation().getLatitude(),allPath.get(i).getLocation().getLongitude()));
                    toReturn.add(allPath.get(i));
                }
            }
            return toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double getDistance(double lat1, double long1,double lat2,double long2){

        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(long2-long1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = (R * c)*1000; // Distance in km
        return d;


    }

    private double deg2rad(double deg) {
        return deg * (3.14/180);
    }



}
