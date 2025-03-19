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
        try {
            mainPage.verifyAllNewsButtonVisible();
        } catch (Exception e) {
            authorizationPage.verifySignInButtonVisible();
            authorizationPage.fillAuthorizationFields(Helper.VALID_LOGIN, Helper.VALID_PASSWORD);
            authorizationPage.clickSignIn();
            mainPage.verifyAllNewsButtonVisible();
        }
    }

    @Test//Тест падает т.к мы уже находимся на главной страницце и кнопка не активна
    @DisplayName("3.1 Переход в раздел Главная через трехстрочное меню с главной страницы")
    public void goToMainThroughMenuFromMainPage() {
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("3.2 Переход в раздел Новости через трехстрочное меню с главной страницы")
    public void goToNewsThroughMenuFromMainPage() {
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.visibilityHeaderNews();
    }

    @Test
    @DisplayName("3.3 Переход в раздел О приложении через трехстрочное меню с главной страницы")
    public void goToAboutThroughMenuFromMainPage() {
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.visibilityHeaderVersion();
    }

    @Test
    @DisplayName("3.4 Переход в раздел Главное-жить любя с главной страницы")
    public void goToChapterButterflyFromMainPage() {
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("3.5 Выход из учетной записи приложения черз иконку профиля с главной страницы")
    public void logOutProfileAppFromMainPage() {
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.verifySignInButtonVisible();
    }

    @Test
    @DisplayName("3.6 Переход в раздел Главная через трехстрочное меню с новостной страницы")
    public void goToMainThroughMenuFromNewsPage() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("3.7 Переход в раздел Новости через трехстрочное меню с новостной страницы")
    public void goToNewsThroughMenuFromNewsPage() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.visibilityHeaderNews();
    }

    @Test//Тест падает т.к раздел не доступен с новостной страницы
    @DisplayName("3.8 Переход в раздел О приложении через трехстрочное меню с новостной страницы")
    public void goToAboutThroughMenuFromNewsPage() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.visibilityHeaderVersion();
    }

    @Test
    @DisplayName("3.9 Переход в раздел Главное-жить любя с новостной страницы")
    public void goToChapterButterflyFromNewsPage() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("3.10 Выход из учетной записи приложения черз иконку профиля с новостной страницы")
    public void logOutProfileAppFromNewsPage() {
        mainPage.clickAllNews();
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.verifySignInButtonVisible();
    }

    @Test
    @DisplayName("3.11 Переход в раздел Главная через трехстрочное меню с панели управления")
    public void goToMainThroughMenuFromControlPanelPage() {
        mainPage.clickAllNews();
        newsPage.clickEditNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("3.12 Переход в раздел Новости через трехстрочное меню с панели управления")
    public void goToNewsThroughMenuFromControlPanelPage() {
        mainPage.clickAllNews();
        newsPage.clickEditNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.visibilityHeaderNews();
    }

    @Test
    @DisplayName("3.13 Переход в раздел О приложении через трехстрочное меню с панели управления")
    public void goToAboutThroughMenuFromControlPanelPage() {
        mainPage.clickAllNews();
        newsPage.clickEditNews();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.visibilityHeaderVersion();
    }

    @Test
    @DisplayName("3.14 Переход в раздел Главное-жить любя с панели управления")
    public void goToChapterButterflyFromControlPanelPage() {
        mainPage.clickAllNews();
        newsPage.clickEditNews();
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("3.15 Выход из учетной записи приложения черз иконку профиля с панели управления")
    public void logOutProfileAppFromControlPanelPage() {
        mainPage.clickAllNews();
        newsPage.clickEditNews();
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.verifySignInButtonVisible();
    }

    @Test
    @DisplayName("3.16 Переход в раздел Главная через трехстрочное меню с страницы Главное-жить любя")
    public void goToMainThroughMenuFromButterflyPage() {
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        mainPage.verifyAllNewsButtonVisible();
    }

    @Test
    @DisplayName("3.17 Переход в раздел Новости через трехстрочное меню с страницы Главное-жить любя")
    public void goToNewsThroughMenuFromButterflyPage() {
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.visibilityHeaderNews();
    }

    @Test
    @DisplayName("3.18 Переход в раздел О приложении через трехстрочное меню с страницы Главное-жить любя")
    public void goToAboutThroughMenuFromButterflyPage() {
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.visibilityHeaderVersion();
    }

    @Test
    @DisplayName("3.19 Переход в раздел Главное-жить любя с страницы Главное-жить любя")
    public void goToChapterButterflyFromButterflyPage() {
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("3.20 Выход из учетной записи приложения черз иконку профиля с страницы Главное-жить любя")
    public void logOutProfileAppFromButterflyPage() {
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.verifySignInButtonVisible();
    }
}