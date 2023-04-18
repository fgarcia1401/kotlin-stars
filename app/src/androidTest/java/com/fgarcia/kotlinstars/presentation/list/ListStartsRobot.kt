package com.fgarcia.kotlinstars.presentation.list

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.fgarcia.kotlinstars.R
import com.fgarcia.kotlinstars.extension.asJsonString
import com.fgarcia.kotlinstars.presentation.list.adapter.viewholder.ItemStarsViewHolder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class ListStartsRobot {

    private lateinit var server: MockWebServer

    fun start() {
        server = MockWebServer().apply { start(PORT) }
    }

    fun mockListStart() {
        server.enqueue(MockResponse().setBody(MOCK_LIST.asJsonString()))
    }

    fun mockPagination() {
        with(server) {
            enqueue(MockResponse().setBody(MOCK_LIST.asJsonString()))
            enqueue(MockResponse().setBody(MOCK_LIST_PAGINATION.asJsonString()))
        }
    }

    fun checkShowListStars() {
        Espresso.onView(
            ViewMatchers.withId(R.id.recycler_stars)
        ).check(
            ViewAssertions.matches(isDisplayed())
        )
    }

    fun checkPaginationTextIsDisplayed() {
        Espresso.onView(
            ViewMatchers.withId(R.id.recycler_stars)
        ).perform(
            RecyclerViewActions
                .scrollToPosition<ItemStarsViewHolder>(30)
        )

        Espresso.onView(ViewMatchers.withText(TEXT_NAME_)).check(
            ViewAssertions.matches(isDisplayed())
        )
    }

    fun mockError() {
        server.enqueue(MockResponse().setResponseCode(404))
    }

    fun checkShowError() {
        Espresso.onView(
            ViewMatchers.withId(R.id.text_initial_loading_error)
        ).check(
            ViewAssertions.matches(isDisplayed())
        )
    }

    fun stopServer() {
        server.shutdown()
    }

    private companion object {
        const val PORT = 8080
        const val MOCK_LIST = "list_star.json"
        const val MOCK_LIST_PAGINATION = "list_star_page_2.json"
        const val TEXT_NAME_ = "ktorio / ktor"
    }

}