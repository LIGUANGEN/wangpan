package li.gen.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String MD5(String input) {
        String output= DigestUtils.md5DigestAsHex(input.getBytes());

             return output;
    }

    public static void main(String[] args) {
          String  s = MD5Util.MD5("123");
        System.out.printf(s);
    }
}
