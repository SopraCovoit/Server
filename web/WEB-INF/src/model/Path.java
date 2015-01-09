package model;

/**
 * Created by root on 19/12/14.
 */
public class Path {

    private Location location;
    private String departureHour;
    private int workPlaceId;
    private String direction;
    private int userId;
    private int id;
    private double distance;



    public Path(Location location, String departureHour, int workPlaceId, String direction, int userId,int id) {
        this.location = location;
        this.departureHour = departureHour;
        this.workPlaceId = workPlaceId;
        this.direction = direction;
        this.userId = userId;
        this.id = id;

    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDepartureHour() {
        return departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public int getWorkPlaceId() {
        return workPlaceId;
    }

    public void setWorkPlaceId(int workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
