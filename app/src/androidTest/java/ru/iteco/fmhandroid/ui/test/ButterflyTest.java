package ru.iteco.fmhandroid.ui.test;

import static org.junit.Assert.assertEquals;


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
import ru.iteco.fmhandroid.ui.pageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("9 Раздел Главное - жить любя")
public class ButterflyTest {
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
    FilterNewsPage filterNewsPage = new FilterNewsPage();
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
        menuApplicationsPage.clickButterfly();
        butterflyPage.visibilityHeaderLoveIsAll();
    }

    @Test
    @DisplayName("9.1 Работа скроллинга списка цитат в разделе Главное - жить любя")
    public void scrollQuote() {
        butterflyPage.scrollQuotePosition(0);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_0);
        butterflyPage.scrollQuotePosition(1);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_1);
        butterflyPage.scrollQuotePosition(2);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_2);
        butterflyPage.scrollQuotePosition(3);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_3);
        butterflyPage.scrollQuotePosition(4);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_4);
        butterflyPage.scrollQuotePosition(5);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_5);
        butterflyPage.scrollQuotePosition(6);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_6);
        butterflyPage.scrollQuotePosition(7);
        butterflyPage.checkQuoteTitle(Helper.QUOTE_TITLE_7);
    }

    @Test
    @DisplayName("9.2.1 Развертывание первого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void firstExpandQuote() {
        butterflyPage.clickExpandQuote(0);
        butterflyPage.scrollQuotePosition(0);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_0);
    }

    @Test
    @DisplayName("9.2.2 Развертывание второго комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void secondExpandQuote() {
        butterflyPage.clickExpandQuote(1);
        butterflyPage.scrollQuotePosition(1);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_1);
    }

    @Test
    @DisplayName("9.2.3 Развертывание третьего комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void thirdExpandQuote() {
        butterflyPage.clickExpandQuote(2);
        butterflyPage.scrollQuotePosition(2);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_2);
    }

    @Test
    @DisplayName("9.2.4 Развертывание четвертого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void fourthExpandQuote() {
        butterflyPage.clickExpandQuote(3);
        butterflyPage.scrollQuotePosition(3);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_3);
    }

    @Test
    @DisplayName("9.2.5 Развертывание пятого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void fifthExpandQuote() {
        butterflyPage.clickExpandQuote(4);
        butterflyPage.scrollQuotePosition(4);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_4);
    }

    @Test
    @DisplayName("9.2.6 Развертывание шестого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void sixthExpandQuote() {
        butterflyPage.clickExpandQuote(5);
        butterflyPage.scrollQuotePosition(5);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_5);
    }

    @Test
    @DisplayName("9.2.7 Развертывание седьмого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void seventhExpandQuote() {
        butterflyPage.clickExpandQuote(6);
        butterflyPage.scrollQuotePosition(6);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_6);
    }

    @Test
    @DisplayName("9.2.8 Развертывание восьмого комментария к цитате в разделе Главное - жить любя через нажатие на стрелочку")
    public void eighthExpandQuote() {
        butterflyPage.clickExpandQuote(7);
        butterflyPage.scrollQuotePosition(7);
        butterflyPage.checkQuoteDescription(Helper.QUOTE_DESCRIPTION_7);
    }
}
