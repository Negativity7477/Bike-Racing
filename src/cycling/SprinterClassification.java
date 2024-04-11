package cycling;
import java.time.LocalTime;
import java.util.HashMap;

public class SprinterClassification {
    
    protected int raceID;
    protected HashMap<Integer, Integer> riderPointsHash;
    protected int[] ridersRanked;
    protected HashMap<Integer, PointHandling> stageLevelHash;

    /**
     * Constructor
     * 
     * @param raceID A unique ID for the race being requested
     */
    public SprinterClassification(int raceID)
    {
        this.raceID = raceID;
        this.stageLevelHash = new HashMap<Integer, PointHandling>();
        this.riderPointsHash = new HashMap<Integer, Integer>();
    }

    /**
     * Getter for ridersRanked
     * 
     * @return An array of riderIDs in the sprinter classification
     */
    public int[] getRidersRanked() {
        return ridersRanked;
    }

    /**
     * @param riderID - unique rider identifier
     * @return - the points of a certain rider
     * 
     * This function returns the respective point value stored in the hash
     * for a rider
     */
    public int getRiderPoints(int riderID) throws IDNotRecognisedException
    {
        if (riderPointsHash.get(riderID) == null)
        {
            throw new IDNotRecognisedException("ID not found in hash");
        }
        return riderPointsHash.get(riderID);
    }


    /**
     * 
     * @param riderID - Unique rider indentifier
     * @param handler - An instance of pointHandling, so we can deal with points
     * @throws IDNotRecognisedException - incase we try to access a rider without the correct ID
     * 
     * Retrieve the current points stored in the hashmap, and readd an updated point back
     */
    public void addRiderPoints(int riderID, PointHandling handler)
    {
        int points = riderPointsHash.get(riderID);
        riderPointsHash.put(riderID, points + handler.getPoints(riderID));
    }

    /**
     * This function is a sorting algorithm (bubble sort) based on rider points
     */
    public void rankRiders()
    {
        int tempID;

        // Filling out ridersRanked with all riderIDs present
        ridersRanked = new int[riderPointsHash.size()];
        int position = 0;
        for (int riderID : riderPointsHash.keySet()) {
            ridersRanked[position++] = riderID;
        }

        // Bubble sort with ridersRanked based on the rider's points
        for (int i = 0; i < ridersRanked.length-1; i++) {
            for (int k = 0; k < ridersRanked.length-2; i++) {

                if (riderPointsHash.get(ridersRanked[k]) > riderPointsHash.get(ridersRanked[k+1])) {
                    tempID = ridersRanked[k+1];
                    ridersRanked[k+1] = ridersRanked[k];
                    ridersRanked[k] = tempID;
                }
            }
        }
    }

        public class PointHandling extends GeneralClassification
        {
            protected int stageID;
            protected int[] positionArray;
            protected HashMap<Integer, Integer> pointsHash;
            protected Race raceObject;
            protected Stage stageObject;

            /**
             * 
             * @param raceID - unique identifier for race
             * @param stageID - unique identifier for stage
             * 
             * Constructor for PointHandling, inherits from GeneralClassification
             */
            public PointHandling(int raceID, int stageID) throws IDNotRecognisedException
            {
                super(raceID);
                try {
                    raceObject = MiscHandling.getRace(raceID);
                    stageObject = raceObject.getStage(stageID);
                    this.stageID = stageID;
                    this.pointsHash = new HashMap<Integer,Integer>();
                } catch (Exception e) {throw e;}
            
            
            }
 
            @Override // Sets up riders for a stage instead of a race
            public void setRiderTimes()
            {   
                int[] riderArray = stageObject.getRiderIDsInStage();

                rankedArray = new LocalTime[riderArray.length];
                int position = 0;
                for (int riderID : riderArray) {
                    rankedArray[position++] = stageObject.getRiderStageTime(riderID);
                }
            }

            /**
             * Getter for a rider's points
             * 
             * @param riderID ID of the rider being queried
             * @return The points the rider earned in the stage or checkpoint,
             *         if the rider is not found then 0 is returned
             */
            public int getPoints(int riderID) {

                int points = 0;

                if (pointsHash.get(riderID) != null) {
                    points = pointsHash.get(riderID);
                }

                return points;
            }

            // Getter for points from a hash
            public int getRiderPoints(int riderID) throws IDNotRecognisedException
            {
                    if(pointsHash.get(riderID) == null)
                    {
                        throw new IDNotRecognisedException("ID was not found in the hash");
                    }
                    int points = pointsHash.get(riderID);
                    return points;
            }

            /**
             * Describes how points should be given based on the type of the stage
             * 
             * @param competitionType The type of stage being queried
             * @return An array that describes how points should be given, 
             *         where the index of the array matches the position of the rider - 1,
             *         an empty array is returned if the stage type is not recognised
             */
            private int[] getPointsDistribution(StageType competitionType) {
                
                int[] pointDistribution;

                switch (competitionType) {

                    case FLAT:
                        pointDistribution = new int[]{50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2};
                        break;

                    case HIGH_MOUNTAIN:
                        pointDistribution = new int[]{30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2};
                        break;

                    case MEDIUM_MOUNTAIN:
                        pointDistribution = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                        break;

                    case TT:
                        pointDistribution = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                        break;

                    // Gives an empty array when given an unrecognised competition type
                    default:
                        pointDistribution = new int[]{};
                }

                return pointDistribution;
            }

            /**
             * Describes how points should be given based on the type of the checkpoint
             * 
             * @param competitionType The type of checkpoint being queried
             * @return An array that describes how points should be given, 
             *         where the index of the array matches the position of the rider - 1,
             *         an empty array is returned if the stage type is not recognised
             */
            private int[] getPointsDistribution(CheckpointType competitionType) {

                int[] pointDistribution;

                switch (competitionType) {

                    case C1:
                        pointDistribution = new int[]{10, 8, 6, 4, 2, 1};
                        break;

                    case C2:
                        pointDistribution = new int[]{5, 3, 2, 1};
                        break;

                    case C3:
                        pointDistribution = new int[]{2, 1};
                        break;

                    case C4:
                        pointDistribution = new int[]{1};
                        break;

                    case HC:
                        pointDistribution = new int[]{20, 15, 12, 10, 8, 6, 4, 2};
                        break;

                    case SPRINT:
                        pointDistribution = new int[]{20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                        break;

                    // Gives an empty array when given an unrecognised competition type
                    default:
                        pointDistribution = new int[]{};
                        break;
                }

                return pointDistribution;
            }

            /**
             * Gives riders points based on their times
             * 
             * @param distribution An array describing how points should be given to riders,
             *                     where the index of the array + 1 matches the riders position
             */
            public void submitRiderPoints(int[] distribution)
            {
                rankRiders();
                int riderID;
                int pointsToAdd;

                for (int i = 0; i < riderArray.length-1; i++) {
                    riderID = riderArray[i];
                    pointsToAdd = distribution[i];
                    pointsHash.put(riderID, pointsToAdd);
                    addRiderPoints(riderID, this);
                }


            }

            /**
             * Adds points from the stage and possible valid checkpoints for this instance of point handling
             */
            public void distributePoints() {
                int[] distribution;

                // Checking if an intermediate sprint is present as their points are considered in a Sprinter classification
                if (stageObject.findIntermediateSprint() != null) {

                    distribution = getPointsDistribution(CheckpointType.SPRINT);
                    submitRiderPoints(distribution);
                }

                // Sorts out points for the stage in general
                distribution = getPointsDistribution(stageObject.getStageType());
                submitRiderPoints(distribution);
            }
        }
}
