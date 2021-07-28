package com.divine.project.service.serviceImpl;

import com.divine.project.exception.BadRequestException;
import com.divine.project.model.Category;
import com.divine.project.model.Color;
import com.divine.project.model.Item;
import com.divine.project.model.Size;
import com.divine.project.payload.requests.AddItemRequest;
import com.divine.project.payload.requests.UpdateItemRequest;
import com.divine.project.payload.responses.PagedItemsResponse;
import com.divine.project.repository.*;
import com.divine.project.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImplementation implements ItemService {

    private ItemRepository itemRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ColorRepository colorRepository;
    private SizeRepository sizeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository, UserRepository userRepository, CategoryRepository categoryRepository, ColorRepository colorRepository, SizeRepository sizeRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Item addItem(AddItemRequest addItemRequest) {

        Item item = handleSizeColorCategories(addItemRequest);

        Item savedItem = itemRepository.save(item);

        if (savedItem!=null){
            return item;
        }
        throw new BadRequestException("Could not add item");

    }

    @Override
    public Item updateItem(Long id, UpdateItemRequest updateItemRequest) {

        Optional<Item> itemOptional = itemRepository.findById(id);

        if (itemOptional.isPresent()){
            AddItemRequest addItemRequest = modelMapper.map(updateItemRequest, AddItemRequest.class);
            Item item = handleSizeColorCategories(addItemRequest);
            return item;
        }
        throw new BadRequestException("Could not find item");
    }

    @Override
    public boolean deleteItem(Long id) {
        return false;
    }

    @Override
    public Item getItem(Long id) {
        return null;
    }

    @Override
    public PagedItemsResponse<Item> getAllItems(int page, int size) {
        return null;
    }

    public Item handleSizeColorCategories(AddItemRequest addItemRequest){
        Item item = new Item();

        if (addItemRequest.getCategories()!=null){

            List<Category> categoryList = new ArrayList<>();

            addItemRequest.getCategories().stream().forEach((category)->
                    {
                        Category categoryByTitle = categoryRepository.findCategoryByTitle(category.getTitle());
                        if (categoryByTitle!=null){
                            categoryList.add(categoryByTitle);
                            categoryByTitle.getItems().add(item);
                            categoryRepository.save(categoryByTitle);
                        }else {
                            throw new BadRequestException("Could not find category");
                        }
                    }
            );
            item.setCategories(categoryList);
        }

        if (addItemRequest.getColors()!=null){
            List<Color> colorList = new ArrayList<>();
            addItemRequest.getColors().stream().forEach((color -> {
                if (colorRepository.findColorByName(color.getName()).isPresent()){
                    colorList.add(color);
                }else {
                    Color newColor = new Color(color.getName());
                    colorList.add(newColor);
                }
            }));
            item.setColors(colorList);
        }

        if (addItemRequest.getSizes()!=null){
            List<Size> sizeList = new ArrayList<>();
            addItemRequest.getSizes().stream().forEach((size -> {
                if (sizeRepository.findSizeByName(size.getName()).isPresent()){
                    sizeList.add(size);
                }else {
                    Size newSize = new Size(size.getName());
                    sizeList.add(newSize);
                }
            }));
            item.setSizes(sizeList);
        }

        item.setDescription(addItemRequest.getDescription());
        item.setName(addItemRequest.getName());
        item.setImageUrl(addItemRequest.getImageUrl());
        item.setPrice(addItemRequest.getPrice());
        item.setQuantityAvailable(addItemRequest.getQuantityAvailable());

        return item;
    }
}
