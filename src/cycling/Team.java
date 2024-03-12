package cycling;

import java.util.HashMap;

public class Team {
    private int teamID;
    private String name;
    private String description;
    private HashMap<Integer, Rider> ridersHash = new HashMap<Integer, Rider>();
    private static int nextTeamID = 0;

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

    

    // Constructor
    public int Team(String name, String description) {

        this.name = name;
        this.description = description;
        this.teamID = nextTeamID++;

        return this.teamID;
    }
}
