import cycling.*;
import java.util.HashMap;
import java.time.LocalTime;

public class RaceTest {
    public static void main(String[] args)
    {
        HashMap<Integer, Checkpoint> checkpointHash1 = new HashMap<Integer, Checkpoint>();
        HashMap<Integer, Checkpoint> checkpointHash2 = new HashMap<Integer, Checkpoint>();
        HashMap<Integer, Stage> stageHashMap = new HashMap<Integer, Stage>();
        IntermediateSprint imSpr1 = new IntermediateSprint("test", 1.0);
        CategorisedClimb CC1 = new CategorisedClimb("test", 1.0, 10.0);
        CategorisedClimb CC2 = new CategorisedClimb("test", 1.0, 4.0);
        CategorisedClimb CC3 = new CategorisedClimb("test", 1.0, 8.0);
        
        IntermediateSprint imSpr2 = new IntermediateSprint("test", 1.0);
        IntermediateSprint imSpr3 = new IntermediateSprint("test", 1.0);
        CategorisedClimb CC4 = new CategorisedClimb("test", 1.0, 2.0);
        CategorisedClimb CC5 = new CategorisedClimb("test", 1.0, 5.0);
        checkpointHash1.put(imSpr1.getCheckpointID(), imSpr1);
        checkpointHash1.put(CC1.getCheckpointID(), CC1);
        checkpointHash1.put(CC2.getCheckpointID(), CC2);
        checkpointHash1.put(CC3.getCheckpointID(), CC3);

        checkpointHash2.put(imSpr2.getCheckpointID(), imSpr2);
        checkpointHash2.put(imSpr3.getCheckpointID(), imSpr2);
        checkpointHash2.put(CC4.getCheckpointID(), CC4);
        checkpointHash2.put(CC5.getCheckpointID(), CC5);

        Stage stage1 = new Stage("stageName", "description", LocalTime.now(), StageType.MEDIUM_MOUNTAIN, checkpointHash1);
        Stage stage2 = new Stage("stage2", "description2", LocalTime.now(), StageType.HIGH_MOUNTAIN, checkpointHash2);

        stageHashMap.put(stage1.getStageID(), stage1);
        stageHashMap.put(stage2.getStageID(), stage2);

        Race race = new Race("race", "description", stageHashMap);

        assert(race.getRaceID() == 0)
        :"RaceID not correct";

    }
}
