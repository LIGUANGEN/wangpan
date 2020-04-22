package li.gen.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 6975601077710753878L;

private String code;
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        code = request.getParameter("checkCode");
        System.out.println("验证码"+code);
    }


    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; code: ").append(this.getCode());
        return sb.toString();    }
}
