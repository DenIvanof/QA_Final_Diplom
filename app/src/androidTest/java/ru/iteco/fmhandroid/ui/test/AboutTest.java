package ru.iteco.fmhandroid.ui.test;

import android.view.View;

import androidx.test.espresso.intent.Intents;
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
@Epic("8 Раздел О приложении")
public class AboutTest {

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
    MenuApplicationsTest menuApplicationsTest = new MenuApplicationsTest();
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
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();

    }

    @Test
    @DisplayName("8.1 Гипер ссылка Политика конфиденциальности в разделе О приложении переводит на страницу с информацие Политика конфиденциальности")
    public void thereMustBeCorrectPrivacyPolicyUrl() {
        Intents.init();
        aboutPage.clickPrivacyPolicy();
        aboutPage.verifyIntent(Helper.PRIVACY_POLICY_URL);
        Intents.release();
    }

    @Test
    @DisplayName("8.2 Гипер ссылка Пользовательское соглашение в разделе О приложении переводит на страницу с информацие Пользовательское соглашение")
    public void thereMustBeCorrectTermsOfUseUrl() {
        Intents.init();
        aboutPage.clickTermsOfUse();
        aboutPage.verifyIntent(Helper.TERMS_OF_USE_URL);
        Intents.release();
    }
}