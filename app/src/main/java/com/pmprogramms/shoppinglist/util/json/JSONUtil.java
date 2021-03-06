package com.pmprogramms.shoppinglist.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pmprogramms.shoppinglist.data.json.Item;
import com.pmprogramms.shoppinglist.data.json.ItemJSON;

import java.util.List;

public class JSONUtil {

    public List<Item> readFromJSON(String jsonString) {
        Gson gson = new Gson();
        ItemJSON itemJSON = gson.fromJson(jsonString, new TypeToken<ItemJSON>(){}.getType());
        return itemJSON.getItem();
    }

    public String generateJSONString(List<Item> items) {
        String json;

        Gson gson = new Gson();
        ItemJSON itemJSON = new ItemJSON(items);
        json = gson.toJson(itemJSON, ItemJSON.class);
        return json;
    }
}
