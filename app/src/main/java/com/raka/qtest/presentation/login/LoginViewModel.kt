package com.raka.qtest.presentation.login

import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRouter: LoginRouter){

    fun onSignInSuccessfull(){
        loginRouter.navigate(LoginRouter.Route.HOME_SCREEN)
    }
}