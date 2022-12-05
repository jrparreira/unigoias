package com.br.cadastros;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// // a linha abaixo é para definir o nome da tabela.
@Entity(tableName = "pessoa_table")

public class PessoaModal {
    // a linha abaixo é para auto incrementar id para cada curso.
    @PrimaryKey(autoGenerate = true)
    // variável para o nosso id.
    private int id;
    // variável para o nome da pessoa.
    private String pessoaName;

    public PessoaModal(String pessoaName) {
        this.id = id;
        this.pessoaName = pessoaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPessoaName() {
        return pessoaName;
    }

    public void setPessoaName(String pessoaName) {
        this.pessoaName = pessoaName;
    }
}
