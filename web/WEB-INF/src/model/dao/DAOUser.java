package model.dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        try {
            ResultSet resultatQuery = this.statement.executeQuery("SELECT *"+" FROM "+this.userTable+" WHERE "+this.id+" = "+id);
            return new User(
                    resultatQuery.getString(this.name),
                    resultatQuery.getString(this.surname),
                    resultatQuery.getInt(this.id),
                    resultatQuery.getString(this.mail),
                    resultatQuery.getString(this.phone),
                    resultatQuery.getBoolean(this.isDriver),
                    resultatQuery.getInt(this.workplaceId),
                    resultatQuery.getString(this.passWord));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Object obj) {
        User userToAdd = (User)obj;
        try {
            return this.statement.execute("INSERT INTO "+this.userTable+" VALUES ("+
                    userToAdd.getName()+", "+
                    userToAdd.getSurname()+", "+
                    userToAdd.getId()+", "+
                    userToAdd.getMail()+", "+
                    userToAdd.getPhone()+", "+
                    userToAdd.isDriver()+", "+
                    userToAdd.getWorkplaceId()+", "+
                    userToAdd.getPassWord());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object obj) {
        User userToUpdate = (User)obj;
        try {
            if( this.statement.executeUpdate("UPDATE "+this.userTable+
                    " SET "+this.name+" = "+userToUpdate.getName()+", "+
                    this.surname +" = "+userToUpdate.getSurname()+", "+
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
            return this.statement.execute("DELETE "+this.userTable+" WHERE "+this.id+" = "+userToDelete.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}