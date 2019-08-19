package root.iv.voting.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import root.iv.voting.db.target.Target;
import root.iv.voting.db.target.TargetDAO;
import root.iv.voting.db.voting.Voting;
import root.iv.voting.db.voting.VotingDAO;
import root.iv.voting.db.voting_target.VotingTarget;
import root.iv.voting.db.voting_target.VotingTargetDAO;

@Database(entities = {Target.class, Voting.class, VotingTarget.class}, version = 2, exportSchema = false)
public abstract class AppDB extends RoomDatabase {
    public abstract TargetDAO targetDAO();
    public abstract VotingDAO votingDAO();
    public abstract VotingTargetDAO votingTargetDAO();
}
