package cycling;

public abstract class Checkpoint {
    protected int checkpointID;
    protected String location;
    protected CheckpointType type;
    protected Double length;
    protected static int nextCheckpointID;

    /**
     * 
     * @param location - location of checkpoint
     * @param length - length of checkpoint
     * 
     * Constructor, inheritted by intermediateSprint and CategorisedClimb
     */
    public Checkpoint(String location, Double length)
    {
        this.checkpointID = nextCheckpointID++;
        this.location = location;
        this.length = length;
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
     * @return - type of checkpoint defined by CheckpointType
     * Getter for checkpoint type
     */
    public CheckpointType getCheckpointType()
    {
        return type;
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
    
    //An abstract method used to get points
    //based on the checkpoint 
    //Not implemented
    public abstract int getPoints();
}
