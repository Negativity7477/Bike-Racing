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
     * Getter for team name
     * 
     * @return Name of the team being queried
     */
    public String getTeamName() {
        return name;
    }

    /**
     * Getter for the number of riders in a team
     * 
     * @return number of riders in a team
     */
    public int getTeamSize() {
        return ridersHash.size();
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
    public void addRider(Rider newRider)
    {

        Integer riderID = newRider.getRiderID();

        ridersHash.put(riderID, newRider);
    
    }

    /**
     * Gets a rider object based on the ID provided
     * 
     * @param riderID ID of the rider wanted
     * @return An object representing the rider with the specified ID given
     */
    public Rider getRider(int riderID) {
        return ridersHash.get(riderID);
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
    public Team(String name, String description) throws IllegalNameException {

        this.name = name;
        this.description = description;
        this.teamID = nextTeamID++;
        this.ridersHash = new HashMap<Integer, Rider>();

        try {
        MiscHandling.addTeam(this);
        } catch(IllegalNameException e) {throw e;}
    }

    /**
     * Reset the static ID counter
     */
    public static void resetTeamIDCount()
    {
        nextTeamID = 0;
    }
}