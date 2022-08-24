package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;

public class ViewHome {
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();

    public ViewHome() {
        switch (roleName) {
            case ADMIN:
                ViewAdminMenu();
                break;
            case USER:
                ViewUserMenu();
                break;
        }


    }
    public void ViewUserMenu() {
        System.out.println("WELLCOME USER:  " + currentUser.getName());
        System.out.println("1: Creat Category");
        System.out.println("2: Show List Category");
        System.out.println("3: Edit Category");
        System.out.println("0: Log Out");
        int chooseMenu = Integer.parseInt(Config.scanner().nextLine());
        switch (chooseMenu) {
            case 0:
                userController.logout();
                new ViewMenu().menu();
            case 1:
                new ViewCategory().formCreatCategory();
                break;
            case 2:
                new ViewCategory().showListCategory();
                break;
            case 3:
                new ViewCategory().formEditCategory();
        }
    }


    public void ViewAdminMenu() {
        System.out.println("WELLCOME ADMIN:  " + currentUser.getName());
        System.out.println("1: Show list of users");
        System.out.println("2: Delete users");
        System.out.println("3: Show list of Category");
        System.out.println("4: Delete Category");
        System.out.println("5: Edit Category");
        System.out.println("0: LogOut");
        int chooseAdminMenu = Integer.parseInt(Config.scanner().nextLine());
        switch (chooseAdminMenu) {
            case 1:
                new ViewMenu().showListUser();
                break;
            case 0:
                userController.logout();
                new ViewMenu().menu();
        }
    }
}
