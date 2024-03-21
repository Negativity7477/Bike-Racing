package cycling;
import java.time.LocalTime;
import java.util.HashMap;


public class Stage {
    private int stageID;
    private String stageName;
    private String description; 
    private double length;
    private LocalTime startTime;
    private StageType stageType;
    //Hash of checkpointID to checkpointType
    private HashMap<Integer, Checkpoint> checkpointIDHashMap;
    private LocalTime[] totalTimes;
    private static int nextStageID = 0;

    /**
     * 
     * @param raceID - ID of the race this stage is a part of (may not be needed)
     * @param stageName - Name of the given stage
     * @param description - Description of the stage
     * @param length - Length of the stage
     * @param startTime - Time the stage started 
     * @param StageType - Type of stage, types defined in StageType
     * @param checkpointIDHashMap - A hashmap corresponding to checkpoints making up the stage
     * each checkpointID maps to a checkpointType
     * 
     * Constructor for stage
     */
    public Stage(String stageName, String description, LocalTime startTime, StageType StageType, int RaceID) throws IDNotRecognisedException
    {
        this.stageName = stageName;
        this.description = description;
        this.startTime = startTime;
        this.stageType = stageType;
        this.stageID = nextStageID++;
        this.length = getStageLength();
        this.checkpointIDHashMap = new HashMap<Integer, Checkpoint>();

        //We can get the race we are add this stage to via 
        //the static class at the top level
        Race race = MiscHandling.getRace(RaceID);
        //We can add the stage to this race
        race.addStageToRace(this);
    }



    /**
     * @return - Name of stage
     * 
     * Allows us to get the stage name 
     */
    public String getStageName()
    {
        return stageName;
    }


    /**
     * @return - Description of stage
     * 
     * Allows us to get the stage description
     */
    public String getStageDescription()
    {
        return description;
    }


    /**
     * @return - Unique identifier of stage
     * 
     * Allows us to get the stage identifier 
     */
    public int getStageID()
    {
        return stageID;
    }

    /**
     * 
     * @param Stagetype - This Stage's type based on checkpoints that make it up
     * 
     * Allows us to set stage type if we need to
     */
    public void setStageType(StageType Stagetype)
    {
        this.stageType = stageType;
    }

    /**
     * Stages are made of checkpoints, this function should add a checkpoint
     * Checkpoints are either an intermediate sprint or a categorised climb
     */
    public void addCheckpoint(Checkpoint type) 
    {
        int checkpointID = type.getCheckpointID();
        addCheckpointToHash(checkpointID, type);      
    }



    /*
     * This function removes a checkpoint 
     */
    public void removeCheckpoint(int checkPointID) throws IDNotRecognisedException
    {
        if(checkpointIDHashMap.containsKey(checkPointID))
        {
            checkpointIDHashMap.remove(checkPointID);
        }
        else
        {
            throw new IDNotRecognisedException("No Checkpoint correponds to ID");
        }

    }




    /*
     * @return - the hashmap containing checkpointID and checkpoint
     * Returns the checkpoints in this stage
     */
    public HashMap<Integer, Checkpoint> getStageCheckpoints() 
    {
        return checkpointIDHashMap;
    }


    /**
     * Allows conversion to string 
     */
    public String toString()
    {
        return checkpointIDHashMap.toString();
    }

    /**
     * 
     * @param checkpointID - Each checkpoint's unique identifier
     * @param checkpointType - Each checkpoint's type defined in CheckpointType
     * 
     * This function is used to generate the hash, showing what checkpoint is what type
     * This hash should be used in anywhere we need to determine type of checkpoint
     */
    private void addCheckpointToHash(int checkpointID, Checkpoint checkpointType)
    {
        checkpointIDHashMap.put(checkpointID, checkpointType);
    }

    /**
     * 
     * @return - Array of all checkpointIDs making up a stage
     * 
     * get all checkpointIDs as an array
     */
    public int[] getAllCheckpointID()
    {
        int[] allCheckpoints = new int[checkpointIDHashMap.size()];
        int position = 0;

        // Loops through hash table and adds all checkpointIDs to an array 
        for (Integer checkpointID : checkpointIDHashMap.keySet())
        {
            allCheckpoints[position++] = checkpointID;
        }
        return allCheckpoints;
    }

    
    /**
     * @param checkpointHashMap - Hashmap of checkpointID to the checkpoint
     * @return - an array of all checkpoints in stage
     * 
     * Allows us to get every checkpoint in a stage
     */
    private Checkpoint[] returnAllCheckpoints()
    {
        Checkpoint[] allCheckpoints = new Checkpoint[checkpointIDHashMap.size()];
        int position = 0;

        for (Checkpoint checkpoint: checkpointIDHashMap.values())
        {
            allCheckpoints[position++] = checkpoint;
        }
        return allCheckpoints;
    }

    
    /**
     * @param checkpointHashMap - Hashmap of checkpointID to the checkpoint
     * @return = the distance of the stage
     * 
     * Allows us to work out the total length of the stage from the checkpoints
     * making up the stage 
     */
    public Double getStageLength()
    {
        Double totalDistance = 0.0;
        Checkpoint[] allCheckpoints = returnAllCheckpoints();
        for(int i = 0; i < allCheckpoints.length; i++)
        {
            totalDistance += allCheckpoints[i].getCheckpointLength();
        }

        return totalDistance;
    }
}
