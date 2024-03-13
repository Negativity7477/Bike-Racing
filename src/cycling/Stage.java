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
    public void addCheckpoint()
    {

    }
    public void removeCheckpoint(int checkPointID)
    {

    }
    public void setStageType(StageType Stagetype)
    {
        this.stageType = stageType;
    }
    public Checkpoint getStageCheckpoints()
    {
        
    }
}
