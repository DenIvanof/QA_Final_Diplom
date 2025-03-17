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
import ru.iteco.fmhandroid.ui.pageObject.AddNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.ButterflyPage;
import ru.iteco.fmhandroid.ui.pageObject.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("7 Раздел Создание новости")
public class AddNewsTest {
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
    AddNewsPage addNewsPage = new AddNewsPage();
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
        mainPage.clickAllNews();
        newsPage.visibilityHeaderNews();
        newsPage.clickEditNews();
        try {
            controlPanelPage.visibilityHeaderControlPanel();
            controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
            controlPanelPage.clickDeletingNews(Helper.TITLE_NEWS_TEXT);
            controlPanelPage.clickOkAlert();
            controlPanelPage.checkingDeletingNews(Helper.TITLE_NEWS_TEXT);
            controlPanelPage.visibilityHeaderControlPanel();
        } catch (Exception e) {
            controlPanelPage.visibilityHeaderControlPanel();
        }
        controlPanelPage.clickAddNewsControlPanel();

    }

    @Test
    @DisplayName("7.1 Создание новости со всеми пустыми полями")
    public void emptyAddNews() {
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }

    @Test
    @DisplayName("7.2 Создание новости со всеми заполнеными полями")
    public void fullAddNews() {
        addNewsPage.categoryAddNews(Helper.ANNOUNCEMENT_CATEGORY);
        addNewsPage.titleAddNews(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setDate(0);
        addNewsPage.setTime(Helper.getCurrentTime());
        addNewsPage.addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);

    }

    @Test
    @DisplayName("7.3 Создание новости с не заполненным полем Категория")
    public void categoryNotFilled() {
        addNewsPage.titleAddNews(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setDate(0);
        addNewsPage.setTime(Helper.getCurrentTime());
        addNewsPage.addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }

    @Test
    @DisplayName("7.4 Создание новости с не заполненным полем Заголовок")
    public void titleNotFilled() {
        addNewsPage.categoryAddNews(Helper.ANNOUNCEMENT_CATEGORY);
        addNewsPage.setDate(0);
        addNewsPage.setTime(Helper.getCurrentTime());
        addNewsPage.addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }

    @Test
    @DisplayName("7.5 Создание новости с не заполненным полем Описание")
    public void descriptionNotFilled() {
        addNewsPage.categoryAddNews(Helper.ANNOUNCEMENT_CATEGORY);
        addNewsPage.titleAddNews(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setDate(0);
        addNewsPage.setTime(Helper.getCurrentTime());
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }

    @Test
    @DisplayName("7.6 Создание новости с не заполненным полем Дата публикации")
    public void dateNotFilled() {
        addNewsPage.categoryAddNews(Helper.ANNOUNCEMENT_CATEGORY);
        addNewsPage.titleAddNews(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setTime(Helper.getCurrentTime());
        addNewsPage.addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }

    @Test
    @DisplayName("7.7 Создание новости с не заполненным полем Время")
    public void timeNotFilled() {
        addNewsPage.categoryAddNews(Helper.ANNOUNCEMENT_CATEGORY);
        addNewsPage.titleAddNews(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setDate(0);
        addNewsPage.addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        addNewsPage.errorMessage();
    }
}
