public class Passenger {
    private String type; //G for group; I for individual.
    private String groupName = null; //group name (n/a for individual passengers).
    private String name = null;
    private boolean occupied;

    /**
     * Passenger constructor.
     *
     * @param newName passenger name
     * @param newType booking type for passenger
     * @param gName   passenger group name
     */
    public Passenger(String newName, String newType, String gName) {
        this.name = newName;
        this.type = newType;
        this.groupName = gName;
        this.occupied = true;
    }


    /**
     * @return passenger name
     */
    public String getName() {
        return name;
    }


    /**
     * @return passenger group name
     */
    public String getGroup() {
        return groupName;
    }

    /**
     * Individual or Group for the passenger.
     *
     * @return the type Individual or Group
     */
    public String getType() {
        return type;
    }

}
