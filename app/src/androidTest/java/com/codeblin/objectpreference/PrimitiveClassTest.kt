package com.codeblin.objectpreference

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.codeblin.objectpreference.models.IntegerClass
import com.codeblin.objectpreference.models.IntegerClassStoreModel
import com.codeblin.objectpreference.models.SharedPrefManager
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrimitiveClassTest {
    companion object{
        const val ASSERTION_VALUE = 100
    }

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setupManager(){
        SharedPrefManager.initialize(activityRule.activity.application)
    }

    @Test
    fun sharedPrefManagerIsInitialized() {
        SharedPrefManager.initialize(activityRule.activity.application)
        assert(SharedPrefManager.sharedPref != null)
    }

    @Test
    fun testInternalValue(){
        val storeModel = IntegerClassStoreModel(IntegerClass(ASSERTION_VALUE))
        assert(storeModel.value?.value == ASSERTION_VALUE)
    }

    @Test
    fun testSaveAndGet(){
        val storeModel = IntegerClassStoreModel(IntegerClass(ASSERTION_VALUE))
        storeModel.save()
        assert(storeModel.get().value == ASSERTION_VALUE)
    }

    @Test
    fun testDelete(){
        val storeModel = IntegerClassStoreModel(IntegerClass(ASSERTION_VALUE))
        storeModel.delete()
        assert(storeModel.get() == null)
    }
}