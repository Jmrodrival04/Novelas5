package com.example.novelas5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NovelaViewModel extends AndroidViewModel {

    private final NovelaRepository repository;
    private final LiveData<List<Novela>> allNovelas;

    public NovelaViewModel(@NonNull Application application) {
        super(application);
        repository = new NovelaRepository(application);
        allNovelas = repository.getAllNovelas();
    }

    public void insert(Novela novela) {
        repository.insert(novela);
    }

    public void update(Novela novela) {
        repository.update(novela);
    }

    public void delete(Novela novela) {
        repository.delete(novela);
    }

    public LiveData<List<Novela>> getAllNovelas() {
        return allNovelas;
    }

    public void fetchNovelasFromServer() {
        repository.fetchNovelasFromServer();
    }
}
