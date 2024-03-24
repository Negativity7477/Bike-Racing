package cycling;
import java.time.LocalTime;
import java.util.HashMap;

public class SprinterClassification {
    
    protected int raceID;
    protected HashMap<Integer, Integer> riderPointsHash;;
    protected int[] ridersRanked;
    protected HashMap<Integer, PointHandling> stageLevelHash;

    public SprinterClassification(int raceID)
    {
        this.raceID = raceID;
        this.stageLevelHash = new HashMap<Integer, PointHandling>();
        this.riderPointsHash = new HashMap<Integer, Integer>();
    }

    public void TotalPoints() //Function might be obselete, up to colum
    {

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
    public void addRiderPoints(int riderID, PointHandling handler) throws IDNotRecognisedException
    {
        try {
            int points = riderPointsHash.get(riderID);
            riderPointsHash.put(riderID, points + handler.getPoints(riderID));
        } catch (IDNotRecognisedException e) {throw e;}
    }

    /**
     * 
     * @return - An array of riders from the fastest time to the slowest in placement terms
     * 
     * This function is a sorting algorithm (bubble sort) to return a sorted array
     */
    public void rankRiders()
    {
        int temp;
    for (int i = 0; i < ridersRanked.length-1; i++)
    {
        if(ridersRanked[i]< ridersRanked[i+1])
        {
            temp=ridersRanked[i];
            ridersRanked[i]=ridersRanked[i+1];
            ridersRanked[i+1]= temp;
            i=-1;
        }
    }
    }

        public class PointHandling extends GeneralClassification
        {
            protected int stageID;
            protected int[] positionArray;
            protected StageType stageType;
            protected HashMap<Integer, Integer> pointsHash;

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
                    MiscHandling.getRaceIDFromStageID(stageID);
                    Race race = MiscHandling.getRace(raceID);
                    Stage stage = race.getStage(stageID);
                    this.stageID = stageID;
                    this.stageType = stage.getStageType();
                    this.pointsHash = new HashMap<Integer,Integer>();
                } catch (Exception e) {throw e;}
            
            
            }

            public void calculatePoints()
            {

            }

            public void submitRiderPoints()
            {

            }

            public void addRiderPoints()
            {

            }

            public void removeRider(int riderID)
            {

            }

            public int getRiderPoints(int riderID)
            {
                return 0;
            }

            public int getPoints(int riderID) throws IDNotRecognisedException
            {
                    if(pointsHash.get(riderID) == null)
                    {
                        throw new IDNotRecognisedException("ID was not found in the hash");
                    }
                    int points = pointsHash.get(riderID);
                    return points;
            }

            private int[] getRiders()
            {
                return ;
            }




        }
}
