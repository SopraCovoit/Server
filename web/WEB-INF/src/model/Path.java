package model;

/**
 * Created by root on 19/12/14.
 */
public class Path {

    private Location location;
    private String departureHour;
    private int workPlaceId;
    private String direction;
    private User user;

    public Path(Location location, String departureHour, int workPlaceId, String direction, User user) {
        this.location = location;
        this.departureHour = departureHour;
        this.workPlaceId = workPlaceId;
        this.direction = direction;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
