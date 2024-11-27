package com.example.novelas5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddNovelaDialog.AddNovelaListener {

    private NovelaViewModel novelaViewModel;
    private RecyclerView recyclerView;
    private NovelAdapter adapter;
    private Button buttonAddNovela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddNovela = findViewById(R.id.button_add_novela);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new NovelAdapter();
        recyclerView.setAdapter(adapter);

        novelaViewModel = new ViewModelProvider(this).get(NovelaViewModel.class);

        // Observar cambios en la lista de novelas
        novelaViewModel.getAllNovelas().observe(this, new Observer<List<Novela>>() {
            @Override
            public void onChanged(List<Novela> novelas) {
                adapter.setNovelas(novelas);
            }
        });

        // Configuración del clic en un elemento para editar
        adapter.setOnItemClickListener(novela -> {
            EditNovelaDialog dialog = new EditNovelaDialog(novela);
            dialog.setListener(updatedNovela -> novelaViewModel.update(updatedNovela));
            dialog.show(getSupportFragmentManager(), "Edit Novela Dialog");
        });

        // Configuración del botón para agregar novelas
        buttonAddNovela.setOnClickListener(v -> {
            AddNovelaDialog dialog = new AddNovelaDialog();
            dialog.setListener(MainActivity.this);
            dialog.show(getSupportFragmentManager(), "Add Novela Dialog");
        });

        // Configuración del deslizar para eliminar novelas
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Novela novela = adapter.getNovelaAt(viewHolder.getAdapterPosition());
                novelaViewModel.delete(novela);
                Toast.makeText(MainActivity.this, "Novela eliminada", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // Sincronización con servidor
        sincronizarNovelas();
    }

    private void sincronizarNovelas() {
        novelaViewModel.fetchNovelasFromServer();
        Toast.makeText(this, "Sincronizando datos...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGuardarNovela(Novela novela) {
        novelaViewModel.insert(novela);
    }
}
