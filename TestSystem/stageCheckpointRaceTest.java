import cycling.*;
import java.util.HashMap;
import java.time.LocalTime;


public class stageCheckpointRaceTest {

public static void main(String args[])
{
        //initialise a hashmap for checkpointIDs and checkpointTypes, and a hashmap for stageID and stage
        HashMap<Integer, Checkpoint> checkpointIDHash = new HashMap<Integer, Checkpoint>();
        HashMap<Integer, Stage> stageIDHash = new HashMap<Integer, Stage>(); 
        
        //initialise classes for testing (like would be done in main)
        IntermediateSprint imSpr1 = new IntermediateSprint("test", 5.0);
        CategorisedClimb CC1 = new CategorisedClimb("test", 1.0, 10.0);
        CategorisedClimb CC2 = new CategorisedClimb("test", 15.0, 4.0);
        CategorisedClimb CC3 = new CategorisedClimb("test", 25.0, 8.0);
        
        //use their values in the hashmap
        checkpointIDHash.put(imSpr1.getCheckpointID(), imSpr1);
        checkpointIDHash.put(CC1.getCheckpointID(), CC1);
        checkpointIDHash.put(CC2.getCheckpointID(), CC2);
        checkpointIDHash.put(CC3.getCheckpointID(), CC3);
        
        //create stages using the hashmap
        Stage stage = new Stage(0, "Bob", "test", LocalTime.now(), StageType.FLAT, checkpointIDHash);

        //test the functions in the class
        System.out.println(CC1.determineClimbType(CC1.getAverageGradient(), CC1.getLength()));
        System.out.println(CC2.determineClimbType(CC2.getAverageGradient(), CC2.getLength()));
        System.out.println(CC3.determineClimbType(CC3.getAverageGradient(), CC3.getLength()));

        System.out.println(stage);
        System.out.println("ahhhhh" + stage.getStageLength());

        stageIDHash.put(stage.getStageID(), stage);

        Race race = new Race("main race", "Testing the race class", stageIDHash);

        int test1 = race.getRaceID();
        int test2 = race.getNumStage();
        Double test3 = race.getTotalDistance();

        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);

        
}
}

