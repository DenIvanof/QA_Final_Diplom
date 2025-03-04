package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.Helper.waitId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage {

    public static ViewInteraction ALL_NEWS_BUTTON = onView(allOf(withId(R.id.all_news_text_view)));
    public static int ALL_NEWS_TEXT_BUTTON = R.id.all_news_text_view;
    public static ViewInteraction EXPAND_MATERIAL_BUTTON = onView(allOf(withId(R.id.expand_material_button)));


    public void clickAllNews() {
        Allure.step("Нажать на Все новости на главной странице");
        MainPage.ALL_NEWS_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void verifyAllNewsButtonVisible() {
        Allure.step("Проверка на отображение кнопки Все новости на главной странице");
        onView(isRoot()).perform(waitId((ALL_NEWS_TEXT_BUTTON), 10000));
    }

    public void allNewsNotDisplayed() {
        Allure.step("Проверка отсутствия элемента на основной странице Main");
        MainPage.ALL_NEWS_BUTTON.check(matches(not(isDisplayed())));
    }

    public void clickExpand() {
        Allure.step("Развернуть новости на главной странице");
        MainPage.EXPAND_MATERIAL_BUTTON.check(matches(isDisplayed())).perform(click());
    }
}