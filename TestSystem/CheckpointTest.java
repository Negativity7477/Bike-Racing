import cycling.*;
import java.util.HashMap;
import java.time.LocalTime;


public class CheckpointTest {

public static void main(String args[])
{        
        //initialise classes for testing (like would be done in main)
        IntermediateSprint imSpr1 = new IntermediateSprint("test", 5.0);
        CategorisedClimb CC1 = new CategorisedClimb("test", 1.0, 10.0);
        CategorisedClimb CC2 = new CategorisedClimb("test", 15.0, 4.0);
        CategorisedClimb CC3 = new CategorisedClimb("test", 25.0, 8.0);

        assert(imSpr1.getCheckpointID() == 0)
                :"CheckpointID is returning wrong";
        assert(imSpr1.getCheckpointType() == CheckpointType.SPRINT)
                :"CheckpointType is returning wrong";
        assert(imSpr1.getCheckpointLength() == 5.0)
                :"CheckpointLength is returning wrong";
        assert(CC1.getCheckpointType() == CheckpointType.HC)
                :"CheckpointType is returning wrong";
        assert(CC2.getCheckpointType() == CheckpointType.C2)
                :"CheckpointType is returning wrong";
        assert(CC3.getCheckpointType() == CheckpointType.HC)
                :"CheckpointType is returning wrong";
}
}

