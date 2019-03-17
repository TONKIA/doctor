
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        Date d = new Date();
        d.setTime(new Date().getTime() + 60000);
        System.out.println(d);
    }
}
