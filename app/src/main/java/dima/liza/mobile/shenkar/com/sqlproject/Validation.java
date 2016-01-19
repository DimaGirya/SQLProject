package dima.liza.mobile.shenkar.com.sqlproject;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Girya on 1/19/2016.
 */
public class Validation {
    public static boolean intValidation(String strInt,String strError,Context context){
        try{
            Integer.parseInt(strInt);
            return true;
        }
        catch(NumberFormatException e){
            Toast.makeText(context,strError,Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public  static boolean gradeValidation(int grade){
        if(grade > 100 ){
            return false;
        }
        if(grade < 0){
            return  false;
        }
        return true;
    }
}
