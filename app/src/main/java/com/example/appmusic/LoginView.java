package com.example.appmusic;

public interface LoginView {

    void loginFail();

    void loginSuccessful();

    void navigateHome();

    void setErrorUsername();

    void setErrorPassword();
}
