package cycling;
import java.time.LocalTime;

public class mroetestinsting {
    public static void main(String[] args) {
        LocalTime object;
        long number = 3759000000023L;
        object = MiscHandling.longToLocalTime(number);
        System.out.println(object);
        System.out.println(number);
        number = MiscHandling.localTimeToLong(object);
        System.out.println(number);

    }
}
