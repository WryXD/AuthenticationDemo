package com.example.authenticationdemo.domain.use_case

import com.example.authenticationdemo.util.containsNumbers
import com.example.authenticationdemo.util.containsSpecialCharacter
import com.example.authenticationdemo.util.containsUpperCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test string contain no number return false`(){
        val result = "Number".containsNumbers()
        assertEquals(result, false)
    }

    @Test
    fun `test string contain number return true`(){
        val result = "Number1".containsNumbers()
        assertEquals(result, true)
    }

    @Test
    fun `test string contains no upper case return false`(){
        val result = "hello".containsUpperCase()
        assertEquals(result, false)
    }

    @Test
    fun `test string contains upper case return true`(){
        val result = "Hello".containsUpperCase()
        assertEquals(result, true)
    }

    @Test
    fun `test string contains no special character return false`(){
        val result = "Hello".containsSpecialCharacter()
        assertEquals(result, false)
    }

    @Test
    fun `test string contains special character return true`(){
        val result = "@!#~~~Hello".containsSpecialCharacter()
        assertEquals(result, true)
    }
}