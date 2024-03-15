package cycling;
import java.time.LocalTime;
import java.util.HashMap;


public class Stage {
    private int stageID;
    private int raceID;
    private String stageName;
    private String description; 
    private double length;
    private LocalTime startTime;
    private StageType stageType;
    private HashMap<Integer, CheckpointType> checkpointIDHashMap = new HashMap<Integer, CheckpointType>();
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
    public Stage(int raceID, String stageName, String description, Double length, LocalTime startTime, HashMap<Integer, CheckpointType> checkpointIDHashMap)
    {

        this.raceID = raceID;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.stageID = nextStageID++;
        this.checkpointIDHashMap = (HashMap<Integer, CheckpointType>)checkpointIDHashMap.clone();
        
        CheckpointType[] types = returnCheckpointTypes(checkpointIDHashMap);
        this.stageType = findStageType(types);
        
    }

    /**
     * 
     * @return - Length of stage
     * 
     * Allows us to get the stage length
     */
    public double getStageLength()
    {
        return length;
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
     * This is not the main way we add checkpoints though (constructor does that)
     */
    public void addCheckpoint(int checkpointID, CheckpointType type) 
    {
        addCheckpointToHash(checkpointID, type);      
    }



    /*
     * This function removes a checkpoint 
     */
    public void removeCheckpoint(int checkPointID)
    {
        
    }




    /*
     *  Returns the checkpoints in this stage
     */
    public HashMap<Integer, CheckpointType> getStageCheckpoints() 
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
    private void addCheckpointToHash(int checkpointID, CheckpointType checkpointType)
    {
        checkpointIDHashMap.put(checkpointID, checkpointType);
    }


/**
 * 
 * @param checkpointHash - The hash of checkpointID to checkpointType
 * @return - All the checkpointIDs in the hash
 * 
 * This function extracts the IDs out of the hashmap and returns them
 * To do this we need to follow this conversion route 
 * Object -> Integer[] -> Integer -> int -> int[]
 */
    private int[] returnCheckpointIDs(HashMap<Integer, CheckpointType> checkpointHash)
    {
        Integer[] wrappedCheckpointIDs = (Integer[]) checkpointHash.keySet().toArray(); //create a Wrapped
        //integer array of all our checkpointIDs because java returns them as objects and we need to convert

        int[] checkpointIDs = new int[checkpointHash.size()]; //Create the array to return
        Integer holder = 0; //Create a wrapped integer object to convert between Integer[] and int[]

        for(int i = 0; i < checkpointHash.size(); i++)  //We need to loop to unwrap our IDs
        {
            holder = wrappedCheckpointIDs[i]; //Storing each array value into a wrapped Integer variable to convert it
            checkpointIDs[i] = holder; //Converting Integer to int which is added to the array
        }
        return checkpointIDs;
    }


    /**
     * 
     * @param checkpointHash - Hash of checkpointID to checkpointType
     * @return - An array of all checkpointTypes in the hash
     * 
     * Returns all the CheckpointTypes that make up this stage
     */
    private CheckpointType[] returnCheckpointTypes(HashMap<Integer, CheckpointType> checkpointHash)
    {
        CheckpointType[] checkpointTypes = checkpointHash.values().toArray(new CheckpointType[0]);
        return checkpointTypes;
    }




    /**
     * 
     * @param checkpointTypes - An array of all checkpoint types in this stage
     * @return - Returns what stage type this stage is
     * 
     * Determines the proportion of categorised climb to return the correct type
     */
    private StageType findStageType(CheckpointType[] checkpointTypes)
    {

        return stageType.FLAT;
    }
}
