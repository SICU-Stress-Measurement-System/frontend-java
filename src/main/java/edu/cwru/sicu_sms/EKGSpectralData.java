package edu.cwru.sicu_sms;
import org.jtransforms.fft.DoubleFFT_1D;

import java.util.*;

/**
 * This class is used to represent a set of spectral (frequency-domain) EKG data, used to calculate the patient Stress Index
 *  *
 * @since December 6, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class EKGSpectralData extends SpectralData {

    private static final double[] LFRANGE = {0.04, 0.15};
    private static final double[] HFRANGE = {0.15, 0.4};

    private DoubleFFT_1D fft;

    /*The list of data points currently being analyzed to compute the stress index.
    5 minutes of data are stored at a time*/
    private ObservableList<T> dataList;
    private double[] data;

    //The sampling frequency
    private int Fs;

    //The current stress index component calculated from this data set alone
    private double stressIndex;


    public EKGSpectralData(ObservableList<T> temporalData, int Fs)
    {
        super(temporalData, Fs);
    }

    public double stressIndex()
    {
        int n = data.length/2;
        double[] mag = sumMagnitude(n);
        double lf = power(mag, n, LFRANGE[0], LFRANGE[1]);
        double hf = power(mag, n, HFRANGE[0], HFRANGE[1]);
        return 1-(hf/(hf+lf));
    }




}
