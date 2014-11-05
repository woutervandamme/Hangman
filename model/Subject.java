package model;

import com.example.wouter.test.view.Observer;

/**
 * Created by wouter on 29/10/14.
 */
public interface Subject {

    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();

}
