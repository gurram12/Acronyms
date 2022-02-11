package com.example.acronyms.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.acronyms.api.ApiInterface
import com.example.acronyms.model.Acronyms
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AcronymsViewModel : ViewModel() {

    var input: String = ""
    val searchResults = MutableLiveData<Acronyms>()
    fun search(): List<String> {
        GlobalScope.launch {
            val apiInterface = ApiInterface.create().search(input)
            apiInterface.enqueue(object : Callback<List<Acronyms>> {
                override fun onResponse(
                    call: Call<List<Acronyms>>?,
                    response: Response<List<Acronyms>>?
                ) {
                    response?.body()?.let {
                        Log.d(TAG, "response: $it")
                        searchResults.value = it.get(0)
                    }
                }
                override fun onFailure(call: Call<List<Acronyms>>?, t: Throwable?) {
                    Log.e(TAG, "Error", t)
                }
            })
        }
        Log.d(TAG, "search: $input")
        return listOf<String>()
    }


}