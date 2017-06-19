/**
 * Created by LaneBoy on 25.05.17.
 */
public class Main {
    public static  void main(String[] args) {
        try {
            Params params = new Params(new String[]{"tail","1.txt" ,"2.txt", "3.txt"});
            Tail tail = new Tail(params);
            tail.GetLines();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
