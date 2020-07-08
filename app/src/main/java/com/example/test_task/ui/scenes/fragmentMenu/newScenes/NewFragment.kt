package com.example.test_task.ui.scenes.fragmentMenu.newScenes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_task.R
import com.example.test_task.data.model.ImageAnswer
import com.example.test_task.ui.Adapter
import com.example.test_task.ui.PaginationScrollListener
import com.example.test_task.ui.scenes.activity.DetailedInformationActivity
import kotlinx.android.synthetic.main.fragment_new.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class NewFragment : MvpAppCompatFragment(), NewView {

    private val newPresenter by moxyPresenter { NewPresenter() }

    private val myAdapter =
        Adapter { openingNewActivity(it) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        workWithAdapter()
        visibilityProgressBar(false)
        showInternet()
        newPresenter.swipeRefresh()

        SwipeRefreshLayoutNew.setColorSchemeResources(
            android.R.color.holo_purple,
            android.R.color.holo_blue_bright,
            android.R.color.holo_blue_dark,
            android.R.color.holo_blue_light
        )

        SwipeRefreshLayoutNew.setOnRefreshListener {
            newPresenter.swipeRefresh()
        }

        RecyclerMainNew.addOnScrollListener(
            PaginationScrollListener(
                { newPresenter.getMoreItems() },
                10
            )
        )
    }

    private fun openingNewActivity(model: ImageAnswer) {
        val intent = Intent(context, DetailedInformationActivity::class.java)
        intent.putExtra("KEY", model)
        startActivity(intent)
    }

    private fun workWithAdapter() {
        RecyclerMainNew.layoutManager = GridLayoutManager(context, 2)
        RecyclerMainNew.adapter = myAdapter
    }

    override fun populateData(model: List<ImageAnswer>) {
        myAdapter.setData(model)
    }

    override fun addData(model: List<ImageAnswer>) {
        myAdapter.addData(model)
    }


    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBarNew.visibility = ProgressBar.VISIBLE
            false -> progressBarNew.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun showNoInternet() {
        RecyclerMainNew.visibility = RecyclerView.INVISIBLE
        imageViewInternetNew.visibility = ImageView.VISIBLE
        textViewInternetNew.visibility = TextView.VISIBLE
        textViewInternetNew2.visibility = TextView.VISIBLE
        SwipeRefreshLayoutNew.isRefreshing = false
    }

    override fun showInternet() {
        RecyclerMainNew.visibility = RecyclerView.VISIBLE
        textViewInternetNew.visibility = TextView.INVISIBLE
        textViewInternetNew2.visibility = TextView.INVISIBLE
        imageViewInternetNew.visibility = ImageView.INVISIBLE
        SwipeRefreshLayoutNew.isRefreshing = false
    }

}