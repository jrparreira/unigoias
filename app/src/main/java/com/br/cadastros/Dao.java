package com.br.cadastros;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
// Adicionando anotação à nossa classe Dao
@androidx.room.Dao

public interface Dao {
    @Insert
    void insert(PessoaModal model);

    @Update
    void update (PessoaModal model);

    @Delete
    void delete (PessoaModal model);

    @Query("DELETE FROM pessoa_table")
    void deleteAllPessoas();

    @Query("SELECT * FROM pessoa_table ORDER BY pessoaName ASC")
    LiveData<List<PessoaModal>> getAllPessoas();
}
