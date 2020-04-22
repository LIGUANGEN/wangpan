package li.gen.controller;
import li.gen.entity.User;
import li.gen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @RequestMapping("login")
    public String userLogin(User user){

        return "admin";
    }
    @RequestMapping("userList")
    @ResponseBody
    public List<User> userList(){
        List<User>  userslist =  userService.selectUserList();
        System.out.println(userslist);

        return userslist;
    }
    @RequestMapping("edit")
    public ModelAndView edit(String username){
            ModelAndView mv = new ModelAndView();
            User user =  userService.findUserByName(username);
            System.out.println("找到了"+user);
            mv.addObject("user",user);
            mv.setViewName("edit");
            return mv;
    }
    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        String result="";
        if(userService.updateUserByUserName(user)){
            result="success";
        }
        return result;
    }
    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(@RequestBody User user){
        String result="";
        if(userService.findUserByName(user.getUsername())!=null){
            result="error";
        }else if(userService.addUser(user)){
            result="success";
        }
        return result;
    }
    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam String username){
        System.out.println(username);
        String result="";
        if(userService.deleteUser(username)){
            result="success";
        }
        return result;
    }
    @RequestMapping("findUser")
    @ResponseBody
    public List<User> findUser(@RequestParam String username){
        List<User> list = new ArrayList<User>();
       User user = userService.findUserByName(username);
       list.add(user);
        System.out.println("单个LIstua"+list);
       return list;
    }


}
