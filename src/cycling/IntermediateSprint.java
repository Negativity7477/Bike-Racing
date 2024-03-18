package cycling;

public class IntermediateSprint extends Checkpoint
{

    /**
     * 
     * @param location - Location of the checkpoint
     * @param length - Length of the checkpoint
     * Constructor, inheritted from Checkpoint, type is constant
     */
    public IntermediateSprint(String location, Double length)
    {
        super(location, length);
        this.type = CheckpointType.SPRINT;
    }

    //Overriding the abstract getPoints method based on what 
    //the intermediate sprint should give 
    //NOT IMPLEMENTED
    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        return 0;
    }
}