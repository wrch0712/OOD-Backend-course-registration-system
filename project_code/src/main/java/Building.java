/*
The Building class holds several attribute: buildingId, buildingName, address, postCode
It is used in Room, Course class
The getter methods in Building class help to get building information easily
in a course instance and in student's schedule
 */

public class Building {
    private int buildingId;
    private String buildingName;
    private String address;
    private String postCode;

    public Building(int buildingId, String buildingName, String address, String postCode) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.address = address;
        this.postCode = postCode;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }
}
