import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class player1 implements WheelOfFortunePlayer{
    public static int robotCount = 0;
    public static Map<Integer,Character> map = new HashMap<>();
    //get the next guess from the player
    public char nextGuess(){
            map.put(0,'a');map.put(1,'b');map.put(2,'c');map.put(3,'d');map.put(4,'e');map.put(5,'f');map.put(6,'g');map.put(7,'h');
            map.put(8,'i');map.put(9,'j');map.put(10,'k');map.put(11,'l');map.put(12,'m');map.put(13,'n');map.put(14,'o');map.put(15,'p');
            map.put(16,'q');map.put(17,'r');map.put(18,'s');map.put(19,'t');map.put(20,'u');map.put(21,'v');map.put(22,'w');map.put(23,'x');
            map.put(24,'y');map.put(25,'z');
            char guess = map.get(robotCount);
            robotCount++;
            return guess;
    }
    // — an id for the player
    public String playerId(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }
    //reset the player to start a new game
    public void reset(){
        robotCount = 0;
    }
}
