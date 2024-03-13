package cycling;

public abstract class Checkpoint {
    protected int checkpointID;
    protected String location;
    protected CheckpointType type;
    protected Double length;

    //Constructor for checkpoint, used in subclasses 
    //IntermediateSprint and CategorisedClimb
    
    public Checkpoint(int checkpointID, String location, Double length)
    {
        this.checkpointID = checkpointID;
        this.location = location;
        this.length = length;
    }

    public int getCheckpointID()
    {
        return checkpointID;
    }

    public CheckpointType getCheckpointType()
    {
        return type;
    }
    
    //An abstract method used to get points
    //based on the checkpoint 
    public abstract int getPoints();
}
