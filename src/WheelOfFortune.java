import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
// an abstract class which including common codes and data member of all WheelOfFortune game
public abstract class WheelOfFortune extends Game{
    protected String phrase;//String phrase
    protected char[] hiddenArray;//para
    protected char[] newArray;//para
    protected char guess;
    protected Map<Integer,Character> map = new HashMap<>();
   // Get a random phrased
    public void randomPhrase() {
        List<String> phraseList = null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrased from the list
        Random rand = new Random();
        int r = rand.nextInt(3); // gets 0, 1, or 2
        phrase = phraseList.get(r).toLowerCase();
        //replaced with asterisks
    }
    // returns the initial hidden phrase
    public void generateHiddenPhrase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) <= 'z' && phrase.charAt(i) >= 'a') {
                sb.append('*');
            } else if (phrase.charAt(i) <= 'Z' && phrase.charAt(i) >= 'A') {
                sb.append('*');
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        String hiddenPhrase = sb.toString();
        System.out.println(hiddenPhrase);
        hiddenArray = hiddenPhrase.toCharArray();
    }
    // get the next guess
    public abstract char getGuess();
    // process the guess, replace old Array with new Array
    public void processGuess() {
        char[] originalArray = phrase.toCharArray();
        newArray = Arrays.copyOf(hiddenArray, hiddenArray.length);
        for (int i = 0; i < phrase.length(); i++) {
            if (guess == phrase.charAt(i)) {
                newArray[i] = guess;
                /*                flag = true;*/
            }

            else {
            }
        }
        System.out.println(newArray);
        System.out.println(originalArray);
    }
    // activate program and coordinate all method
    @Override
    public GameRecord play(){
        return null;
    }
    // check if we need a new game
    @Override
    public boolean playNext(){
        return true;
    }
}
