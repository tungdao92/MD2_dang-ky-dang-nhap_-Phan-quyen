package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.Category;

import java.util.List;

public class ViewCategory {
    private CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showListCategory();
    public void formCreatCategory(){
        int id;
        if (categoryList.size() == 0) {
            id = 1;
        } else {
            id = categoryList.get(categoryList.size() - 1).getId() + 1;
        }
        System.out.println("Enter the name category:");
        String name = Config.scanner().nextLine();
        Category category = new Category(id, name);
        categoryController.creteCategory(category);
        System.out.println("List category: " + categoryList);
        System.out.println("Enter 'back' to back Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ViewHome();
        }
    }
    public void showListCategory(){
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println("======"+categoryList.get(i).getId() + "======" + categoryList.get(i).getName() + "======\n");
        }
        new ViewHome();
    }
    public void formEditCategory(){
        System.out.println("Enter ID to edit category");
        int idCategory = Integer.parseInt(Config.scanner().nextLine());
        if (categoryController.searchCategory(idCategory) == null) {
            System.out.println("ID does not exist");
        } else {
            Category category = categoryController.searchCategory(idCategory);
            System.out.println("OLD category: " + category.getName());
            System.out.println("Enter new Name category");
            String nameCategory = Config.scanner().nextLine();
            Category newCategory = new Category(nameCategory);
            categoryController.editCategory(idCategory,newCategory);
            System.out.println("Edit success!!");
            categoryController.showListCategory();
            System.out.println("Enter 'back' to back Menu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new ViewHome();
            }

        }
    }
}
