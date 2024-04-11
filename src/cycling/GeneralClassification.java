package cycling;
import java.time.LocalTime;


public class GeneralClassification {
    
    protected int raceID;
    protected int[] riderArray;
    protected LocalTime[] rankedArray;

    public GeneralClassification(int raceID)
    {
        this.raceID = raceID;
        this.riderArray = MiscHandling.getRiderIDArray();
        this.rankedArray = new LocalTime[riderArray.length];
    }

    public void setRiderTimes() throws IDNotRecognisedException
    {
        Race raceObject = MiscHandling.getRace(raceID);
        int index = 0;
        for (int riderID : riderArray) {
            rankedArray[index++] = raceObject.getRiderRaceTime(riderID);
        }
    }

    public int[] getRankedRiderArray() {
        return riderArray;
    }

    /**
     * THIS IS NOT WORKING HOW I WOULD LIEK IT TO
     * 
     * @return - An array of riderTimes from the fastest time to the slowest
     * 
     * This function is a sorting algorithm (bubble sort) to return a sorted array
     */
    public void rankRiders()
    {
        LocalTime temp;
        for (int i = 0; i < rankedArray.length-1; i++)
        {
            //Cant directly compare localtimes, so we use a function 
            //that returns a negative value if this element is less than
            if(rankedArray[i].compareTo(rankedArray[i+1]) < 0)
            {
                temp=rankedArray[i];
                rankedArray[i]=rankedArray[i+1];
                rankedArray[i+1]= temp;
                i=-1;
            }
        }
    }
    
    /**
     * this function adjusts the riders time, so they have the same time if they are less
     * then a second apart
     * This has to be done through converting to nanoseconds 
     * and then to miliseconds to check the placement
     */
    public void adjustTimes()
    {
        //have to convert to nano seconds later which is stored as a long
        long[] rankedArrayInMiliSeconds = localTimeToMiliSeconds();
        long[] placeHolderArray = rankedArrayInMiliSeconds;

        //For all the times in the rankedArray (stored as miliseconds)
        //we check if each rider is less than a second apart 
        //and if they are we change them in the array
        for(long times : rankedArrayInMiliSeconds)
        {
            if(placeHolderArray[(int) times] - placeHolderArray[(int) ++times] >= -100)
            {
                rankedArrayInMiliSeconds[(int) ++times] = rankedArrayInMiliSeconds[(int) times];
            }
        }
        //Convert from miliSeconds to LocalTime
        rankedArray = miliSecondsToLocalTime(rankedArrayInMiliSeconds);
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

    /**
     * 
     * @return - the ranked array times converted to seconds
     * 
     * Converts every element in the rankedArray array into seconds to be
     * adjusted easily
     */
    private long[] localTimeToMiliSeconds()
    {
        //Convert every element of the array into seconds 
        //add those seconds to a new integer array
        long nanoSeconds;
        int position = 0;
        long[] convertedRankedArray = new long[rankedArray.length];
        for(LocalTime time : rankedArray)
        {
            nanoSeconds = rankedArray[position].toNanoOfDay();
            //we store as miliseconds in order to adjust times
            convertedRankedArray[position] = nanoSeconds / 100000;
        }
        return convertedRankedArray;
    }

    /**
     * 
     * @param array - an array that is in miliseconds
     * @return - the array converted back to localtime
     * 
     * Converts each element in the array to localTime so
     * the formatting is correct
     */
    private LocalTime[] miliSecondsToLocalTime(long[] array)
    {
        //Convert every element of the array into LocalTime 
        //add those seconds to a new integer array
        LocalTime localTime;
        int position = 0;
        LocalTime[] convertedRankedArray = new LocalTime[array.length];
        for(long seconds : array)
        {
            localTime = miliSecondsToTimeConversion(array[position]);
            convertedRankedArray[position] = localTime;
        }
        return convertedRankedArray;
    }
    
    /**
     * 
     * @param seconds - Each element in the array in the method above
     * this represents seconds 
     * @return - a formatted dateTime
     * 
     * A method to abstract converting to the correct date time values
     */
    private static LocalTime miliSecondsToTimeConversion(long miliseconds)
    {
        int seconds = (int) miliseconds / 100;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return LocalTime.of(hours, minutes, secs);
    }
}
