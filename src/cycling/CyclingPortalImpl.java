package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import cycling.SprinterClassification.PointHandling;


/**
 * BadCyclingPortal is a minimally compiling, but non-functioning implementor
 * of the CyclingPortal interface.
 * 
 * @author Diogo Pacheco
 * @version 2.0
 *
 */
public class CyclingPortalImpl implements CyclingPortal {

	@Override
	public int[] getRaceIds() {
		return MiscHandling.getRaceIDs();
	}

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
		catch (Exception e) {throw e;} 
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


	//No time to check for invalidLengthException
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
				if (stage.getStageState().equals("Stage has been created")) {
					{throw new InvalidStageStateException("Stage state is wrong");}
				}
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
                    if (stage.getStageState().equals("Stage has been created")) {
                        
                            throw new InvalidStageStateException("Stage state is wrong");
                        }
                    else{
                    //Construct the new checkpoint with the correct parameters, length and gradient
                    //do not matter here so we assign them null
                    Checkpoint intermediateSprint = new Checkpoint(location, CheckpointType.SPRINT, stageId, raceID);
                    //Add the checkpoint to stage and return the checkpointID
                    stage.addCheckpoint(intermediateSprint);
                    return intermediateSprint.getCheckpointID();
                    }
                }
                catch(Exception e) {throw e;}
        
    }
	public void removeCheckpoint(int checkpointId) throws IDNotRecognisedException, InvalidStageStateException {
		//Retrieve the stageID and raceID from the checkpointID and create the
		//race and stage object to remove the checkpoint from stage
		try{
		int[] stageAndRaceID = MiscHandling.getRaceStageIDFromCheckpointID(checkpointId);
		Race race = MiscHandling.getRace(stageAndRaceID[1]);
		Stage stage = race.getStage(stageAndRaceID[0]); 
		if (stage.getStageState().equals("Stage has been created")) 
		{
			throw new InvalidStageStateException("Stage state is wrong");
		}
			
		stage.removeCheckpoint(checkpointId);
		}
		catch(Exception e) {throw e;}
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

		int teamID = MiscHandling.getTeamIDFromRiderID(riderId);
		Team teamObject = MiscHandling.getTeam(teamID);
		Rider riderObject = teamObject.getRider(riderId);

		riderObject.addResults(stageId, checkpoints);
		/* 
		try{
			//retrieve the race and stage object
			int raceID = MiscHandling.getRaceIDFromStageID(stageId);
			Race race = MiscHandling.getRace(raceID); 
			Stage stage = race.getStage(stageId);
			if (stage.getStageState().equals("Waiting for results.")) {
				throw new InvalidStageStateException("Stage state is wrong");
			}
			//For each time in the checkpoints, we add it to stage with the corresponding riderID
			for (LocalTime times : checkpoints)
			{
				stage.addRiderStageTime(riderId, times);
			}
		}
		catch(Exception e) {throw e;}
		*/

	}

	//Need to look over this
	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {


		int teamIdOfRider = MiscHandling.getTeamIDFromRiderID(riderId);
		Team teamOfRider = MiscHandling.getTeam(teamIdOfRider);
		Rider riderObject = teamOfRider.getRider(riderId);

		return riderObject.getStageResults(stageId);

	}

	// Needs looking at
	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		SprinterClassification sc = new SprinterClassification(raceID);
		PointHandling stageToAdjust = sc.new PointHandling(raceID, stageId);
		stageToAdjust.rankRiders();
		stageToAdjust.adjustTimes();
		return stageToAdjust.getAdjustedTimes(riderId);
		
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		try{
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		Race race = MiscHandling.getRace(raceID);
		Stage stage = race.getStage(stageId);
		stage.removeRiderStageTime(riderId);

		int teamID = MiscHandling.getTeamIDFromRiderID(riderId);
		Team teamObject = MiscHandling.getTeam(teamID);
		Rider riderObject = teamObject.getRider(riderId);
		riderObject.removeStageCheckpointTimes(stageId);

		}
		catch(IDNotRecognisedException e){throw e;}
	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		SprinterClassification sc = new SprinterClassification(raceID);
		PointHandling stageToAdjust = sc.new PointHandling(raceID, stageId);
		stageToAdjust.rankRiders();
		return stageToAdjust.getRankedRiderArray();
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		SprinterClassification sc = new SprinterClassification(raceID);
		PointHandling stageToAdjust = sc.new PointHandling(raceID, stageId);
		stageToAdjust.rankRiders();
		stageToAdjust.adjustTimes();
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {

		int raceID = MiscHandling.getRaceIDFromStageID(stageId);
		SprinterClassification sprint = new SprinterClassification(raceID);
		PointHandling stageRanking = sprint.new PointHandling(raceID, stageId);

		stageRanking.setRiderTimes();
		stageRanking.distributePoints();

		int[] riderIDArray = getRidersRankInStage(stageId);
		int[] pointArray = new int[riderIDArray.length];
		for (int i = 0; i < riderIDArray.length-1; i++) {
			pointArray[i] = sprint.getRiderPoints(riderIDArray[i]);
		}

		return pointArray;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		//Functions to reset the static counters
		Team.resetTeamIDCount();
		Race.resetRaceIDCount();
		Checkpoint.resetCheckpointIDCount();
		Stage.resetStageIDCount();
		Rider.resetRiderIDCount();
		
		//Reset the hash storing everything 
		MiscHandling.resetHash();

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		Serialiser serialise = new Serialiser();
		try {
			serialise.saveFile(filename);
		} catch (Exception e) {throw e;}

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		Serialiser serialise = new Serialiser();
		try {
			serialise.loadFile(filename);
		} catch (Exception e) {throw e;}
	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		
		int raceID;

		try {
		raceID = MiscHandling.getRaceIDFromName(name);
		MiscHandling.removeRace(raceID);
		} catch(Exception e) {throw new NameNotRecognisedException();}
	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {

		Race raceObject = MiscHandling.getRace(raceId);
		int[] riderIDArray = getRidersGeneralClassificationRank(raceId);
		LocalTime[] timeArray = new LocalTime[riderIDArray.length];
		for (int i = 0; i < riderIDArray.length-1; i++) {
			timeArray[i] = raceObject.getRiderRaceTime(riderIDArray[i]);
		}

		return timeArray;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {

		SprinterClassification sprint = new SprinterClassification(raceId);
		Race raceObject = MiscHandling.getRace(raceId);
		int[] stageIDArray = raceObject.getRaceStages();
		PointHandling stageRanking;

		// Calculates points for all stages
		for (int stageID : stageIDArray) {
			stageRanking = sprint.new PointHandling(raceId, stageID);
			stageRanking.setRiderTimes();
			stageRanking.distributePoints();
		}

		// Ordering the points to fit the function's format
		int[] riderIDArray = getRidersGeneralClassificationRank(raceId);
		int[] pointArray = new int[riderIDArray.length];
		for (int i = 0; i < riderIDArray.length-1; i++) {
			pointArray[i] = sprint.getRiderPoints(riderIDArray[i]);
		}

		return pointArray;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		Race raceObject = MiscHandling.getRace(raceId);
		int[] riderIDArray = MiscHandling.getRiderIDArray();

		for (int riderID : riderIDArray) {
			raceObject.setRiderRaceTime(riderID);
		}

		GeneralClassification raceRanking = new GeneralClassification(raceId);
		raceRanking.setRiderTimes();
		raceRanking.rankRiders();
		riderIDArray = raceRanking.getRankedRiderArray();

		return riderIDArray;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		
		SprinterClassification sprint = new SprinterClassification(raceId);
		Race raceObject = MiscHandling.getRace(raceId);
		int[] stageIDArray = raceObject.getRaceStages();
		PointHandling stageRanking;

		// Calculates points for all stages
		for (int stageID : stageIDArray) {
			stageRanking = sprint.new PointHandling(raceId, stageID);
			stageRanking.setRiderTimes();
			stageRanking.distributePoints();
		}

		// Getting an array of riders and an array of points
		int[] riderIDArray = sprint.getRidersRanked();
		int[] pointArray = new int[riderIDArray.length];
		int position = 0;
		for (int riderID : riderIDArray) {
			pointArray[position++] = sprint.getRiderPoints(riderID);
		}

		// Bubble sort on the points array with the rider ID array following along
		int IDPlaceholder;
		int pointPlaceholder;
		for (int i = 0; i < riderIDArray.length-1; i++) {
			for (int k = 0; k < riderIDArray.length-2; k++) {
				
				if (pointArray[k] > pointArray[k+1]) {

					IDPlaceholder = riderIDArray[k+1];
					pointPlaceholder = pointArray[k+1];

					riderIDArray[k+1] = riderIDArray[k];
					pointArray[k+1] = pointArray[k];

					riderIDArray[k] = IDPlaceholder;
					pointArray[k] = pointPlaceholder;
				}
			}
		}

		return riderIDArray;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

}