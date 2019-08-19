package root.iv.voting.db;

import androidx.room.TypeConverter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import timber.log.Timber;

public class DateStringConverter {
    @TypeConverter
    public String toString(Calendar calendar) {
        return calendar.getTime().toString();
    }

    @TypeConverter
    public Calendar toDate(String date) {
        Calendar calendar = Calendar.getInstance();

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            Timber.e(e);
        }

        return calendar;
    }
}
