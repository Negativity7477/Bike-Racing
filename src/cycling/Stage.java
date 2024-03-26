package cycling;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.HashMap;


public class Stage {
    private int stageID;
    private String stageName;
    private String description; 
    private double length;
    private LocalDateTime startTime;
    private StageType stageType;
    //Hash of checkpointID to checkpointType
    private HashMap<Integer, Checkpoint> checkpointIDHashMap;
    private static int nextStageID = 0;
    private int raceID;
    private HashMap<Integer, LocalTime> riderTimesHash;
    

    /**
     * 
     * @param raceID - ID of the race this stage is a part of (may not be needed)
     * @param stageName - Name of the given stage
     * @param description - Description of the stage
     * @param length - Length of the stage
     * @param startTime - Time the stage started 
     * @param StageType - Type of stage, types defined in StageType
     * @param checkpointIDHashMap - A hashmap corresponding to checkpoints making up the stage
     * each checkpointID maps to a checkpointType
     * 
     * Constructor for stage
     */
    public Stage(String stageName, String description, LocalDateTime startTime, StageType StageType, int RaceID) throws IDNotRecognisedException, InvalidNameException
    {
        try {
        this.checkName(stageName);
        this.stageName = stageName;
        this.description = description;
        this.startTime = startTime;
        this.stageType = stageType;
        this.stageID = nextStageID++;
        this.length = getStageLength();
        this.checkpointIDHashMap = new HashMap<Integer, Checkpoint>();

        //We can get the race we are add this stage to via 
        //the static class at the top level
        Race race = MiscHandling.getRace(RaceID);
        //We can add the stage to this race
        race.addStage(this);

        } catch (Exception e) {throw e;}
        
    }
    


    /**
     * @return - Name of stage
     * 
     * Allows us to get the stage name 
     */
    public String getStageName()
    {
        return stageName;
    }


    /**
     * @return - Description of stage
     * 
     * Allows us to get the stage description
     */
    public String getStageDescription()
    {
        return description;
    }


    /**
     * @return - Unique identifier of stage
     * 
     * Allows us to get the stage identifier 
     */
    public int getStageID()
    {
        return stageID;
    }

    /**
     * Stages are made of checkpoints, this function should add a checkpoint
     * Checkpoints are either an intermediate sprint or a categorised climb
     */
    public void addCheckpoint(Checkpoint type) 
    {
        int checkpointID = type.getCheckpointID();
        addCheckpointToHash(type);      
    }



    /*
     * This function removes a checkpoint 
     */
    public void removeCheckpoint(int checkPointID) throws IDNotRecognisedException
    {
        if(checkpointIDHashMap.containsKey(checkPointID))
        {
            checkpointIDHashMap.remove(checkPointID);
        }
        else
        {
            throw new IDNotRecognisedException("No Checkpoint correponds to ID");
        }
    }

    /**
     * 
     * @param checkpointID - Each checkpoint's unique identifier
     * @param checkpointType - Each checkpoint's type defined in CheckpointType
     * 
     * This function is used to generate the hash, showing what checkpoint is what type
     * This hash should be used in anywhere we need to determine type of checkpoint
     */
    private void addCheckpointToHash(Checkpoint checkpoint)
    {
        checkpointIDHashMap.put(checkpoint.getCheckpointID(), checkpoint);
    }

    /**
     * 
     * @return - Array of all checkpointIDs making up a stage
     * 
     * get all checkpointIDs as an array
     */
    public int[] getStageCheckpoints()
    {
        int[] allCheckpoints = new int[checkpointIDHashMap.size()];
        int position = 0;

        // Loops through hash table and adds all checkpointIDs to an array 
        for (Integer checkpointID : checkpointIDHashMap.keySet())
        {
            allCheckpoints[position++] = checkpointID;
        }
        return allCheckpoints;
    }

    
    /**
     * @param checkpointHashMap - Hashmap of checkpointID to the checkpoint
     * @return - an array of all checkpoints in stage
     * 
     * Allows us to get every checkpoint in a stage
     */
    private Checkpoint[] returnAllCheckpoints()
    {
        Checkpoint[] allCheckpoints = new Checkpoint[checkpointIDHashMap.size()];
        int position = 0;

        for (Checkpoint checkpoint: checkpointIDHashMap.values())
        {
            allCheckpoints[position++] = checkpoint;
        }
        return allCheckpoints;
    }

    
    /**
     * @param checkpointHashMap - Hashmap of checkpointID to the checkpoint
     * @return = the distance of the stage
     * 
     * Allows us to work out the total length of the stage from the checkpoints
     * making up the stage 
     */
    public Double getStageLength()
    {
        Double totalDistance = 0.0;
        Checkpoint[] allCheckpoints = returnAllCheckpoints();
        for(int i = 0; i < allCheckpoints.length; i++)
        {
            totalDistance += allCheckpoints[i].getCheckpointLength();
        }

        return totalDistance;
    }

    private void checkName(String name) throws InvalidNameException
    {
        if(name != null)
        {
            for(int i = 0; i < name.length(); i++)
            {
                if(Character.isWhitespace(name.charAt(i)))
                {
                    throw new InvalidNameException("Name does not fit the convention");
                }
            }
        }
        else{
        throw new InvalidNameException("Name does not fit the convention");
        }
    }

    
    /**
     * 
     * @param riderID - unique identifier of the rider
     * @param riderTime - the time to add to the hash for this stage
     * Adds the time the rider got in this stage to the hashmap storing them
     */
    public void addRiderStageTime(int riderID, LocalTime riderTime)
    {
        riderTimesHash.put(riderID, riderTime);
    }

    
    /**
     * 
     * @param riderID - unique identifier of the rider
     * @return - the time to complete the stage for a given rider
     * 
     * returns the time the rider took to complete the stage
     */
    public LocalTime getRiderStageTime(int riderID)
    {
        LocalTime riderTime = riderTimesHash.get(riderID);
        return riderTime;
    }


    /**
     * 
     * @param riderID- unique identifier of the rider
     * 
     * removes the rider and their time's from the hash
     */
    public void removeRiderStageTime(int riderID)
    {
        riderTimesHash.remove(riderID);
    }

    /**
     * Orders checkpoints based on their location
     * 
     * @return
     */
    private Checkpoint[] getOrderedCheckpointArray() {

        int numCheckpoints = checkpointIDHashMap.size();
        Checkpoint[] orderedArray = new Checkpoint[numCheckpoints];
        int counter = 0;
        Checkpoint temp;

        // Creates an array of checkpoints in the stage
        for (Checkpoint checkpointObject : checkpointIDHashMap.values()) {
            orderedArray[counter] = checkpointObject;
            counter++;
        }

        // Bubble sort implementation
        for (int i = 0; i < numCheckpoints; i++) {
            for (int k = 0; k < numCheckpoints-1; k++) {
                if (orderedArray[k].getCheckpointLocation() > orderedArray[k+1].getCheckpointLocation()) {
                    temp = orderedArray[k];
                    orderedArray[k] = orderedArray[k+1];
                    orderedArray[k+1] = temp;
                }
            }
        }

        return orderedArray;
    }

    public void setCheckpointTimes() throws IDNotRecognisedException, InvalidCheckpointTimesException {

        int numCheckpoints = checkpointIDHashMap.size();
        Rider[] riderArray = MiscHandling.getRiderArray();
        Checkpoint[] checkpointArray = getOrderedCheckpointArray();
        LocalTime[] riderTimeArray;

        // Loops through all riders in the program
        for (Rider riderObject : riderArray) {

            // Gets results from the rider for a specific stage
            try {
            riderTimeArray = riderObject.getStageResults(stageID);
            } catch(Exception e) {throw e;}

            // Checks if the rider's results will match with the checkpoints given to this stage
            if (numCheckpoints != checkpointArray.length) {
                throw new InvalidCheckpointTimesException("The number of checkpoint times given to a rider does not match the number of checkpoints in the stage");
            }

            // Adds the rider's times to the checkpoints in this stage
            for (int i=0; i<numCheckpoints; i++) {
                checkpointArray[i].addRiderCheckpointTime(riderObject.getRiderID(), riderTimeArray[i]);
            }
        }
    }
}
