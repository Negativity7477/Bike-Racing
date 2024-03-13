package cycling;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.ArrayList;

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

    //Constructor
    public Stage(int raceID, String stageName, String description, Double length, LocalTime startTime, StageType StageType, HashMap<Integer, CheckpointType> checkpointIDHashMap)
    {
        this.raceID = raceID;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.stageType = stageType; 
        this.stageID = nextStageID++;
        this.checkpointIDHashMap = (HashMap<Integer, CheckpointType>)checkpointIDHashMap.clone(); //Only way to create stage out of checkpoints, need to call this with a hashmap from main somehow

    }

    public double getStageLength()
    {
        return length;
    }

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
                                 //This should remove a checkpoint 
    }
    /*
     *  Returns the checkpoints in this stage
     */
    public HashMap<Integer, CheckpointType> getStageCheckpoints() 
    {
        return checkpointIDHashMap;
    }

    public String toString()
    {
        return checkpointIDHashMap.toString();
    }

    /**
     * 
     * @param checkpointID
     * @param checkpointType
     * 
     * This function is used to generate the hash, showing what checkpoint is what type
     * This hash should be used in anywhere we need to determine type of checkpoint
     */
    private void addCheckpointToHash(int checkpointID, CheckpointType checkpointType)
    {
        checkpointIDHashMap.put(checkpointID, checkpointType);
    }
}
