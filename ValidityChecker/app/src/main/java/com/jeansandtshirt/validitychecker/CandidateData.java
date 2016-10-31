package com.jeansandtshirt.validitychecker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by samir on 2016-10-30.
 */
public class CandidateData {

    public String name;
    public String pNumber;
    public String dateAdded;

    public CandidateData(String name, String pNumber, String dateAdded){
        this.name = name;
        this.pNumber = pNumber;
        this.dateAdded = dateAdded;
    }

    public CandidateData(){

    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    @JsonProperty("pNumber")
    public String getpNumber() { return pNumber; }

    public void setpNumber(String pNumber) { this.pNumber = pNumber; }

    @JsonProperty("dateAdded")
    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) { this.dateAdded = dateAdded; }

}
