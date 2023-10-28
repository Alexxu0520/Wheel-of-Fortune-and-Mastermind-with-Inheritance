import java.util.*;

import static java.util.Collections.reverse;
import static java.util.Collections.sort;

public class AllGamesRecord {
    @Override
    public String toString() {
        for(int i = 0; i < recordList.size();i++){
            System.out.println(recordList.get(i));
        }
        return "Congratulations";
    }

    private List<GameRecord> recordList = new ArrayList<>();
    private int num = 0;
    private int playerSum = 0;
    private int sum;
    //adds a GameRecord to the AllGamesRecord
    public void add(GameRecord gr){
        recordList.add(gr);
    }
    //returns the average score for all games added to the record
    public Integer average(){
        for(GameRecord g : recordList){
            sum += g.score;
        }
        return sum/recordList.size();
    }
    //average(playerId) -- returns the average score for all games of a particular player
    public Integer average(String playerId){
        playerSum = 0;
        num = 0;
        for(GameRecord g : recordList){
            if(playerId == g.id){
                playerSum += g.score;
                num += 1;
            }
        }
        return playerSum/num;
    }
    //highGameList(n)-- returns a sorted list of the top n scores including player and score.
    // This method should use the Collections class to sort the game instances.
    public List<GameRecord> highGameList(Integer n){
        sort(recordList);
        List<GameRecord> high = new ArrayList<>();
        for(int i = 0; i < n; i++){
        high.add(recordList.get(i));
        }
        return high;
    }
    //highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player..
    // This method should use the Collections class to sort the game instances.
    public List<GameRecord> highGameList(String playerId, Integer n){
        List<GameRecord> player = new ArrayList<>();
        for(int i = 0; i < recordList.size(); i++){
            if(recordList.get(i).id == playerId) {
                player.add(recordList.get(i));
            }
        }
        sort(player);
        List<GameRecord> highPlayer = new ArrayList<>();
        for(int i = 0; i < n; i++){
            highPlayer.add(player.get(i));
        }
        return highPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllGamesRecord that = (AllGamesRecord) o;
        return num == that.num && playerSum == that.playerSum && sum == that.sum && Objects.equals(recordList, that.recordList);
    }
}
