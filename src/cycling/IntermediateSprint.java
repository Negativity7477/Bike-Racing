package cycling;

public class IntermediateSprint extends Checkpoint
{
    private final CheckpointType type = CheckpointType.SPRINT;

    public IntermediateSprint(int stageID, String location, Double length)
    {
        super(stageID, location, length);
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        return 0;
    }
}