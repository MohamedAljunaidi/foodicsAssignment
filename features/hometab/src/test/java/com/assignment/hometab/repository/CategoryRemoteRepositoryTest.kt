package com.assignment.hometab.repository

import com.assignment.core.model.ResultWrapper
import com.assignment.hometab.common.TestConstants.API_ERROR
import com.assignment.hometab.common.TestConstants.CATEGORY_LIST_RESPONSE
import com.assignment.hometab.common.TestCoroutineRule
import com.assignment.hometab.tables.data.HomeService
import com.assignment.hometab.tables.data.mapper.toCategories
import com.assignment.hometab.tables.data.repository.CategoriesRemoteRepository
import com.assignment.network.result.NetworkResult
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
class CategoryRemoteRepositoryTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    private lateinit var sut: CategoriesRemoteRepository

    @Mock
    private lateinit var homeService: HomeService

    @Before
    fun setUp() {
        sut = CategoriesRemoteRepository(homeService)
    }

    @Test
    fun `test getCategories when api success, getCategories should emit success data`() {
        testCoroutineRule.runTest {
            prepareSuccess()
            val result = sut.getCategories()
            Assert.assertEquals(
                ResultWrapper.Success(
                    CATEGORY_LIST_RESPONSE.toCategories()
                ), result.single()
            )
        }
    }

    @Test
    fun `test getCategories when api failure, getCategories should emit failure data`() {
        testCoroutineRule.runTest {
            prepareError()
            val result = sut.getCategories()
            Assert.assertEquals(ResultWrapper.Error(API_ERROR), result.single())
        }
    }

    private suspend fun prepareSuccess() {
        Mockito.`when`(homeService.getCategories())
            .thenReturn(NetworkResult.Success(CATEGORY_LIST_RESPONSE))
    }

    private suspend fun prepareError() {
        Mockito.`when`(homeService.getCategories())
            .thenReturn(NetworkResult.Error(API_ERROR))
    }
}