package cycling;

public abstract class Checkpoint {
    protected int stageID;
    protected int checkpointID;
    protected String location;
    protected CheckpointType type;
    protected Double length;

    public Checkpoint(int stageID, String location, Double length)
    {
        this.stageID = stageID;
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
    public abstract int getPoints();
}
