package com.divine.project.service;

import com.divine.project.model.Item;
import com.divine.project.payload.requests.AddItemRequest;
import com.divine.project.payload.requests.UpdateItemRequest;
import com.divine.project.payload.responses.PagedItemsResponse;
import org.springframework.data.domain.Page;

public interface ItemService {
    Item addItem(AddItemRequest addItemRequest);
    Item updateItem(Long id, UpdateItemRequest updateItemRequest);
    boolean deleteItem(Long id);
    Item getItem(Long id);

    PagedItemsResponse<Item> getAllItems(int page, int size);
}
