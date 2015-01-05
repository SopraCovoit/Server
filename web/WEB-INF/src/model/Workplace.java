package model;

/**
 * Created by root on 19/12/14.
 */
public class Workplace {

    private Location location;
    private int id;
    private String name;

    public Workplace(Location location, int workplaceId, String name) {
        this.location = location;
        this.id = workplaceId;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setWorkplaceId(int workplaceId) {
        this.id = workplaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
