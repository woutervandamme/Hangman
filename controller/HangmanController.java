package controller;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.example.wouter.test.view.Observer;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import db.Database;
import db.DatabaseFactory;
import db.GeheugenDatabase;
import model.HangManGame;

/**
 * Created by wouter on 29/10/14.
 */
public class HangmanController extends Application {

    private HangManGame game;
    private Database db;
    private DatabaseFactory dbFac = new DatabaseFactory();
    private String thema;
    private Random randomGenerator = new Random();

    protected HangmanController(){ }
    private static HangmanController instance = null;

    public static HangmanController getInstance() {
        if(instance == null) {
            instance = new HangmanController();
        }
        return instance;
    }
    public void startGame(){
        String woord = db.getWoorden(thema).get(randomGenerator.nextInt(db.getWoorden(thema).size()));
        game = new HangManGame(woord);
    }

    public void setThema(String thema){
        this.thema = thema;
    }

    public void setDatabase(String database){ db = dbFac.createDatabase(database); }

    public ArrayList<String> getThemas(){
        ArrayList<String> lijst = null;
        try {
            lijst =  db.getThemas();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lijst;
    }

    public void addObserver(Observer o){ game.addObserver(o); }

    public boolean isGameOver(){ return game.isGameOver(); }

    public void restartGame() {
        String woord = db.getWoorden(thema).get(randomGenerator.nextInt(db.getWoorden(thema).size()));
        game.restartGame(woord);
    }

    public boolean isGewonnen() { return game.isGewonnen(); }

    public String getWordToDisplay(){ return game.getWordToDisplay(); }

    public int getAantalFouten(){ return game.getTriesLeft(); }

    public void guessLetter(char letter){
        game.guessLetter(letter);
    }

    public int getTriesLeft(){
        return game.getTriesLeft();
    }
}
