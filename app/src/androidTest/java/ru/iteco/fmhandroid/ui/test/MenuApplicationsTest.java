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
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.ButterflyPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("3 Меню приложения")
public class MenuApplicationsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    MenuApplicationsPage menuApplicationsPage = new MenuApplicationsPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    ButterflyPage butterflyPage = new ButterflyPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    AuthorizationTest authorizationTest = new AuthorizationTest();
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
        authorizationTest.registeredUserAuthorization();
    }

    @Test
    @DisplayName("3.1 Переход в раздел Главная через трехстрочное меню")
    public void goToMainThroughMenu() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("3.2 Переход в раздел Новости через трехстрочное меню")
    public void goToNewsThroughMenu() {
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.visibilityHeaderNews();
    }

    @Test
    @DisplayName("3.3 Переход в раздел О приложении через трехстрочное меню")
    public void goToAboutThroughMenu() {
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.visibilityHeaderVersion();
    }

    @Test
    @DisplayName("3.4 Переход в раздел Главное-жить любя")
    public void goToChapterButterfly() {
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("3.5 Выход из учетной записи приложения черз иконку профиля ")
    public void logOutProfileApp() {
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.verifySignInButtonVisible();
    }
}