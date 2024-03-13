package cycling;

import java.util.Arrays;

public class PersonalTestingSystem {
    public static void main( String [] args) {
        Team var1 = new Team("Hehe", "heheheha");
        Team var2 = new Team("Hehe", "heheheha");
        System.out.println(var1.getTeamID());
        System.out.println(var2.getTeamID());
        System.out.println(Arrays.toString(var1.getRiderIDArray()));
    }
}
