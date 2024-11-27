package com.example.novelas5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NovelAdapter extends RecyclerView.Adapter<NovelAdapter.NovelViewHolder> {

    private List<Novela> novelas = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_novela, parent, false);
        return new NovelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        Novela currentNovela = novelas.get(position);
        holder.textViewTitulo.setText(currentNovela.getTitulo());
        holder.textViewAutor.setText(currentNovela.getAutor());
        holder.checkBoxLeido.setChecked(currentNovela.isLeido());

        holder.checkBoxLeido.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentNovela.setLeido(isChecked);
            Toast.makeText(holder.itemView.getContext(),
                    "Estado de lectura cambiado: " + isChecked,
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return novelas.size();
    }

    public void setNovelas(List<Novela> novelas) {
        this.novelas = novelas;
        notifyDataSetChanged();
    }

    public Novela getNovelaAt(int position) {
        return novelas.get(position);
    }

    class NovelViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitulo;
        private TextView textViewAutor;
        private CheckBox checkBoxLeido;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.text_view_titulo);
            textViewAutor = itemView.findViewById(R.id.text_view_autor);
            checkBoxLeido = itemView.findViewById(R.id.check_box_leido);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(novelas.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Novela novela);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
