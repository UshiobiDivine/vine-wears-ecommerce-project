package com.divine.project.util;
import com.divine.project.model.Category;
import com.divine.project.model.user.Role;
import com.divine.project.model.user.RoleEnum;
import com.divine.project.repository.CategoryRepository;
import com.divine.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AppInitializer implements ApplicationRunner {
    private final RoleRepository roleRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public AppInitializer(RoleRepository roleRepository, CategoryRepository categoryRepository) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        Role role;
        if (roleRepository.findByName(RoleEnum.ROLE_ADMIN).isEmpty()) {
            role = new Role();
            role.setName(RoleEnum.ROLE_ADMIN);
            roleRepository.save(role);
        }
        if (roleRepository.findByName(RoleEnum.ROLE_USER).isEmpty()) {
            role = new Role();
            role.setName(RoleEnum.ROLE_USER);
            roleRepository.save(role);
        }

        Category mens = new Category("Mens", "mens", "https://downloader.la/temp/60f483a01acb0.jpg",
                "shop/mens");

        Category womens = new Category("Womens", "womens", "https://i.ibb.co/GCCdy8t/womens.png",
                "shop/womens");

        Category jackets = new Category("Jackets", "jackets", "https://i.ibb.co/px2tCc3/jackets.png",
                "shop/jackets");

        Category hats = new Category("Hats", "hats", "https://i.ibb.co/cvpntL1/hats.png",
                "shop/hats");

        Category sneakers = new Category("Sneakers", "sneakers", "https://i.ibb.co/0jqHpnp/sneakers.png",
                "shop/sneakers");

        List<Category> categoryList = List.of(mens, womens, hats, jackets, sneakers);
        categoryList.stream().forEach(category -> {
            if (categoryRepository.findCategoryByTitle(category.getTitle())==null){
                categoryRepository.save(category);
            }
        });



    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

}