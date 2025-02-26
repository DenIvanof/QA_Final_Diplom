package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertTrue;

import static ru.iteco.fmhandroid.ui.data.Helper.waitId;

import androidx.test.espresso.ViewInteraction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;

public class NewsPage {
    public static ViewInteraction SORT_NEWS_BUTTON = onView(allOf(withId(R.id.sort_news_material_button), withContentDescription("Sort news list button")));
    public static ViewInteraction FILTER_NEWS_BUTTON = onView(allOf(withId(R.id.filter_news_material_button)));
    public static ViewInteraction EDIT_NEWS_BUTTON = onView(allOf(withId(R.id.edit_news_material_button)));
    public static ViewInteraction HEADER_NEWS = onView(allOf(withText("News")));
    public static ViewInteraction NEWS_LIST = onView(withId(R.id.news_list_recycler_view));

    public static final int NEWS_LIST_RECYCLER_VIEW = R.id.news_list_recycler_view;
    public static final int NEWS_ITEM_PUBLICATION_DATE_VIEW = R.id.news_item_publication_date_text_view;

    public void visibilityHeaderNews() {
        Allure.step("Проверка на отображение заголовка Новости на новостной странице");
        NewsPage.HEADER_NEWS.check(matches(isDisplayed()));
    }

    public void clickSortNews() {
        Allure.step("Нажать на сортировку новостей на новостной странице");
        NewsPage.SORT_NEWS_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickFilterNews() {
        Allure.step("Нажать на фильтр новостей на новостной странице");
        NewsPage.FILTER_NEWS_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickEditNews() {
        Allure.step("Нажать на Редактирование новостей на новостной странице");
        NewsPage.EDIT_NEWS_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void scrollToNewsItem(int position) {
        Allure.step("Прокручиваем к элементу новостей с позицией: " + position);
        NEWS_LIST.perform(scrollToPosition(position))
                .perform(actionOnItemAtPosition(position, scrollTo()))
                .check(matches(isDisplayed()));
    }

    public int getNewsItemCount() {
        Allure.step("Получить количество элементов новостей");
        return Helper.getRecyclerViewItemCount(NEWS_LIST_RECYCLER_VIEW);
    }

    public void checkAllNewsDateRange(int fromWhatDay, int untilWhatDay) {
        String startDateStr = Helper.getDate(fromWhatDay);
        String endDateStr = Helper.getDate(untilWhatDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        Allure.step("Проверить, что все элементы в списке новостей имеют дату публикации в диапазоне от: " + startDate + " до: " + endDate);
        for (int i = 0; i < getNewsItemCount(); i++) {
            scrollToNewsItem(i);
            String actualDateStr = Helper.getTextFromNews(NEWS_ITEM_PUBLICATION_DATE_VIEW, i);
            LocalDate actualDate = LocalDate.parse(actualDateStr, formatter);
            assertTrue("Ожидается, что дата для элемента " + i + " будет в диапазоне от " + startDate + " до " + endDate,
                    (actualDate.isEqual(startDate) || actualDate.isEqual(endDate) ||
                            (actualDate.isAfter(startDate) && actualDate.isBefore(endDate))));
        }
    }

}
