package cycling;

import java.util.HashMap;
import java.time.LocalTime;

public class Rider {

    private int riderID;
    private int teamID;
    private String name;
    private int yearOfBirth;
    // Hash of stageIDs to 1 or more checkpoint finishing times
    private HashMap<Integer, LocalTime[]> checkpointTimeHash = new HashMap<Integer, LocalTime[]>();
    private static int nextRiderID = 0;

    // Getter for riderID
    public int getRiderID() {
        return riderID;
    }

    // Adds a key of a stage ID and a value of all the finishing times of checkpoints for a rider to checkpointHash
    public void addResults(int stageID, LocalTime... results) {
        checkpointTimeHash.put(stageID, results);
    }

    // Getter for a specfied stage's checkpoint times
    public LocalTime[] getStageResults(int stageID) {
        return checkpointTimeHash.get(stageID);
    }

    // Removes all recorded checkpoint times from a stage
    public void removeStageCheckpointTimes(int stageID) throws IDNotRecognisedException {
        if (checkpointTimeHash.remove(stageID) == null) {
            throw new IDNotRecognisedException("Invalid stageID");
        }
    }

    // Totals an array of LocalTime values
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

    // Constructor
    public Rider(int teamID, String name, int yearOfBirth) {

        this.riderID = nextRiderID++;
        this.teamID = teamID;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

}
