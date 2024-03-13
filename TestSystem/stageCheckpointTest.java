import cycling.*;
import java.util.HashMap;
import java.time.LocalTime;


public class stageCheckpointTest {
    public static void main(String args[])
    {
        HashMap<Integer, CheckpointType> checkpointIDHash = new HashMap<Integer, CheckpointType>();
        
        IntermediateSprint imSpr1 = new IntermediateSprint(1, "fuck off", 5.0);
        CategorisedClimb CC1 = new CategorisedClimb(2, "HAHAH", 1.0, 10.0);
        CategorisedClimb CC2 = new CategorisedClimb(3, "HAHAH", 15.0, 4.0);
        CategorisedClimb CC3 = new CategorisedClimb(4, "HAHAH", 25.0, 8.0);
        
        checkpointIDHash.put(imSpr1.getCheckpointID(), imSpr1.getCheckpointType());
        checkpointIDHash.put(CC1.getCheckpointID(), CC1.getCheckpointType());
        checkpointIDHash.put(CC2.getCheckpointID(), CC2.getCheckpointType());
        checkpointIDHash.put(CC3.getCheckpointID(), CC3.getCheckpointType());
        
        Stage stage = new Stage(0, "Bob", "This shit sucks", 46.0, LocalTime.now(), StageType.FLAT, checkpointIDHash);

        System.out.println(CC1.determineClimbType(CC1.getAverageGradient(), CC1.getLength()));
        System.out.println(CC2.determineClimbType(CC2.getAverageGradient(), CC2.getLength()));
        System.out.println(CC3.determineClimbType(CC3.getAverageGradient(), CC3.getLength()));

        System.out.println(stage);
    }
}

