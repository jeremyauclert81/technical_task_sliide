package com.test.news.login

import com.test.news.R
import com.test.news.common.BaseTestRobot
import com.test.news.common.Launch
import com.test.news.common.TestData.Companion.INVALID_USER_NAME
import com.test.news.common.TestData.Companion.INVALID_USER_PASSWORD
import com.test.news.common.TestData.Companion.VALID_USER_NAME
import com.test.news.common.TestData.Companion.VALID_USER_PASSWORD

fun login(func: LoginRobot.() -> Unit) = LoginRobot().apply {
    func()
}

fun login(launch: Launch, func: LoginRobot.() -> Unit) = LoginRobot().apply {
    launch.launch()
    func()
}


class LoginRobot : BaseTestRobot() {

    fun enterValidUsername() {
        fillEditText(
            resId = R.id.editTextUserName,
            text = VALID_USER_NAME
        )
    }

    fun enterValidPassword() {
        fillEditText(
            resId = R.id.editTextPassword,
            text = VALID_USER_PASSWORD
        )
    }

    fun successfulLoginCred() {
        enterValidUsername()
        enterValidPassword()
        clickLoginButton()
    }

    fun enterInvalidUsername() {
        fillEditText(
            resId = R.id.editTextUserName,
            text = INVALID_USER_NAME
        )
    }

    fun enterInvalidPassword() {
        fillEditText(
            resId = R.id.editTextPassword,
            text = INVALID_USER_PASSWORD
        )
    }

    fun clickUsernameField() {
        clickButton(R.id.editTextUserName)
    }

    fun clickPasswordField() {
        clickButton(R.id.editTextPassword)
    }

    fun invalidUsernameValidPassword() {
        enterInvalidUsername()
        enterValidPassword()
        clickLoginButton()
        clickUsernameField()
    }

    fun validUsernameInvalidPassword() {
        enterValidUsername()
        enterInvalidPassword()
        clickLoginButton()
        clickPasswordField()
    }

    fun clickLoginButton() {
        clickButton(R.id.buttonLogin)
    }

    infix fun result(func: ResultRobot.() -> Unit): ResultRobot {
        return ResultRobot().apply { func() }
    }

    class ResultRobot : BaseTestRobot() {

        fun usernameFieldIsVisible() {
            viewIsVisible(R.id.editTextUserName)
        }

        fun passwordFieldIsVisible() {
            viewIsVisible(R.id.editTextPassword)
        }

        fun loginButtonIsVisible() {
            viewIsVisible(R.id.buttonLogin)
        }

        fun loginScreenElementsCheck() {
            usernameFieldIsVisible()
            passwordFieldIsVisible()
            loginButtonIsVisible()
        }

        fun wrongUsernameAlertIsShown() {
            stringIsVisibleIsDescendant(
                text = "Wrong user name",
                resId = R.id.textViewError
            )
        }

        fun wrongPasswordAlertIsShown() {
            stringIsVisibleIsDescendant(
                text = "Wrong password",
                resId = R.id.textViewError
            )
        }

    }

}