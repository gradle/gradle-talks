package org.shops;

import com.google.common.collect.MapMaker;

import java.util.Map;

public class Grocery {

    Map products = new MapMaker().makeMap();

    public boolean isProductAvailable(String product) {
        return products.containsKey(product);
    }
}