package cycling;
import java.time.LocalTime;

public class Checkpoint
{
    protected int checkpointID;
    protected String location;
    protected CheckpointType type;
    protected Double length;
    private Double averageGradient;
    private LocalTime startTime;
    private static int nextCheckpointID;

    /**
     * 
     * @param location - location of checkpoint
     * @param length - length of checkpoint 
     * @param averageGradient - average gradient of the checkpoint
     * 
     * Constructor 
     */
    public Checkpoint(String location, Double length, Double averageGradient, LocalTime startTime, int stageID, int raceID) throws IDNotRecognisedException
    {
        this.checkpointID = nextCheckpointID++;
        this.location = location;
        this.length = length;
        this.averageGradient = averageGradient;
        this.startTime = startTime;
        this.type = determineClimbType(averageGradient, length);

        //We can get the race object for finding which stage this checkpoint
        //should be added to by using our static class that acts as a top level
        Race race = MiscHandling.getRace(raceID);
        //We need the stage object from the correct race fed into the constructor
        Stage stage = race.getStage(stageID);
        //Now we can add this checkpoint to the stage
        stage.addCheckpoint(this);

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
     * @return - length of checkpoint
     * Gets the length of the checkpoint
     */
    public  double getLength()
    {
        return length;
    }


    /*
     * @param - Average gradient of the checkpoint
     * @param - Length of the gradient 
     * This method is used in order to set checkpoint type as there are 
     * multiple different types of categorised climb.
     * 
     * @return - The type of climb this checkpoint is
     */
    public CheckpointType determineClimbType(Double averageGradient, Double length)
    {
        if (averageGradient < 6  && averageGradient > 4 && length < 2 || averageGradient < 4 && length < 5)
        {
            return CheckpointType.C4;
        }
        else if (averageGradient < 11  && averageGradient > 9  && length < 2 || averageGradient < 6 && length < 11)
        {
            return CheckpointType.C3;
        }
        else if (averageGradient < 9  && averageGradient > 7  && length < 6 || averageGradient < 5 && length < 16)
        {
            return CheckpointType.C2;
        }
        else if (averageGradient < 9  && averageGradient > 7  && length < 9 || averageGradient < 6 && length < 21)
        {
            return CheckpointType.C1;
        }
        else
        {
            return CheckpointType.HC;
        }
    }
}