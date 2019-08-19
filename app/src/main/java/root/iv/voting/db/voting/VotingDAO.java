package root.iv.voting.db.voting;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VotingDAO {
    @Insert
    long insert(Voting voting);
    @Update
    int update(Voting voting);
    @Delete
    void delete(Voting ... votings);
    @Query("SELECT * FROM voting")
    List<Voting> getAll();
}
