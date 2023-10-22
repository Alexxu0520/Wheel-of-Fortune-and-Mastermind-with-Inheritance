import java.util.Scanner;

public abstract class Game {
    public AllGamesRecord playAll() {
        AllGamesRecord allGameRecord = new AllGamesRecord();
        boolean flag = true;
        while (flag == true){
            GameRecord gameRecord = play();
            allGameRecord.add(gameRecord);
            flag = playNext();
 /*           if(WheelOfFortuneUserGame.needStore == true){
                WheelOfFortuneUserGame.needStore =false;
                allGameRecordNew.add(allGameRecord.recordList.get(allGameRecord.recordList.size()-1));
            }*/
        }
        return allGameRecord;
    }
    //- GameRecord play()-- plays a game and returns a GameRecord
    //- boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played). The function should return a boolean.
    public abstract GameRecord play();
    public abstract boolean playNext();
}
