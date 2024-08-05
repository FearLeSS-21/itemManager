package com.example.demo.service;

import com.example.demo.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemServiceInterface {
    Item addItem(Item item);

    Optional<Item> getItemById(int id);

    List<Item> getAllItems();

    boolean deleteItemById(int id);
}
