package com.example.databinding.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val name = MutableLiveData<String>()

}