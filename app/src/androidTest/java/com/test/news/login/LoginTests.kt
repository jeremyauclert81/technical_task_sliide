package com.test.news.login

import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.test.news.common.Launch
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.news.news
import org.junit.Rule
import org.junit.Test

class LoginTests : Launch {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    override fun launch(intent: Intent?) {
        activityTestRule.launchActivity(Intent())
    }

    //Scenario 1 user opens the android app first time (when not logged in yet)
    /**
     * Given:   The user opens app for the first time (when not logged in yet)
     * When:    The user looks at the login screen
     * Then:    Login screen with user name and password entries and login button is displayed
     **/
    @Test
    fun loginScreenInitialChecks() {
        login(this) {
            shortSleep()
        } result {
            loginScreenElementsCheck()
        }
    }

    // Scenario 2 user login failed
    // I am writing a test for both scenario, should one scenario fail, it is quicker to pinpoint
    // where the defect is.
    /**
     * Given:   the user provided wrong user name and/or password
     * When:    login button is clicked
     * Then:    error markers are displayed by user name and/or password entries
     **/
    @Test
    fun failedLoginInvalidUsernameChecks() {
        login(this) {
            invalidUsernameValidPassword()
        } result {
            wrongUsernameAlertIsShown()
        }
    }

    @Test
    fun failedLoginValidUserInvalidPassword() {
        login(this) {
            validUsernameInvalidPassword()
        } result {
            wrongPasswordAlertIsShown()
        }
    }

    //Scenario 3 user login succeed
    /**
     * Given: the user provided right user name and password
     * When: login button is clicked
     * Then: user is taken to the news screen
     **/

    @Test
    fun loginWithValidCredentials() {
        login(this) {
            successfulLoginCred()
        }
        news {
            mediumSleep()
        } result {
            newsHomePageIsShown()
        }
    }
}
