package model;

/**
 * Created by julescantegril on 19/12/2014.
 */
public class Location {

    long latitude;
    long longitude;

    public Location(long latitude, long longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }


}
