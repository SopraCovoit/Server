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
    private int workplaceId;
    private String passWord;
    private String token;


    public User(String name, String surname, String mail, String phone,
                boolean isDriver, int workplaceId){
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phone = phone;
        this.isDriver = isDriver;
        this.workplaceId = workplaceId;
    }
    public User(String name, String surname, int id, String mail, String phone,
                boolean isDriver, int workplaceId,String passWord) {
        this.passWord = passWord;
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.mail = mail;
        this.phone = phone;
        this.isDriver = isDriver;
        this.workplaceId = workplaceId;

    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
