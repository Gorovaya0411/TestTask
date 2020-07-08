package com.example.test_task.ui.scenes.fragmentMenu.newScenes

import com.example.test_task.data.apiServiсe.GalleryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class NewPresenter() : MvpPresenter<NewView>() {
    private val apiService = GalleryApiService.create()
    var increment: Int = 1
    var isRequest: Boolean = false
    fun swipeRefresh() {
        increment = 1
        val subscribe = apiService.getImage(true, false, page = increment)
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
            val subscribe = apiService.getImage(new = true, popular = false, page = increment)
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