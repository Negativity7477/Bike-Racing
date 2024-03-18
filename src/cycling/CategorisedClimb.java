package cycling;

public class CategorisedClimb extends Checkpoint
{
    private Double averageGradient;

    //Constructor
    public CategorisedClimb(String location, Double length, Double averageGradient)
    {
        super(location, length);
        this.averageGradient = averageGradient;
        this.type = determineClimbType(averageGradient, length);
    }
    
    public Double getAverageGradient()
    {
        return averageGradient;
    }

    /**
    *This function is kinda obselete
    */
    public void setType()
    {
        this.type = determineClimbType(averageGradient, length);
    }
    
    public  double getLength()
    {
        return length;
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