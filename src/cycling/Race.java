package cycling;
import java.util.HashMap;
public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private HashMap<Integer, Stage> stageIDHash = new HashMap<Integer, Stage>();

    //Constructor
    public Race(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public int getRaceID()
    {
        return raceID;
    }

    public int getNumStage()
    {
        return numOfStages;
    }

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
     * This function returns an array of all stages in this race
     * @return
     */
    public int[] getStagesInRace()
    {
        int[] stageIDArray = new int[1];
        return stageIDArray;
    }
    /**
     * 
     * @param stageID
     * 
     * Remoes a stage in thsi race
     */
    public void removeStageByID(int stageID)
    {

    }
}
