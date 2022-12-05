package com.br.cadastros;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class ViewModal extends AndroidViewModel {
    private PessoaRepository repository;
    private LiveData<List<PessoaModal>> allPessoas;

    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new PessoaRepository(application);
        allPessoas = repository.getAllCourses();
    }

    public void insert(PessoaModal model) {
        repository.insert(model);
    }
    public void update(PessoaModal model) {
        repository.update(model);
    }

    public void delete(PessoaModal model) {
        repository.delete(model);
    }

    public void deleteAllPessoas() {
        repository.deleteAllPessoas();
    }

    public LiveData<List<PessoaModal>> getAllPessoas() {
        return allPessoas;
    }

}
