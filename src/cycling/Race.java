package cycling;
import java.util.HashMap;
public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private HashMap<Integer, Stage> stageIDHash = new HashMap<Integer, Stage>();

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
    public void addStageToRace()
    {
        
    }
    public int[] getStagesInRace()
    {

    }
    public void removeStageByID(int stageID)
    {
        
    }
}
