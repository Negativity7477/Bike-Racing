package cycling;
import java.util.HashMap;
import java.util.regex.*;
import java.time.LocalTime;

public class Race {
    private int raceID;
    private int numOfStages;
    private double totalDistance;
    private String name;
    private String description;
    private static int nextRaceID = 0; 
    private HashMap<Integer, Stage> stageIDHashmap;
    private HashMap<Integer, LocalTime> riderTimesHash;

    /**
     * 
     * @param name - Name of the race
     * @param description - Description of the race
     * 
     * Constructor for race
     */
    public Race(String name, String description) throws InvalidNameException, IllegalNameException
    {
        try{
            try{
                if (MiscHandling.getRaceIDFromName(name) > -99999)
                    {
                        throw new IllegalNameException("This name is taken");
                    }
            }
            catch(Exception e){}
        this.stageIDHashmap = new HashMap<Integer, Stage>();
        this.checkName(name);
        this.name = name;
        this.totalDistance = calculateDistance();
        this.description = description;
        this.numOfStages = stageIDHashmap.size();
        this.raceID = nextRaceID++;
        MiscHandling.addRace(this);
        }

        catch (InvalidNameException e) {throw e;}    
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
    public void addStage(Stage stage)
    {
        int stageID = stage.getStageID();
        stageIDHashmap.put(stageID, stage);
    }

    /**
     * @return - An array of all stageIDs
     * This function returns an array of all stages in this race
     * in order they were added
     */
    public int[] getRaceStages()
    {
        int[] allStageIDs = new int[getNumStage()];
        int position = 0;

        // Loops through hash table and adds all stage IDs to an array
        for (Integer stageID : stageIDHashmap.keySet()) {
            allStageIDs[position++] = stageID;
        }
        
        return allStageIDs;
    }
    /**
     * 
     * @param stageID - The unique identifier of stage
     * 
     * Removes a stage in this race
     */
    public void removeStage(int stageID) throws IDNotRecognisedException
    {
        if(stageIDHashmap.containsKey(stageID))
        {
            stageIDHashmap.remove(stageID);
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
        Stage stageObject = stageIDHashmap.get(stageID);
        if (stageObject == null) {
            throw new IDNotRecognisedException("The stage requested is not in the hashmap");
        }
        return stageObject;
    }

    /**
     * 
     * @return - total distance of the race
     * This function calculates the race distance based on the stage's distance
     */
    private Double calculateDistance()// throws InvalidLengthException
    {
        Double totalDistance = 0.0;
        Stage[] allStages = returnAllStages();
        for(int i = 0; i < allStages.length; i++)
        {
            totalDistance += allStages[i].getStageLength();
        }
        if (totalDistance < 5) 
        {
           // throw new InvalidLengthException("Invalid length of stage");    
        }
        return totalDistance;
    }

    /**
     * 
     * @param name - Name of race
     * @return - Only return if the name is valid
     * @throws InvalidNameException - name cannot contain whitespace or be null
     * 
     * This function checks if the name is valid to call race
     */
    private void checkName(String name) throws InvalidNameException
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
        }
        else{
        throw new InvalidNameException("Name does not fit the convetion");
        }
    }
    

    /**
     * 
     * @return - All stage objects in race
     * Returns all stages that makes up a race
     */
    private Stage[] returnAllStages()
    {
        Stage[] allStages = new Stage[stageIDHashmap.size()];
        int position = 0;

        for (Stage stage : stageIDHashmap.values())
        {
            allStages[position++] = stage;
        }
        return allStages;
    }



    /**
     * 
     * @param riderID - unique identifier of the rider
     * @param riderTime - the time to add to the hash for this race
     * Adds the time the rider got in this race to the hashmap storing them
     */
    public void addRiderRaceTime(int riderID, LocalTime riderTime)
    {
        riderTimesHash.put(riderID, riderTime);
    }

    
    /**
     * 
     * @param riderID - unique identifier of the rider
     * @return - the time to complete the race for a given rider
     * 
     * returns the time the rider took to complete the race
     */
    public LocalTime getRiderRaceTime(int riderID)
    {
        LocalTime riderTime = riderTimesHash.get(riderID);
        return riderTime;
    }


    /**
     * 
     * @param riderID- unique identifier of the rider
     * 
     * removes the rider and their time's from the hash
     */
    public void removeRiderRaceTime(int riderID)
    {
        riderTimesHash.remove(riderID);
    }

    /**

     * Adds up the stage times for a race for a specified rider
     * and then puts them in a hashmap
     * 
     * @param riderID ID of the rider 
     */
    public void setRiderRaceTime(int riderID) {

        int numStages = stageIDHashmap.size();
        LocalTime[] stageTimeArray = new LocalTime[numStages];
        LocalTime riderTime;
        int counter = 0;

        // Loops through all checkpoints in the stage and collects the rider's times
        for (Stage stageObject : stageIDHashmap.values()) {
            stageTimeArray[counter++] = stageObject.getRiderStageTime(riderID);
        }

        // Totals times and adds them to a hashmap
        riderTime = MiscHandling.totalTimes(stageTimeArray);
        addRiderRaceTime(riderID, riderTime);
    }
/** 
     * Reset the static ID counter
     */
    public static void resetRaceIDCount()
    {
        nextRaceID = 0;
    }

    

}
