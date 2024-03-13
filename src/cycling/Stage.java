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
    private HashMap<Integer, String> checkpointIDHashMap = new HashMap<Integer, String>();
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
    public int addCheckpoint() ///Check this, think should return ID but is void in UML
    {
        int checkPointID = 0;   //This should make a checkpoint to use in stage and return the ID
        return checkPointID;
    }
    public void removeCheckpoint(int checkPointID)
    {
                                //This should remove a checkpoint 
    }
    public void setStageType(StageType Stagetype)
    {
        this.stageType = stageType;
    }
    public Checkpoint getStageCheckpoints() //Not sure the type
    {
        //This function will have to return some type of checkpoint
        
    }
}
