package com.eaglesakura.swagger;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterValidatorTest {

    @Test
    public void Nullは不正である() throws Throwable {
        assertFalse(new ParameterValidator(null).valid());
        assertTrue(new ParameterValidator("test").valid());
        assertTrue(new ParameterValidator(1).valid());
        assertTrue(new ParameterValidator(1.12f).valid());
        assertTrue(new ParameterValidator(1.123).valid());
        assertTrue(new ParameterValidator(this).valid());
    }

    @Test
    public void 文字列のチェック() throws Throwable {
        assertFalse(new ParameterValidator("tes").minLength(4).valid());
        assertTrue(new ParameterValidator("test").minLength(4).valid());

        assertTrue(new ParameterValidator("test").maxLength(4).valid());
        assertFalse(new ParameterValidator("test-").maxLength(4).valid());


        assertTrue(new ParameterValidator("test01234").pattern("/[0-9a-zA-Z]/").valid());
        assertFalse(new ParameterValidator("テスト文字列").pattern("/[0-9a-zA-Z]/").valid());

    }

}