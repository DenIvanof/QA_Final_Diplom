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
@Epic("4 Раздел Главная")
public class MainTest {

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
            mainPage.verifyAllNewsButtonVisible();
        } catch (Exception e) {
            authorizationPage.verifySignInButtonVisible();
            authorizationPage.fillAuthorizationFields(Helper.VALID_LOGIN, Helper.VALID_PASSWORD);
            authorizationPage.clickSignIn();
            mainPage.verifyAllNewsButtonVisible();
        }
    }

    @Test
    @DisplayName("4.1 В разделе Главная кнопка ссылка ВСЕ НОВОСТИ переводит в раздел Новости")
    public void goToNewsThroughAllNews() {
        mainPage.clickAllNews();
        newsPage.visibilityHeaderNews();
    }

    @Test
    @DisplayName("4.2 Свернуть список последних новостей")
    public void expandToNews() {
        mainPage.clickExpand();
        mainPage.allNewsNotDisplayed();
    }
}