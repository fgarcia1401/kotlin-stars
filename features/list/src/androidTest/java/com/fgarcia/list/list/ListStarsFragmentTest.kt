package com.fgarcia.list.list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fgarcia.common.network.di.BaseUrlModule
import com.fgarcia.list.launchFragmentInHiltContainer
import com.fgarcia.list.presentation.list.ListStarsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class ListStarsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val robot = ListStartsRobot()

    @Before
    fun setUp() {
        robot.start()
        launchFragmentInHiltContainer<ListStarsFragment>()
    }

    @Test
    fun shouldShowListStars_whenViewIsCreated() {
        robot.run {
            mockListStart()
            checkShowListStars()
        }
    }

    @Test
    fun shouldShowMoreListStars_whenNewPageViewIsCreated() {
        robot.run {
            mockPagination()
            checkShowListStars()
            checkPaginationTextIsDisplayed()
        }
    }

    @Test
    fun shouldShowErrorListStars_whenReturnError() {
        robot.run {
            mockError()
            checkShowError()
        }
    }

    @After
    fun tearDown() = robot.stopServer()

}
