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
    public void addCheckpoint() 
    {
       int checkpointID = Checkpoint.getCheckpointID();
       CheckpointType checkpointType = Checkpoint.getCheckpointType();
        addCheckpointToHash(checkpointID, checkpointType);
    }
    public void removeCheckpoint(int checkPointID)
    {
                                 //This should remove a checkpoint 
    }
    public Checkpoint getStageCheckpoints() 
    {
    
    }

    private void addCheckpointToHash(int checkpointID, CheckpointType checkpointType)
    {
        checkpointIDHashMap.put(checkpointID, checkpointType);
    }
}
