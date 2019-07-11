package com.example.photogrid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photogrid.Api.PhotoService
import com.example.photogrid.photoModel.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val APIClient by lazy {
        PhotoService.create();
    }

    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPhotos.layoutManager = LinearLayoutManager(this)
        beginSearch()
    }

    private fun beginSearch() {
        APIClient.getPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                Log.d("Result: ", result.toString())
                setDataInRecyclerView(result);
            }, { error ->
                Log.d("error", error.message.toString())
            })
    }

    private fun setDataInRecyclerView(results: ArrayList<Photo>) {
        rvPhotos.adapter = PhotoAdapter(results,this)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}


