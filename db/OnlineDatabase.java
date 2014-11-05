package db;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by wouter on 3/11/14.
 */
public class OnlineDatabase implements Database{


    private String url ="https://u0047590.webontwerp.khleuven.be/php/fetchGuessWords.php";
    private ArrayList<String> lijst = new ArrayList<String>();

    private JSONArray jsonarray = null;

    @Override
    public ArrayList<String> getThemas() {

            try {
                jsonarray = new JSONArray(readJSONFeed(url));


                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobj = jsonarray.getJSONObject(i);
                    String type = jsonobj.getString("type");

                    addType(type);
                }
            } catch (JSONException e) {
                e.printStackTrace();

        }
        return lijst;
    }

    private void addType(String type) {

        boolean dubbel = false;
        int i = 0;

        while(i < lijst.size() && !dubbel){
            if(lijst.get(i).equals(type)){
                dubbel = true;
            }
            i++;
        }
        if(!dubbel) {
            lijst.add(type);
        }

    }

    @Override
    public ArrayList<String> getWoorden(String thema) {
        ArrayList<String> woorden = new ArrayList<String>();

        for (int i = 0; i < jsonarray.length(); i++) {

            JSONObject jsonobj = null;
            try {
                jsonobj = jsonarray.getJSONObject(i);

                String type = jsonobj.getString("type");
                String woord = jsonobj.getString("guessword");

                if(type.equals(thema)){
                    woorden.add(woord);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return woorden;

    }



    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);

        try {

            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {

                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

                inputStream.close();

            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }
}

