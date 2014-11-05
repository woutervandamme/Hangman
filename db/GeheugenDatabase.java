package db;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wouter on 29/10/14.
 */
public class GeheugenDatabase implements Database{

    private ArrayList<String> themas;
    private ArrayList<String> natuur;
    private ArrayList<String> muziek;
    private static GeheugenDatabase instance = null;
    private String thema;

    protected GeheugenDatabase(){
        themas = new ArrayList(Arrays.asList("natuur", "muziek"));
        natuur = new ArrayList();
        muziek = new ArrayList();
        natuur.add("vogel");
        muziek.add("gitaar");
        muziek.add("piano");
    }

    public static GeheugenDatabase getInstance() {
        if(instance == null) {
            instance = new GeheugenDatabase();
        }
        return instance;
    }

    @Override
    public ArrayList<String> getThemas() {
        return themas;

    }

    @Override
    public ArrayList<String> getWoorden(String thema) {
        ArrayList<String> woorden;
        if(thema.equals("natuur")){
          woorden = natuur;
        } else {
          woorden = muziek;
        }
        return woorden;
    }


}
