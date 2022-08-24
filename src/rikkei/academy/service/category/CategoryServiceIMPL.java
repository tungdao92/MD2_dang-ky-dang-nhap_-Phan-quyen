package rikkei.academy.service.category;

import rikkei.academy.config.Config;
import rikkei.academy.model.Category;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService, Serializable {
    IUserService userService = new UserServiceIMPL();
    public static String PATH_CATEGORY = "D:\\HTML, JAVA data\\MD2\\dang nhap\\src\\rikkei\\academy\\database\\category.txt";
    public static Config<List<Category>> categoryConfig = new Config<>();
    public static List<Category>  categoryList = categoryConfig.readFile(PATH_CATEGORY);
    static {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
    }

    @Override
    public List<Category> findAll() {
        categoryConfig.writeFile(PATH_CATEGORY, categoryList);

        return categoryList;
    }

    @Override
    public void save(Category category) {
        User user = userService.getCurrentUser();
        category.setUser(user);
        categoryList.add(category);


    }

    @Override
    public Category findByID(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()) {
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()) {
                categoryList.remove(i);
            }
        }
    }
}
