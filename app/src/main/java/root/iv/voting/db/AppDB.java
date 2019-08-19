package root.iv.voting.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import root.iv.voting.db.target.Target;
import root.iv.voting.db.target.TargetDao;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.db.voting.VotingDAO;

@Database(entities = {Target.class, Voting.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract TargetDao targetDao();
    public abstract VotingDAO votingDAO();
}
