package li.gen.controller;

import li.gen.util.CheckCodeUtil;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping("logout")
    public String logout(){
        return "123";
    }

    @RequestMapping(value="checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*设置返回头信息，内容类型为图片*/
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        CheckCodeUtil ccu = new CheckCodeUtil(85, 39, 4);
        ccu.drawImage();
        byte[] buffer = ccu.getImageBuffer();
        response.setContentLength(buffer.length);
        response.getOutputStream().write(buffer);
        response.getOutputStream().close();
        request.getSession().setAttribute("CODE",ccu.getCheckCode());
        System.out.println(ccu.getCheckCode());

    }
    @RequestMapping("me")
    public String  me(){
        return "success";
    }
}
