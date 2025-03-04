package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ControlPanelPage {

    public static ViewInteraction SORT_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.sort_news_material_button)));
    public static ViewInteraction FILTER_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.filter_news_material_button)));
    public static ViewInteraction ADD_NEWS_CONTROL_PANEL_BUTTON = onView(allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")));

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
}