package root.iv.voting.db.target;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TargetDao {
    @Insert
    Long insertTarget(Target target);
    @Update
    int updateTarget(Target target);
    @Delete
    void deleteTargets(Target ... target);
    @Query("SELECT * FROM target")
    List<Target> getAll();
}
