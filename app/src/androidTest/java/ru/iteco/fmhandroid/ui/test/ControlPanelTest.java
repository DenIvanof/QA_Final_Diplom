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
import ru.iteco.fmhandroid.ui.pageObject.AddNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pageObject.ButterflyPage;
import ru.iteco.fmhandroid.ui.pageObject.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pageObject.MainPage;
import ru.iteco.fmhandroid.ui.pageObject.MenuApplicationsPage;
import ru.iteco.fmhandroid.ui.pageObject.NewsPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("6 Раздел Панель управления")
public class ControlPanelTest {
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
        } catch (Exception e) {
            controlPanelPage.visibilityHeaderControlPanel();
        }
        try {
            controlPanelPage.visibilityHeaderControlPanel();
            controlPanelPage.scrollNewsList(Helper.CHANGE_TITLE_NEWS_TEXT);
            controlPanelPage.clickDeletingNews(Helper.CHANGE_TITLE_NEWS_TEXT);
            controlPanelPage.clickOkAlert();
            controlPanelPage.checkingDeletingNews(Helper.CHANGE_TITLE_NEWS_TEXT);
        } catch (Exception e) {
            controlPanelPage.visibilityHeaderControlPanel();
        }
    }

    @Test
    @DisplayName("6.1 В разделе редактирования новостей Панель управления включить сортировку новостей от более старой к более новой новости и от более новой к более старой новости")
    public void verifyNewsDateSorting() {
        String firstDateBeforeSorting = controlPanelPage.getFirstNewsDate();
        String lastDateBeforeSorting = controlPanelPage.getLastNewsDate();
        controlPanelPage.clickSortNewsControlPanel();
        String firstDateAfterSorting = controlPanelPage.getFirstNewsDate();
        String lastDateAfterSorting = controlPanelPage.getLastNewsDate();
        assertEquals(lastDateBeforeSorting, firstDateAfterSorting);
        assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    }

    @Test
    @DisplayName("6.2 В разделе редактирования новостей Панель управления включить фильтр новостей по статусу новости только Не активна")
    public void filterStatusNotActive() {
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.clickActive();
        filterNewsPage.clickFilter();
        controlPanelPage.checkAllNewsStatus(ControlPanelPage.STATUS_NOT_ACTIVE);
    }

    @Test
    @DisplayName("6.3 В разделе редактирования новостей Панель управления включить фильтр новостей по статусу новости только Активна")
    public void filterStatusActive() {
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.clickNotActive();
        filterNewsPage.clickFilter();
        controlPanelPage.checkAllNewsStatus(ControlPanelPage.STATUS_ACTIVE);
    }

    @Test
    @DisplayName("6.4 В разделе редактирования новостей Панель управления включить фильтр новостей по дате")
    public void filterNewsWithinDateRange() {
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.enterFromWhatDate(-7);
        filterNewsPage.enterUntilWhatDate(7);
        filterNewsPage.clickFilter();
        controlPanelPage.checkAllNewsDateRange(-7, 7);
    }

    @Test
    @DisplayName("6.5 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Объявление")
    public void filterCategoryAnnouncementOnControlPanel() {
        String category = Helper.ANNOUNCEMENT_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.6 В разделе редактирования новостей Панель управления включить фильтр новостей по категории День рождения")
    public void filterCategoryBirthdaytOnControlPanel() {
        String category = Helper.BIRTHDAY_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.7 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Зарплата")
    public void filterCategorySalaryOnControlPanel() {
        String category = Helper.SALARY_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.8 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Профсоюз")
    public void filterCategoryTradeUnionOnControlPanel() {
        String category = Helper.TRADE_UNION_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.9 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Праздник")
    public void filterCategoryHolidayOnControlPanel() {
        String category = Helper.HOLIDAY_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.10 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Массаж")
    public void filterCategoryMassageOnControlPanel() {
        String category = Helper.MASSAGE_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.11 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Благодарность")
    public void filterCategoryGratitudeOnControlPanel() {
        String category = Helper.GRATITUDE_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test//?
    @DisplayName("6.12 В разделе редактирования новостей Панель управления включить фильтр новостей по категории Нужна помощь")
    public void filterCategoryNeedHelpOnControlPanel() {
        String category = Helper.NEED_HELP_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(category,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test//?
    @DisplayName("6.13 Удаление опубликованной новости на странице редактирования новестей Панель управления в разделе Новости")
    public void deletingPublishedNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickDeletingNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickOkAlert();
        controlPanelPage.checkingDeletingNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.14 Отмена удаления опубликованной новости на странице редактирования новестей Панель управления в разделе Новости")
    public void DeletingPublishedNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickDeletingNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickCancelAlert();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test//?
    @DisplayName("6.15 Развертывание описания к опубликованной новости на странице редактирования новостей Панель управления через нажатие на стрелочку")
    public void ExpandPublishedNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickExpandNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);

    }

    @Test//?
    @DisplayName("6.16 Свертывание описания к опубликованной новости на странице редактирования новостей Панель управления через нажатие на стрелочку")
    public void RollUpPublishedNewsOnControlPanel() {
        String title = Helper.TITLE_NEWS_TEXT;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,title);
        controlPanelPage.scrollNewsList(title);
        controlPanelPage.clickExpandNews(title);
        controlPanelPage.clickExpandNews(title);
        controlPanelPage.scrollNewsList(title);
        controlPanelPage.checkNews(title);
    }

    @Test
    @DisplayName("6.17 Редактирование поля Категория у опубликованной новости на странице редактирования новостей Панель управления")
    public void EditCategoryNewsOnControlPanel() {
        String category = Helper.NEED_HELP_CATEGORY;
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        filterNewsPage.categoryFilterNews(category);
        addNewsPage.clickSave();
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.categoryFilterNews(category);
        filterNewsPage.clickFilter();
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.18 Редактирование поля Заголовок у опубликованной новости на странице редактирования новостей Панель управления")
    public void EditTitleNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        addNewsPage.titleAddNews(Helper.CHANGE_TITLE_NEWS_TEXT);
        addNewsPage.clickSave();
        controlPanelPage.scrollNewsList(Helper.CHANGE_TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.CHANGE_TITLE_NEWS_TEXT);
    }

    @Test//?
    @DisplayName("6.19 Редактирование поля Описание у опубликованной новости на странице редактирования новостей Панель управления")
    public void EditDescriptionNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        addNewsPage.addNewsDescription(Helper.NEW_DESCRIPTION_NEWS_TEXT);
        addNewsPage.clickSave();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickExpandNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNewsDescription(Helper.NEW_DESCRIPTION_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.20 Редактирование статуса на Не активна у опубликованной активной новости на странице редактирования новостей Панель управления")
    public void StatusNotActiveNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY, Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        filterNewsPage.clickStatusButton();
        addNewsPage.clickSave();
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.clickActive();
        filterNewsPage.clickFilter();
        controlPanelPage.checkAllNewsStatus(ControlPanelPage.STATUS_NOT_ACTIVE);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.21 Редактирование статуса на Активна у опубликованной не активной новости на странице редактирования новостей Панель управления")
    public void StatusActiveNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY, Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        filterNewsPage.clickStatusButton();
        addNewsPage.clickSave();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        filterNewsPage.clickStatusButton();
        addNewsPage.clickSave();
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.clickNotActive();
        filterNewsPage.clickFilter();
        controlPanelPage.checkAllNewsStatus(ControlPanelPage.STATUS_ACTIVE);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.22 Редактирование поля Дата публикации у опубликованной новости на странице редактирования новостей Панель управления")
    public void EditDateNewsOnControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setDate(20);
        addNewsPage.clickSave();
        controlPanelPage.clickFilterNewsControlPanel();
        filterNewsPage.enterFromWhatDate(19);
        filterNewsPage.enterUntilWhatDate(21);
        filterNewsPage.clickFilter();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
    }

    @Test
    @DisplayName("6.23 Редактирование поля Время публикации у опубликованной новости на странице редактирования новостей Панель управления")
    public void EditTimeNewsOnControlPanel() {
        String time ="22:22";
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.createNews(Helper.ANNOUNCEMENT_CATEGORY,Helper.TITLE_NEWS_TEXT);
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        addNewsPage.setTime(time);
        addNewsPage.clickSave();
        controlPanelPage.scrollNewsList(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.checkNews(Helper.TITLE_NEWS_TEXT);
        controlPanelPage.clickNewsEditor(Helper.TITLE_NEWS_TEXT);
        addNewsPage.visibilityTime(time);
    }

    @Test
    @DisplayName("6.24 Переход на страницу Создание новости через Панель управления в разделе Новости")
    public void switchToNewsCreationUsingTheControlPanel() {
        controlPanelPage.clickAddNewsControlPanel();
        addNewsPage.visibilityHeaderAddNews();
    }
}
