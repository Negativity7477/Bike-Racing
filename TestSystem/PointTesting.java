import java.time.LocalDateTime;

import cycling.*;
public class PointTesting {
    
    public static void main(String[] args) throws IDNotRecognisedException, InvalidNameException, DuplicatedResultException
    {
          //Declare all classes for a test, do it outside a for loop because i think it makes it easier to test and more readable and it literally doesn't matter
        Race race = new Race("test_race", "a race to test functions"); //Race ID should be 0 i think
        Race race3 = new Race("last_race", "the last testing");

        Stage stage = new Stage("first_stage", "should be in race1", LocalDateTime.now(), StageType.HIGH_MOUNTAIN, 0);
        Stage stage3 = new Stage("second_stage", "second stage in race", LocalDateTime.now(), StageType.MEDIUM_MOUNTAIN, 0);
        Stage stage4 = new Stage("Stage_in_race3", "first stage in race 3", LocalDateTime.now(), StageType.HIGH_MOUNTAIN, 2);
        Stage stage5 = new Stage("Last_Stage", "Last stage, in race 3", LocalDateTime.now(), StageType.TT, 2);

        Checkpoint imSpr1 = new Checkpoint(2.1, null, null, CheckpointType.SPRINT, 0, 0);
        Checkpoint CC1 = new Checkpoint(12.0, 3.4 , 12.0, CheckpointType.HC, 0, 0);
        Checkpoint CC2 = new Checkpoint(1.2, 3.1415, 5.3, CheckpointType.C2, 2, 0);

        Checkpoint CC3 = new Checkpoint(12.3, 15.2, 1.2, CheckpointType.C3, 3, 2);
        Checkpoint ImSpr2 = new Checkpoint(2.3, null, null, CheckpointType.SPRINT, 3, 2);
        Checkpoint ImSpr3 = new Checkpoint(52.2, null, null, CheckpointType.SPRINT, 4, 2);

        Team team = new Team("test_Team", "Test for general classification");
        Rider rider = new Rider(0, "john", 1989);
        Rider rider2 = new Rider(0, "bob", 1987);
        Rider rider3 = new Rider(0, "bill", 1979);
        Rider rider4 = new Rider(0, "fatty", 2000);
        Rider rider5 = new Rider(0, "phil", 2001);


        GeneralClassification generalClassification = new GeneralClassification(0);
        //no idea how to test any of these functions
    }
}
