package com.test.news.news

import com.test.news.R
import com.test.news.common.BaseTestRobot
import com.test.news.common.Launch

fun news(func: NewsRobot.() -> Unit) = NewsRobot().apply {
    func()
}

fun news(launch: Launch, func: NewsRobot.() -> Unit) = NewsRobot().apply {
    launch.launch()
    func()
}


class NewsRobot : BaseTestRobot() {
    fun clickOnSecondNewsPicture() {
        pressItemInList(
            listRes = R.id.recyclerViewNews,
            position = 1
        )
    }

    fun turnOffInternet() {
        turnOffData()
    }

    fun turnInternetBackOn() {
        turnOnData()
    }

    infix fun result(func: ResultRobot.() -> Unit): ResultRobot {
        return ResultRobot().apply { func() }
    }

    class ResultRobot : BaseTestRobot() {

        fun newsHomePageIsShown() {
            checkToolbarTitleString("News")
        }

        fun browserIsOpened() {
            viewIsVisible(R.id.recyclerViewNews)
        }

        fun failureToLoadMessageIsDisplayed() {
            textStringIsVisible("Failed to load news")
        }

    }
}
