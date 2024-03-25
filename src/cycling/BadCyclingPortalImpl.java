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
		MiscHandling.add(race);
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
		} catch (Exception e) {throw e;}
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
		//We need to use the race class to access the race length
		//So we create a new race, which contains the correct stage ID
		//and retrieve the stage to return its length
		try {
			int raceID = MiscHandling.getRaceIDFromStageID(stageId);
			Race race = MiscHandling.getRace(raceID);
			Stage stage = race.getStage(stageId);
			return stage.getStageLength();
		} catch (IDNotRecognisedException e) {throw e;}
		
	}

	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		try {
			//Get the race containing the stage ID
			//Delete the stage from that race
			int raceID = MiscHandling.getRaceIDFromStageID(stageId);
			Race race = MiscHandling.getRace(raceID);
			race.removeStage(stageId);
		} catch (IDNotRecognisedException e) {throw e;}
		
	}

	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, CheckpointType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {

			try {
				//Retrieve the race to get the stage to add the checkpoint to
				int raceID = MiscHandling.getRaceIDFromStageID(stageId);
				Race race = MiscHandling.getRace(raceID);
				Stage stage = race.getStage(stageId);
				//Construct the categorisedClimb with given params
				Checkpoint categorisedClimb = new Checkpoint(location, length, averageGradient, type, stageId, raceID);
				int checkpointID = categorisedClimb.getCheckpointID();
				//Add the checkpoint to the stage
				stage.addCheckpoint(categorisedClimb);
				//return the checkpointID
				return checkpointID;
			} catch (Exception e) {throw e;}
			

	}

	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
				try
				{	
					//Get the race and stage to add the checkpoint to
					int raceID = MiscHandling.getRaceIDFromStageID(stageId);
					Race race = MiscHandling.getRace(raceID);
					Stage stage = race.getStage(stageId);
					//Construct the new checkpoint with the correct parameters, length and gradient
					//do not matter here so we assign them null
					Checkpoint intermediateSprint = new Checkpoint(location, null, null, CheckpointType.SPRINT, stageId, raceID);
					//Add the checkpoint to stage and return the checkpointID
					stage.addCheckpoint(intermediateSprint);
					return intermediateSprint.getCheckpointID();
				}
				catch(Exception e) {throw e;}
		
	}

	@Override
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		//Retrieve the stageID and raceID from the checkpointID and create the
		//race and stage object to remove the checkpoint from stage
		int[] stageAndRaceID = MiscHandling.getStageIDFromCheckpointID(checkpointId);
		Race race = MiscHandling.getRace(stageAndRaceID[1]);
		Stage stage = race.getStage(stageAndRaceID[0]);
		stage.removeCheckpoint(checkpointId);
	}

	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		//Create the race and update its' state
		String state = "waiting for results.";
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		Race race = MiscHandling.getRace(raceID);
		Stage stage = race.getStage(stageId);
		stage.setStageState(state);


	}

	@Override
	public int[] getStageCheckpoints(int stageId) throws IDNotRecognisedException {
		//get the stage object with the checkpoints 
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		Race race = MiscHandling.getRace(raceID);
		Stage stage = race.getStage(stageId);
		//get the array of the checkpointIDs from stage
		int[] checkpointIDs = stage.getStageCheckpoints();

		return checkpointIDs;
	}


	//This function needs to have the duplicated result exception fixed
	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		try {
			Team team = new Team(name, description);
			return team.getTeamID();
		} catch (Exception e) {throw e;}
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		try{
		MiscHandling.removeTeam(teamId);
		}
		catch (IDNotRecognisedException e) {throw e;}
	}

	@Override
	public int[] getTeams() {
		
		int[] teamIDs = MiscHandling.getTeamIDs();
		return teamIDs;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		try {
			Team team = MiscHandling.getTeam(teamId);
		return team.getRiderIDArray();
		} catch (IDNotRecognisedException e) {throw e;}
		
	}

	//This function needs to have its duplicated result exception removed
	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		try {
			Rider rider = new Rider(teamID, name, yearOfBirth);
			return rider.getRiderID();
		} catch (Exception e) {throw e;}
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		try {
			MiscHandling.removeRace(riderId);
		} catch (IDNotRecognisedException e) {throw e;}
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointTimesException,
			InvalidStageStateException {
		
			//retrieve the race and stage object
			int raceID = MiscHandling.getRaceIDFromStageID(stageId);
			Race race = MiscHandling.getRace(raceID); 
			Stage stage = race.getStage(stageId);
			//For each time in the checkpoints, we add it to stage with the corresponding riderID
			for (LocalTime times : checkpoints)
			{
				stage.addRiderStageTime(riderId, times);
			}
			

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		try{
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		GeneralClassification generalClassification = new GeneralClassification(raceID);
		return generalClassification.getAdjustedTimes(riderId);
		}
		catch(IDNotRecognisedException e){throw e;}
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		try{
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		Race race = MiscHandling.getRace(raceID);
		Stage stage = race.getStage(stageId);
		stage.removeRiderStageTime(riderId);
		}
		catch(IDNotRecognisedException e){throw e;}
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
