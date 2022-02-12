package com.example.wk01hw01retrofitexample.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wk01hw01retrofitexample.models.VolumesResponse;
import com.example.wk01hw01retrofitexample.Repositories.BookRepository;

public class BookViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new BookRepository();
        volumesResponseLiveData = bookRepository.getVolumesResponseLiveData();
    }

    public void searchVolumes(String keyword, String author) {
        //TODO: Fix this
        bookRepository.searchVolumes(keyword, author);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }
}