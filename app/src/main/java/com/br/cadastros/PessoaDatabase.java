package com.br.cadastros;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
// adicionando anotações para nossas entidades de banco de dados e versão db..
@Database(entities = {PessoaModal.class}, version = 1)

public abstract class PessoaDatabase extends RoomDatabase {
    private static PessoaDatabase instance;

    public abstract Dao Dao();

    public static synchronized PessoaDatabase getInstance(Context context) {
        if ( instance == null) {
            instance =  Room.databaseBuilder(context.getApplicationContext(),
                            PessoaDatabase.class, "pessoa_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
            return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,
            Void> {
        PopulateDbAsyncTask(PessoaDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
