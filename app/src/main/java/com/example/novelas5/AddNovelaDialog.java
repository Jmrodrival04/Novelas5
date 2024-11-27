package com.example.novelas5;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddNovelaDialog extends DialogFragment {

    private EditText editTextTitulo, editTextAutor, editTextGenero;
    private CheckBox checkBoxLeido;
    private Button buttonGuardar, buttonCancelar;
    private AddNovelaListener listener;

    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_novela, null);

        // Inicializar vistas
        editTextTitulo = view.findViewById(R.id.edit_text_titulo);
        editTextAutor = view.findViewById(R.id.edit_text_autor);
        editTextGenero = view.findViewById(R.id.edit_text_genero);
        checkBoxLeido = view.findViewById(R.id.check_box_leido);
        buttonGuardar = view.findViewById(R.id.button_guardar);
        buttonCancelar = view.findViewById(R.id.button_cancelar);

        // Configurar eventos de los botones
        buttonGuardar.setOnClickListener(v -> guardarNovela());
        buttonCancelar.setOnClickListener(v -> dismiss());

        // Crear el diálogo
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(view);
        return dialog;
    }

    private void guardarNovela() {
        String titulo = editTextTitulo.getText().toString().trim();
        String autor = editTextAutor.getText().toString().trim();
        String genero = editTextGenero.getText().toString().trim();
        boolean leido = checkBoxLeido.isChecked();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (listener != null) {
            listener.onGuardarNovela(new Novela(titulo, autor, genero, leido));
        }
        dismiss();
    }

    public void setListener(AddNovelaListener listener) {
        this.listener = listener;
    }

    public interface AddNovelaListener {
        void onGuardarNovela(Novela novela);
    }
}
