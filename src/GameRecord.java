import java.util.*;
public class GameRecord implements Comparable<GameRecord> {

    //GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
    // It must implement Comparable and provide a default implementation of compareTo which compares scores.
    Integer score;
    String id;

    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(GameRecord o){
        if(this.score < o.score){
            return -1;
        }
        else if(this.score > o.score){
            return 1;
        }
        return 0;
    }
}