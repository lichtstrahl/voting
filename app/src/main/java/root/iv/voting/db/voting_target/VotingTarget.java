package root.iv.voting.db.voting_target;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import lombok.Data;
import root.iv.voting.db.target.Target;
import root.iv.voting.db.voting.Voting;

@Data
@Entity(tableName = "voting_target", foreignKeys = {
        @ForeignKey(entity = Voting.class, parentColumns = "id", childColumns = "voting"),
        @ForeignKey(entity = Target.class, parentColumns = "id", childColumns = "target")
})
public class VotingTarget {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "voting")
    private long votingID;
    @ColumnInfo(name = "target")
    private long targetID;

    public static VotingTarget create(Voting voting, Target target) {
        VotingTarget vt = new VotingTarget();

        vt.setVotingID(voting.getId());
        vt.setTargetID(target.getId());

        return vt;
    }
}
