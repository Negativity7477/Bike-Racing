package cycling;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class Checkpoint
{
    private int checkpointID;
    private int stageID;
    private int raceID;
    private Double location;
    private CheckpointType type;
    private Double length;
    private Double averageGradient;
    private static int nextCheckpointID;
    private HashMap<Integer, LocalTime> riderTimesHash;

    /**
     * 
     * @param location - location of checkpoint
     * @param length - length of checkpoint 
     * @param averageGradient - average gradient of the checkpoint
     * 
     * Constructor 
     */

    public Checkpoint(Double location, Double length, Double averageGradient, CheckpointType checkpointType, int stageID, int raceID) throws IDNotRecognisedException, InvalidLocationException

    {
        try{
            this.checkpointID = nextCheckpointID++;
            this.location = location;
            this.length = length;
            this.averageGradient = averageGradient;
            this.type = checkpointType;
            this.stageID = stageID;
            this.raceID = raceID;
            //We can get the race object for finding which stage this checkpoint
            //should be added to by using our static class that acts as a top level
            Race race = MiscHandling.getRace(raceID);
            //We need the stage object from the correct race fed into the constructor
            Stage stage = race.getStage(stageID);

            //Check that the location is not > stage length
            Double stageLength = stage.getStageLength();
            if (location > stageID) 
            {
                throw new InvalidLocationException("Location cannot be greater than stage length");
            }
            //Now we can add this checkpoint to the stage
            stage.addCheckpoint(this);
        }
        catch(Exception e) {throw e;}
    

    }

    /**
     * 
     * @param location
     * @param checkpointType
     * @param stageID
     * @param raceID
     * @throws IDNotRecognisedException
     * @throws InvalidLocationException
     * 
     * Another constructor for intermediate sprint, calls the main constructor but with 0 for length and gradient
     */
    public Checkpoint(Double location, CheckpointType checkpointType, int stageID, int raceID) throws IDNotRecognisedException, InvalidLocationException
    {
        this(location, 0.0, 0.0, checkpointType, stageID, raceID);
    }

    /**
     * 
     * @return - Unique identifier of checkpoint
     * Getter for indentifier
     */
    public int getCheckpointID()
    {
        return checkpointID;
    }

    /**
     * 
     * @return - length of checkpoint
     * Getter for checkpoint length
     */
    public Double getCheckpointLength()
    {
        return length;
    }


    /**
     * 
     * @return - type of checkpoint defined by CheckpointType
     * Getter for checkpoint type
     */
    public CheckpointType getCheckpointType()
    {
        return type;
    }

    /**
     * Getter for location
     * 
     * @return location of the checkpoint
     */
    public Double getCheckpointLocation() {
        return location;
    }

    /**
     * 
     * @return - average gradient of checkpoint
     * 
     * Getter for average gradient 
     */
    public Double getAverageGradient()
    {
        return averageGradient;
    }

    /**
     * 
     * @param riderID - unique identifier of the rider
     * @param riderTime - the time to add to the hash for this checkpoint
     * Adds the time the rider got in this checkpoint to the hashmap storing them
     */
    public void addRiderCheckpointTime(int riderID, LocalTime riderTime)
    {
        riderTimesHash.put(riderID, riderTime);
    }

    /**
     * 
     * @param riderID - unique identifier of the rider
     * @return - the time to complete the checkpoint for a given rider
     * 
     * returns the time the rider took to complete the checkpoint
     */
    public LocalTime getRiderCheckpointTime(int riderID)
    {
        LocalTime riderTime = riderTimesHash.get(riderID);
        return riderTime;
    }

    /**
     * 
     * @param riderID- unique identifier of the rider
     * 
     * removes the rider and their time's from the hash
     */
    public void removeRiderCheckpointTime(int riderID)
    {
        riderTimesHash.remove(riderID);
    }

    /**
     * Reset the static ID counter
     */
    public static void resetCheckpointIDCount()
    {
        nextCheckpointID = 0;
    }
}