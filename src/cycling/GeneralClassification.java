package cycling;
import java.time.LocalTime;


public class GeneralClassification {
    
    protected int raceID;
    protected int[] riderArray;
    protected LocalTime[] rankedArray;

    /**
     * Constructor
     * 
     * @param raceID A unique ID for a race being requested
     */
    public GeneralClassification(int raceID)
    {
        this.raceID = raceID;
        this.riderArray = MiscHandling.getRiderIDArray();
        this.rankedArray = new LocalTime[riderArray.length];
    }

    /**
     * Sets up the classification to have all the times of all of the riders in the race
     */
    public void setRiderTimes()
    {
        try {
            Race raceObject = MiscHandling.getRace(raceID);
            int index = 0;
            for (int riderID : riderArray) {
                rankedArray[index++] = raceObject.getRiderRaceTime(riderID);
        }

        } catch (IDNotRecognisedException e) {;} //This error should not appear so it is not rethrown
    }

    public int[] getRankedRiderArray() {
        return riderArray;
    }

    /**
     * This function is a sorting algorithm (bubble sort) to sort riderArray and rankedArray,
     * Since both array are connected they must be sorted together
     */
    public void rankRiders()
    {
        long[] secondsArray = new long[riderArray.length];

        int tempID;
        LocalTime tempTime;
        long tempSeconds;

        // Creates a matching array to rankedArray but in nanoseconds so it can be more easily processed
        for (int i = 0; i < riderArray.length; i++) {
            secondsArray[i] = MiscHandling.localTimeToLong(rankedArray[i]);
        }

        // Bubble sort for 3 arrays at once - based off of seconds
        for (int i = 0; i < riderArray.length-1; i++) {
            for (int k = 0; k < riderArray.length-2; i++) {

                if (secondsArray[k] < secondsArray[k+1]) {

                    tempID = riderArray[k+1];
                    tempTime = rankedArray[k+1];
                    tempSeconds = secondsArray[k+1];

                    riderArray[k+1] = riderArray[k];
                    rankedArray[k+1] = rankedArray[k];
                    secondsArray[k+1] = secondsArray[k];

                    riderArray[k] = tempID;
                    rankedArray[k] = tempTime;
                    secondsArray[k] = tempSeconds;
                }
            }
        }
    }
    
    /**
     * this function adjusts the riders time, so they have the same time if they are less
     * then a second apart
     * This has to be done through converting to nanoseconds 
     */
    public void adjustTimes()
    {
        long[] secondsArray = new long[riderArray.length];
        // Creates a matching array to rankedArray but in nanoseconds so it can be more easily processed
        for (int i = 0; i < riderArray.length; i++) {
            secondsArray[i] = MiscHandling.localTimeToLong(rankedArray[i]);
        }

        // Loops through time arrays to adjust all the times
        for (int i = 0; i < riderArray.length-2; i++) {

            // Checks if the difference is less than to a second
            if (secondsArray[i+1] - secondsArray[i] < 1000000000) {
                secondsArray[i+1] = secondsArray[i];
                rankedArray[i+1] = rankedArray[i];
            }
        }
    }

    /**
     * 
     * @param riderID - Unique rider identifier 
     * @return - The LocalTime for the rider
     * @throws IDNotRecognisedException
     * 
     * this method returns the adjusted time for a rider
     */
    public LocalTime getAdjustedTimes(int riderID) throws IDNotRecognisedException
    {
        int counter = 0;
        for (int riderIDQueried : riderArray) {   
            if (riderID == riderIDQueried) {
                return rankedArray[counter];
            }
            counter++;
        }
        throw new IDNotRecognisedException("ID was not found");
    }
}