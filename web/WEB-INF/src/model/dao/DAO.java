package model.dao;

import java.sql.Connection;

/**
 * Created by julescantegril on 19/12/2014.
 */
public abstract class DAO<T> {

    public Connection connect = JDBCConnector.getInstance();

    /**
     * Name of every column we can have
     */

    protected String id = "id";
    protected String name = "name";
    protected String surname = "surname";
    protected String mail = "mail";
    protected String phone = "phone";
    protected String isDriver = "isDriver";
    protected String latitude = "lat";
    protected String longitude = "lng";
    protected String workplaceId = "workplaceId";
    protected String passWord = "passWord";
    protected String departureHour = "departureHour";

    //protected String direction = "direction";

    protected String userMail = "userMail";
    protected String error = "error";
    protected String type = "type";
    protected String message = "message";

    /**
     * Name of every table
     */

    protected String userTable = "userTable";
    protected String pathTable = "pathTable";
    protected String workplaceTable = "workplaceTable";

    protected String userId = "workplaceTable";
    protected String direction = "workplaceTable";


    /**
     * Permet de récupérer un objet via son ID
     * @param id
     * @return
     */
    public abstract T find(long id);

    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj
     */
    public abstract boolean create(T obj);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base
     * @param obj
     */
    public abstract boolean update(T obj);

    /**
     * Permet la suppression d'une entrée de la base
     * @param obj
     */
    public abstract boolean delete(T obj);
}
