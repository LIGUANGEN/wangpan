package li.gen.dao;

import li.gen.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    User findUserByName(String username);
    List<User> selectUserByName(String username);
     int updateUserByUserName(@Param("user") User user);
    List<User> selectUserList();
    int addUser(@Param("user") User user);
    int deleteUser(String username);
    int registerUser(@Param("user") User user);


}
