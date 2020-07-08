package com.example.test_task.ui.scenes.fragmentMenu.newScenes

import com.example.test_task.data.model.ImageAnswer
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface NewView : MvpView {
    fun showInternet()
    fun showNoInternet()
    fun populateData(model: List<ImageAnswer>)
    fun visibilityProgressBar(isVisible: Boolean)
    fun addData(model: List<ImageAnswer>)
}