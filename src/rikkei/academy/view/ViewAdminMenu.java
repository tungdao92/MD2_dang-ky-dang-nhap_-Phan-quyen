package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.service.role.RoleServiceIMPL;

public class ViewAdminMenu {
    public ViewAdminMenu() {
        UserController userController = new UserController();
        User user = userController.getCurrentUser();
        new RoleServiceIMPL().findAll();
        System.out.println(new RoleServiceIMPL().findAll());
        System.out.println("1: Show list of users");
        int chooseAdminMenu = Integer.parseInt(Config.scanner().nextLine());
        switch (chooseAdminMenu) {
            case 1:
                new ViewUser().showListUser();
        }
    }
}
