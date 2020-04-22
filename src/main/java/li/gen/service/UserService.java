package li.gen.service;

import li.gen.entity.File;
import li.gen.entity.User;
import li.gen.security.LoginUserDetailService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends LoginUserDetailService {
    User findUserByName(String name);

    List<User> selectUserByName(String name);
    boolean deleteUser(String username);
    List<User> selectUserList();
    boolean updateUserByUserName(User user);
    boolean  addUser(User user);
    boolean registerUser(User user);

}