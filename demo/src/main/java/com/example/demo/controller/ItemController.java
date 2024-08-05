package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {


    public final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Add a new item
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item addedItem = itemService.addItem(item);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Optional<Item> item = itemService.getItemById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all data
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // Endpoint to delete an item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemById(@PathVariable int id) {
        boolean removed = itemService.deleteItemById(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
