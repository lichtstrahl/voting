package root.iv.voting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import root.iv.voting.db.AppDB;
import root.iv.voting.log.ReleaseTree;
import root.iv.voting.network.AnswerAPI;
import timber.log.Timber;


public class App extends Application {

    private static final String DB_NAME = "db_voting";
    @Getter
    private AppDB DB;
    @Getter
    private AnswerAPI answerAPI;
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
        // Database
        DB = Room.databaseBuilder(this, AppDB.class, DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(migration12, migration21)
                .build();
        // Stetho
        Stetho.initializeWithDefaults(this);
        // OkHttp client
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.FANORONA_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        answerAPI = retrofit.create(AnswerAPI.class);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }
    }
}
