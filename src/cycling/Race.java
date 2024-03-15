package cycling;
import java.util.HashMap;
public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private HashMap<Integer, Stage> stageIDHash = new HashMap<Integer, Stage>();

    /**
     * 
     * @param name - Name of the race
     * @param description - Description of the race
     * 
     * Constructor for race
     */
    public Race(String name, String description)
    {
        this.name = name;
        this.description = description;
    }


    /**
     * 
     * @return - RaceID
     * 
     * Allows us to get the unique identifier for this race
     */
    public int getRaceID()
    {
        return raceID;
    }

    /**
     * 
     * @return - Number of stages making up the race 
     * 
     * Allows us to see the amount of stages in this race
     */
    public int getNumStage()
    {
        return numOfStages;
    }


    /**
     * 
     * @return - Total distance of the race
     * Allows us to see the total distance in this race
     */
    public double getTotalDistance()
    {
        return totalDistance;
    }

    /**
     * stage make up a race, this function should allow us to create a stage
     * and use it in this race
     */
    public void addStageToRace()
    {
        
    }

    /**
     * @return - 
     * This function returns an array of all stages in this race
     */
    public int[] getStagesInRace()
    {
        int[] stageIDArray = new int[1];
        return stageIDArray;
    }
    /**
     * 
     * @param stageID - The unique identifier of stage
     * 
     * Removes a stage in this race
     */
    public void removeStageByID(int stageID)
    {

    }
}
