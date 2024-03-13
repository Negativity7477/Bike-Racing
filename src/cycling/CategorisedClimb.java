package cycling;

public class CategorisedClimb extends Checkpoint
{
    private Double averageGradient;

    public CategorisedClimb(int stageID, String location, Double length)
    {
        super(stageID, location, length);
    }
    
    public Double getAverageGradient()
    {
        return averageGradient;
    }

    public void setType()
    {
        this.type = determineClimbType(this.averageGradient);
    }
    @Override
    public int getPoints()
    {
        
        return 0;
    }
    private CheckpointType determineClimbType(Double averageGradient, Double length)
    {
        switch (averageGradient) {
            case value:
                
                break;
        
            default:
                break;
        }
    }
}