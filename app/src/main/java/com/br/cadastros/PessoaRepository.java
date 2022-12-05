package com.br.cadastros;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class PessoaRepository {
    private Dao dao;
    private LiveData<List<PessoaModal>> allPessoas;

    public PessoaRepository(Application application) {
        PessoaDatabase database = PessoaDatabase.getInstance(application);
        dao = database.Dao();
        allPessoas = dao.getAllPessoas();
    }

    public void insert(PessoaModal model) {
        new InsertPessoaAsyncTask(dao).execute(model);
    }

    public void update(PessoaModal model) {
        new UpdatePessoaAsyncTask(dao).execute(model);
    }

    public void delete(PessoaModal model) {
        new DeletePessoaAsyncTask(dao).execute(model);
    }

    public void deleteAllPessoas() {
        new DeleteAllPessoasAsyncTask(dao).execute();
    }

    public LiveData<List<PessoaModal>> getAllCourses() {
        return allPessoas;
    }

    private static class InsertPessoaAsyncTask extends AsyncTask<PessoaModal,
            Void, Void> {
        private Dao dao;
        private InsertPessoaAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PessoaModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdatePessoaAsyncTask extends AsyncTask<PessoaModal, Void, Void> {
        private Dao dao;
        private UpdatePessoaAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PessoaModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeletePessoaAsyncTask extends AsyncTask<PessoaModal, Void, Void> {
        private Dao dao;
        private DeletePessoaAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PessoaModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllPessoasAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllPessoasAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllPessoas();
            return null;
        }
    }
}
