package com.b1707b.cours.resto_app.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Blabla_function {
    public Boolean compareTime(String time1, String time2, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
        try {
            Date timeOne = simpleDateFormat.parse(time1);
            Date timeTwo = simpleDateFormat.parse(time2);
            Date CompTime = simpleDateFormat.parse(time);
            assert CompTime != null;
            return CompTime.after(timeOne) && CompTime.before(timeTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Boolean compareDateTime(String time1, String time2, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", Locale.ENGLISH);
        try {
            Date timeOne = simpleDateFormat.parse(time1);
            Date timeTwo = simpleDateFormat.parse(time2);
            Date CompTime = simpleDateFormat.parse(time);
            assert CompTime != null;
            return CompTime.after(timeOne) && CompTime.before(timeTwo);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
