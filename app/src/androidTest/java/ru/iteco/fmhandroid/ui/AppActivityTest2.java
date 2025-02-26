package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.pageObject.AboutPage;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.ButterflyPage;
import ru.iteco.fmhandroid.ui.pageObject.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;
import ru.iteco.fmhandroid.ui.test.AuthorizationTest;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest2 {

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
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    private View decorView;

    /*@Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(activity -> decorView = activity.getWindow().getDecorView());
        try {
            authorizationPage.verifySignInButtonVisible();
        } catch (Exception e) {
            menuApplicationsPage.clickProfile();
            menuApplicationsPage.clickLogOut();
        }
        authorizationTest.registeredUserAuthorization();
    }*/

    @Test
    public void appActivityTest2() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button)));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText("News")));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());

        Thread.sleep(3000);
        ViewInteraction textView = onView(allOf(withText("News")));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText("News")));
    }
    @Test
    public void shouldGoToNewsPageFromMainPage() throws InterruptedException {
        Thread.sleep(5000);
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();

        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickMain();
        menuApplicationsPage.clickButterfly();
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickAbout();
        aboutPage.clickBack();
        menuApplicationsPage.clickProfile();
        menuApplicationsPage.clickLogOut();
        authorizationPage.fillAuthorizationFields("login2", "password2");
        authorizationPage.clickSignIn();

    }

    @Test
    public void appActivityTest() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction materialTextView = onView(allOf(withId(R.id.all_news_text_view)));
        materialTextView.check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void shouldGoToNewsPageFromMainPage1() throws InterruptedException {
        Thread.sleep(5000);
        mainPage.clickAllNews();
    }

    @Test
    public void registeredUserAuthorization() throws InterruptedException {
        Thread.sleep(5000);
        authorizationPage.fillAuthorizationFields("login2", "password2");
        authorizationPage.clickSignIn();

    }

    @Test
    public void shouldGoToNewsPageFromMain() throws InterruptedException {
        Thread.sleep(5000);
        menuApplicationsPage.clickMenu();
        menuApplicationsPage.clickNews();
        newsPage.clickEditNews();
        Thread.sleep(2000);
        controlPanelPage.clickSortNewsControlPanel();
        Thread.sleep(2000);
        //controlPanelPage.clickFilterNewsControlPanel();
        controlPanelPage.clickAddNewsControlPanel();

    }

    @Test
    @DisplayName("5.2 Применение фильтра списка новостей в разделе Новости по категории Объявление")
    public void go() throws InterruptedException {
        Thread.sleep(5000);
        mainPage.clickAllNews();
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        Thread.sleep(2000);
        filterNewsPage.categoryFilterNews(Helper.ANNOUNCEMENT_CATEGORY);
        Thread.sleep(2000);
        filterNewsPage.clickFilter();
    }
}
