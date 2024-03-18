import cycling.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Arrays;

public class StageTest {
    public static void main(String args[])
    {
        HashMap<Integer, Checkpoint> checkpointHash = new HashMap<Integer, Checkpoint>();
        IntermediateSprint imSpr1 = new IntermediateSprint("test", 5.0);
        CategorisedClimb CC1 = new CategorisedClimb("test", 1.0, 10.0);
        CategorisedClimb CC2 = new CategorisedClimb("test", 15.0, 4.0);
        CategorisedClimb CC3 = new CategorisedClimb("test", 25.0, 8.0);
       
        checkpointHash.put(imSpr1.getCheckpointID(), imSpr1);
        checkpointHash.put(CC1.getCheckpointID(), CC1);
        checkpointHash.put(CC2.getCheckpointID(), CC2);
        checkpointHash.put(CC3.getCheckpointID(), CC3);
        int[] checkpointIDs = new int[4];
        for(int i = 0; i < 4; i++)
        {
            checkpointIDs[i] = i;
        }

        Stage stage = new Stage("stageName", "Description", LocalTime.now(), StageType.MEDIUM_MOUNTAIN, checkpointHash);
        //length of all checkpoints is 46 as declared above
        assert(stage.getStageLength() == 46.0)
        :"Problem with adding length";
        assert(stage.getStageID() == 0)
        :"ID not correct";
        assert(stage.getStageName().equals("stageName"))
        :"Name is not returning correctly";
        assert(stage.getStageDescription().equals("Description"))
        :"Description is not returning correctly";
        if (!Arrays.equals(stage.getAllCheckpointID(), checkpointIDs))
        {
            System.out.println("CheckpointIDs are not returning correctly");
        }
       
    }
}
