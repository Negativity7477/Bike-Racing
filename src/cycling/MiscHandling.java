package cycling;

import java.time.LocalTime;
import java.util.HashMap;


// Functions as a static class
public class MiscHandling{
    
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
    public static void addTeam(Team teamObject) throws IllegalNameException {

        Integer teamID = teamObject.getTeamID();

        String teamName;
        String teamNameQuery;

        // Checks if the race name given has been used before
        teamName = teamObject.getTeamName();
        for (Team teamObjectQuery : teamsHash.values()) {
            teamNameQuery = teamObjectQuery.getTeamName();

            if (teamName.equals(teamNameQuery)) {
                throw new IllegalNameException("The name given has been used for a team before");
            }
        }
        // Duplicate objects could be put into the hashtable however the logic
        // linking team ID and the object means no problem would be caused
        teamsHash.put(teamID, teamObject);
    }

    /**
     * Adds a race object linked to its ID into a race hashmap
     * 
     * @param raceObject The object to be added to the array
     * 
     * @throws IllegalNameException If name of the race has been used before
     */
    public static void addRace(Race raceObject) throws IllegalNameException {

        Integer raceID = raceObject.getRaceID();

        String raceName;
        String raceNameQuery;

        // Checks if the race name given has been used before
        raceName = raceObject.getRaceName();
        for (Race raceObjectQuery : racesHash.values()) {
            raceNameQuery = raceObjectQuery.getRaceName();

            if (raceName.equals(raceNameQuery)) {
                throw new IllegalNameException("The name given has been used for a race before");
            }
        }
        // Duplicate objects could be put into the hashtable however the logic
        // linking race ID and the object means no problem would be caused
        racesHash.put(raceID, raceObject);
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
            if (raceObject.getRaceName() == name) {
                return raceID;
            }
        }

        // If no matches were found then this error is raised
        throw new InvalidNameException("Given name is not recognised");
    }
    
    /**
     * Finds the race ID of a stage using its stage ID
     * 
     * @param stageID ID of the stage being queried
     * @return ID of the race that the stage belongs to
     * 
     * @throws IDNotRecognisedException If the stage ID is not present in the program
     */
    public static int getRaceIDFromStageID(int stageID) throws IDNotRecognisedException {

        // Loops through all races and the stage IDs that they contain
        for (Race raceObject : racesHash.values()) {
            for (int queriedStageID : raceObject.getRaceStages()) {

                if (queriedStageID == stageID) {
                    return raceObject.getRaceID();
                }
            }
        }

        throw new IDNotRecognisedException("stageID given is not recognised");
    }

    /**
     * Finds the race ID and stage ID of a checkpoint using its checkpoint ID
     * 
     * @param checkpointID ID of the checkpoint being queried
     * @return An array of IDs in the format [race ID, stage ID] where race ID
     *         and stage ID belong to the race that contains the stage that
     *         contains checkpoint queried
     * 
     * @throws IDNotRecognisedException If 
     */
    public static int[] getRaceStageIDFromCheckpointID(int checkpointID) throws IDNotRecognisedException {
        
        Stage stageObject;
        int[] IDArray = new int[2];

        // Loops through all races, their stages and their checkpoints
        for (Race raceObject : racesHash.values()) {
            for (int stageID : raceObject.getRaceStages()) {
                stageObject = raceObject.getStage(stageID);
                for (int queriedCheckpointID : stageObject.getStageCheckpoints()) {

                    // When a matching checkpoint ID is found, the respective stage and race ID is returned
                    if (queriedCheckpointID == checkpointID) {
                        IDArray[0] = raceObject.getRaceID();
                        IDArray[1] = stageID;
                        return IDArray;
                    }
                }
            }
        }
        throw new IDNotRecognisedException("checkpoitnID given is not recognised");
    }

    /**
     * Converts localTime to a long in nanoseconds
     * 
     * @param timeObject the time to be converted
     * @return represents the time in the parameter but in nanoseconds
     */
    public static long localTimeToLong(LocalTime timeObject) {

        long nanoseconds = 0L;

        nanoseconds += timeObject.getHour();
        nanoseconds *= 60;
        nanoseconds += timeObject.getMinute();
        nanoseconds *= 60;
        nanoseconds += timeObject.getSecond();
        nanoseconds *= 1000000000L;
        nanoseconds += timeObject.getNano();

        return nanoseconds;
    }

    /**
     * Converts nanoseconds to hours, minutes, seconds and nanoseconds in the form of an object
     * 
     * @param nanoseconds the nanoseconds to be converted
     * @return object that represents the nanoseconds
     */
    public static LocalTime longToLocalTime(long nanoseconds) {

        LocalTime totalTime = LocalTime.of(0, 0, 0, 0);
        long modulo;

        modulo = nanoseconds / 3600000000000L;
        totalTime = totalTime.plusHours(modulo);
        nanoseconds -= modulo * 3600000000000L;

        modulo = nanoseconds / 60000000000L;
        totalTime = totalTime.plusMinutes(modulo);
        nanoseconds -= modulo * 60000000000L;

        modulo = nanoseconds / 1000000000L;
        totalTime = totalTime.plusSeconds(modulo);
        nanoseconds -= modulo * 1000000000L;

        totalTime = totalTime.plusNanos(nanoseconds);

        return totalTime;
    }

    /**
     * Getter for an array of all the riderIDs in the program
     * @return
     */
    public static Rider[] getRiderArray() {

        int numRiders = 0;
        for (Team teamObject : teamsHash.values()) {
            numRiders += teamObject.getTeamSize();
        }

        Rider[] riderArray = new Rider[numRiders];
        int counter = 0;
        for (Team teamObject : teamsHash.values()) {
            for (int riderID : teamObject.getRiderIDArray()) {
                riderArray[counter] = teamObject.getRider(riderID);
                counter++;
            }
        }

        return riderArray;
    }

    /**
     * Finds the sum of times as a time
     * 
     * @param timesToTotal Array of localTimes to be summed
     * @return the total time as one time
     */
    public static LocalTime totalTimes(LocalTime[] timesToTotal) {

        long totalNanoTime = 0;
        LocalTime totalTime;

        // Converts and totals time in a long format
        for (LocalTime timeObject : timesToTotal) {
            totalNanoTime += localTimeToLong(timeObject);
        }

        // Converts back to LocalTime object
        totalTime = longToLocalTime(totalNanoTime);

        return totalTime;
    }

    
}