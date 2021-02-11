package com.pmprogramms.shoppinglist.util.text;

import org.jetbrains.annotations.NotNull;

public class TextHelper {
    public boolean checkInputs(String item, String count) {
        return item != null && count != null &&
                !item.equals("") && !count.equals("");
    }

    public boolean checkTitle(String title) {
        return title != null && !title.equals("");
    }
}
