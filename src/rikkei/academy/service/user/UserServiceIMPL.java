package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService, Serializable {
    public static String PATH_USER = "D:\\HTML, JAVA data\\MD2\\dang nhap\\src\\rikkei\\academy\\database\\user.txt";
    public static String PATH_USER_PRINCIPAL = "D:\\HTML, JAVA data\\MD2\\dang nhap\\src\\rikkei\\academy\\database\\user_principal.txt";
    public static Config<List<User>> userConfig = new Config<>();

    public static List<User> userList = userConfig.readFile(PATH_USER);
    static{
        if (userList == null){
            userList = new ArrayList<>();
        }
    }

    @Override
    public List<User> findAll() {
        userConfig.writeFile(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        userConfig.writeFile(PATH_USER, userList);

    }

    @Override
    public User findByID(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
            }
        }
    }

    @Override
    public boolean existsByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (email.equals(userList.get(i).getEmail())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())) {
                return true;
            }

        }
        return false;
    }

    @Override
    public  User findByUserName(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public User getCurrentUser() {
       User user = new Config<User>().readFile(PATH_USER_PRINCIPAL);
       if (user == null) {
           return null;
       }
       return findByUserName(user.getUsername());
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().writeFile(PATH_USER_PRINCIPAL, user);

    }


}