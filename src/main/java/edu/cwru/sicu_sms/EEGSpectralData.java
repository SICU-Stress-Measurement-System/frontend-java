package edu.cwru.sicu_sms;
import org.jtransforms.fft.DoubleFFT_1D;

import java.util.*;

/**
 * This class is used to represent a set of spectral (frequency-domain) EEG data, used to calculate the patient Stress Index
 *  *
 * @since December 6, 2016
 * @author Ted Frohlich <ttf10@case.edu>
 * @author Abby Walker <amw138@case.edu>
 */
public class EEGSpectralData extends SpectralData {

    private static final double[] HIGHBETARANGE = {18, 30};
    private static final double[] TOTALRANGE = {2, 45};
    private DoubleFFT_1D fft;

    /*The list of data points currently being analyzed to compute the stress index.
    5 minutes of data are stored at a time*/
    private ObservableList<T> dataList;
    private double[] data;

    //The sampling frequency
    private int Fs;

    //The current stress index component calculated from this data set alone
    private double stressIndex;


    public EEGSpectralData(/*List of dataSets*/, int Fs)
    {
        /*TODO: average data sets into one*/

        super(/*average*/, Fs);
    }


    public double stressIndex()
    {
        int n = data.length/2;
        double[] mag = sumMagnitude(n);
        double hb = power(mag, n, HIGHBETARANGE[0], HIGHBETARANGE[1]);
        double tf = power(mag, n, TOTALRANGE[0], TOTALRANGE[1]);
        return hb/tf;
    }


}
