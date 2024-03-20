package cycling;

import java.util.HashMap;

// Functions as a static class
public class MiscHandling {
    
    // Hash of team IDs to their respective team object
    private static HashMap<Integer, Team> teamsHash = new HashMap<Integer, Team>();
    // Hash of race IDs to their respective race object
    private static HashMap<Integer, Race> racesHash = new HashMap<Integer, Race>();

    /**
     * Adds a team object linked to its ID into a team hashmap
     * 
     * @param teamObject The object to be added to the array
     * 
     * @throws DuplicatedResultException If the object has already been added
     */
    public static void add(Team teamObject) throws DuplicatedResultException {

        Integer teamID = teamObject.getTeamID();

        if (teamsHash.put(teamID, teamObject) != null) {
            throw new DuplicatedResultException("This team has already been added");
        }
    }

    /**
     * Adds a race object linked to its ID into a race hashmap
     * 
     * @param raceObject The object to be added to the array
     * 
     * @throws DuplicatedResultException If the object has already been added
     */
    public static void add(Race raceObject) throws DuplicatedResultException{

        Integer raceID = raceObject.getRaceID();

        if (racesHash.put(raceID, raceObject) != null) {
            throw new DuplicatedResultException("This race has already been added");
        }
    }

    /**
     * Removes a team from the hashmap
     * 
     * @param teamID A unique ID for the team being deleted
     * 
     * @throws IDNotRecognisedException If the team requested is not in the hashmap
     */
    public static void removeTeam(int teamID) throws IDNotRecognisedException {

        if (teamsHash.remove(teamID) == null) {
            throw new IDNotRecognisedException("The team requested is not in the hashmap");
        }
    }

    /**
     * Removes a race from the hashmap
     * 
     * @param raceID A unique ID for the race being deleted
     * 
     * @throws IDNotRecognisedException If the race requested is not in the hashmap
     */
    public static void removeRace(int raceID) throws IDNotRecognisedException {

        if (racesHash.remove(raceID) == null) {
            throw new IDNotRecognisedException("The race requested is not in the hashmap");
        }
    }

    /**
     * Getter for a team object using its unique ID
     * 
     * @param teamID A unique ID linked to the team 
     * @return Team object that is linked to the ID given
     * 
     * @throws IDNotRecognisedException If the ID is not in the team hashmap
     */
    public static Team getTeam(int teamID) throws IDNotRecognisedException {

        Team teamObject = teamsHash.get(teamID);
        if (teamObject == null) {
            throw new IDNotRecognisedException("The team requested is not in the hashmap");
        }
        return teamObject;
    }

    /**
     * Getter for a race object using its unique ID
     * 
     * @param raceID A unique ID linked to the race
     * @return Race object that is linked to the ID given
     * 
     * @throws IDNotRecognisedException If the ID is not in the race hashmap
     */
    public static Race getRace(int raceID) throws IDNotRecognisedException {

        Race raceObject = racesHash.get(raceID);
        if (raceObject == null) {
            throw new IDNotRecognisedException("The race requested is not in the hashmap");
        }
        return raceObject;
    }

    /**
     * Gets an array of all the team IDs present in the hashmap
     * 
     * @return An array of all the IDs of the teams in the team hashmap
     */
    public static int[] getTeamIDs() {

        int[] teamIDArray = new int[teamsHash.size()];
        int position = 0;

        // Loops through hash table and adds all rider IDs to an array
        for (Integer teamID : teamsHash.keySet()) {
            teamIDArray[position++] = teamID;
        }
        
        return teamIDArray;
    }

    /**
     * Gets an array of all the race IDs present in the hashmap
     * 
     * @return An array of all the IDs of the races in the race hashmap
     */
    private static int[] getRaceIDs() {

        int[] raceIDArray = new int[racesHash.size()];
        int position = 0;

        // Loops through hash table and adds all rider IDs to an array
        for (Integer raceID : racesHash.keySet()) {
            raceIDArray[position++] = raceID;
        }
        
        return raceIDArray;
    }    

    /**
     * Gets the race ID from the race name
     * 
     * @param name Name of a race that has already been added to the class
     * @return ID of the race that matches the name 
     * 
     * @throws InvalidNameException
     */
    public static int getRaceIDFromName(String name) throws InvalidNameException {
        
        int[] raceIDArray = getRaceIDs();
        Race raceObject;

        // Loops through all race IDs and selects their respective object
        for (int raceID : raceIDArray) {
            raceObject = racesHash.get(raceID);

            // Checks for a name match
            if (raceObject.getName() == name) {
                return raceID;
            }
        }

        // If no matches were found then this error is raised
        throw new InvalidNameException("Given name is not recognised");
    }
}