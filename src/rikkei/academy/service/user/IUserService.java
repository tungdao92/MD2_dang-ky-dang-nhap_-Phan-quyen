package rikkei.academy.service.user;

import rikkei.academy.model.User;
import rikkei.academy.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existsByUserName(String username);
    boolean existsByEmail(String email);
    boolean checkLogin(String username, String password);
    User findByUserName(String username);
    User getCurrentUser();

}


