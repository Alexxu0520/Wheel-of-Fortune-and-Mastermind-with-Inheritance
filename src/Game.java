import java.util.Scanner;
// It is the ancestor of all game
public abstract class Game {
    // It can play a series of games, and return all game record
    public AllGamesRecord playAll() {
        AllGamesRecord allGameRecord = new AllGamesRecord();
        boolean flag = true;
        while (flag == true){
            GameRecord gameRecord = play();
            allGameRecord.add(gameRecord);
            flag = playNext();
        }
        return allGameRecord;
    }
    //plays a game and returns a GameRecord

    public abstract GameRecord play();
    // asks if the next game should be played (this will be called even to check if the first game should be played).
    public abstract boolean playNext();
}
