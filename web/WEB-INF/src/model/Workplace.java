package model;

/**
 * Created by root on 19/12/14.
 */
public class Workplace {

    private Location location;
    private int workplaceId;
    private String name;

    public Workplace(Location location, int workplaceId, String name) {
        this.location = location;
        this.workplaceId = workplaceId;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(int workplaceId) {
        this.workplaceId = workplaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
