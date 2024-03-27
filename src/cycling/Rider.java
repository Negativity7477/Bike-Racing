package cycling;

import java.util.HashMap;
import java.time.LocalTime;

public class Rider {

    // Hash of stageIDs to 1 or more checkpoint finishing times
    private HashMap<Integer, LocalTime[]> checkpointTimeHash;
    private int riderID;
    private int teamID;
    private String name;
    private int yearOfBirth;
    private static int nextRiderID = 0;

    /**
     * Getter for riderID
     * 
     * @return A unique ID for the rider being queried
     */
    public int getRiderID() {
        return riderID;
    }

    /**
     * Adds a key of a stage ID and a value of all the finishing times of
     * checkpoints for a rider to checkpointHash
     * 
     * @param stageID The ID of the stage being added to the hashmap
     * @param results An array of all the finishing times of the checkpoints
     *                in the afformentioned stage for the rider being queried
     * 
     * @throws DuplicatedResultException If the stage entered has been entered before
     *                                   for the rider being queried. The data for the
     *                                   stage is replaced anyway.
     */
    public void addResults(int stageID, LocalTime... results) {
        checkpointTimeHash.put(stageID, results);
    }

    /**
     * Getter for a specfied stage's checkpoint times
     * 
     * @param stageID The unique ID of the stage requested
     * @return An array of the checkpoint finishing times for the specified stage
     * 
     * @throws IDNotRecognisedException If the stage requested is not in the hashmap or
     *                                  has no value associated
     */
    public LocalTime[] getStageResults(int stageID) throws IDNotRecognisedException {

        LocalTime[] stageResults = getStageResults(stageID);

        if (stageResults == null) {
            throw new IDNotRecognisedException("Invalid stageID");
        }

        return stageResults;
    }

    /**
     * Removes all recorded checkpoint times from a stage
     * 
     * @param stageID The unique ID of the stage being deleted
     * 
     * @throws IDNotRecognisedException If the stage requested is not in the hashmap
     *                                  or has no value associated
     */
    public void removeStageCheckpointTimes(int stageID) throws IDNotRecognisedException {

        if (checkpointTimeHash.remove(stageID) == null) {
            throw new IDNotRecognisedException("Invalid stageID");
        }
    }

    /**
     * Totals an array of LocalTime values
     * 
     * @param timeArray An array of localTime values to be summed
     * @return Totaled time of all times from timeArray
     */
    public LocalTime totalLocalTime(LocalTime... timeArray) {

        LocalTime totalTime = LocalTime.of(0, 0, 0);

        // Loops through hash and adds hours, minutes and seconds to a total
        for (LocalTime i : timeArray) {
            totalTime = totalTime.plusHours(i.getHour());
            totalTime = totalTime.plusMinutes(i.getMinute());
            totalTime = totalTime.plusSeconds(i.getSecond());
        }

        return totalTime;
    }

    private void addRiderToTeam(int teamID) throws IDNotRecognisedException  {

        try {
            Team teamObject = MiscHandling.getTeam(teamID);
            teamObject.addRider(this);

        // Rethrows exceptions
        } catch(IDNotRecognisedException e) {throw e;}
    }

    /**
     * Constructor
     * 
     * @param teamID Unique ID of the Team the rider belongs too
     * @param name Name of the rider
     * @param yearOfBirth Birth year of the rider
     */
    public Rider(int teamID, String name, int yearOfBirth) throws IDNotRecognisedException  {

        // Increments value with every new object so that each ID is unique
        this.riderID = nextRiderID++;
        this.teamID = teamID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.checkpointTimeHash = new HashMap<Integer, LocalTime[]>();

        try {
            this.addRiderToTeam(teamID);
        } catch(IDNotRecognisedException e) {throw e;}
            
    }

}
