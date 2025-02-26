package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.data.Helper.RecyclerViewMatcher.withRecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.platform.app.InstrumentationRegistry;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.runner.Description;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

import ru.iteco.fmhandroid.R;

public class Helper {

    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";
    public static final String INVALID_LOGIN = "login";
    public static final String INVALID_PASSWORD = "password";
    public static final String AUTHENTICATION_ERROR_MESSAGE = "Something went wrong. Try again later.";
    public static final String EMPTY_FIELD = "";
    public static final String EMPTY_FIELDS_ERROR_MESSAGE = "Login and password cannot be empty";
    public static final String PRIVACY_POLICY_URL = "https://vhospice.org/#/privacy-policy/";
    public static final String TERMS_OF_USE_URL = "https://vhospice.org/#/terms-of-use";
    public static final String ANNOUNCEMENT_CATEGORY = "Объявление";
    public static final String BIRTHDAY_CATEGORY = "День рождения";
    public static final String SALARY_CATEGORY = "Зарплата";
    public static final String TRADE_UNION_CATEGORY = "Профсоюз";
    public static final String HOLIDAY_CATEGORY = "Праздник";
    public static final String MASSAGE_CATEGORY = "Массаж";
    public static final String GRATITUDE_CATEGORY = "Благодарность";
    public static final String NEED_HELP_CATEGORY = "Нужна помощь";


    // Метод waitForView ожидает появления элемента на странице, чтобы убедиться, что он готов к взаимодействию.
    public static ViewAction waitForView(final Matcher<View> viewMatcher, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long endTime = System.currentTimeMillis() + millis;

                while (System.currentTimeMillis() < endTime) {
                    if (isViewPresent(view, viewMatcher)) {
                        return;
                    }
                    uiController.loopMainThreadForAtLeast(50);
                }

                throw createTimeoutException(view);
            }

            private boolean isViewPresent(View rootView, Matcher<View> viewMatcher) {
                for (View child : TreeIterables.breadthFirstViewTraversal(rootView)) {
                    if (viewMatcher.matches(child)) {
                        return true;
                    }
                }
                return false;
            }

            private PerformException createTimeoutException(View view) {
                return new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }

    public static ViewAction waitId(final int viewId, final long millis) {
        return waitForView(withId(viewId), millis);
    }

    public static ViewAction waitMatcher(final Matcher<View> matcher, final long millis) {
        return waitForView(matcher, millis);
    }

    // Параметр days положительный для предстоящей даты и отрицательный для прошедшей даты
    public static String getDate(int days) {
        LocalDate date;
        if (days >= 0) {
            date = LocalDate.now().plusDays(days); // Будущая дата
        } else {
            date = LocalDate.now().minusDays(-days); // Прошлая дата
        }
        return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    // Метод извлекает строку по идентификатору ресурса из ресурсов приложения.
    public static String getStringFromResource(int resourceId) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(resourceId);
    }

    // Метод возвращает количество элементов в указанном RecyclerView.
    public static int getRecyclerViewItemCount(@IdRes int recyclerViewId) {
        final int[] count = new int[1];
        onView(allOf(withId(recyclerViewId), isDisplayed()))
                .check((view, noViewFoundException) -> {
                    if (view instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) view;
                        RecyclerView.Adapter adapter = recyclerView.getAdapter();
                        if (adapter != null) {
                            count[0] = adapter.getItemCount();
                        }
                    }
                });
        return count[0];
    }

    // Метод получает текст из элемента новостей по заданной позиции.
    public static String getTextFromNews(int fieldId, int position) {
        final String[] itemText = new String[1];
        onView(withRecyclerView(R.id.news_list_recycler_view).atPosition(position))
                .check((view, noViewFoundException) -> {
                    if (noViewFoundException != null) {
                        throw noViewFoundException;
                    }
                    TextView textView = view.findViewById(fieldId);
                    itemText[0] = textView.getText().toString();
                });
        return itemText[0];
    }

    // Класс для поиска элементов в RecyclerView по позициям и идентификаторам.
    public static class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
            return new RecyclerViewMatcher(recyclerViewId);
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {
            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException e) {
                            idDescription = String.format("%s (resource not found)", recyclerViewId);
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {
                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView = view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                            if (viewHolder != null) {
                                childView = viewHolder.itemView;
                            }
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }
                }
            };
        }
    }


    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
