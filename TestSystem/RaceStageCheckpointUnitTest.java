import cycling.*;
import java.util.HashMap;
import java.time.LocalTime;
import java.time.LocalDateTime;

//I HAVE RUN THIS UNIT TEST 
//IT HAS NO ERRORS IN THE CURRENT DECLARATIONS
//AS OF 27/03/2024 13:31

public class RaceStageCheckpointUnitTest {

public static void main(String args[]) throws IDNotRecognisedException, InvalidNameException, IllegalNameException, InvalidLocationException
{        
        
        //Declare all classes for a test, do it outside a for loop because i think it makes it easier to test and more readable and it literally doesn't matter
        Race race = new Race("test_race", "a race to test functions"); //Race ID should be 0 i think
        //Race race2 = new Race("A second test race", "A race to test secondary functions"); //THis should cause error (white space in name) -- IVE RUN THIS, EXCEPTION IS THROWN
        Race race3 = new Race("test_race", "the last testing");

        Stage stage = new Stage("first_stage", "should be in race1", LocalDateTime.now(), StageType.HIGH_MOUNTAIN, 0);
        //Stage stage2 = new Stage("SHOULD THROW", "should throw error", LocalDateTime.now(), StageType.FLAT, 0); //Should throw invalid name
        //IVE RUN THE CONSTRUCTOR ABOVE, THE EXCEPTION IS THROWN
        Stage stage3 = new Stage("second_stage", "second stage in race", LocalDateTime.now(), StageType.MEDIUM_MOUNTAIN, 0);
        Stage stage4 = new Stage("Stage_in_race3", "first stage in race 3", LocalDateTime.now(), StageType.HIGH_MOUNTAIN, 1);
        Stage stage5 = new Stage("Last_Stage", "Last stage, in race 3", LocalDateTime.now(), StageType.TT, 1);

        Checkpoint imSpr1 = new Checkpoint(2.1, null, null, CheckpointType.SPRINT, 0, 0);
        Checkpoint CC1 = new Checkpoint(12.0, 3.4 , 12.0, CheckpointType.HC, 0, 0);
        Checkpoint CC2 = new Checkpoint(1.2, 3.1415, 5.3, CheckpointType.C2, 1, 0);

        Checkpoint CC3 = new Checkpoint(12.3, 15.2, 1.2, CheckpointType.C3, 2, 1);
        Checkpoint ImSpr2 = new Checkpoint(2.3, null, null, CheckpointType.SPRINT, 2, 1);
        Checkpoint ImSpr3 = new Checkpoint(52.2, null, null, CheckpointType.SPRINT, 3, 1);


        //Mischandling and race checks first
        assert(MiscHandling.getRace(0) == race)
                :"MiscHandling is not returning race properly";
        //try {
        //        MiscHandling.getRace(15);
        //        assert false
        //                :"MiscHandling is pulling from a non existent raceID";
        //} catch (Exception e) {
        //        System.out.println("THIS CRASH IS GOOD");
        //        throw e;
        //}
        //CODE ABOVE THROWS AN ERROR PROPERLY

        assert(MiscHandling.getRace(14) == null)
                :"MiscHandling is not throwing exceptions properly";
        assert(MiscHandling.getRaceIDFromName(race3.getRaceName()) == race3.getRaceID())
                :"MiscHandling is not returning raceID properly";

        //Try catch clauses for testing adding races and deleting races
        try{
                MiscHandling.addRace(race3);
        }
        catch(Exception e)
        {
                assert false 
                        :"A problem has occurred with adding race";
        }

        try{
                MiscHandling.removeRace(2);
        }
        catch(Exception e) //Generic exceptions, we don't want to test that our thrown exceptions work because they will by java's design
        {
                assert false 
                        :"There was a problem removing race";
        }
        //try{
        //        MiscHandling.removeRace(6); //Should throw an error!
        //        assert false
        //                :"Removing a non existent raceID";
        //}
        //catch(Exception e)
        //{
        //        System.out.println("Removing a non existing ID throws an error (good)");
        //}
        //THE CODE ABOVE THROWS THE CORRECT ERROR

        assert(MiscHandling.getRaceIDFromStageID(4) == race3.getRaceID())
                :"MiscHandling is not returning raceID from stageID properly";

        int[] RaceIDStageID = {0,0};
        assert(MiscHandling.getRaceStageIDFromCheckpointID(0) == RaceIDStageID)
                :"MiscHandling is not returning stageID or raceID from checkpointID properly"; //Note i cannot test the abstraction because how this function returns >:(
        

        assert(race.getNumStage() == 3)
                :"race is not returning the number of stages properly"; //This could also be because it is not updating as stages get added
        assert(race.getRaceDescription().equals("a race to test functions"))
                :"The getter for race description is not working";
        assert(race3.getRaceDescription().equals("the last testing"))
                :"The getter for race description is not working";
        assert(race3.getRaceName().equals("last_race"))
                :"The getter for race name is not working";
        int[] StageIDsInRace = {0,1,2};
        assert(race.getRaceStages() == StageIDsInRace)
                :"returning all stageIDs from the race is not working";
        //assert(race.getTotalDistance() = ) respectfully kys if you think im checking this

        assert(imSpr1.getCheckpointID() == 0)
                :"CheckpointID is returning wrong";
        assert(imSpr1.getCheckpointType() == CheckpointType.SPRINT)
                :"CheckpointType is returning wrong";
        assert(imSpr1.getCheckpointLength() == null)
                :"CheckpointLength is returning wrong";
        assert(CC1.getCheckpointLength() == 3.4)
                :"CheckpointLength is returning wrong";
        assert(CC1.getCheckpointType() == CheckpointType.HC)
                :"CheckpointType is returning wrong";
        assert(CC2.getCheckpointType() == CheckpointType.C2)
                :"CheckpointType is returning wrong";
        assert(CC3.getCheckpointType() == CheckpointType.C3)
                :"CheckpointType is returning wrong";
        assert(CC3.getAverageGradient() == 1.2)
                :"Average gradient is returning wrong";
}
}

