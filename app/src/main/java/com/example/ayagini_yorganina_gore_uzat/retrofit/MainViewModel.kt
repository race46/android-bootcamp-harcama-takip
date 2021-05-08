package com.example.ayagini_yorganina_gore_uzat.retrofit

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ayagini_yorganina_gore_uzat.room_database.ViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.POST

class MainViewModel(val context: Context): androidx.lifecycle.ViewModel() {
    private val apiService = RetrofitService()
    private val disposable =  CompositeDisposable()
    val USD = MutableLiveData<Float>()
    val EUR = MutableLiveData<Float>()
    val GBP = MutableLiveData<Float>()

    init {
        //Toast.makeText(context,"init-main view model",Toast.LENGTH_SHORT).show()
        val sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
        USD.value = sp.getFloat("USD",1f)
        EUR.value = sp.getFloat("EUR",1f)
        GBP.value = sp.getFloat("GBP",1f)
        GlobalScope.launch {
            try {
                retrofitCallUsd()
                retrofitCallEur()
                retrofitCallGbp()
            }finally {

            }
        }


    }
    fun retrofitCallUsd() {
        disposable.add(
                apiService.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<USD>(){
                            override fun onSuccess(t: USD) {
                                val sp = context?.getSharedPreferences("sp", Context.MODE_PRIVATE)
                                val editor = sp.edit()
                                editor.putFloat("USD",t.USD_PHP.toFloat()).apply()
                                USD.value = sp.getFloat("USD",1f)

//                                val sp = context?.getSharedPreferences("sp", Context.MODE_PRIVATE)
//                                val editor = sp?.edit()
//                                editor?.apply {
//                                    putString("name",view.isim.text.toString())
//                                    var gender = ""
//                                    when(input_gender.checkedRadioButtonId){
//                                        R.id.radioButton -> gender = "Bey"
//                                        R.id.radioButton3 -> gender = "Hanim"
//                                    }
//                                    putString("gender",gender)
//                                }?.apply()
//
                            }

                            override fun onError(e: Throwable) {

                            }

                        })
        )
    }
    fun retrofitCallEur() {
        disposable.add(
                apiService.getDataEur()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<EUR>(){
                            override fun onSuccess(t: EUR) {
                                val sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
                                val editor = sp.edit()
                                editor.putFloat("EUR",t.USD_PHP.toFloat()).apply()
                                EUR.value = sp.getFloat("EUR",1f)

                            }

                            override fun onError(e: Throwable) {

                            }

                        })
        )
    }
    fun retrofitCallGbp() {
        disposable.add(
                apiService.getDataGbp()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<GBP>(){
                            override fun onSuccess(t: GBP) {
                                val sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE)
                                val editor = sp.edit()
                                editor.putFloat("GBP",t.USD_PHP.toFloat()).apply()
                                GBP.value = sp.getFloat("GBP",1f)


                            }

                            override fun onError(e: Throwable) {


                            }

                        })
        )
    }

}