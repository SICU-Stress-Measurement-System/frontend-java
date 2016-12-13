package edu.cwru.sicu_sms;

import java.util.*;
import javafx.collections.ObservableList;
//import org.jtransforms.fft.DoubleFFT_1D;

/**
 * This is an abstract data class with methods common to the two types of spectral data;
 * EKGSpectralData and EEGSpectralData
 *
 * @since December 6, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public abstract class SpectralData<T> extends StressIndexData {

//    private DoubleFFT_1D fft;

    /*The list of data points currently being analyzed to compute the stress index.
    5 minutes of data are stored at a time*/
    private ObservableList<T> dataList;
    private ObservableList<T> spectralDataList;
    private double[] data;

    //The sampling frequency
    private int Fs;

    //The current stress index component calculated from this data set alone
    private double stressIndex;

    public SpectralData(ObservableList<T> temporalData, int Fs)
    {
        super(temporalData, Fs);
//        fft = new DoubleFFT_1D((long) MAXSIZE);
        data = new double[MAXSIZE*2];
        transform();
    }

    public void addDataPoint(T/*type to use*/ newPoint)
    {
        dataList.add(newPoint);
        if (dataList.size() > MAXSIZE)
        {
            dataList.remove(0);
        }
        setArray();
        transform();
    }

    /*Sets up the data array for frequency analysis by creating an array of length 2*dataList.size()
    The first half contains the elements from dataList that are to be analyzed.
    The second half is set to zero, and will be filled when transforming the data
    (accounts for real and imaginary parts of output)*/
    public void setArray()
    {
        int i;
        for(i = 0; i < dataList.size(); i++)
        {
//            data[i] = dataList.get(i);
        }
        for(; i < data.length; i++)
        {
            data[i] = 0;
        }
    }

    public void transform()
    {

//        fft.realForwardFull(data);
    }

    protected double[] sumMagnitude(int n)
    {
        double mag[] = new double[n];
        for(int i = 0; i < n; i++)
        {
            double re = data[2*i];
            double im = data[2*i+1];
            if (i == 0)
                im = 0;
            mag[i] = Math.pow(re*re+im*im, 0.5);
        }
        return mag;
    }


    protected double power(double[] mag, int n, double lowerBound, double upperBound)
    {
        int startIndex = (int) (lowerBound*n/Fs);
        int endIndex = (int) (upperBound*n/Fs);
        double power = 0;
        for(int i = startIndex; i < endIndex && i < mag.length; i++)
        {
            power += mag[i];
        }
        return power;
    }


}
