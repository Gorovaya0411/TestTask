package com.example.test_task.ui.scenes.fragmentMenu.popularScenes

import com.example.test_task.data.apiServiсe.GalleryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class PopularPresenter() : MvpPresenter<PopularView>() {
    var increment: Int = 1
    private val apiService = GalleryApiService.create()
    var isRequest: Boolean = false
    fun swipeRefresh() {
        increment = 1
        val subscribe = apiService.getImage(false, true, page = increment)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                viewState.populateData(it.data)
                viewState.showInternet()
            }, {
                viewState.showNoInternet()

            })
    }

    fun getMoreItems() {
        if (!isRequest) {
            isRequest = true
            increment += 1
            viewState.visibilityProgressBar(true)
            val subscribe = apiService.getImage(new = false, popular = true, page = increment)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    viewState.addData(it.data)
                    isRequest = false
                    viewState.visibilityProgressBar(false)
                }, { error ->
                    error.printStackTrace()
                })
        }
    }
}