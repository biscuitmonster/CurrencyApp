package com.biscuitkid.currencyapp.viewModel

import com.biscuitkid.currencyapp.InstantTaskExecutorRule
import com.biscuitkid.currencyapp.RxImmediateSchedulerRule
import com.biscuitkid.currencyapp.database.CurrencyRepo
import com.biscuitkid.currencyapp.model.CurrencyInfo
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class CurrencyViewModelTest {
    val Rxrule = RxImmediateSchedulerRule()
    val rule = InstantTaskExecutorRule()
    val mockCurrencyRepo = mockk<CurrencyRepo>()
    lateinit var instance: CurrencyViewModel

    @Before
    fun before() {
        Rxrule.run()
        rule.start()

        instance = CurrencyViewModel(mockCurrencyRepo)
    }

    @After
    fun after() {
        Rxrule.reset()
        rule.stop()
    }

    @Test
    fun testOnLoad() {
        val mockResult = mockk<List<CurrencyInfo>>()

        every { mockCurrencyRepo.selectAllCurrencyInfo() } returns Single.just(mockResult)

        instance.onLoad()

        //test
        assertEquals(instance.currencyInfoList.value, mockResult)
    }

    @Test
    fun testOnSort() {
        val mockResult = mockk<List<CurrencyInfo>>()

        every { mockCurrencyRepo.selectAllCurrencyInfoSortByName() } returns Single.just(mockResult)

        instance.onSort()

        //test
        assertEquals(instance.currencyInfoList.value, mockResult)
    }
}