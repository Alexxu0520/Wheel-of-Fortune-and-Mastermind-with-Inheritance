import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class player2 implements WheelOfFortunePlayer{
        public static int robotCount = 0;
        public static Map<Integer,Character> map = new HashMap<>();
        //get the next guess from the player
        public char nextGuess(){
/*        1   E 12.25 2   T 9.41 3   A 8.19 4   O 7.26  5    I 7.10  6    N 7.06 7    R 6.85 8    S 6.36 9    H 4.57 10  D 3.91 11  C 3.8312  L 3.77
        13  M 3.34 14  P 2.89 15  U 2.58 16  F 2.2617  G 1.71 18 W 1.59 19 Y 1.58 20 B 1.47
        21 K 0.4122 J 0.14 23 V 1.09 24  X 0.2125 Q 0.09
        26 Z 0.08*/
            map.put(0,'e');map.put(1,'t');map.put(2,'a');map.put(3,'o');map.put(4,'i');map.put(5,'n');map.put(6,'r');map.put(7,'s');
            map.put(8,'h');map.put(9,'d');map.put(10,'c');map.put(11,'l');map.put(12,'m');map.put(13,'p');map.put(14,'u');map.put(15,'f');
            map.put(16,'g');map.put(17,'w');map.put(18,'y');map.put(19,'b');map.put(20,'k');map.put(21,'j');map.put(22,'v');map.put(23,'x');
            map.put(24,'q');map.put(25,'z');
            char guess = map.get(robotCount);
            robotCount++;
            return guess;
        }
        // — an id for the player
        public String playerId(){
            return "p2";
        }
        //reset the player to start a new game
        public void reset(){
            robotCount = 0;
        }
}
