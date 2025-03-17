package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;

public class AddNewsPage {
    public static ViewInteraction SAVE_BUTTON = onView(allOf(withId(R.id.save_button)));
    public static ViewInteraction CANCEL_BUTTON = onView(allOf(withId(R.id.cancel_button)));
    public static ViewInteraction CATEGORY_NEWS = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction TITLE_NEWS = onView(withId(R.id.news_item_title_text_input_edit_text));
    public static ViewInteraction DATE_NEWS = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public static ViewInteraction TIME_NEWS = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public static ViewInteraction DESCRIPTION_NEWS = onView(withId(R.id.news_item_description_text_input_edit_text));
    public static ViewInteraction HEADER_ADD_NEWS = onView(allOf(withText("Creating")));
    private View decorView;

    public void visibilityHeaderAddNews() {
        Allure.step("Проверка на отображение заголовка Создание новостей");
        AddNewsPage.HEADER_ADD_NEWS.check(matches(isDisplayed()));
    }

    public void categoryAddNews(String category) {
        Allure.step("Установка категории '" + category + "'");
        AddNewsPage.CATEGORY_NEWS.check(matches(isDisplayed())).perform(replaceText(category), closeSoftKeyboard());
        onView(withText(category)).check(matches(isDisplayed()));
    }

    public void titleAddNews(String title) {
        Allure.step("Установка категории '" + title + "'");
        AddNewsPage.TITLE_NEWS.check(matches(isDisplayed())).perform(replaceText(title), closeSoftKeyboard());
        onView(withText(title)).check(matches(isDisplayed()));
    }

    public void setDate(int date) {
        Allure.step("Ввести дату новости '" + date + "'");
        AddNewsPage.DATE_NEWS.check(matches(isDisplayed())).perform(replaceText(Helper.getDate(date)), closeSoftKeyboard());
    }

    public void setTime(String text) {
        Allure.step("Ввести время новости '" + text + "'");
        AddNewsPage.TIME_NEWS.check(matches(isDisplayed())).perform(replaceText(text), closeSoftKeyboard());
    }

    public void addNewsDescription(String text) {
        Allure.step("Ввести описание новости '" + text + "'");
        AddNewsPage.DESCRIPTION_NEWS.check(matches(isDisplayed())).perform(replaceText(text));
    }

    public void clickCancel() {
        Allure.step("Проверка на отображение заголовка Панель управления на новостной странице");
        AddNewsPage.CANCEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickSave() {
        Allure.step("Проверка на отображение заголовка Панель управления на новостной странице");
        AddNewsPage.SAVE_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void errorMessage() {
        Allure.step("Всплывающее сообщение о пустом поле при авторизации");
        onView(withText(Helper.ERROR_MESSAGE))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void createNews(String category, String title) {
        Allure.step("Создать новость c заголовком '" + title + "'");
        categoryAddNews(category);
        titleAddNews(title);
        setDate(1);
        setTime(Helper.getCurrentTime());
        addNewsDescription(Helper.DESCRIPTION_NEWS_TEXT);
        clickSave();
    }

    public void visibilityTime(String text) {
        Allure.step("Проверить элемент '" + text + "' на видимость");
        ViewInteraction titleView = onView(allOf(withText(text), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        titleView.check(matches(isDisplayed()));
        titleView.check(matches(withText(endsWith(text))));
    }
}
