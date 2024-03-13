package cycling;

public class CategorisedClimb extends Checkpoint
{
    private Double averageGradient;

    //Constructor
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

    //Overriding the abstract getPoints method based on what 
    //the categorise climb should give (for each category)
    @Override 
    public int getPoints()
    {
        
        return 0;
    }

    /*
     * This method is used in order to set checkpoint type as there are 
     * multiple different types of categorised climb.
     */
    private CheckpointType determineClimbType(Double averageGradient, Double length)
    {
        switch (averageGradient) {
            case averageGradient > 0 && averageGradient < :
                
                break;
        
            default:
                break;
        }
    }
}