package model;

import android.util.Log;

import com.example.wouter.test.view.Observer;

import java.util.ArrayList;


/**
 * Created by wouter on 29/10/14.
 */
public class HangManGame implements Subject{

    private ArrayList<Observer>observers = new ArrayList();
    private int tries = 7;
    private String wordToGuess;
    private boolean gewonnen = false,gameOver = false;
    // word to display ziet er zo uit: _ _ _ _ _  met de juiste lengte
    private String wordToDisplay = "";

    public HangManGame(String woord){
        wordToGuess = woord;
        generateWordToDisplay();
    }

    public void generateWordToDisplay(){
        for(int i = 0; i < wordToGuess.length();i++){
            wordToDisplay += "_";
        }
    }

    public void restartGame(String woord){
        wordToGuess = woord;
        wordToDisplay = "";
        generateWordToDisplay();
        tries = 7;
        gewonnen = false;
        gameOver = false;
        notifyObservers();
    }

    public String getWordToDisplay() {
        return wordToDisplay;
    }

    public boolean isGameOver() { return gameOver; }

    public void guessLetter(char letter){
        if(tries <= 1){ gameOver = true; }

        StringBuilder temp = new StringBuilder(wordToDisplay);
        boolean goed = false;

        for (int i=0; i<wordToGuess.length() && !goed; i++) {
            if (wordToGuess.charAt(i) == letter && wordToGuess.charAt(i) != wordToDisplay.charAt(i)) {
                temp.setCharAt(i, letter);
                goed = true;
            }
        }

        if(!goed){
            tries--;
        }

        wordToDisplay = temp.toString();

        //als er geen underscores meer in het woord zitten -> gewonnen

        if(wordToDisplay.indexOf("_") == -1){
            gewonnen = true;
        }
        notifyObservers();
    }

    public int getTriesLeft(){ return tries; }

    @Override
    public void addObserver(Observer o) {observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size();i++){
            observers.get(i).update();
        }
    }

    public boolean isGewonnen() {return gewonnen;}


}
