package org.launchcode.boot.store.models;

import java.util.List;

public class SearchResponse {

    private Iterable<Item> items;
    private List<String> keywords;

    public SearchResponse(Iterable<Item> items, List<String> keywords) {
        this.items = items;
        this.keywords = keywords;
    }

    public Iterable<Item> getItems() {
        return items;
    }

    public void setItems(Iterable<Item> items) {
        this.items = items;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
