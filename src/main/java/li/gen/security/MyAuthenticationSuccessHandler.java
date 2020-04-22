package li.gen.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        System.out.println(request.getContextPath());
        if (roles.contains("ROLE_ADMIN")){
            /*response.sendRedirect(basePath+"app/admin");
            return;*/
            //如果是要跳转到某个页面的，比如我们的那个whoim的则
            new DefaultRedirectStrategy().sendRedirect(request, response, "/admin/login");
            return;
        }
        /*response.sendRedirect(basePath+"app/welcome");*/
        new DefaultRedirectStrategy().sendRedirect(request, response,"/user/login" );

    }
}
