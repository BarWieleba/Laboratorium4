package com.example.myfrags;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragsData extends ViewModel {

    public final MutableLiveData<String> content = new MutableLiveData<>("0");   //pojemnik na dane stringow

    public final MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    public void overrideCounter(){
        counter.setValue(Integer.parseInt(content.getValue()));     //nadpisanie countera
    }

    public void overrideContent(){
        content.setValue(Integer.toString(counter.getValue()));     //nadpisanie content
    }
}
