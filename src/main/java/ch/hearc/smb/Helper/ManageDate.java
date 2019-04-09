package ch.hearc.smb.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageDate {
    private SimpleDateFormat dfDataBase;
    private SimpleDateFormat dfDisplay;
    private static ManageDate instance = null;

    private ManageDate() {
        dfDataBase = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dfDisplay = new SimpleDateFormat("dd MMMM yyyy HH:mm");
    }

    public static synchronized ManageDate getInstance() {
        if(instance == null) {
            instance = new ManageDate();
        }
        return instance;
    }

    public String dateToDB(Date dateIn) {
        return dfDataBase.format(dateIn);
    }

    public Date dateFromDB(String dateIn) {
        try {
            return dfDataBase.parse(dateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(); //In case there is a problem
    }

    public String dateToDisplay(Date dateIn) {
        return dfDisplay.format(dateIn);
    }

    public Date dateFromDisplay(String dateIn) {
        try {
            return dfDisplay.parse(dateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(); //In case there is a problem
    }

    public String dateFromDBToDisplay(String dateIn) {
        return dateToDisplay(dateFromDB(dateIn));
    }
}
