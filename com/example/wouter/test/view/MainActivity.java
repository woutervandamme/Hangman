package com.example.wouter.test.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wouter.test.R;

import controller.HangmanController;


public class MainActivity extends Activity implements Observer {


    /*
    bij de onclick moet gewoon alles doorgestuurd worden naar de controller, die regelt da dan wel af
    met het model
     */

    private TextView tries,intro,guesses;
    private Button restartKnop;
    private EditText sendLetter;
    private ImageView galg;
    private HangmanController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_you);

        Bundle extras = getIntent().getExtras();
        String thema = extras.getString("thema");
        controller = HangmanController.getInstance();
        controller.setThema(thema);
        controller.startGame();
        controller.addObserver(this);
        init();
        update();

        intro.setText("Thema: " + thema);
    }

    private void init(){
        galg = (ImageView) findViewById(R.id.galg);
        restartKnop = (Button)findViewById(R.id.restart);
        guesses=(TextView)findViewById(R.id.guesses);
        intro=(TextView)findViewById(R.id.hello);
        sendLetter = (EditText) findViewById(R.id.sendLetter);
        sendLetter.setText("");
        tries =(TextView)findViewById(R.id.tries);

        sendLetter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    verstuurLetter();
                }
                return false;
            }
        });
    }

    private void verstuurLetter() {
        final String letter = sendLetter.getText().toString();
        if (!isValidGuess(letter)) {
            sendLetter.setError("je moet een letter ingeven");
        } else {
            controller.guessLetter(letter.charAt(0));
        }

        sendLetter.setText("");
    }

    private boolean isValidGuess(String letter){
        if( letter.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_you, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void restartGame(View view){
        controller.restartGame();
        restartKnop.setVisibility(View.INVISIBLE);
    }

    @Override
    public void update() {
        tries.setText("kansen: " + controller.getTriesLeft());
        guesses.setText(getDisplayWord());

        setHangManImage();

        if(controller.isGewonnen() || controller.isGameOver()){
            restartKnop.setVisibility(View.VISIBLE);
        }
    }

    private String getDisplayWord(){
        String display = controller.getWordToDisplay();
        String result = "";
        for(int i = 0 ; i < display.length();i++){
            result += display.charAt(i);
            result += " ";
        }
        return result;
    }

    private void setHangManImage() {
        switch(controller.getAantalFouten()){
            case 1:  galg.setImageResource(R.drawable.galg7);
                break;
            case 2:  galg.setImageResource(R.drawable.galg6);
                break;
            case 3:  galg.setImageResource(R.drawable.galg5);
                break;
            case 4:  galg.setImageResource(R.drawable.galg4);
                break;
            case 5:  galg.setImageResource(R.drawable.galg3);
                break;
            case 6:  galg.setImageResource(R.drawable.galg2);
                break;
            case 7:  galg.setImageResource(R.drawable.galg1);
                break;
            default: galg.setImageResource(R.drawable.galg1);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("TRIES", "" + tries.getText());
        savedInstanceState.putString("DISPLAY", controller.getWordToDisplay());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        tries.setText(savedInstanceState.getString("TRIES"));
        guesses.setText(savedInstanceState.getString("DISPLAY"));

    }
}
