package model;

/**
 * Created by root on 19/12/14.
 */
public class User {

    private String name;
    private String surname;
    private int id;
    private String mail;
    private String phone;
    private boolean isDriver;
    private Location homeLocation;
    private int workplaceId;

    public User(String name, String surname, int id, String mail, String phone,
                boolean isDriver, Location homeLocation, int workplaceId) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.mail = mail;
        this.phone = phone;
        this.isDriver = isDriver;
        this.homeLocation = homeLocation;
        this.workplaceId = workplaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean isDriver) {
        this.isDriver = isDriver;
    }

    public Location getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Location homeLocation) {
        this.homeLocation = homeLocation;
    }

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }
}
