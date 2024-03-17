package cycling;

import java.util.HashMap;

public class Team {
    private int teamID;
    private String name; 
    private String description;
    // Hash of rider IDs to their respective rider object
    private HashMap<Integer, Rider> ridersHash = new HashMap<Integer, Rider>();
    private static int nextTeamID = 0;

    /**
     * Getter for TeamID
     * 
     * @return A unique ID for the team being queried
     */
    public int getTeamID() {
        return teamID;
    }

    // Gets an array of all the rider's IDs in the team
    public int[] getRiderIDArray() {

        int[] riderIDArray = new int[ridersHash.size()];
        int position = 0;

        // Loops through hash table and adds all rider IDs to an array
        for (Integer riderID : ridersHash.keySet()) {
            riderIDArray[position++] = riderID;
        }
        
        return riderIDArray;
    }

    // Adds Rider object as a value in ridersHash with a key of its Rider's ID
    public void addRider(Rider newRider) throws DuplicatedResultException
    {

        Integer riderID = newRider.getRiderID();

        if (ridersHash.put(riderID, newRider) != null) {
            throw new DuplicatedResultException("Bad ID");
        } 
    }

    // Removes Rider object reference which then deletes the object
    public void deleteRider(int riderID) {

        ridersHash.remove(riderID);
    }

    // Constructor
    public Team(String name, String description) {

        this.name = name;
        this.description = description;
        this.teamID = nextTeamID++;
    }
}
