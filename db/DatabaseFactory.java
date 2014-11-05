package db;

/**
 * Created by wouter on 30/10/14.
 */
public class DatabaseFactory {



    public Database createDatabase(String dbNaam){
        Database db = null;

        if(dbNaam.equals("geheugen")){
            db = new GeheugenDatabase();
        } else if (dbNaam.equals("online")){
            db = new OnlineDatabase();
        }

        return db;
    }
}
