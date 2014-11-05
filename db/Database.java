package db;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by wouter on 29/10/14.
 */
public interface Database {

    public ArrayList<String> getThemas() throws JSONException;
    public ArrayList<String> getWoorden(String thema);

}
