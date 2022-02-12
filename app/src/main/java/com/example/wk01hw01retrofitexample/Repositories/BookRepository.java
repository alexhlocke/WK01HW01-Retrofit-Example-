package com.example.wk01hw01retrofitexample.Repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wk01hw01retrofitexample.API.BookSearchService;
import com.example.wk01hw01retrofitexample.models.VolumesResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchService bookSearchService;
    private MutableLiveData<VolumesResponse> volumesResponseLiveData;

    public BookRepository() {
        volumesResponseLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        bookSearchService = new retrofit2.Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);

    }

    public void searchVolumes(String keyword, String author/*, String apiKey*/) {
        bookSearchService.searchVolumes(keyword, author/*, apiKey*/)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() != null) {
                            volumesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}
