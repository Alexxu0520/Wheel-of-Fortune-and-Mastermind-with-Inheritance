import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
// the version of AIPlayer of WheelOfFortune
public class WheelOfFortuneAIGame extends WheelOfFortune{

    private int left = 3;
    private boolean needNewGamePlayer = true;
    private boolean needNewPhrase = true;
    private GameRecord gameRecord = new GameRecord();
    private GameRecord gameRecordNew = new GameRecord();
    private List<String> phraseList = null;
    private Random rand = new Random();
    private playerAlphabetical playerAlpha = new playerAlphabetical();
    private WheelOfFortunePlayer currentPlayer;
    private int countPlayer = 0;
    List <WheelOfFortunePlayer> playArray = new ArrayList<>();
    //create a game which has a default player
    public WheelOfFortuneAIGame(){
        playArray.add(playerAlpha);
    }

    public WheelOfFortuneAIGame(WheelOfFortunePlayer player){
        playArray.add(player);
    }
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> array){
        playArray = array;
    }
    // generate a randomPhrase to guess
    @Override
    public void randomPhrase() {
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
        }
        //replaced with asterisks
    }
    @Override
    // activate program and coordinate all method
    public GameRecord play(){
        if(needNewGamePlayer == true){
            needNewGamePlayer = false;
            currentPlayer = playArray.get(countPlayer);
            gameRecord.id = currentPlayer.playerId();
            gameRecord.score = 0;
            countPlayer++;
        }
        int count = 0;
        randomPhrase();
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
            System.out.println(playerAlphabetical.robotCount);
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
    // check if we need a new game
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
    // get the next guess
    public char getGuess(){
        guess =  currentPlayer.nextGuess();
        return guess;
    }
    // cast to string
    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" +
                "left=" + left +
                ", needNewGamePlayer=" + needNewGamePlayer +
                ", needNewPhrase=" + needNewPhrase +
                ", gameRecord=" + gameRecord +
                ", gameRecordNew=" + gameRecordNew +
                ", phraseList=" + phraseList +
                ", rand=" + rand +
                ", playerAlpha=" + playerAlpha +
                ", currentPlayer=" + currentPlayer +
                ", countPlayer=" + countPlayer +
                ", playArray=" + playArray +
                '}';
    }
    //  provide for sort
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return left == that.left && needNewGamePlayer == that.needNewGamePlayer && needNewPhrase == that.needNewPhrase && countPlayer == that.countPlayer && Objects.equals(gameRecord, that.gameRecord) && Objects.equals(gameRecordNew, that.gameRecordNew) && Objects.equals(phraseList, that.phraseList) && Objects.equals(rand, that.rand) && Objects.equals(playerAlpha, that.playerAlpha) && Objects.equals(currentPlayer, that.currentPlayer) && Objects.equals(playArray, that.playArray);
    }

    public static void main(String[] args)  {
        List<WheelOfFortunePlayer> list = new ArrayList<>();
        WheelOfFortunePlayer playerAlpha = new playerAlphabetical();
        WheelOfFortunePlayer playerFrequnce = new playerFrequency();
        WheelOfFortunePlayer playerRand = new playerRandom();
        list.add(playerAlpha);
        list.add(playerFrequnce);
        list.add(playerRand);
        // why we need cast list into (ArrayList<WheelOfFortunePlayer>) list?
        WheelOfFortuneAIGame WheelOfFortuneAI = new WheelOfFortuneAIGame(list);
        AllGamesRecord record = WheelOfFortuneAI.playAll();
        System.out.println(record);
        System.out.println("Total average is "+ record.average());
        System.out.println("playerAlphabetical average is "+ record.average(playerAlpha.playerId()));
        System.out.println("playerFrequnce average is "+ record.average(playerFrequnce.playerId()));
        System.out.println("playerRand average is "+ record.average(playerRand.playerId()));
        System.out.println("highGameList "+ record.highGameList(playerAlpha.playerId(),3));
        System.out.println("highGameList "+ record.highGameList(playerFrequnce.playerId(),3));// or call specific functions of record
        System.out.println("highGameList "+ record.highGameList(playerRand.playerId(),3));
    }
}
