

import cycling.DuplicatedResultException;
import cycling.IDNotRecognisedException;
import cycling.Rider;
import cycling.Team;


//THIS CODE HAS RUN WITH NO ERRORS 
//THIS IS AS OF 27/03/2024 13:37

public class TeamRiderUnitTest {
    public static void main(String[] args) throws DuplicatedResultException, IDNotRecognisedException{


        // Testing team ID uniqueness
        Team[] teamArray = new Team[3];
        String[] nameArray = {"name1", "name2", "name3"};
        String[] descArray = {"desc1", "desc2", "desc3"};
        int[] yearArray = {2000, 1998, 2024};

        for (int i=0; i < 3; i++) {
            teamArray[i] = new Team(nameArray[i], descArray[i]);
            assert (teamArray[i].getTeamID() != i)
            : "Error with team ID values and or team ID incrementing";
        }

        Team teamObject = new Team("name", "desc");
        assert (teamObject.getRiderIDArray().length == 0)
        : "Initial hashmap not empty as required or not returning an empty array";

        Rider[] riderObject = new Rider[3];
        for (int i=0; i < 3; i++) {
            riderObject[i] = new Rider(1, nameArray[i], yearArray[i]);
            try {
                teamObject.addRider(riderObject[i]);
            } catch(Exception e) {
                assert false
                : "Unexpected issue with rider IDs, investigate Rider class";
            }
        }
        
            try{
                teamObject.deleteRider(0);
            }
            catch(Exception e)
            {
                assert false 
                    :"A problem occurred in removing rider";
            }

        

    }
}
