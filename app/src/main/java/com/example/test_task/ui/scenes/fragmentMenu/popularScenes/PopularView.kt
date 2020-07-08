package com.example.test_task.ui.scenes.fragmentMenu.popularScenes

import com.example.test_task.data.model.ImageAnswer
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface PopularView : MvpView {
    fun showNoInternet()
    fun showInternet()
    fun populateData(model: List<ImageAnswer>)
    fun visibilityProgressBar(isVisible: Boolean)
    fun addData(model: List<ImageAnswer>)
}