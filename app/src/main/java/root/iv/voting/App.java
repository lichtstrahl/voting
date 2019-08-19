package root.iv.voting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.facebook.stetho.Stetho;

import root.iv.voting.db.AppDB;
import root.iv.voting.log.ReleaseTree;
import timber.log.Timber;

public class App extends Application {

    private static final String DB_NAME = "db_voting";
    private AppDB db;
    private static final Migration migration12 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };
    private static final Migration migration21 = new Migration(2, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        db = Room.databaseBuilder(this, AppDB.class, DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(migration12, migration21)
                .build();
        Stetho.initializeWithDefaults(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }

    public AppDB getDB() {
        return db;
    }
}
