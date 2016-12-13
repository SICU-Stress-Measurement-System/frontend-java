/*                                                                      *\
**                    SICU Stress Measurement System                    **
**                      Project P04  |  C380 Team A                     **
**          EBME 380: Biomedical Engineering Design Experience          **
**                    Case Western Reserve University                   **
**                          2016 Fall Semester                          **
\*                                                                      */

package edu.cwru.sicu_sms;

import javafx.collections.ObservableList;

import java.util.*;

/**
 * This class is used to represent a set of temporal (time-domain) EKG data, used to calculate the patient Stress Index.
 *
 * @since December 6, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public abstract class EKGTemporalData<T> extends StressIndexData {

    public static final int THRESHOLD = 800;

    /**
     * Current list of data points, being analyzed to compute the stress index.
     * <p><i>Note: 5 minutes of data are stored at a time.
     */
    private ObservableList<T> dataList;
    private double[] data;

    /** The sampling frequency, in hertz. */
    private int Fs;

    /** The current stress index component calculated from this data set alone. */
    private double stressIndex;

    /**
     * Constructs an <code>EKGTemporalData</code> object given a list of data along with its sampling frequency.
     *
     * @param dataList the list of data points
     * @param Fs       the sampling frequency, in hertz
     */
    public EKGTemporalData(ObservableList<T> dataList, int Fs)
    {
        super(dataList, Fs);
        data = new double[MAXSIZE];
    }

    /** TODO */
    public void setArray()
    {
//        data = dataList.toArray();
    }
    
    /** TODO */
    public void addDataPoint(T/* TODO: type to use */ newPoint)
    {
        dataList.add(newPoint);
        if (dataList.size() > MAXSIZE)
        {
            dataList.remove(0);
        }
        setArray();
    }

    /**
     * Calculates the stress index.
     *
     * @return the stress index
     */
    public double stressIndex()
    {
        ArrayList<Double> RtagInts = new ArrayList<>(MAXSIZE/2);
        for (int i = 1; i < MAXSIZE; i++)
        {
            double time = (1/Fs)*i;
            if (data[i-1] < THRESHOLD && data[i] >= THRESHOLD)
                RtagInts.add(time);
        }

        for (int i = 1; i < RtagInts.size(); i++)
        {
            RtagInts.set(i-1, RtagInts.get(i)-RtagInts.get(i-1));
        }
        RtagInts.remove(RtagInts.size()-1);  // final Rtag time will not be replaced by an interval time, so remove it from the list

        double AAD = calculateAAD(RtagInts);
        return 1/(1+AAD);
    }

    /** Calculates... TODO */
    private double calculateAAD(ArrayList RRintervals)
    {
        double intervalSum = 0;
        for (int i = 0; i < RRintervals.size(); i++)
        {
            intervalSum += (double) RRintervals.get(i);
        }
        return intervalSum/RRintervals.size();
    }
}
