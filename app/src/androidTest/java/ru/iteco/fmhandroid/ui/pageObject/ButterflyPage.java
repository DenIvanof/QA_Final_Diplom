package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ButterflyPage {

    public static ViewInteraction EXPAND_QUOTES_BUTTON = onView(allOf(withId(R.id.our_mission_item_list_recycler_view)));
    public static ViewInteraction HEADER_LOVE_IS_ALL = onView(allOf(withId(R.id.our_mission_title_text_view), withText("Love is all")));

    public void clickExpandQuote() {
        Allure.step("Развернуть цитату на странице Главное-жить любя");
        ButterflyPage.EXPAND_QUOTES_BUTTON.perform(actionOnItemAtPosition(0, click()));
    }

    public void visibilityHeaderLoveIsAll() {
        Allure.step("Проверка на отображение заголовка Главное-жить любя");
        ButterflyPage.HEADER_LOVE_IS_ALL.check(matches(isDisplayed()));
    }
}
