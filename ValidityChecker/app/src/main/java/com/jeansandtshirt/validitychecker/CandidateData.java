package com.jeansandtshirt.validitychecker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by samir on 2016-10-30.
 */
public class CandidateData {

    String name;
    String pNumber;
    String dateAdded;

    public CandidateData(String name, String pNumber, String dateAdded){
        this.name = name;
        this.pNumber = pNumber;
        this.dateAdded = dateAdded;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getName() {
        return name;
    }

    public String getpNumber() {
        return pNumber;
    }
}
