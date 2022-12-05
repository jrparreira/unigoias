package com.br.cadastros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView pessoasRV;
    private static final int ADD_PESSOA_REQUEST = 1;
    private static final int EDIT_PESSOA_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pessoasRV = findViewById(R.id.idRVPessoas);
        FloatingActionButton fab = findViewById(R.id.idFABotaoAdd);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewPessoaActivity.class);
                startActivityForResult(intent, ADD_PESSOA_REQUEST);
            }
        });


        pessoasRV.setLayoutManager(new LinearLayoutManager(this));
        pessoasRV.setHasFixedSize(true);


        final PessoaRVAdapter adapter = new PessoaRVAdapter();


        pessoasRV.setAdapter(adapter);


        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);


        viewmodal.getAllPessoas().observe(this, new Observer<List<PessoaModal>>() {
            @Override
            public void onChanged(List<PessoaModal> models) {
                adapter.submitList(models);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                viewmodal.delete(adapter.getPessoaAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Pessoa excluída com sucesso.", Toast.LENGTH_SHORT).show();
            }
        }).

                attachToRecyclerView(pessoasRV);

        adapter.setOnItemClickListener(new PessoaRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PessoaModal model) {
                Intent intent = new Intent(MainActivity.this, NewPessoaActivity.class);
                intent.putExtra(NewPessoaActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewPessoaActivity.EXTRA_PESSOA_NAME, model.getPessoaName());
                startActivityForResult(intent, EDIT_PESSOA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PESSOA_REQUEST && resultCode == RESULT_OK) {
            String pessoaName = data.getStringExtra(NewPessoaActivity.EXTRA_PESSOA_NAME);
            PessoaModal model = new PessoaModal(pessoaName);
            viewmodal.insert(model);
            Toast.makeText(this, "Pessoa adicionado com sucesso.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PESSOA_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewPessoaActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Pessoa não atualizado.", Toast.LENGTH_SHORT).show();
                return;
            }
            String pessoaName = data.getStringExtra(NewPessoaActivity.EXTRA_PESSOA_NAME);

            PessoaModal model = new PessoaModal(pessoaName);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Pessoa atualizado.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Pessoa não salvo.", Toast.LENGTH_SHORT).show();
        }
    }
}
