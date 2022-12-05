package com.br.cadastros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPessoaActivity extends AppCompatActivity {


    private EditText pessoaNameEdt;
    private Button pessoaBtn;




    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_PESSOA_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PESSOA_NAME";

    private void savePessoa(String pessoaName) {


        Intent data = new Intent();


        data.putExtra(EXTRA_PESSOA_NAME, pessoaName);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {

            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);


        Toast.makeText(this, "Pessoa salva com sucesso no banco de dados Room. ", Toast.LENGTH_SHORT).show();
    }

    private void deletaPessoa(int id) {


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pessoa);


        pessoaNameEdt = findViewById(R.id.idEdtNomePessoa);
        pessoaBtn = findViewById(R.id.idBtnSalvarPessoa);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            pessoaNameEdt.setText(intent.getStringExtra(EXTRA_PESSOA_NAME));
        }

        pessoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pessoaName = pessoaNameEdt.getText().toString();


                if (pessoaName.isEmpty()) {
                    Toast.makeText(NewPessoaActivity.this, "Preencha todas as informações sobre a pessoa.", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePessoa(pessoaName);
            }
        });



    }
}
