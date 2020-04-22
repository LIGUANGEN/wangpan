package li.gen.controller;

import li.gen.entity.User;
import li.gen.security.CustomWebAuthenticationDetails;
import li.gen.service.UserService;

import li.gen.util.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
   @Autowired
    UserService userService;
    @GetMapping(value = "list")
    public ModelAndView listController( Principal principal){
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

        //User user= userService.findUserByName(authentication.getName());
        List<User> list = userService.selectUserByName(principal.getName());
        System.out.printf(list.toString());
       // mv.addObject("user",user);
        mv.addObject("list",list);
        //mv.addObject("message","终于过来了");
        mv.addObject("message",principal.getName());
        mv.setViewName("success");
        return mv;
   }
//    @RequestMapping(value="login" ,method=RequestMethod.POST)
//    public void login(String username, String password, String checkCode, HttpServletRequest request, HttpServletResponse response){
//
//    }
    @RequestMapping("index")
    public String index(){

        return "index";
    }

    @RequestMapping(value="login" )
    public String login( HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
        request.getSession().setAttribute("username",principal.getName());


      return "index";


}
    @RequestMapping("register")
        @ResponseBody
        public String Register(@RequestBody User user){
            user.setAuthority("ROLE_USER");
            String result = "";
            if(userService.findUserByName(user.getUsername())!=null){
                result="error";
            }else {
                if(userService.registerUser(user)){
                    result="success";
                }else{
                    result="error2";
                }

            }
            return result;

    }

    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        user.setAuthority("ROLE_USER");
        String result="";
        if(userService.updateUserByUserName(user)){
            result="success";
        }
        return result;
    }

    @RequestMapping("edit")
    @ResponseBody
    public ModelAndView edit(String username){
        ModelAndView mv = new ModelAndView();
        User user =  userService.findUserByName(username);
        System.out.println("wo找到了"+username+user);
        mv.addObject("user",user);
        mv.setViewName("useredit");
        return mv;
    }






}
