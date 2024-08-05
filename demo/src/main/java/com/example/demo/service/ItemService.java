package com.example.demo.service;

import com.example.demo.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements ItemServiceInterface {

    private List<Item> items = new ArrayList<Item>() {{
        add(new Item(1, "Item1", "Item1 is here"));
        add(new Item(2, "Item2", "Item2 is here"));
        add(new Item(3, "Item3", "Item3 is here"));
    }};


    @Override
    public Item addItem(Item item) {
        items.add(item);
        return item;
    }

    @Override
    public Optional<Item> getItemById(int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst();
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean deleteItemById(int id) {
        return items.removeIf(item -> item.getId() == id);
    }
}
