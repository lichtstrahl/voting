package root.iv.voting.db.voting_target;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface VotingTargetDAO {
    @Insert
    long insert(VotingTarget vt);
    @Update
    int update(VotingTarget vt);
    @Delete
    void delete(VotingTarget ... vt);
}
