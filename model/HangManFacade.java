package model;

import com.example.wouter.test.view.Observer;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import db.Database;
import db.DatabaseFactory;

/**
 * Created by wouter on 5/11/14.
 */
public class HangManFacade {

    private HangManGame game;
    private Database db;
    private DatabaseFactory dbFac = new DatabaseFactory();
    private String thema;
    private Random randomGenerator = new Random();
    
    public HangManFacade() {
    }

    public ArrayList<String> getThemas(){
        ArrayList<String> lijst = null;
        try {
            lijst =  db.getThemas();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    public void setDatabase(String database){ db = dbFac.createDatabase(database); }

    public void setThema(String thema){
        this.thema = thema;
    }

    public void addObserver(Observer o) {
        game.addObserver(o);
    }

    public boolean isGameOver() {
        return game.isGameOver();
    }

    public void restartGame() {
        String woord = db.getWoorden(thema).get(randomGenerator.nextInt(db.getWoorden(thema).size()));
        game.restartGame(woord);
    }

    public boolean isGewonnen() {
        return game.isGewonnen();
    }

    public String getWordToDisplay() {
        return game.getWordToDisplay();
    }

    public int getTriesLeft() {
        return game.getTriesLeft();
    }

    public void guessLetter(char letter) {
        game.guessLetter(letter);
    }

    public void startGame() {
        String woord = db.getWoorden(thema).get(randomGenerator.nextInt(db.getWoorden(thema).size()));
        game = new HangManGame(woord);
    }
}
