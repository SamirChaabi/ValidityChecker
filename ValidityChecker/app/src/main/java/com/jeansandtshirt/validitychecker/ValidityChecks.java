package com.jeansandtshirt.validitychecker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by samir on 2016-10-29.
 */
public class ValidityChecks {

    //Returns the date in a format easy to work with, null gets returned if the date entered is not correct
    public String toStdPNumberFormat(String pNumber){
        //Removes all non-numbers
        pNumber = pNumber.replaceAll("[^\\d]", "");

        if (pNumber.length() > 12 || pNumber.length() == 11 || pNumber.length() < 10)
            return null;
        else if(pNumber.length() == 12)
            return pNumber.substring(2);
        else
            return pNumber;
    }

    //Checks if date entered is possible to have as a birthdate
    public Boolean born(String formattedDate){
        try{
            return new SimpleDateFormat("yyMMDD", Locale.ENGLISH).parse(formattedDate.substring(0, 5)).compareTo(new Date()) != 1;
        }
        catch(Exception e){
            return null;
        }
    }

    //Returns a int array form a String
    public List<Integer> toIntArray(String pNumber){

        List<Integer> ints = new ArrayList<Integer>();

        //Converts and places the chars in List
        for (int i = 0; i < pNumber.length(); i++){
            ints.add(Character.getNumericValue(pNumber.charAt(i)));
        }
        return ints;
    }

    //Calculates if the Personal ID Number is valid
    public boolean correctPNum(List<Integer> pNum){
        int controlNbr = 0;
        for (int i = 0; i < pNum.size()-1; i++){
            //Toggles with multiplying a numer from the PID with 1 or 2
            int a = pNum.get(i) * (2-i%2);
            //if bigger than 10 the numbers are "splitted" and added if not the number is added
            controlNbr += a/10 + a%10;
        }
        controlNbr %= 10;

        return (10 - controlNbr)%10 == pNum.get(pNum.size()-1);
    }

    public boolean isNull(CandidateData candidateData){
        return candidateData.dateAdded == null || candidateData.pNumber == null || candidateData.name == null;
    }
}
