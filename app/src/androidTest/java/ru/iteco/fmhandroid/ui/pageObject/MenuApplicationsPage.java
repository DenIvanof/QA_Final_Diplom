package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MenuApplicationsPage {

    public static ViewInteraction MENU_BUTTON = onView(allOf(withId(ru.iteco.fmhandroid.R.id.main_menu_image_button)));
    public static ViewInteraction NEWS_BUTTON = onView(allOf(withId(android.R.id.title), withText("News")));
    public static ViewInteraction ABOUT_BUTTON = onView(allOf(withId(android.R.id.title), withText("About")));
    public static ViewInteraction MAIN_BUTTON = onView(allOf(withId(android.R.id.title), withText("Main")));
    public static ViewInteraction BUTTERFLY_BUTTON = onView(allOf(withId(R.id.our_mission_image_button)));
    public static ViewInteraction PROFILE_BUTTON = onView(allOf(withId(R.id.authorization_image_button)));
    public static ViewInteraction LOG_OUT_BUTTON = onView(allOf(withId(android.R.id.title), withText("Log out")));


    public void clickMenu() {
        Allure.step("Нажать на Трехстрочное меню");
        MenuApplicationsPage.MENU_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickNews() {
        Allure.step("Нажать кнопку Новости в трехстрочном меню");
        MenuApplicationsPage.NEWS_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickAbout() {
        Allure.step("Нажать кнопку О риложении в трехстрочном меню");
        MenuApplicationsPage.ABOUT_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickMain() {
        Allure.step("Нажать кнопку Главная в трехстрочном меню");
        MenuApplicationsPage.MAIN_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickButterfly() {
        Allure.step("Нажать кнопку Бабочки в меню приложения");
        MenuApplicationsPage.BUTTERFLY_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickProfile() {
        Allure.step("Нажать кнопку Профиля в меню приложения");
        MenuApplicationsPage.PROFILE_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void clickLogOut() {
        Allure.step("Нажать кнопку Выйти в меню приложения");
        MenuApplicationsPage.LOG_OUT_BUTTON.check(matches(isDisplayed())).perform(click());
    }
}