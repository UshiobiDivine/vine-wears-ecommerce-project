package com.divine.project.controller;

import com.divine.project.model.Item;
import com.divine.project.payload.requests.AddItemRequest;
import com.divine.project.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item addItem(@RequestBody AddItemRequest addItemRequest){
        return  itemService.addItem(addItemRequest);
    }
}
