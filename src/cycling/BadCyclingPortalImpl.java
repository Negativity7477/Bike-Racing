package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;



/**
 * BadCyclingPortal is a minimally compiling, but non-functioning implementor
 * of the CyclingPortal interface.
 * 
 * @author Diogo Pacheco
 * @version 2.0
 *
 */
public class BadCyclingPortalImpl implements CyclingPortal {

	@Override
	public int[] getRaceIds() {
		// TODO Auto-generated method stub
		return new int[] {};
	}

//We need to implement IllegalNameException for this method
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		try 
		{
		//Create a new race, an error will be thrown if the name is invalid or illegal
		Race race = new Race(name, description);
		//Add the race to the handling class at the top of the hierarchy
		MiscHandling.addRace(race);
		//return the ID of the race as the method requires
		return race.getRaceID();
		} 
		//In the case there is an invalid race name, throw an exception
		catch (InvalidNameException e) {throw e;} 
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		try {
		//Get the race out of the handling class
		//If there is no race with the ID, this will throw an error
		Race race = MiscHandling.getRace(raceId);
		//Call the getters for the required details
		String name = race.getRaceName();
		String description = race.getRaceDescription();
		int numStages = race.getNumStage();
		double distance = race.getTotalDistance();

		//return a formatted string of all the details
		return "RaceID = " + raceId + 
		"\n Name = " + name + 
		"\n Description = " + description + 
		"\n NumStages = " + numStages +
		"\n distance = " + distance;
		
		//Incase and error is thrown when finding the race,
		//rethrow the error
		} catch (IDNotRecognisedException e) {
			throw e;
		}
		
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		try {
			//call the method to remove the race,
			//will throw an error if there is no race
			//with the given ID
			MiscHandling.removeRace(raceId);
			//In the case there is no race with this ID
			//rethrow the error
		} catch (IDNotRecognisedException e) {
			throw e;
		}

	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		try {
			//Get the race out of the handling class
			//If there is no race with the ID, this will throw an error
			Race race = MiscHandling.getRace(raceId);
			//return the number of stages
			return race.getNumStage();
			//if ID is not found, rethrow the error
		} catch (IDNotRecognisedException e) {
			throw e;
		}
		
	}


	//We need to implement IllegalNameException for this method
	//I think i might also have to look over InvalidLengthException,
	//Not entirely sure on how to implement yet
	//Just going to go for functionality for now though
	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException{
		try {
		//Get the race out of the handling class,
		//This will throw an error if the ID is not recognised
		Race race = MiscHandling.getRace(raceId);
		//Create the stage to add to the race
		//This will throw an exception if the raceID does not exist 
		//or if the stageName is wrong
		Stage stage = new Stage(stageName, description, startTime, type, raceId);
		race.addStage(stage);
		int stageID = stage.getStageID();
		//return the stageID 
		return stageID;

		//Because we are dealing with lots of exceptions,
		//we throw a generic one 
		} catch (Exception e) {
			throw e;
		}
	}



	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		try {
		//Get the race out of the handler
		//will throw error if ID is not valid
		Race race = MiscHandling.getRace(raceId);
		return race.getRaceStages();
		} catch (Exception e) {throw e;}
		
	}

	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {
		
			

		return 0;
	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
			InvalidStageStateException {

		

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

		int teamIdOfRider = MiscHandling.getTeamIDFromRiderID(riderId);
		Team teamOfRider = MiscHandling.getTeam(teamIdOfRider);
		Rider riderObject = teamOfRider.getRider(riderId);

		return riderObject.getStageResults(stageId);
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}
