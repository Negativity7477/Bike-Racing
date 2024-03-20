package cycling;

public class CategorisedClimb extends Checkpoint
{
    private Double averageGradient;

    /**
     * 
     * @param location - location of checkpoint
     * @param length - length of checkpoint 
     * @param averageGradient - average gradient of the checkpoint
     * 
     * Constructor 
     */
    public CategorisedClimb(String location, Double length, Double averageGradient, int stageID)
    {
        super(location, length);
        this.averageGradient = averageGradient;
        this.type = determineClimbType(averageGradient, length);
    }


    /**
     * 
     * @return - average gradient of checkpoint
     * 
     * Getter for average gradient 
     */
    public Double getAverageGradient()
    {
        return averageGradient;
    }

    
    /**
     * 
     * @return - length of checkpoint
     * Gets the length of the checkpoint
     */
    public  double getLength()
    {
        return length;
    }

    
    //Overriding the abstract getPoints method based on what 
    //the categorise climb should give (for each category)
    //Not ready yet
    @Override 
    public int getPoints()
    {

        return 0;
    }

    /*
     * @param - Average gradient of the checkpoint
     * @param - Length of the gradient 
     * This method is used in order to set checkpoint type as there are 
     * multiple different types of categorised climb.
     * 
     * @return - The type of climb this checkpoint is
     */
    public CheckpointType determineClimbType(Double averageGradient, Double length)
    {
        if (averageGradient < 6  && averageGradient > 4 && length < 2 || averageGradient < 4 && length < 5)
        {
            return CheckpointType.C4;
        }
        else if (averageGradient < 11  && averageGradient > 9  && length < 2 || averageGradient < 6 && length < 11)
        {
            return CheckpointType.C3;
        }
        else if (averageGradient < 9  && averageGradient > 7  && length < 6 || averageGradient < 5 && length < 16)
        {
            return CheckpointType.C2;
        }
        else if (averageGradient < 9  && averageGradient > 7  && length < 9 || averageGradient < 6 && length < 21)
        {
            return CheckpointType.C1;
        }
        else
        {
            return CheckpointType.HC;
        }
    }
}