package com.jeansandtshirt.validitychecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samir on 2016-10-29.
 */
public class ValidityChecks {

    public List<Integer> toIntArray(String pNumber){
        //Removes all non-numbers
        pNumber = pNumber.replaceAll("[^\\d]", "");

        if (pNumber.length() > 12 || pNumber.length() < 10)
            return null;
        else if(pNumber.length() == 12)
            pNumber = pNumber.substring(2);

        List<Integer> ints = new ArrayList<Integer>();

        //Converts and places the chars in List
        for (int i = 0; i < pNumber.length(); i++){
            ints.add(Character.getNumericValue(pNumber.charAt(i)));
        }
        return ints;
    }

    public boolean correctPNum(List<Integer> pNum){
        int controlNbr = 0;
        for (int i = 0; i < pNum.size()-1; i++){
            int a = pNum.get(i) * (2-i%2);   // multiplicera med 2 eller 1
            controlNbr += a/10 + a%10;      // addera siffrorna i resultatet till summan
        }
        controlNbr %= 10;

        if((10 - controlNbr)%10 == pNum.get(pNum.size()-1))
            return  true;
        else
            return false;
    }
}
