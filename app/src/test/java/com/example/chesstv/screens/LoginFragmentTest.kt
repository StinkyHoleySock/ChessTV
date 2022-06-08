package com.example.chesstv.screens

import org.junit.Assert.*
import org.junit.Test

class LoginFragmentTest {
    @Test
    fun `valid user's data returns true`() {
        val result = LoginFragment().validateUser(
            "piervov00@gmail.com",
            "12345678"
        )
        assertTrue(result)
    }

    @Test
    fun `valid email contains`() {
        val result = LoginFragment().isEmailValid("piervov00@gmail.com")

        assertTrue(result)
    }


}