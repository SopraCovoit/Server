package model.dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by julescantegril on 19/12/2014.
 */

public class DAOUser extends DAO {

    Statement statement;

    public DAOUser(){
        super();
        try {
            statement = this.connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(long id) {
        User newUser = null;
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.userTable+" WHERE "+this.id+" = "+id);
            resultatQuery.first();
            newUser = new User(
                    resultatQuery.getString(this.name),
                    resultatQuery.getString(this.surname),
                    resultatQuery.getInt(this.id),
                    resultatQuery.getString(this.mail),
                    resultatQuery.getString(this.phone),
                    resultatQuery.getBoolean(this.isDriver),
                    resultatQuery.getInt(this.workplaceId),
                    resultatQuery.getString(this.passWord));
            System.out.println(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newUser;
    }

    public User find(String mail, String password) {
        User newUser = null;
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.userTable+" WHERE "+this.mail+" = "+mail+" AND "+this.passWord+" = "+password);
            resultatQuery.first();
            newUser = new User(
                    resultatQuery.getString(this.name),
                    resultatQuery.getString(this.surname),
                    resultatQuery.getInt(this.id),
                    resultatQuery.getString(this.mail),
                    resultatQuery.getString(this.phone),
                    resultatQuery.getBoolean(this.isDriver),
                    resultatQuery.getInt(this.workplaceId),
                    resultatQuery.getString(this.passWord));
            System.out.println(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newUser;
    }


    public ArrayList<User> findAll(){
        try {
            ArrayList<User> toReturn = new ArrayList<User>() ;
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.userTable);
            boolean rowExist = resultatQuery.first();
            while(rowExist){
                resultatQuery.first();
                toReturn.add(new User(
                        resultatQuery.getString(this.name),
                        resultatQuery.getString(this.surname),
                        resultatQuery.getInt(this.id),
                        resultatQuery.getString(this.mail),
                        resultatQuery.getString(this.phone),
                        resultatQuery.getBoolean(this.isDriver),
                        resultatQuery.getInt(this.workplaceId),
                        resultatQuery.getString(this.passWord)));
                rowExist = resultatQuery.next();
            }
            return toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User create(Object obj) {
        User userToAdd = (User)obj;
        try {
            if(this.statement.executeUpdate("INSERT INTO "+this.userTable+
                    " ( "+this.name+","+this.surname+","+this.mail+","+this.phone+","+this.isDriver+","+this.workplaceId+","+this.passWord+" ) VALUES ('"+
                    userToAdd.getName()+"', '"+
                    userToAdd.getSurname()+"',' "+
                    userToAdd.getMail()+"', '"+
                    userToAdd.getPhone()+"', "+
                    userToAdd.isDriver()+", "+
                    userToAdd.getWorkplaceId()+", '"+
                    userToAdd.getPassWord()+"')") == 1){
                return userToAdd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;//METTRE UNE ERREUR
    }

    @Override
    public boolean update(Object obj) {
        User userToUpdate = (User)obj;
        try {
            if( this.statement.executeUpdate("UPDATE "+this.userTable+
                    " SET "+this.name+" = '"+userToUpdate.getName()+"', "+
                    this.surname +" = '"+userToUpdate.getSurname()+"', "+
                    this.isDriver+" = "+userToUpdate.isDriver()+
                    this.workplaceId+" = "+userToUpdate.getWorkplaceId()+", "+
                    this.passWord+ "= "+userToUpdate.getPassWord()+" WHERE "+this.id+" = "+userToUpdate.getId()) != 0){
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
            User userToDelete = (User)obj;
        try {
            return this.statement.execute("DELETE FROM "+this.userTable+" WHERE "+this.id+" = "+userToDelete.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
