import java.util.*;
//GameRecord keeps track of the score (integer) and player id (String) for a single play of a game.
// It must implement Comparable and provide a default implementation of compareTo which compares scores.
public class GameRecord implements Comparable<GameRecord> {
    protected Integer score;
    protected String id;

    // cast game record to string
    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", id='" + id + '\'' +
                '}';
    }
   //define compareTo to work for sort
    @Override
    public int compareTo(GameRecord o){
        if(this.score < o.score){
            return 1;
        }
        else if(this.score > o.score){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRecord that = (GameRecord) o;
        return Objects.equals(score, that.score) && Objects.equals(id, that.id);
    }
}