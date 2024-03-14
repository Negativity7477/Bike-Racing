package cycling;

public class IntermediateSprint extends Checkpoint
{

    //Constructor for IntermediateSprint
    public IntermediateSprint(String location, Double length)
    {
        super(location, length);
        this.type = CheckpointType.SPRINT;
    }

    //Overriding the abstract getPoints method based on what 
    //the intermediate sprint should give 
    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        return 0;
    }
}