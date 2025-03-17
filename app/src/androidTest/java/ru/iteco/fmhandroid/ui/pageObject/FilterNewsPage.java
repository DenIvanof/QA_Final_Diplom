package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


import androidx.test.espresso.ViewInteraction;

import java.util.Arrays;
import java.util.List;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Helper;

public class FilterNewsPage {
    public static ViewInteraction FILTER_BUTTON = onView(allOf(withId(R.id.filter_button), withText("Filter")));
    public static ViewInteraction CANCEL_BUTTON = onView(allOf(withId(R.id.cancel_button), withText("Cancel")));
    public static ViewInteraction CATEGORY_FILTER_NEWS = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public static ViewInteraction FROM_WHAT_DATE = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public static ViewInteraction UNTIL_WHAT_DATE = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public static ViewInteraction ACTIVE_CHECK_BOX = onView(withId(R.id.filter_news_active_material_check_box));
    public static ViewInteraction NOT_ACTIVE_CHECK_BOX = onView(withId(R.id.filter_news_inactive_material_check_box));
    public static ViewInteraction STATUS_BUTTON = onView(allOf(withId(R.id.switcher)));

    public void clickFilter() {
        Allure.step("Нажать на кнопку Фильтр");
        FilterNewsPage.FILTER_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickCancel() {
        Allure.step("Нажать на кнопку Отмена");
        FilterNewsPage.CANCEL_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void categoryFilterNews(String category) {
        Allure.step("Установка категории '" + category + "'");
        CATEGORY_FILTER_NEWS.check(matches(isDisplayed())).perform(replaceText(category), closeSoftKeyboard());
        onView(withText(category)).check(matches(isDisplayed()));
    }

    public void enterFromWhatDate(int days) {
        Allure.step("Ввести применить фильтр от '" + days + "' числа даты");
        FROM_WHAT_DATE.check(matches(isDisplayed())).perform(replaceText(Helper.getDate(days)), closeSoftKeyboard());
    }

    public void enterUntilWhatDate(int days) {
        Allure.step("Ввести применить фильтр до '" + days + "' числа даты");
        UNTIL_WHAT_DATE.check(matches(isDisplayed())).perform(replaceText(Helper.getDate(days)), closeSoftKeyboard());
    }

    public void clickActive() {
        Allure.step("Нажать на чекбокс Активна");
        FilterNewsPage.ACTIVE_CHECK_BOX.check(matches(isDisplayed())).perform(click());
    }

    public void clickNotActive() {
        Allure.step("Нажать на чекбокс Не активна");
        FilterNewsPage.NOT_ACTIVE_CHECK_BOX.check(matches(isDisplayed())).perform(click());
    }

    public void clickStatusButton() {
        Allure.step("Нажать на переключатель статуса");
        FilterNewsPage.STATUS_BUTTON.check(matches(isDisplayed())).perform(click());
    }
}