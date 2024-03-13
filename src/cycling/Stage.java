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
   
    //Constructor
    public int Stage(int raceID, String stageName, String description, Double length, LocalTime startTime, StageType StageType)
    {
        this.raceID = raceID;
        this.stageName = stageName;
        this.description = description;
        this.length = length;
        this.startTime = startTime;
        this.stageType = stageType;
        this.stageID = nextStageID++;

        return stageID;
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
     * 
     * Current error is that we need an instance of either intermediate sprint
     * or categorised climb (we will have to get that initalised from somewhere else)
     * and use that instance variable instead of the Checkpoint in Checkpoint.getCheckpointID()
     */
    public void addCheckpoint() 
    {
        int checkpointID = Checkpoint.getCheckpointID();  
        CheckpointType checkpointType = Checkpoint.getCheckpointType(); 
        addCheckpointToHash(checkpointID, checkpointType);      
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
    public Checkpoint getStageCheckpoints() 
    {
    
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
