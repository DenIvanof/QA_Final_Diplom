package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutPage {

    public static ViewInteraction BACK_BUTTON = onView(allOf(withId(R.id.about_back_image_button)));
    public static ViewInteraction VERSION = onView(allOf(withId(R.id.about_version_title_text_view), withText("Version:")));
    public static ViewInteraction PRIVACY_POLICY_BUTTON_LINK = onView(withId(R.id.about_privacy_policy_value_text_view));
    public static ViewInteraction TERMS_OF_USE_BUTTON_LINK = onView(withId(R.id.about_terms_of_use_value_text_view));

    public void clickBack() {
        Allure.step("Нажать на конпку Назад в разделе О приложении");
        AboutPage.BACK_BUTTON.check(matches(isDisplayed())).perform(click());
    }

    public void visibilityHeaderVersion() {
        Allure.step("Проверка на отображение версии на странице О приложении");
        AboutPage.VERSION.check(matches(isDisplayed()));
    }

    public void clickPrivacyPolicy() {
        Allure.step("Нажать на ссылку Политика конфиденциальности");
        PRIVACY_POLICY_BUTTON_LINK.check(matches(isDisplayed())).perform(click());
    }

    public void clickTermsOfUse() {
        Allure.step("Нажать на ссылку Условия эксплуатации");
        TERMS_OF_USE_BUTTON_LINK.check(matches(isDisplayed())).perform(click());
    }

    public void verifyIntent(String expectedUrl) {
        Allure.step("Проверка, что инициирован Intent с действием VIEW и правильным URL");
        intended(allOf(hasAction(Intent.ACTION_VIEW), hasData(expectedUrl)));
    }
}