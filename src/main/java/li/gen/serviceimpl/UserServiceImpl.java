package li.gen.serviceimpl;


import li.gen.dao.UserDao;
import li.gen.entity.User;
import li.gen.service.UserService;
import li.gen.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User findUserByName(String name) {
        User user = userDao.findUserByName(name);
        return user;
    }

    @Override
    public List<User> selectUserByName(String name) {
        return userDao.selectUserByName(name);
    }

    @Override
    public boolean deleteUser(String username) {
        if(userDao.deleteUser(username)==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<User> selectUserList() {
        return userDao.selectUserList();
    }

    @Override
    public boolean updateUserByUserName( User user) {
        System.out.println(userDao.updateUserByUserName(user));
        if(userDao.updateUserByUserName(user)==1){
        return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean addUser(User user) {
        if(userDao.addUser(user)==1)
        {
          return true;
        }
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        if(userDao.registerUser(user)==1){
            return true;
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    private ArrayList<? extends GrantedAuthority> getAuthorities(String username){
        List<User> users = userDao.selectUserByName(username);
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for(User item: users){
            list.add(new SimpleGrantedAuthority(item.getAuthority()));
        }
        return  list;
    }

    @Override
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        System.out.println("前端"+username);
        System.out.println("username长度"+username.length());
        if(StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("用户名不能为空");

        }
        User user = userDao.findUserByName(username);
        System.out.println("数组"+user);
        if(user==null){
            throw new BadCredentialsException("用户名不存在");
        }
        if (StringUtils.isEmpty(password)){
            System.out.println("密码为空");
            throw new BadCredentialsException("密码不能为空");

        }
        if(!user.getPassword().equals(password)){
            System.out.println("前端" +password+"后台"+user.getPassword());
            throw new BadCredentialsException("密码错误");
        }

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),getAuthorities(username));
    }
}

