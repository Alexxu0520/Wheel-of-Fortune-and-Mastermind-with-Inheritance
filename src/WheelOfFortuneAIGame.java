import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class WheelOfFortuneAIGame extends WheelOfFortune{

    public int left = 3;
    boolean needNewGamePlayer = true;
    boolean needNewPhrase = true;
    GameRecord gameRecord = new GameRecord();
    GameRecord gameRecordNew = new GameRecord();
    List<String> phraseList = null;
    Random rand = new Random();
    player1 p1 = new player1();
    static player2 p2 = new player2();
    player3 p3 = new player3();
    WheelOfFortunePlayer currentPlayer;
    int countPlayer = 0;
    List <WheelOfFortunePlayer> playArray = new ArrayList<>();
    public WheelOfFortuneAIGame(){
        playArray.add(p1);
    }

    public WheelOfFortuneAIGame(WheelOfFortunePlayer player){
        playArray.add(player);
    }
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> array){
        playArray = array;
    }
    @Override
    public boolean randomPhrase() {
        if(needNewPhrase == true){
            left = 3;
            needNewPhrase = false;
            // Get the phrase from a file of phrases
            try {
                phraseList = Files.readAllLines(Paths.get("phrases.txt"));
            } catch (IOException e) {
                System.out.println(e);
            }
            // Get a random phrased from the list
        }
        if(left != 0){
            int r = rand.nextInt(left); // gets 0, 1, or 2
            phrase = phraseList.get(r).toLowerCase();
            phraseList.remove(r);
            left--;
        }
        else{
            System.out.println("No more phrases");
            return false;
        }
        return true;
        //replaced with asterisks
    }
    @Override
    public GameRecord play(){
        if(needNewGamePlayer == true){
            needNewGamePlayer = false;
            currentPlayer = playArray.get(countPlayer);
            gameRecord.id = currentPlayer.playerId();
            gameRecord.score = 0;
            countPlayer++;
        }
        int count = 0;
        boolean a = randomPhrase();
        generateHiddenPhrase();
        Set set = new HashSet();
        while (true) {
            char[] ori = phrase.toCharArray();
            if (Arrays.equals(ori, hiddenArray)) {
                System.out.println("congratulations");
                gameRecord.score = 10 - count;
                break;
            }
            if (count == 10) {
                System.out.println("Sorry you lose");
                gameRecord.score = 0;
                break;
            }
            getGuess();
            System.out.println(guess);
            System.out.println(p1.robotCount);
            if (set.contains(guess)) {
                System.out.println("already used");
                continue;
            }
            processGuess();
            set.add(guess);
            if (Arrays.equals(newArray, hiddenArray)) {
                count++;
                System.out.println("used " + count + " times");
            } else {
                hiddenArray = newArray;
            }
        }
        // Deep copy GameRecord
        gameRecordNew = new GameRecord();
        gameRecordNew.id = gameRecord.id;
        gameRecordNew.score = gameRecord.score;
        currentPlayer.reset();
        return gameRecordNew;
    }

    @Override
    public boolean playNext() {
        if (left == 0) {
            System.out.println("No more phrases");
            needNewGamePlayer = true;
            needNewPhrase = true;
        }
        if(left == 0 && countPlayer == playArray.size()){
            return false;
        }
        return true;
    }
    @Override
    public char getGuess(){
        guess =  currentPlayer.nextGuess();
        return guess;
    }
    public static void main(String[] args)  {
        List<WheelOfFortunePlayer> list = new ArrayList<>();
        WheelOfFortunePlayer p1 = new player1();
        WheelOfFortunePlayer p2 = new player2();
        WheelOfFortunePlayer p3 = new player3();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        WheelOfFortuneAIGame WOFA = new WheelOfFortuneAIGame((ArrayList<WheelOfFortunePlayer>) list);
        AllGamesRecord record = WOFA.playAll();
        System.out.println(record);
        System.out.println("Total average is "+ record.average());
        System.out.println("p1 average is "+ record.average(p1.playerId()));
        System.out.println("p2 average is "+ record.average(p2.playerId()));
        System.out.println("p3 average is "+ record.average(p3.playerId()));
        System.out.println("highGameList "+ record.highGameList(p1.playerId(),3));
        System.out.println("highGameList "+ record.highGameList(p2.playerId(),3));// or call specific functions of record
        System.out.println("highGameList "+ record.highGameList(p3.playerId(),3));
    }
}
