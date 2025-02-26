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
import ru.iteco.fmhandroid.ui.pageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Раздел Новости")
public class NewsTest {

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
        mainPage.clickAllNews();
    }

    @Test
    @DisplayName("5.2 Применение фильтра списка новостей в разделе Новости по категории Объявление")
    public void filterCategoryAnnouncement() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.ANNOUNCEMENT_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.3 Применение фильтра списка новостей в разделе Новости по категории День рождения")
    public void filterCategoryBirthday() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.BIRTHDAY_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.4 Применение фильтра списка новостей в разделе Новости по категории Зарплата")
    public void filterCategorySalary() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.SALARY_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.5 Применение фильтра списка новостей в разделе Новости по категории Профсоюз")
    public void filterCategoryTradeUnion() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.TRADE_UNION_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.6 Применение фильтра списка новостей в разделе Новости по категории Праздник")
    public void filterCategoryHoliday() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.HOLIDAY_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.7 Применение фильтра списка новостей в разделе Новости по категории Массаж")
    public void filterCategoryMassage() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.MASSAGE_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.8 Применение фильтра списка новостей в разделе Новости по категории Благодарность")
    public void filterCategoryGratitude() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.GRATITUDE_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("5.9 Применение фильтра списка новостей в разделе Новости по категории Нужна помощь")
    public void filterCategoryNeedHelp() {
        newsPage.visibilityHeaderNews();
        newsPage.clickFilterNews();
        filterNewsPage.categoryFilterNews(Helper.NEED_HELP_CATEGORY);
        filterNewsPage.clickFilter();
    }

    @Test
    @DisplayName("Фильтрация новостей по диапазону дат")
    public void shouldFilterNewsWithinDateRange() {
        newsPage.clickFilterNews();
        filterNewsPage.enterFromWhatDate(-7);
        filterNewsPage.enterUntilWhatDate(7);
        filterNewsPage.clickFilter();
        newsPage.checkAllNewsDateRange(-7, 7);
    }
}
