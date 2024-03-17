package cycling;

public abstract class Checkpoint {
    protected int checkpointID;
    protected String location;
    protected CheckpointType type;
    protected Double length;
    protected static int nextCheckpointID;

    //Constructor for checkpoint, used in subclasses 
    //IntermediateSprint and CategorisedClimb
    
    public Checkpoint(String location, Double length)
    {
        this.checkpointID = nextCheckpointID++;
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

    public Double getCheckpointLength()
    {
        return length;
    }
    
    //An abstract method used to get points
    //based on the checkpoint 
    public abstract int getPoints();
}
