package com.test.news.news

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.test.news.common.Launch
import com.test.news.features.news.presentation.NewsActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsLossOfConnection : Launch {
    @get:Rule
    var activityTestRule = ActivityTestRule<NewsActivity>(NewsActivity::class.java)

    override fun launch(intent: Intent?) {
        activityTestRule.launchActivity(Intent())
    }

    // Scenario 2 failed to load images

    @Before
    fun turnOffInternetAccess() {
        news {
            turnOffInternet()
        }
    }

    @After
    fun turnBackOnInternetAccess() {
        news {
            turnInternetBackOn()
        }
    }

// Scenario 2 failed to load images
    /**
     * Given: the user successfully logged in to the app
     * When: there is no internet connection
     * Then: “failed to load news” error message is displayed and Retry button
     **/
    @Test
    fun correctErrorMessageIsDisplayedIfNewsCannotBeLoaded() {
        news(this) {
            longSleep()
        } result {
            failureToLoadMessageIsDisplayed()
        }
    }
}
