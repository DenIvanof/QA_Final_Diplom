package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ButterflyPage {

    public static ViewInteraction HEADER_LOVE_IS_ALL = onView(allOf(withId(R.id.our_mission_title_text_view), withText("Love is all")));
    public static ViewInteraction QUOTES_CARD = onView(withId(R.id.our_mission_item_list_recycler_view));
    public static ViewInteraction EXPAND_QUOTES_BUTTON = onView(allOf(withId(R.id.our_mission_item_list_recycler_view)));
    private static final int MATERIAL_CARD_VIEW = R.id.our_mission_item_material_card_view;
    public final int OUR_MISSION_DESCRIPTION = R.id.our_mission_item_description_text_view;
    public final int OUR_MISSION_QUOTE_TITLE = R.id.our_mission_item_title_text_view;

    public void visibilityHeaderLoveIsAll() {
        Allure.step("Проверка на отображение заголовка Главное-жить любя");
        ButterflyPage.HEADER_LOVE_IS_ALL.check(matches(isDisplayed()));
    }

    public void scrollQuotePosition(int position) {
        Allure.step("Прокрутить до позиции " + position);
        ButterflyPage.QUOTES_CARD.check(matches(isDisplayed())).perform(actionOnItemAtPosition(position, scrollTo()));
    }

    public void clickExpandQuote(int position) {
        Allure.step("Развернуть цитату на странице Главное-жить любя");
        ButterflyPage.EXPAND_QUOTES_BUTTON.perform(actionOnItemAtPosition(position, click()));
    }

    public void checkQuoteDescription(String expectedText) {
        Allure.step("Проверяем текст описания цитаты: " + expectedText);
        onView(allOf(withId(OUR_MISSION_DESCRIPTION), withText(expectedText),
                withParent(withParent(withId(MATERIAL_CARD_VIEW))), isDisplayed()))
                .check(matches(isCompletelyDisplayed()));
    }

    public void checkQuoteTitle(String expectedText) {
        Allure.step("Проверяем текст заголовка цитаты: " + expectedText);
        onView(allOf(withId(OUR_MISSION_QUOTE_TITLE), withText(expectedText),
                withParent(withParent(withId(MATERIAL_CARD_VIEW))), isDisplayed()))
                .check(matches(isCompletelyDisplayed()));
    }
}