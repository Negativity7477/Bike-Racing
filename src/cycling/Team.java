package cycling;

import java.util.HashMap;

public class Team {
    private int teamID;
    private String name; 
    private String description;
    // Hash of rider IDs to their respective rider object
    private HashMap<Integer, Rider> ridersHash;
    private static int nextTeamID = 0;

    /**
     * Getter for TeamID
     * 
     * @return A unique ID for the team being queried
     */
    public int getTeamID() {
        return teamID;
    }

    /**
     * Gets an array of all the rider's IDs in the team, 
     * If there are no riders than an empty array is returned
     * 
     * @return An array of the IDs of all the riders in the team being queried,
     *         if there are no riders then an empty array is returned
     */
    public int[] getRiderIDArray() {

        int[] riderIDArray = new int[ridersHash.size()];
        int position = 0;

        // Loops through hash table and adds all rider IDs to an array
        for (Integer riderID : ridersHash.keySet()) {
            riderIDArray[position++] = riderID;
        }
        
        return riderIDArray;
    }

    /**
     * Adds Rider object as a value in ridersHash with a key of its Rider's ID
     * 
     * @param newRider A Rider object to be added to the rider hashmap
     * 
     * @throws DuplicatedResultException If the rider being added has already been added
     */
    public void addRider(Rider newRider) throws DuplicatedResultException
    {

        Integer riderID = newRider.getRiderID();

        if (ridersHash.put(riderID, newRider) != null) {
            throw new DuplicatedResultException("This rider has already been added to this team");
        } 
    }

    /**
     * Removes Rider object reference which then deletes the object
     * 
     * @param riderID The ID of the rider to be removed from the rider hash
     * 
     * @throws IDNotRecognisedException If the rider being removed is not in rider hashmap
     */
    public void deleteRider(int riderID) throws IDNotRecognisedException {

        if (ridersHash.remove(riderID) == null) {
            throw new IDNotRecognisedException("This rider is not in this team");
        }
    }

    /**
     * Constructor
     * 
     * @param name A name for the team
     * @param description A description for the team
     */
    public Team(String name, String description) throws DuplicatedResultException {

        this.name = name;
        this.description = description;
        this.teamID = nextTeamID++;
        this.ridersHash = new HashMap<Integer, Rider>();

        try {
        MiscHandling.add(this);
        } catch(DuplicatedResultException e) {throw e;}
    }
}
