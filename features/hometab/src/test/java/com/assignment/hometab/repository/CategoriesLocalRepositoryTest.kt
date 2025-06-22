package com.assignment.hometab.repository

import com.assignment.caching.manager.BaseManager
import com.assignment.caching.manager.CachingManager
import com.assignment.caching.manager.ProviderEnum
import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.CATEGORY_LIST_ENTITY
import com.assignment.hometab.common.TestConstants.RESULT_EXCEPTION
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.data.mapper.entityToCategoryList
import com.assignment.hometab.tables.data.repository.CategoriesLocalRepository
import kotlinx.coroutines.flow.single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CategoriesLocalRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: CategoriesLocalRepository

    @Mock
    private lateinit var cachingManager: CachingManager

    @Mock
    private lateinit var mockBaseManager: BaseManager

    @Before
    fun setUp() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM))
            .thenReturn(mockBaseManager)
        sut = CategoriesLocalRepository(cachingManager)
    }

    @Test
    fun `test getCategoryList when cache success, getCategoryList should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getCategories()
            Assert.assertEquals(
                ResultWrapper.Success(
                    CATEGORY_LIST_ENTITY.entityToCategoryList()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getCategoryList when cache failure, getCategoryList should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getCategories()
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    @Test
    fun `test insertCategories when cache success, insertCategories should emit success data`() {
        testCoroutineRule.runTest {
            prepareInsertCategoriesSuccess()
            val result = sut.insertCategories(CATEGORY_LIST_ENTITY.entityToCategoryList())
            Assert.assertEquals(
                ResultWrapper.Success(
                    Unit
                ), result.single()
            )
        }
    }

    @Test
    fun `test insertCategories when cache failure, insertCategories should emit failure data`() {
        testCoroutineRule.runTest {
            prepareInsertCategoriesError()
            val result = sut.insertCategories(CATEGORY_LIST_ENTITY.entityToCategoryList())
            Assert.assertEquals(ResultWrapper.Error(RESULT_EXCEPTION), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getCategories())
            .thenReturn(ResultWrapper.Success(CATEGORY_LIST_ENTITY))
    }

    private suspend fun prepareError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).getCategories())
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

    private suspend fun prepareInsertCategoriesSuccess() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).insertCategories(CATEGORY_LIST_ENTITY))
            .thenReturn(ResultWrapper.Success(Unit))
    }

    private suspend fun prepareInsertCategoriesError() {
        Mockito.`when`(cachingManager.getProvider(ProviderEnum.ROOM).insertCategories(CATEGORY_LIST_ENTITY))
            .thenReturn(ResultWrapper.Error(RESULT_EXCEPTION))
    }

}