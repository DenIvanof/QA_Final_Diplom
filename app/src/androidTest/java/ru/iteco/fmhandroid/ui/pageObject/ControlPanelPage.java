package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;

public class ControlPanelPage {

    public static ViewInteraction SORT_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.sort_news_material_button)));
    public static ViewInteraction FILTER_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.filter_news_material_button)));
    public static ViewInteraction ADD_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));
    private static final Matcher<View> DELETE_BUTTON = allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"));
    private static final Matcher<View> EDIT_BUTTON = allOf(withId(R.id.edit_news_item_image_view), withContentDescription("News editing button"));
    public static ViewInteraction OK_BUTTON = onView(allOf(withId(android.R.id.button1)));
    public static ViewInteraction CANCEL_BUTTON = onView(withId(android.R.id.button2));
    public static ViewInteraction HEADER_CONTROL_PANEL = onView(allOf(withText("Control panel")));
    public static final int NEWS_ITEM_PUBLICATION_DATE_VIEW = R.id.news_item_publication_date_text_view;
    public static final int NEWS_ITEM_PUBLISHED_TEXT_VIEW = R.id.news_item_published_text_view;
    public static final String STATUS_ACTIVE = Helper.getStringFromResource(R.string.news_control_panel_active);
    public static final String STATUS_NOT_ACTIVE = Helper.getStringFromResource(R.string.news_control_panel_not_active);
    public static ViewInteraction NEWS_LIST = onView(withId(R.id.news_list_recycler_view));
    public final int NEWS_ITEM_DESCRIPTION = (R.id.news_item_description_text_view);
    public final int NEWS_ITEM_MATERIAL_CARD = (R.id.news_item_material_card_view);



    public void visibilityHeaderControlPanel() {
        Allure.step("Проверка на отображение заголовка Панель управления на новостной странице");
        ControlPanelPage.HEADER_CONTROL_PANEL.check(matches(isDisplayed()));
    }

    public void clickSortNewsControlPanel() {
        Allure.step("Нажать на сортировку новостей на панели управления");
        ControlPanelPage.SORT_NEWS_CONTROL_PANEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickFilterNewsControlPanel() {
        Allure.step("Нажать на фильтр новостей на панели управления");
        ControlPanelPage.FILTER_NEWS_CONTROL_PANEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickAddNewsControlPanel() {
        Allure.step("Нажать на Создание новостей на панели управления");
        ControlPanelPage.ADD_NEWS_CONTROL_PANEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickDeletingNews(String text) {
        Allure.step("Удаление новости с заголовком '" + text + "'");
        onView(allOf(DELETE_BUTTON, hasSibling(withText(text)))).perform(click());
    }

    public void clickNewsEditor(String text) {
        Allure.step("Редактирование новости с заголовком '" + text + "'");
        onView(allOf(EDIT_BUTTON, hasSibling(withText(text)))).perform(click());
    }

    public void clickExpandNews(String text) {
        Allure.step("Развертываниие/Свертывание новости с заголовком '" + text + "'");
        ControlPanelPage.NEWS_LIST.check(matches(isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(text)), click()));
    }

    public void checkNewsDescription(String text) {
        Allure.step("Проверяем текст описания новости: " + text);
        onView(allOf(withId(NEWS_ITEM_DESCRIPTION), withText(text),
                withParent(withParent(withId(NEWS_ITEM_MATERIAL_CARD))), isDisplayed()))
                .check(matches(isCompletelyDisplayed()));
    }

    public void checkingDeletingNews(String text) {
        Allure.step("Проверить удаление новости c заголовком '" + text + "'");
        onView(allOf(withText(text), isDisplayed())).check(doesNotExist());
    }

    public void clickOkAlert() {
        Allure.step("Нажать OK в диалоговом окне");
        OK_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickCancelAlert() {
        Allure.step("Нажать отмена в диалоговом окне");
        CANCEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public String getDateAtPosition(int position) {
        Allure.step("Получить дату новости по позиции " + position);
        return Helper.getTextFromNews(NEWS_ITEM_PUBLICATION_DATE_VIEW, position);
    }

    public String getFirstNewsDate() {
        Allure.step("Получить дату первой новости");
        scrollToItem(0);
        return getDateAtPosition(0);
    }

    public int getItemCount() {
        Allure.step("Получить количество элементов в списке новостей");
        return Helper.getRecyclerViewItemCount(NewsPage.NEWS_LIST_RECYCLER_VIEW);
    }

    public int getNewsItemCount() {
        Allure.step("Получить количество элементов новостей");
        return Helper.getRecyclerViewItemCount(NewsPage.NEWS_LIST_RECYCLER_VIEW);
    }

    public String getLastNewsDate() {
        Allure.step("Получить дату последней новости");
        scrollToItem(getItemCount() - 1);
        return getDateAtPosition(getItemCount() - 1);
    }

    private void scrollToItem(int position) {
        Allure.step("Скролить список новостей до элемента на позиции " + position);
        onView(withId(NewsPage.NEWS_LIST_RECYCLER_VIEW)).perform(RecyclerViewActions.scrollToPosition(position));
    }

    public void scrollToNewsItem(int position) {
        Allure.step("Прокручиваем к элементу новостей с позицией: " + position);
        NewsPage.NEWS_LIST.perform(scrollToPosition(position))
                .perform(actionOnItemAtPosition(position, scrollTo()))
                .check(matches(isDisplayed()));
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

    public void scrollNewsList(String text) {
        Allure.step("Прокрутить до опубликованной новости '" + text + "'");
        ControlPanelPage.NEWS_LIST.check(matches(isDisplayed()))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(text))));
    }

    public void checkNews(String text) {
        Allure.step("Проверить элемент '" + text + "' на видимость");
        ViewInteraction titleView = onView(allOf(withText(text), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        titleView.check(matches(isDisplayed()));
        titleView.check(matches(withText(endsWith(text))));
    }

    public void checkAllNewsStatus(String expectedStatus) {
        Allure.step("Проверить, что все элементы в списке новостей имеют статус: " + expectedStatus);
        for (int i = 0; i < getNewsItemCount(); i++) {
            scrollToNewsItem(i);
            String actualStatus = Helper.getTextFromNews(NEWS_ITEM_PUBLISHED_TEXT_VIEW, i);
            assertEquals("Ожидается статус '" + expectedStatus + "' для элемента " + i,
                    expectedStatus, actualStatus);
        }
    }
}