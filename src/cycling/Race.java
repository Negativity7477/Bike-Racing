package cycling;
import java.util.HashMap;
public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private static int nextRaceID = 0; 
    private HashMap<Integer, Stage> stageIDHash = new HashMap<Integer, Stage>();

    /**
     * 
     * @param name - Name of the race
     * @param description - Description of the race
     * 
     * Constructor for race
     */
    public Race(String name, String description, HashMap<Integer, Stage> stageIDHash)
    {
        this.name = name;
        this.description = description;
        this.stageIDHash = (HashMap<Integer, Stage>) stageIDHash.clone();
        this.numOfStages = stageIDHash.size();
        this.raceID = nextRaceID++;
        this.totalDistance = calculateDistance(stageIDHash);
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
     * @return - name of race
     * 
     * Allows us to find name of race
     */
    public String getName()
    {
        return name;
    }

    
/**
     * @return - description of race
     * 
     * Allows us to find description of race
     */
    public String getDescription()
    {
        return description;
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
     * A function to add more stages to race later if needed
     */
    public void addStageToRace(int stageID, Stage stage)
    {
        addToStageHash(stageID, stage);
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
    public void removeStageByID(int stageID) throws IDNotRecognisedException
    {
        if(stageIDHash.containsKey(stageID))
        {
            stageIDHash.remove(stageID);
        }
        else
        {
            throw new IDNotRecognisedException("No stage correponds to ID");
        }
    }

    private void addToStageHash(int stageID, Stage stage)
    {
        stageIDHash.put(stageID, stage);
    }

    private Double calculateDistance(HashMap<Integer,Stage> stageHashMap)
    {
        Double totalDistance = 0.0;
        Stage[] allStages = returnAllStages(stageHashMap);
        for(int i = 0; i < allStages.length; i++)
        {
            totalDistance += allStages[i].getStageLength();
        }

        return totalDistance;
    }

    private int[] returnStageIDs(HashMap<Integer,Stage> stageHashMap)
    {
        int[] allStages = new int[stageHashMap.size()];
        int position = 0;

        for (Integer stageID : stageHashMap.keySet())
        {
            allStages[position++] = stageID;
        }
        return allStages;
    }

    private Stage[] returnAllStages(HashMap<Integer,Stage> stageHashMap)
    {
        Stage[] allStages = new Stage[stageHashMap.size()];
        int position = 0;

        for (Stage stage : stageHashMap.values())
        {
            allStages[position++] = stage;
        }
        return allStages;
    }

}
