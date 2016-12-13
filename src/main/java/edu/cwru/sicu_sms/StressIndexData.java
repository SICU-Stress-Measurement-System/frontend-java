package edu.cwru.sicu_sms;

import java.util.*;
import javafx.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is a basic List data structure used to represent sets of data used to calculate the patient Stress Index
 * It is an abstract class, and the child classes are specific types of data used to calculate the stress index,
 * i.e. EKG, EEG, temporal, or spectral
 *
 * @since December 6, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public abstract class StressIndexData<T> {

    static final int TIMEWINDOW = 300; //seconds of data saved
    static int MAXSIZE; //Total number of points we want to store/analyze based on sampling frequency and seconds of data saved

    /*The list of data points currently being analyzed to compute the stress index.
    5 minutes of data are stored at a time*/
    private ObservableList<T> dataList;
    private double[] data;

    //The sampling frequency
    private int Fs;

    //The current stress index component calculated from this data set alone
    private double stressIndex;


    public StressIndexData(ObservableList<T> dataList, int Fs)
    {
        this.Fs = Fs;
        MAXSIZE = Fs*TIMEWINDOW;
        stressIndex = 0;
        setList(dataList);
        setArray();
    }

    private void setList(ObservableList<T> dataList)
    {
        int currSize = dataList.size();
        int fromIndex = 0;
        if (currSize > MAXSIZE)
            fromIndex = currSize-MAXSIZE+1;
        this.dataList = FXCollections.observableList(dataList.subList(fromIndex, currSize));

    }

    /*TODO: figure out how to best add new data points - through observableList or by adding a point directly to array*/
    public abstract void addDataPoint(float/*type to use*/ newPoint);

    public abstract double stressIndex();

    public abstract void setArray();

    public double getSamplingFreq()
    {
        return Fs;
    }

    public double getStressIndex()
    {
        return stressIndex;
    }

}
