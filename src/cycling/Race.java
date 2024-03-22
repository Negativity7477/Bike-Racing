package cycling;
import java.util.HashMap;
import java.util.regex.*;
public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private static int nextRaceID = 0; 
    private HashMap<Integer, Stage> stageIDHash;

    /**
     * 
     * @param name - Name of the race
     * @param description - Description of the race
     * 
     * Constructor for race
     */
    public Race(String name, String description) throws InvalidNameException
    {
        try{
        this.checkName(name);
        this.name = name;
        this.description = description;
        this.numOfStages = stageIDHash.size();
        this.raceID = nextRaceID++;
        this.totalDistance = calculateDistance();
        this.stageIDHash = new HashMap<Integer, Stage>();
        }

        catch (Exception e) {throw e;}    
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
    public String getRaceName()
    {
        return name;
    }


/**
     * @return - description of race
     * 
     * Allows us to find description of race
     */
    public String getRaceDescription()
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
     * 
     * @param stageID - Unique identifier of the stage
     * @param stage - The stage making up the race
     * A function to add more stages to race later if needed
     */
    public void addStageToRace(Stage stage)
    {
        int stageID = stage.getStageID();
        addToStageHash(stageID, stage);
    }

    /**
     * @return - An array of all stageIDs
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

    /**
     * Getter for a stage object using its unique ID
     * 
     * @param stage A unique ID linked to the stage
     * @return stage object that is linked to the ID given
     * 
     * @throws IDNotRecognisedException If the ID is not in the stage hashmap
     */
    public Stage getStage(int stageID) throws IDNotRecognisedException
    {
        Stage stageObject = stageIDHash.get(stageID);
        if (stageObject == null) {
            throw new IDNotRecognisedException("The stage requested is not in the hashmap");
        }
        return stageObject;
    }

    /**
     * 
     * @param stageID - unique identifier for stage
     * @param stage - the stages that makes up the race 
     * Allows us to add to the hash in a private method
     */
    private void addToStageHash(int stageID, Stage stage)
    {
        stageIDHash.put(stageID, stage);
    }

    /**
     * 
     * @return - total distance of the race
     * This function calculates the race distance based on the stage's distance
     */
    private Double calculateDistance() throws InvalidLengthException
    {
        Double totalDistance = 0.0;
        Stage[] allStages = returnAllStages();
        for(int i = 0; i < allStages.length; i++)
        {
            totalDistance += allStages[i].getStageLength();
        }
        if (totalDistance < 5) 
        {
            throw new InvalidLengthException("Invalid length");    
        }
        return totalDistance;
    }

    /**
     * 
     * @return - All stages in the race as an int array
     * Allows us to get all stage identifiers that make up the race
     */
    private int[] returnStageIDs()
    {
        int[] allStages = new int[stageIDHash.size()];
        int position = 0;

        for (Integer stageID : stageIDHash.keySet())
        {
            allStages[position++] = stageID;
        }
        return allStages;
    }


    /**
     * 
     * @param name - Name of race
     * @return - Only return if the name is valid
     * @throws InvalidNameException - name cannot contain whitespace or be null
     * 
     * This function checks if the name is valid to call race
     */
    private Boolean checkName(String name) throws InvalidNameException
    {
        if(name != null)
        {
            for(int i = 0; i < name.length(); i++)
            {
                if(Character.isWhitespace(name.charAt(i)))
                {
                    throw new InvalidNameException("Name does not fit the convetion");
                }
            }
        return true;
        }

        throw new InvalidNameException("Name does not fit the convetion");
    }
     

    /**
     * 
     * @return - All stage objects in race
     * Returns all stages that makes up a race
     */
    private Stage[] returnAllStages()
    {
        Stage[] allStages = new Stage[stageIDHash.size()];
        int position = 0;

        for (Stage stage : stageIDHash.values())
        {
            allStages[position++] = stage;
        }
        return allStages;
    }

    

}
