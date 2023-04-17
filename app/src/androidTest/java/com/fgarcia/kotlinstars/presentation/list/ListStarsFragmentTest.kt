package com.fgarcia.kotlinstars.presentation.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fgarcia.kotlinstars.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.fgarcia.kotlinstars.R
import com.fgarcia.kotlinstars.extension.asJsonString
import com.fgarcia.kotlinstars.frameworks.di.BaseUrlModule
import com.fgarcia.kotlinstars.presentation.list.adapter.viewholder.ItemStarsViewHolder
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class ListStarsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply { start(PORT) }
        launchFragmentInHiltContainer<ListStarsFragment>()
    }

    @Test
    fun shouldShowListStars_whenViewIsCreated() {
        server.enqueue(MockResponse().setBody(MOCK_LIST.asJsonString()))
        onView(
            withId(R.id.recycler_stars)
        ).check(
            matches(isDisplayed())
        )

        onView(
            withId(R.id.include_view_stats_error_state)
        ).check(
            matches(not(isDisplayed()))
        )
    }

    @Test
    fun shouldShowMoreListStars_whenNewPageViewIsCreated() {
        with(server) {
            enqueue(MockResponse().setBody(MOCK_LIST.asJsonString()))
            enqueue(MockResponse().setBody(MOCK_LIST_PAGINATION.asJsonString()))
        }

        // Action
        onView(
            withId(R.id.recycler_stars)
        ).perform(
            RecyclerViewActions
                .scrollToPosition<ItemStarsViewHolder>(30)
        )

        // Assert
        onView(
            withId(R.id.recycler_stars)
        ).check(
            matches(isDisplayed())
        )
        onView(withText("ktorio / ktor")).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun shouldShowErrorListStars_whenReturnError() {
        server.enqueue(MockResponse().setResponseCode(404))
        onView(
            withId(R.id.text_initial_loading_error)
        ).check(
            matches(isDisplayed())
        )
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private companion object {
        const val PORT = 8080
        const val MOCK_LIST = "list_star.json"
        const val MOCK_LIST_PAGINATION = "list_star_page_2.json"
    }

}