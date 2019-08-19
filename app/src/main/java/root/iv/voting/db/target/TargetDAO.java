package root.iv.voting.db.target;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TargetDAO {
    @Insert
    Long insert(Target target);
    @Update
    int update(Target target);
    @Delete
    void delete(Target ... target);
    @Query("SELECT * FROM target")
    List<Target> getAll();
    @Query("SELECT * FROM target WHERE id = :id")
    public Target findByID(long id);
}
