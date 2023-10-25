import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// player using random to guess
public class playerRandom implements WheelOfFortunePlayer {
    public int random;
    public static Map<Integer,Character> map = new HashMap<>();
    //get the next guess from the player
    public char nextGuess(){
        Random rand = new Random();
        random = rand.nextInt(26); // gets 0, 1, or 2
        map.put(0,'e');map.put(1,'t');map.put(2,'a');map.put(3,'o');map.put(4,'i');map.put(5,'n');map.put(6,'r');map.put(7,'s');
        map.put(8,'h');map.put(9,'d');map.put(10,'c');map.put(11,'l');map.put(12,'m');map.put(13,'p');map.put(14,'u');map.put(15,'f');
        map.put(16,'g');map.put(17,'w');map.put(18,'y');map.put(19,'b');map.put(20,'k');map.put(21,'j');map.put(22,'v');map.put(23,'x');
        map.put(24,'q');map.put(25,'z');
        char guess = map.get(random);
        return guess;
    }
    // — an id for the player
    public String playerId(){
        return "playerRandom";
    }
    //reset the player to start a new game
    public void reset(){
    }
}
