package com.example.test_task.ui.scenes.fragmentMenu.popularScenes

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
import com.example.test_task.ui.Adapter
import com.example.test_task.R
import com.example.test_task.data.model.ImageAnswer
import com.example.test_task.ui.PaginationScrollListener
import com.example.test_task.ui.scenes.activity.DetailedInformationActivity
import kotlinx.android.synthetic.main.fragment_popular.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class PopularFragment : MvpAppCompatFragment(), PopularView {

    private val popularPresenter by moxyPresenter { PopularPresenter() }
    private val myAdapter =
        Adapter { openingNewActivity(it) }

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
        visibilityProgressBar(false)
        showInternet()
        popularPresenter.swipeRefresh()
        SwipeRefreshLayoutPopular.setColorSchemeResources(
            android.R.color.holo_purple,
            android.R.color.holo_blue_bright,
            android.R.color.holo_blue_dark,
            android.R.color.holo_blue_light
        )
        SwipeRefreshLayoutPopular.setOnRefreshListener {
            popularPresenter.swipeRefresh()
        }
        RecyclerMainPopular.addOnScrollListener(
            PaginationScrollListener(
                { popularPresenter.getMoreItems() },
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
        RecyclerMainPopular.layoutManager = GridLayoutManager(context, 2)
        RecyclerMainPopular.adapter = myAdapter
    }

    override fun populateData(model: List<ImageAnswer>) {
        myAdapter.setData(model)
    }

    override fun showNoInternet() {
        RecyclerMainPopular.visibility = RecyclerView.INVISIBLE
        imageViewInternetPopular.visibility = ImageView.VISIBLE
        textViewInternetPopular.visibility = TextView.VISIBLE
        textViewInternetPopular2.visibility = TextView.VISIBLE
        SwipeRefreshLayoutPopular.isRefreshing = false
    }

    override fun showInternet() {
        RecyclerMainPopular.visibility = RecyclerView.VISIBLE
        textViewInternetPopular.visibility = TextView.INVISIBLE
        textViewInternetPopular2.visibility = TextView.INVISIBLE
        imageViewInternetPopular.visibility = ImageView.INVISIBLE
        SwipeRefreshLayoutPopular.isRefreshing = false
    }

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBarPopular.visibility = ProgressBar.VISIBLE
            false -> progressBarPopular.visibility = ProgressBar.INVISIBLE
        }
    }

    override fun addData(model: List<ImageAnswer>) {
        myAdapter.addData(model)
    }
}
