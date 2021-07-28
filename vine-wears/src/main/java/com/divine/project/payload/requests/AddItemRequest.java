package com.divine.project.payload.requests;

import com.divine.project.model.Category;
import com.divine.project.model.Color;
import com.divine.project.model.Size;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AddItemRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String price;

    private String description;

    @NotBlank
    private String quantityAvailable;

    @NotBlank
    private List<Category> categories;

    private List<Color> colors;

    private List<Size> sizes;
}
