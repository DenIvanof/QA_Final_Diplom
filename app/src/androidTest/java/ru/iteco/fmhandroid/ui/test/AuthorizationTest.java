package ru.iteco.fmhandroid.ui.test;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("2 Страница авторизациии")
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    MenuApplicationsPage menuApplicationsPage = new MenuApplicationsPage();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        try {
            authorizationPage.verifySignInButtonVisible();
        } catch (Exception e) {
            menuApplicationsPage.clickProfile();
            menuApplicationsPage.clickLogOut();
        }
    }

    @Test
    @DisplayName("2.1 Авторизация при валидном вводе в поле Логин и Пароль")
    public void registeredUserAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.VALID_LOGIN, Helper.VALID_PASSWORD);
        authorizationPage.clickSignIn();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("2.2 Авторизация при не валидном вводе в поле Логин")
    public void invalidLoginAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.INVALID_LOGIN, Helper.VALID_PASSWORD);
        authorizationPage.clickSignIn();
        authorizationPage.authorizationErrorMessageDisplay();
    }

    @Test
    @DisplayName("2.3 Авторизация при не валидном вводе в поле Пароль")
    public void invalidPasswordAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.VALID_LOGIN, Helper.INVALID_PASSWORD);
        authorizationPage.clickSignIn();
        authorizationPage.authorizationErrorMessageDisplay();
    }

    @Test
    @DisplayName("2.4 Авторизация при пустых полях Логин и Пароль")
    public void emptyLoginPasswordAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.EMPTY_FIELD, Helper.EMPTY_FIELD);
        authorizationPage.clickSignIn();
        authorizationPage.emptyFieldErrorMessageDisplay();
    }

    @Test
    @DisplayName("2.5 Авторизация при пустом поле Пароль")
    public void emptyPasswordAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.VALID_LOGIN, Helper.EMPTY_FIELD);
        authorizationPage.clickSignIn();
        authorizationPage.emptyFieldErrorMessageDisplay();
    }

    @Test
    @DisplayName("2.6 Авторизация при пустом поле Логин")
    public void emptyLoginAuthorization() {
        authorizationPage.fillAuthorizationFields(Helper.EMPTY_FIELD, Helper.VALID_PASSWORD);
        authorizationPage.clickSignIn();
        authorizationPage.emptyFieldErrorMessageDisplay();
    }

}