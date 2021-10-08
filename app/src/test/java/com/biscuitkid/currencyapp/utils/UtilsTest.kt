package com.biscuitkid.currencyapp.utils

import android.content.Context
import android.content.res.AssetManager
import io.mockk.*
import org.junit.After
import org.junit.jupiter.api.assertThrows
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.IOException
import java.io.StringWriter
import java.lang.Exception


class UtilsTest {

    lateinit var mockContext: Context
    lateinit var mockAssetManager: AssetManager

    @Before
    fun before() {
        mockContext = mockk(relaxed = true)
        mockAssetManager = mockk(relaxed = true)

        every { mockContext.assets } returns mockAssetManager

        mockkConstructor(BufferedReader::class, StringWriter::class)
    }

    @After
    fun after() {
        unmockkConstructor(BufferedReader::class, StringWriter::class)
    }

    @Test
    fun giveOpenAssertsSuccess() {
        every { anyConstructed<BufferedReader>().read(any<CharArray>()) } returns 5
        every {
            anyConstructed<StringWriter>().write(
                any<CharArray>(), 0, any()
            )
        } answers {
            every {
                anyConstructed<BufferedReader>().read(any<CharArray>())
            } returns -1
        }


        Utils.openAssets(mockContext, "")

        verify {
            anyConstructed<StringWriter>().write(
                any<CharArray>(),
                any(),
                any()
            )
        }

    }
}