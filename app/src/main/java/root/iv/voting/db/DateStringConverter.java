package root.iv.voting.db;

import androidx.room.TypeConverter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import timber.log.Timber;

public class DateStringConverter {
    private static final SimpleDateFormat FMT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    @TypeConverter
    public static String toString(Calendar calendar) {
        return FMT_DATE_TIME.format(calendar.getTime());
    }

    @TypeConverter
    public static Calendar toDate(String date) {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(FMT_DATE_TIME.parse(date));
        } catch (ParseException e) {
            Timber.e(e);
        }

        return calendar;
    }
}
