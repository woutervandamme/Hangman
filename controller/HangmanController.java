package controller;

import android.app.Application;

import com.example.wouter.test.view.Observer;

import java.util.ArrayList;

import model.HangManFacade;

/**
 * Created by wouter on 29/10/14.
 */
public class HangmanController extends Application {

    private HangManFacade gameFacade;

    protected HangmanController(){
        gameFacade = new HangManFacade();
    }

    private static HangmanController instance = null;

    public static HangmanController getInstance() {
        if(instance == null) {
            instance = new HangmanController();
        }
        return instance;
    }

    public void startGame(){ gameFacade.startGame(); }

    public void setThema(String thema){ gameFacade.setThema(thema); }

    public void setDatabase(String database){ gameFacade.setDatabase(database); }

    public ArrayList<String> getThemas(){ return gameFacade.getThemas(); }

    public void addObserver(Observer o){ gameFacade.addObserver(o); }

    public boolean isGameOver(){ return gameFacade.isGameOver(); }

    public void restartGame() { gameFacade.restartGame(); }

    public boolean isGewonnen() { return gameFacade.isGewonnen(); }

    public String getWordToDisplay(){ return gameFacade.getWordToDisplay(); }

    public void guessLetter(char letter){
        gameFacade.guessLetter(letter);
    }

    public int getTriesLeft(){
        return gameFacade.getTriesLeft();
    }
}
