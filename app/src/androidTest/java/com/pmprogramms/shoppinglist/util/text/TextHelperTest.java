package com.pmprogramms.shoppinglist.util.text;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextHelperTest {
    private TextHelper textHelper;
    @Before
    public void setUp() throws Exception {
        textHelper = new TextHelper();
    }

    @Test
    public void checkInputsShouldBeTrueWhenAllDataFill() {
        assertTrue(textHelper.checkInputs("test", "5"));

    }

    @Test
    public void checkTitleShouldBeTrueWhenTitleIsFill() {
        assertTrue(textHelper.checkTitle("test title"));
    }

    @Test
    public void checkInputsShouldBeFalseWhenAllDataNull() {
        assertFalse(textHelper.checkInputs(null, null));

    }

    @Test
    public void checkTitleShouldBeFalseWhenTitleIsNull() {
        assertFalse(textHelper.checkTitle(null));
    }

    @Test
    public void checkInputsShouldBeFalseWhenAllDataEmpty() {
        assertFalse(textHelper.checkInputs("", ""));

    }

    @Test
    public void checkInputsShouldBeFalseWhenOneIsEmptyAndOneIsNull() {
        assertFalse(textHelper.checkInputs("", null));
        assertFalse(textHelper.checkInputs( null, ""));
    }
}