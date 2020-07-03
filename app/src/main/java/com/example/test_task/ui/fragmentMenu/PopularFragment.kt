package com.example.test_task.ui.fragmentMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.Adapter
import com.example.test_task.ui.activity.DetailedInformationActivity
import com.example.test_task.R
import com.example.test_task.data.apiServise.GalleryApiService
import com.example.test_task.data.model.ImageAnswer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() {
    private val myAdapter = Adapter { openingNewActivity(it) }
    private val apiService = GalleryApiService.create()
    var isRequest: Boolean = false
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workWithAdapter()
        val subscribe = apiService.search(false,true, 10,1)//todo пачимуниработаетпомоги
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                populateData(it)
            }, { error ->
                error.printStackTrace()
            })
    }

    private fun openingNewActivity(model: ImageAnswer) {
        val intent = Intent(context, DetailedInformationActivity::class.java)
        intent.putExtra("KEY", model)
        startActivity(intent)
    }

    private fun workWithAdapter() {
        RecyclerMainPopular.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        RecyclerMainPopular.adapter = myAdapter
    }

    private fun populateData(model: List<ImageAnswer>) {
        myAdapter.setData(model)
    }
}
