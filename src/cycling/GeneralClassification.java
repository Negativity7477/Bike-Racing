package cycling;
import java.time.LocalTime;


public class GeneralClassification {
    
    protected int raceID;
    protected int[] riderArray;
    protected LocalTime[] rankedArray;

    public GeneralClassification(int raceID)
    {
        this.raceID = raceID;
        this.riderArray = null; // MiscHandling.getRiderArray();
    }

    public void setRiderTimes()
    {
        //left blank for now
    }


    /**
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
    
    public void adjustTimes()
    {
        int[] rankedArrayInSeconds = localTimeToSeconds();
        int[] placeHolderArray = rankedArrayInSeconds;

        
        for(int times : rankedArrayInSeconds)
        {
            if(placeHolderArray[times] - placeHolderArray[++times] > -1)
            {
                rankedArrayInSeconds[++times] = rankedArrayInSeconds[times];
            }
        }
        rankedArray = secondsToLocalTime(rankedArrayInSeconds);
    }

    /**
     * 
     * @return - the ranked array times converted to seconds
     * 
     * Converts every element in the rankedArray array into seconds to be
     * adjusted easily
     */
    private int[] localTimeToSeconds()
    {
        //Convert every element of the array into seconds 
        //add those seconds to a new integer array
        int seconds;
        int position = 0;
        int[] convertedRankedArray = new int[rankedArray.length];
        for(LocalTime time : rankedArray)
        {
            seconds = rankedArray[position].toSecondOfDay();
            convertedRankedArray[position] = seconds;
        }
        return convertedRankedArray;
    }

    /**
     * 
     * @param array - an array that is in seconds
     * @return - the array converted back to localtime
     * 
     * Converts each element in the array to localTime so
     * the formatting is correct
     */
    private LocalTime[] secondsToLocalTime(int[] array)
    {
        //Convert every element of the array into LocalTime 
        //add those seconds to a new integer array
        LocalTime localTime;
        int position = 0;
        LocalTime[] convertedRankedArray = new LocalTime[array.length];
        for(int seconds : array)
        {
            localTime = secondsToTimeConversion(array[position]);
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
    private static LocalTime secondsToTimeConversion(int seconds)
    {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return LocalTime.of(hours, minutes, secs);
    }
}
