package com.onstella

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.onstella.database.AppDao
import com.onstella.database.AppDatabase
import com.onstella.database.DataRepository
import com.onstella.database.entities.LearnCategories
import com.onstella.viewmodels.LearnViewModel
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {


   lateinit var database: AppDatabase
     lateinit var dao: AppDao
     //lateinit var dataRepository: DataRepository

     //lateinit var learnViewModel : LearnViewModel

    @Before
    fun setup() {

      //  learnViewModel  = LearnViewModel(dataRepository)

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.appDao()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.onstella", appContext.packageName)
    }

    //method to close the database after each test completed
    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun verifyGetCategoriesInsert(){
        var learnCategories = arrayListOf<LearnCategories>()
        var learnCategory = LearnCategories(1, "learn")
        learnCategories.add(learnCategory)
        dao.insertCategories(learnCategories)

        assertEquals(dao.getCategories(),learnCategories)

    }

    /**
     * verify unique key for categories table
     */
    @Test
    fun verifyInsertCategories(){
        var learnCategories = arrayListOf<LearnCategories>()
        var learnCategory = LearnCategories(1, "learn")
        var mindCategory = LearnCategories(1, "mindful")
        learnCategories.add(learnCategory)
        learnCategories.add(mindCategory)
        dao.insertCategories(learnCategories)
       // assertThrows( dao.insertCategories(learnCategories))
        assertFalse(dao.getCategories().contains(learnCategory))
        assertTrue(dao.getCategories().contains(mindCategory))

    }



}