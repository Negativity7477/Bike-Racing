package cycling;

public class IntermediateSprint extends Checkpoint
{
    private final CheckpointType type = CheckpointType.SPRINT; 
    //Type will always be SPRINT

    //Constructor for IntermediateSprint
    public IntermediateSprint(int stageID, String location, Double length)
    {
        super(stageID, location, length);
    }

    //Overriding the abstract getPoints method based on what 
    //the intermediate sprint should give 
    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        return 0;
    }
}