package root.iv.voting.db.voting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Data;
import root.iv.voting.db.DateStringConverter;

@Data
@Entity(tableName = "voting", indices = {
        @Index(value = {"name"}, unique = true, name = "voting_unique_name")
})
public class Voting implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @TypeConverters({DateStringConverter.class})
    @ColumnInfo(name = "created")
    private Calendar created;

    public static Voting create(String name) {
        Voting voting = new Voting();

        voting.name = name;
        voting.created = Calendar.getInstance();

        return voting;
    }
}
