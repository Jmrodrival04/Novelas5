package com.example.novelas5;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.squareup.okhttp3.*;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;

public class NovelaRepository {

    private final NovelaDao novelaDao;
    private final ExecutorService executorService;
    private final OkHttpClient client;

    public NovelaRepository(Application application) {
        NovelaDatabase database = NovelaDatabase.getInstance(application);
        novelaDao = database.novelaDao();
        executorService = Executors.newSingleThreadExecutor();
        client = new OkHttpClient();
    }

    public void fetchNovelasFromServer() {
        Request request = new Request.Builder()
                .url("https://example.com/api/novelas")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    // Aquí debes procesar la respuesta (por ejemplo, parsear JSON y actualizar Room)
                    // Ejemplo:
                    // List<Novela> novelas = parseJson(responseData);
                    // novelaDao.insertAll(novelas);
                }
            }
        });
    }

    public void insert(Novela novela) {
        executorService.execute(() -> novelaDao.insert(novela));
    }

    public void update(Novela novela) {
        executorService.execute(() -> novelaDao.update(novela));
    }

    public void delete(Novela novela) {
        executorService.execute(() -> novelaDao.delete(novela));
    }

    public LiveData<List<Novela>> getAllNovelas() {
        return novelaDao.getAllNovelas();
    }
}
