package lib;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author jonathan
 */
public class DateFr extends GregorianCalendar{

  
    
    
    private  String format;

    public static long difDates(DateFr date1, DateFr date2) {
             long difference = (date1.getTime()).getTime() -
                        (date2.getTime()).getTime();
      difference = (difference) / (1000 * 3600 * 24);
      return difference;

    }
    public DateFr()
    {
        super();
        this.format = "dd/MM/yyyy";
    }            
    public DateFr(String str)
    {
        super();
        this.format = "dd/MM/yyyy";
        this.set(str);
    }
      public DateFr(Date date)
    {
        super();
        this.format = "dd/MM/yyyy";
        this.setTime(date);
    }
    
    public final void  set(String str)
    {
        try {
            this.setTime((new SimpleDateFormat(format)).parse(str));
        } catch (ParseException ex) {
            Logger.getLogger(DateFr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  void setFormat(String str) {
                format = str;
    }
    
    public  String toFormat() {
       
                
                return format ;
    }
    
    @Override
    public String toString()
    {
        return (new SimpleDateFormat(format)).format(this.getTime());
    }   
}
