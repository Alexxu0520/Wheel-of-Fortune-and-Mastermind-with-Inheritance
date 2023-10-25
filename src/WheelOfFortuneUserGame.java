import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
//an UserGame version of WheelOfFortune
public class WheelOfFortuneUserGame extends WheelOfFortune{
    private int left = 3;
    private boolean needNewGamePlayer = true;
    private boolean needNewPhrase = true;
    private GameRecord gameRecord = new GameRecord();
    private GameRecord gameRecordNew = new GameRecord();
    private List<String> phraseList = null;
    private Random rand = new Random();
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
            System.out.println("please enter your name");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            gameRecord.id = s;
            gameRecord.score = 0;
        }
            int count = 0;
            randomPhrase();
//        if(!a){
//            return gameRecord;
//        }
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
                    break;
                }
                getGuess();

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
            return gameRecordNew;
        }

    @Override
    // check if we need a new game
    public boolean playNext() {
        if (left == 0) {
            System.out.println("No more phrases");
            System.out.println("A new player want to join or you want to play a new round?");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if (s.equals("yes")) {
                needNewGamePlayer = true;
                needNewPhrase = true;
                return true;
            }
            else {
                return false;
        }
    }
        System.out.println("Do you want to play again?");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if(s.equals("no")) {
            System.out.println("A new player want to join or you want to play a new round?");
            Scanner scanner1 = new Scanner(System.in);
            String s1 = scanner1.nextLine();
            if (s1.equals("yes")) {
                needNewGamePlayer = true;
                needNewPhrase = true;
                return true;
            }
            else {
                return false;
            }
        }
        else if(s.equals("yes")) {
            return true;
        }
        return false;
    }
    // get the next guess
    public char getGuess(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            String z = s.toLowerCase();
            guess = z.charAt(0);
            if (guess > 'z' || guess < 'a') {
                System.out.println("invalid input");
                continue;
            }

            break;
        }
        return guess;
    }
    // cast to string
    @Override
    public String toString() {
        return "WheelOfFortuneUserGame{" +
                "left=" + left +
                ", needNewGamePlayer=" + needNewGamePlayer +
                ", needNewPhrase=" + needNewPhrase +
                ", gameRecord=" + gameRecord +
                ", gameRecordNew=" + gameRecordNew +
                ", phraseList=" + phraseList +
                ", rand=" + rand +
                '}';
    }
    //  provide for sort
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WheelOfFortuneUserGame that = (WheelOfFortuneUserGame) o;
        return left == that.left && needNewGamePlayer == that.needNewGamePlayer && needNewPhrase == that.needNewPhrase && Objects.equals(gameRecord, that.gameRecord) && Objects.equals(gameRecordNew, that.gameRecordNew) && Objects.equals(phraseList, that.phraseList) && Objects.equals(rand, that.rand);
    }

    public static void main(String[] args)  {
        WheelOfFortuneUserGame wf = new WheelOfFortuneUserGame();
        AllGamesRecord record = wf.playAll();
        System.out.println(record);
        System.out.println("The average is "+ record.average());
        System.out.println("highGameList "+ record.highGameList(2));// or call specific functions of record
    }
}
