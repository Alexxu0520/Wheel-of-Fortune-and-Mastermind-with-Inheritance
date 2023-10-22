public interface WheelOfFortunePlayer {
//get the next guess from the player
abstract char nextGuess();
// — an id for the player
abstract String playerId();
//reset the player to start a new game
abstract void reset();
}
