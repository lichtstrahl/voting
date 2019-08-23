package root.iv.voting.db.target;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Data;

@Data
@Entity(tableName = "target")
public class Target implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "desc")
    private String desc;
    @ColumnInfo(name = "vote")
    private int vote;

    public static Target create(String name, String desc) {
        Target t = new Target();
        t.setName(name);
        t.setDesc(desc);
        t.setVote(0);
        return t;
    }

    public void vote() {
        vote++;
    }

    public void badVote() {
        if (--vote < 0) vote++;
    }
}
